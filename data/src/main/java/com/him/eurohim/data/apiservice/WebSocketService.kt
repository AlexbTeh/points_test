package com.him.eurohim.data.apiservice

import com.him.eurohim.data.model.QuoteResponse
import com.him.eurohim.data.utils.Constants
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.client.request.header
import io.ktor.client.request.url
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.readText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonPrimitive
import javax.inject.Inject

class WebSocketService @Inject constructor(
    private val client: HttpClient,
    private val json: Json
) {
    private val _quotes = MutableStateFlow<List<QuoteResponse>>(emptyList()) // ✅ StateFlow для UI
    val quotes: StateFlow<List<QuoteResponse>> = _quotes.asStateFlow()

    private var session: WebSocketSession? = null

    init {
        connect()
    }

    private fun connect() {
        CoroutineScope(Dispatchers.IO).launch {
            while (isActive) {
                try {
                    client.webSocket({
                        url("wss://wss.tradernet.com")
                        header("Content-Type", "application/json")
                    }) {
                        session = this
                        sendSubscriptionMessage()
                        handleIncomingMessages()
                    }
                } catch (e: Exception) {
                    delay(5000)
                }
            }
        }
    }

    private suspend fun sendSubscriptionMessage() {
        if (session?.isActive == true) {
            val message = json.encodeToString(
                JsonArray(
                    listOf(
                        JsonPrimitive("realtimeQuotes"),
                        JsonArray(Constants.FIXED_STOCK_LIST.map { JsonPrimitive(it) })
                    )
                )
            )
            session?.send(Frame.Text(message))
        }
    }

    private suspend fun handleIncomingMessages() {
        session?.incoming?.consumeEach { frame ->
            if (frame is Frame.Text) {
                try {
                    val message = frame.readText()
                    val jsonElement = json.parseToJsonElement(message)

                    if (jsonElement is JsonArray && jsonElement.size == 2) {
                        val event = jsonElement[0].jsonPrimitive.content
                        val dataElement = jsonElement[1]

                        if (event == "q") {
                            val newData: List<QuoteResponse> = when (dataElement) {
                                is JsonObject -> listOf(json.decodeFromJsonElement(dataElement))
                                is JsonArray -> json.decodeFromJsonElement(dataElement)
                                else -> return@consumeEach
                            }

                            _quotes.update { oldList ->
                                val updatedList = oldList.map { oldQuote ->
                                    newData.find { it.ticker == oldQuote.ticker } ?: oldQuote
                                } + newData.filter { new -> oldList.none { it.ticker == new.ticker } }

                                updatedList.toList()
                            }

                        }
                    }
                } catch (_: Exception) { }
            }
        }
    }
}
