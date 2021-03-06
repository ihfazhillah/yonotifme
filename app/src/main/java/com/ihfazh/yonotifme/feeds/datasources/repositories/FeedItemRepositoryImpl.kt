package com.ihfazh.yonotifme.feeds.datasources.repositories

import com.ihfazh.yonotifme.feeds.DataMapper
import com.ihfazh.yonotifme.feeds.datasources.local.FeedDatabase
import com.ihfazh.yonotifme.feeds.domain.models.Item
import com.ihfazh.yonotifme.feeds.domain.repositories.FeedItemRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class FeedItemRepositoryImpl @Inject constructor(private var localDataSource: FeedDatabase): FeedItemRepository {
    override fun insert(item: Item): Completable {
        return localDataSource.feedItemDao().insert(
            DataMapper.feedItemMapper(item)
        )
    }

    override fun list(): Flowable<List<Item>> {
        return localDataSource.feedItemDao().list().map{
            it.map {
                DataMapper.feedEntityMapper(it)
            }
        }
    }

    override fun detail(id: String): Flowable<Item> {
        return localDataSource.feedItemDao().detail(id).map {
            DataMapper.feedEntityMapper(it)
        }
    }
}