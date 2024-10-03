package com.him.eurohim.data.module

import com.him.eurohim.domain.repository.PointsRepository
import com.him.eurohim.domain.usecases.GetPointsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetPointsUseCase(repository: PointsRepository): GetPointsUseCase {
        return GetPointsUseCase(repository)
    }
}