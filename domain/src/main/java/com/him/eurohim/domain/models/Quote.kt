package com.him.eurohim.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Quote(
    val ticker: String,
    val exchange: String? = null,
    val name: String? = null,
    val lastTradePrice: Double? = null,
    val percentChange: Double? = null,
    val priceChange: Double?
)
