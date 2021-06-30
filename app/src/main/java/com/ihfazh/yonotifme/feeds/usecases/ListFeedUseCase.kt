package com.ihfazh.yonotifme.feeds.usecases

import com.ihfazh.yonotifme.feeds.domain.models.Item
import io.reactivex.Flowable

interface ListFeedUseCase {
    fun list(): Flowable<List<Item>>
}