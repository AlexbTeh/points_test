package com.him.eurohim.data.apiservice

import com.him.eurohim.data.model.QuoteResponse
import io.ktor.client.HttpClient
import io.ktor.client.plugins.websocket.webSocket
import io.ktor.client.request.header
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.websocket.Frame
import io.ktor.websocket.WebSocketSession
import io.ktor.websocket.readText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonObject

class WebSocketService(private val client: HttpClient) {
    private val _quotes = MutableStateFlow<QuoteResponse?>(null)
    val quotes = _quotes.asStateFlow()

    private var session: WebSocketSession? = null
    private val json = Json { ignoreUnknownKeys = true }

    init {
        connect()
    }

    private fun connect() {
        CoroutineScope(Dispatchers.IO).launch {
            while (isActive) {
                try {
                    client.webSocket({
                        url("wss://wss.tradernet.com")
                        header(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                    }) {
                        session = this
                        sendSubscriptionMessage()
                        handleIncomingMessages()
                    }
                } catch (e: Exception) {
                    println("WebSocket connection failed: ${e.localizedMessage}, retrying in 5 seconds...")
                    delay(5000)
                }
            }
        }
    }

    private suspend fun sendSubscriptionMessage() {
        val message = json.encodeToString(listOf("realtimeQuotes", listOf("AAPL", "GOOGL")))
        session?.send(Frame.Text(message))
    }

    private suspend fun handleIncomingMessages() {
        session?.incoming?.consumeEach { frame ->
            if (frame is Frame.Text) {
                val jsonElement = Json.parseToJsonElement(frame.readText()).jsonObject
                jsonElement["q"]?.let {
                    val data = json.decodeFromJsonElement<QuoteResponse>(it)
                    _quotes.value = data
                }
            }
        }
    }
}

