package com.him.eurohim.data.mapper

import com.him.eurohim.data.model.QuoteResponse
import com.him.eurohim.data.utils.Mapper
import com.him.eurohim.domain.models.Quote

class QuoteResponseMapper : Mapper<List<QuoteResponse>, List<Quote>> {
    override fun map(from: List<QuoteResponse>): List<Quote> {
        return from.map {
            Quote(
                ticker = it.ticker,
                exchange = it.exchange,
                name = it.name,
                lastTradePrice = it.lastTradePrice,
                percentChange = it.percentChange,
                priceChange = it.priceChange
            )
        }
    }
}
