package com.him.eurohim.data.repository

import com.him.eurohim.data.models.AlertResponse
import com.him.eurohim.data.models.ZoneInfoDto
import com.him.eurohim.data.models.toWeatherAlert
import com.him.eurohim.domain.models.WeatherAlert
import com.him.eurohim.domain.models.ZoneInfo
import com.him.eurohim.domain.repository.WeatherRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.sync.Semaphore
import kotlinx.coroutines.sync.withPermit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepositoryImpl @Inject constructor(
    private val client: HttpClient
) : WeatherRepository {

    private val alertUrl = "https://api.alltheapps.org/weather/v3/allActiveAlerts"
    private val zoneBaseUrl = "https://api.alltheapps.org/weather/v3/alertZone"

    private val semaphore = Semaphore(permits = 2)

    override suspend fun getAllAlerts(): List<WeatherAlert> = coroutineScope {
        val response = client.get(alertUrl).body<AlertResponse>()
        val alerts = response.alerts.take(5)

        alerts.map { dto ->
            val zones = dto.affectedZoneIDs.map { zoneId ->
                async {
                    semaphore.withPermit {
                        try {
                            val zone = getZoneInfo(zoneId.id, zoneId.type)
                            ZoneInfo(zoneId.id, zone.name)
                        } catch (e: Exception) {
                            ZoneInfo(zoneId.id, name = "Unknown")
                        }
                    }
                }
            }.awaitAll()
            dto.toWeatherAlert(zones)
        }
    }


    private suspend fun getZoneInfo(id: String, type: String): ZoneInfoDto {
        return client.get(zoneBaseUrl) {
            parameter("id", id)
            parameter("type", type)
            parameter("apiKey", "rt221019")
        }.body<ZoneInfoDto>()
    }
}