package com.him.eurohim.data.repository

import com.him.eurohim.data.apiservice.WebSocketService
import com.him.eurohim.data.model.QuoteResponse
import com.him.eurohim.data.utils.Mapper
import com.him.eurohim.domain.models.Quote
import com.him.eurohim.domain.repository.QuotesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class QuotesRepositoryImpl @Inject constructor(
    private val webSocketService: WebSocketService,
    private val mapper: Mapper<List<QuoteResponse>, List<Quote>>
) : QuotesRepository {

    override fun getQuotes(): Flow<List<Quote>> = webSocketService.quotes
        .map(mapper::map)
        .distinctUntilChanged()
}

