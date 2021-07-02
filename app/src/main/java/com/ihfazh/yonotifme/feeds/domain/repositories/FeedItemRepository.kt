package com.ihfazh.yonotifme.feeds.domain.repositories

import com.ihfazh.yonotifme.feeds.datasources.entities.FeedItemEntity
import com.ihfazh.yonotifme.feeds.domain.models.Item
import io.reactivex.Completable
import io.reactivex.Flowable

interface FeedItemRepository {
    fun insert(item: Item): Completable
    fun list(): Flowable<List<Item>>
    fun detail(id: String): Flowable<Item>
}