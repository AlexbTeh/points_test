package com.him.eurohim.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherAlert(
    val id: String,
    val title: String,
    val startDate: Long,
    val endDate: Long,
    val severity: String,
    val certainty: String,
    val urgency: String,
    val source: String,
    val description: String,
    val affectedZones: List<ZoneInfo>
) : Parcelable
