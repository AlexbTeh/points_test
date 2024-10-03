package com.him.eurohim.data.module

import com.him.eurohim.data.repository.PointsRepositoryImpl
import com.him.eurohim.domain.repository.PointsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule{

    @Binds
    fun bindBitcoinRepository(bitcoinRepositoryImpl: PointsRepositoryImpl): PointsRepository

}