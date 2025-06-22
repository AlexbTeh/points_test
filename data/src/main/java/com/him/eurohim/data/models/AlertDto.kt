package com.him.eurohim.data.models

import com.him.eurohim.domain.models.WeatherAlert
import com.him.eurohim.domain.models.ZoneInfo
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AlertDto(
    @SerialName("id")
    val id: String,

    @SerialName("areaDescription")
    val areaDescription: String,

    @SerialName("dateOnset")
    val dateOnset: Long,

    @SerialName("dateEnds")
    val dateEnds: Long? = null,

    @SerialName("severity")
    val severity: String,

    @SerialName("certainty")
    val certainty: String,

    @SerialName("urgency")
    val urgency: String,

    @SerialName("senderName")
    val senderName: String,

    @SerialName("description")
    val description: String,

    @SerialName("event")
    val event: String,

    @SerialName("affectedZoneIDs")
    val affectedZoneIDs: List<ZoneIdDto>
)


fun AlertDto.toWeatherAlert(zones: List<ZoneInfo>) = WeatherAlert(
    id = id,
    title = event,
    startDate = dateOnset,
    endDate = dateEnds ?: dateOnset,
    severity = severity,
    certainty = certainty,
    urgency = urgency,
    source = senderName,
    description = description,
    affectedZones = zones
)