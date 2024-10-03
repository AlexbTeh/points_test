package com.him.eurohim.domain.usecases

import com.him.eurohim.domain.models.Point
import com.him.eurohim.domain.repository.PointsRepository

class GetPointsUseCase(private val repository: PointsRepository) {
    suspend operator fun invoke(count: Int): Result<List<Point>> {
        return repository.fetchPoints(count)
    }
}