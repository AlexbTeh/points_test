package com.him.eurohim.data.repository

import com.him.eurohim.data.apiservice.ApiService
import com.him.eurohim.domain.models.Point
import com.him.eurohim.domain.repository.PointsRepository
import javax.inject.Inject

class PointsRepositoryImpl @Inject constructor(private val api: ApiService) : PointsRepository {
    override suspend fun fetchPoints(count: Int): Result<List<Point>> {
        return try {
            val response = api.getPoints(count)
            if (response.isSuccessful) {
                response.body()?.points?.let { Result.success(it) } ?: Result.failure(Exception("No data"))
            } else {
                Result.failure(Exception("Server error: ${response.code()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}