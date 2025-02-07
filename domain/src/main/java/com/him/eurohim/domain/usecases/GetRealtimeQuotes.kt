package com.him.eurohim.domain.usecases

import com.him.eurohim.domain.models.Quote
import com.him.eurohim.domain.repository.QuotesRepository
import kotlinx.coroutines.flow.Flow

class GetRealtimeQuotes(private val repository: QuotesRepository) {
    operator fun invoke(): Flow<List<Quote>> = repository.getQuotes()
}