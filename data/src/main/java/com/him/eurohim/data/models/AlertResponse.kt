package com.him.eurohim.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlertResponse(
    @SerialName("alerts")
    val alerts: List<AlertDto>
)