package com.him.eurohim.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Quote(
    val ticker: String,
    val exchange: String,
    val name: String,
    val lastTradePrice: Double,
    val percentChange: Double
)
