package com.ihfazh.yonotifme.feeds.usecases

import com.ihfazh.yonotifme.feeds.domain.models.Item
import io.reactivex.Completable

interface InsertFeedUseCase {
    fun insert(item: Item): Completable
}