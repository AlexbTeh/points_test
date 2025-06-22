package com.him.eurohim.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ZoneIdDto(
    @SerialName("type")
    val type: String,

    @SerialName("id")
    val id: String
)