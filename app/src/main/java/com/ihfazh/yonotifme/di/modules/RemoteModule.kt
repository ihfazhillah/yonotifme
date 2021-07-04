package com.ihfazh.yonotifme.di.modules

import com.ihfazh.yonotifme.feeds.datasources.remote.SembadaService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://sembada.ihfazh.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideSembadaService(retrofit: Retrofit): SembadaService {
        return retrofit.create(SembadaService::class.java)
    }
}