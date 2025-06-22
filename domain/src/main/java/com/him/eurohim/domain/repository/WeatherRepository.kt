package com.him.eurohim.domain.repository

import com.him.eurohim.domain.models.WeatherAlert

interface WeatherRepository {
    suspend fun getAllAlerts(): List<WeatherAlert>
}