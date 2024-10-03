package com.him.eurohim.domain.repository

import com.him.eurohim.domain.models.Point

interface PointsRepository {
    suspend fun fetchPoints(count: Int): Result<List<Point>>
}