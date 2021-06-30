package com.ihfazh.yonotifme.feeds.usecases

import com.ihfazh.yonotifme.feeds.domain.models.Item
import com.ihfazh.yonotifme.feeds.domain.repositories.FeedItemRepository
import io.reactivex.Completable
import javax.inject.Inject

class InsertFeedUseCaseImpl @Inject constructor(var repository: FeedItemRepository): InsertFeedUseCase {
    override fun insert(item: Item): Completable {
        return repository.insert(item)
    }
}