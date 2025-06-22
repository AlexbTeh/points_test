package com.him.eurohim.domain.usecases

import com.him.eurohim.domain.models.WeatherAlert
import com.him.eurohim.domain.repository.WeatherRepository

class GetWeatherAlertsUseCase(private val repo: WeatherRepository) {
    suspend fun execute(): List<WeatherAlert> = repo.getAllAlerts()
}