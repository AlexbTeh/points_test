package com.him.eurohim.data.module

import com.him.eurohim.data.mapper.QuoteResponseMapper
import com.him.eurohim.data.model.QuoteResponse
import com.him.eurohim.data.utils.Mapper
import com.him.eurohim.domain.models.Quote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MapperModule {
    @Provides
    fun provideQuoteResponseMapper(): Mapper<@JvmSuppressWildcards
    List<QuoteResponse>,
            @JvmSuppressWildcards List<Quote>> {
        return QuoteResponseMapper()
    }
}