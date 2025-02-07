package com.him.eurohim.data.apiservice

import com.him.eurohim.data.model.QuoteResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class QuotesApiService @Inject constructor(
    private val client: HttpClient
) {
    suspend fun getTopSecurities(): List<QuoteResponse> {
        return try {
            client.get("https://tradernet.com/tradernet-api/quotes-get-top-securities") {
                parameter("type", "stocks")
                parameter("exchange", "russia")
                parameter("gainers", 0)
                parameter("limit", 30)
            }.body()
        } catch (e: Exception) {
            emptyList()
        }
    }
}