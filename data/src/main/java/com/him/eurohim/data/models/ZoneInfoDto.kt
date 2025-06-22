package com.him.eurohim.data.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ZoneInfoDto(
    @SerialName("name")
    val name: String
)