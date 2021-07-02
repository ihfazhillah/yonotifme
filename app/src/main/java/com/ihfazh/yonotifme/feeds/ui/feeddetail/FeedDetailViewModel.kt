package com.ihfazh.yonotifme.feeds.ui.feeddetail

import androidx.lifecycle.*
import com.ihfazh.yonotifme.feeds.domain.models.Item
import com.ihfazh.yonotifme.feeds.usecases.DetailFeedUseCase
import com.ihfazh.yonotifme.feeds.usecases.ListFeedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FeedDetailViewModel @Inject constructor(
    private val useCase: DetailFeedUseCase
): ViewModel() {
    val feedId = MutableLiveData("")
    val feedDetail = Transformations.switchMap(feedId){
        LiveDataReactiveStreams.fromPublisher(useCase.detail(it))
    }
}