package com.ihfazh.yonotifme.feeds.usecases

import com.ihfazh.yonotifme.feeds.domain.models.Item
import com.ihfazh.yonotifme.feeds.domain.repositories.FeedItemRepository
import io.reactivex.Flowable
import javax.inject.Inject

class DetailFeedUseCaseImpl @Inject constructor(var repository: FeedItemRepository): DetailFeedUseCase {
    override fun detail(id: String): Flowable<Item> {
        return repository.detail(id)
    }
}