package com.ihfazh.yonotifme.di.modules

import android.content.Context
import androidx.room.Room
import com.ihfazh.yonotifme.feeds.datasources.local.FeedDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context:Context): FeedDatabase {
        return Room.databaseBuilder(
            context,
            FeedDatabase::class.java,
            "yonotifme.db"
        ).build()
    }
}