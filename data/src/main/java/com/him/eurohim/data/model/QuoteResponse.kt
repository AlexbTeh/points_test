package com.him.eurohim.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class QuoteResponse(
    @SerialName("c") val ticker: String,
    @SerialName("ltr") val exchange: String? = null,
    @SerialName("name") val name: String? = null,
    @SerialName("name2") val nameLatin: String? = null,
    @SerialName("bbp") val bestBidPrice: Double? = null,
    @SerialName("ltp") val lastTradePrice: Double? = null,
    @SerialName("lts") val lastTradeSize: Int? = null,
    @SerialName("ltt") val lastTradeTime: String? = null,
    @SerialName("chg") val priceChange: Double? = null,
    @SerialName("pcp") val percentChange: Double? = null,
    @SerialName("vol") val dailyVolume: Int? = null,
    @SerialName("vlt") val dailyVolumeInCurrency: Double? = null
)
