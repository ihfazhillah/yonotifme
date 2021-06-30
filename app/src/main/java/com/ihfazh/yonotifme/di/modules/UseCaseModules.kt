package com.ihfazh.yonotifme.di.modules

import com.ihfazh.yonotifme.feeds.datasources.repositories.FeedItemRepositoryImpl
import com.ihfazh.yonotifme.feeds.domain.repositories.FeedItemRepository
import com.ihfazh.yonotifme.feeds.usecases.InsertFeedUseCase
import com.ihfazh.yonotifme.feeds.usecases.InsertFeedUseCaseImpl
import com.ihfazh.yonotifme.feeds.usecases.ListFeedUseCase
import com.ihfazh.yonotifme.feeds.usecases.ListFeedUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModules {
    @Provides
    fun provideInsertFeedUseCase(feedItemRepository: FeedItemRepositoryImpl): InsertFeedUseCase {
        return InsertFeedUseCaseImpl(feedItemRepository)
    }

    @Provides
    fun provideListFeedUseCase(feedItemRepository: FeedItemRepositoryImpl): ListFeedUseCase {
        return ListFeedUseCaseImpl(feedItemRepository)
    }
}