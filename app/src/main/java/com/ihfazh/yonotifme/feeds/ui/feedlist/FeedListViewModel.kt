package com.ihfazh.yonotifme.feeds.ui.feedlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.ihfazh.yonotifme.feeds.domain.models.Item
import com.ihfazh.yonotifme.feeds.usecases.ListFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeedListViewModel @Inject constructor(
    private val useCase: ListFeedUseCase
): ViewModel() {
    fun list(): LiveData<List<Item>> {
        return LiveDataReactiveStreams.fromPublisher(useCase.list())
    }
}