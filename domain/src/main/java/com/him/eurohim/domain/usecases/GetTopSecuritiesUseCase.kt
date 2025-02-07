package com.him.eurohim.domain.usecases

import com.him.eurohim.domain.models.Quote
import com.him.eurohim.domain.repository.QuotesRepository

class GetTopSecuritiesUseCase(private val repository: QuotesRepository) {
    suspend operator fun invoke(): List<Quote> = repository.getTopSecurities()
}