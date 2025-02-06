package com.him.eurohim.data.module

import com.him.eurohim.domain.repository.QuotesRepository
import com.him.eurohim.domain.usecases.GetRealtimeQuotes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetRealtimeQuotesUseCase(repository: QuotesRepository): GetRealtimeQuotes {
        return GetRealtimeQuotes(repository)
    }
}