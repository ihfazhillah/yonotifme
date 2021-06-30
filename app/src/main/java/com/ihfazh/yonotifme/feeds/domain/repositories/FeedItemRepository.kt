package com.ihfazh.yonotifme.feeds.domain.repositories

import com.ihfazh.yonotifme.feeds.domain.models.Item
import io.reactivex.Completable

interface FeedItemRepository {
    fun insert(item: Item): Completable
}