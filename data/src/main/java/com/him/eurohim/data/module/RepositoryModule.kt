package com.him.eurohim.data.module

import com.him.eurohim.data.repository.QuotesRepositoryImpl
import com.him.eurohim.domain.repository.QuotesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    fun bindQuotesRepositoryImpl(quotesRepositoryImpl: QuotesRepositoryImpl): QuotesRepository
}
