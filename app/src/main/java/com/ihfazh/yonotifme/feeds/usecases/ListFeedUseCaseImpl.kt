package com.ihfazh.yonotifme.feeds.usecases

import com.ihfazh.yonotifme.feeds.domain.models.Item
import com.ihfazh.yonotifme.feeds.domain.repositories.FeedItemRepository
import io.reactivex.Flowable
import javax.inject.Inject

class ListFeedUseCaseImpl @Inject constructor(var repository: FeedItemRepository): ListFeedUseCase {
    override fun list(): Flowable<List<Item>>{
        return repository.list()
    }
}