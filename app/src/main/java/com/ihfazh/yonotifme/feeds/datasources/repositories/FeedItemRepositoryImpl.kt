package com.ihfazh.yonotifme.feeds.datasources.repositories

import com.ihfazh.yonotifme.feeds.DataMapper
import com.ihfazh.yonotifme.feeds.datasources.FeedDatabase
import com.ihfazh.yonotifme.feeds.domain.models.Item
import com.ihfazh.yonotifme.feeds.domain.repositories.FeedItemRepository
import io.reactivex.Completable
import javax.inject.Inject

class FeedItemRepositoryImpl(@Inject private val localDataSource: FeedDatabase): FeedItemRepository {
    override fun insert(item: Item): Completable {
        return localDataSource.feedItemDao().insert(
            DataMapper.feedItemMapper(item)
        )
    }
}