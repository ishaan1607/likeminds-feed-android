package com.likeminds.feed.android.core.universalfeed.viewmodel

import androidx.lifecycle.*
import com.likeminds.feed.android.core.universalfeed.model.LMFeedPostViewData
import com.likeminds.feed.android.core.utils.LMFeedViewDataConvertor
import com.likeminds.feed.android.core.utils.base.BaseViewModel
import com.likeminds.feed.android.core.utils.coroutine.launchIO
import com.likeminds.likemindsfeed.universalfeed.model.GetFeedRequest

class LMFeedUniversalFeedViewModel : BaseViewModel() {

    private val _universalFeedResponse = MutableLiveData<Pair<Int, List<LMFeedPostViewData>>>()
    val universalFeedResponse: LiveData<Pair<Int, List<LMFeedPostViewData>>> =
        _universalFeedResponse

    companion object {
        const val PAGE_SIZE = 20
    }

    fun getFeed(page: Int, topicsIds: List<String>? = null) {
        viewModelScope.launchIO {
            val request = GetFeedRequest.Builder()
                .page(page)
                .pageSize(PAGE_SIZE)
                .topicIds(topicsIds)
                .build()

            //call universal feed api
            val response = lmFeedClient.getFeed(request)

            if (response.success) {
                val data = response.data ?: return@launchIO
                val posts = data.posts
                val usersMap = data.users
                val topicsMap = data.topics

                //convert to view data
                val listOfPostViewData =
                    LMFeedViewDataConvertor.convertUniversalFeedPosts(posts, usersMap, topicsMap)

                //send it to ui
                _universalFeedResponse.postValue(Pair(page, listOfPostViewData))
            } else {
                //for error
//                errorMessageChannel.send(ErrorMessageEvent.UniversalFeed(response.errorMessage))
            }
        }
    }
}