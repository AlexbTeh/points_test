package com.him.eurohim.domain.repository

import com.him.eurohim.domain.models.Quote
import kotlinx.coroutines.flow.Flow

interface QuotesRepository {
    fun getQuotes(): Flow<Quote>
}