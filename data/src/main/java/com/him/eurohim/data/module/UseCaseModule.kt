package com.him.eurohim.data.module

import com.him.eurohim.domain.repository.WeatherRepository
import com.him.eurohim.domain.usecases.GetWeatherAlertsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetWeatherAlertsUseCase(repository: WeatherRepository): GetWeatherAlertsUseCase {
        return GetWeatherAlertsUseCase(repository)
    }
}
