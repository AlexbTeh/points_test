package com.him.eurohim.data.mapper

import com.him.eurohim.data.model.QuoteResponse
import com.him.eurohim.data.utils.Mapper
import com.him.eurohim.domain.models.Quote

class QuoteResponseMapper : Mapper<QuoteResponse, Quote> {
    override fun map(from: QuoteResponse): Quote {
       return Quote(
            ticker = from.ticker,
            exchange = from.exchange,
            name = from.name,
            lastTradePrice = from.lastTradePrice,
            percentChange = from.percentChange
        )
    }
}