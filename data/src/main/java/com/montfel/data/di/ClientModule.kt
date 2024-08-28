package com.montfel.data.di

import com.montfel.data.datasource.remote.FcmService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ClientModule {
    private const val RETROFIT_BASE_URL = "http://10.0.2.2:8080/"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(RETROFIT_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkHttpClient())
            .build()
    }

    @Provides
    @Singleton
    fun provideFcmService(retrofit: Retrofit): FcmService {
        return retrofit.create(FcmService::class.java)
    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient
            .Builder()
            .addNetworkInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            )
            .build()
    }
}