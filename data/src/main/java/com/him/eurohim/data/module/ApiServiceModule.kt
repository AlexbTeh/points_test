package com.him.eurohim.data.module


import com.him.eurohim.data.apiservice.ApiService
import com.him.eurohim.di.qualifier.AppBaseUrl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {
    @Provides
    @Singleton
    fun provideApiService(@AppBaseUrl retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}