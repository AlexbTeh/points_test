package com.him.eurohim.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuoteResponse(
    @SerialName("c") val ticker: String,
    @SerialName("ltr") val exchange: String,
    @SerialName("name") val name: String,
    @SerialName("name2") val nameLatin: String,
    @SerialName("bbp") val bestBidPrice: Double,
    @SerialName("bbc") val bestBidChange: String,
    @SerialName("ltp") val lastTradePrice: Double,
    @SerialName("lts") val lastTradeSize: Int,
    @SerialName("ltt") val lastTradeTime: String,
    @SerialName("chg") val priceChange: Double,
    @SerialName("pcp") val percentChange: Double,
    @SerialName("vol") val dailyVolume: Int,
    @SerialName("vlt") val dailyVolumeInCurrency: Double,
    @SerialName("extra_field") val extraField: String? = null
)