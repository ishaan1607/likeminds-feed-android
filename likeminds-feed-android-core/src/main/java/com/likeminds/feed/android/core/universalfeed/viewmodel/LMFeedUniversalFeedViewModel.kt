package com.likeminds.feed.android.core.universalfeed.viewmodel

import androidx.lifecycle.*
import com.likeminds.feed.android.core.universalfeed.model.LMFeedPostViewData
import com.likeminds.feed.android.core.utils.LMFeedViewDataConvertor
import com.likeminds.feed.android.core.utils.coroutine.launchIO
import com.likeminds.likemindsfeed.LMFeedClient
import com.likeminds.likemindsfeed.post.model.*
import com.likeminds.likemindsfeed.universalfeed.model.GetFeedRequest
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class LMFeedUniversalFeedViewModel : ViewModel() {

    private val lmFeedClient: LMFeedClient = LMFeedClient.getInstance()

    private val _universalFeedResponse = MutableLiveData<Pair<Int, List<LMFeedPostViewData>>>()
    val universalFeedResponse: LiveData<Pair<Int, List<LMFeedPostViewData>>> =
        _universalFeedResponse

    private val _postLikedResponse = MutableLiveData<Pair<String, Boolean>>()
    val postLikedResponse: LiveData<Pair<String, Boolean>> = _postLikedResponse

    private val _postSavedResponse = MutableLiveData<LMFeedPostViewData>()
    val postSavedResponse: LiveData<LMFeedPostViewData> = _postSavedResponse

    private val _postPinnedResponse = MutableLiveData<LMFeedPostViewData>()
    val postPinnedResponse: LiveData<LMFeedPostViewData> = _postPinnedResponse

    private val _deletePostResponse = MutableLiveData<String>()
    val deletePostResponse: LiveData<String> = _deletePostResponse

    private val errorMessageChannel = Channel<ErrorMessageEvent>(Channel.BUFFERED)
    val errorMessageEventFlow = errorMessageChannel.receiveAsFlow()

    sealed class ErrorMessageEvent {
        data class LikePost(val postId: String, val errorMessage: String?) : ErrorMessageEvent()

        data class SavePost(val postId: String, val errorMessage: String?) : ErrorMessageEvent()

        data class DeletePost(val errorMessage: String?) : ErrorMessageEvent()

        data class PinPost(val postId: String, val errorMessage: String?) : ErrorMessageEvent()
    }

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

    //for like/unlike a post
    fun likePost(postId: String, postLiked: Boolean) {
        viewModelScope.launchIO {
            val request = LikePostRequest.Builder()
                .postId(postId)
                .build()

            //call like post api
            val response = lmFeedClient.likePost(request)

            //check for error
            if (response.success) {
                _postLikedResponse.postValue(Pair(postId, postLiked))
            } else {
                errorMessageChannel.send(
                    ErrorMessageEvent.LikePost(
                        postId,
                        response.errorMessage
                    )
                )
            }
        }
    }

    //for save/unsave a post
    fun savePost(postViewData: LMFeedPostViewData) {
        viewModelScope.launchIO {
            val request = SavePostRequest.Builder()
                .postId(postViewData.id)
                .build()

            //call like post api
            val response = lmFeedClient.savePost(request)

            //check for error
            if (response.success) {
                _postSavedResponse.postValue(postViewData)
            } else {
                errorMessageChannel.send(
                    ErrorMessageEvent.SavePost(
                        postViewData.id,
                        response.errorMessage
                    )
                )
            }
        }
    }

    //for pin/unpin post
    fun pinPost(postViewData: LMFeedPostViewData) {
        viewModelScope.launchIO {
            val request = PinPostRequest.Builder()
                .postId(postViewData.id)
                .build()

            //call pin api
            val response = lmFeedClient.pinPost(request)

            if (response.success) {
                _postPinnedResponse.postValue(postViewData)
            } else {
                errorMessageChannel.send(
                    ErrorMessageEvent.PinPost(
                        postViewData.id,
                        response.errorMessage
                    )
                )
            }
        }
    }

    //for delete post
    fun deletePost(
        post: LMFeedPostViewData,
        reason: String? = null
    ) {
        viewModelScope.launchIO {
            val request = DeletePostRequest.Builder()
                .postId(post.id)
                .deleteReason(reason)
                .build()

            //call delete post api
            val response = lmFeedClient.deletePost(request)

            if (response.success) {
                //todo: event

                // sends post deleted event
//                sendPostDeletedEvent(post, reason)
                _deletePostResponse.postValue(post.id)
            } else {
                errorMessageChannel.send(ErrorMessageEvent.DeletePost(response.errorMessage))
            }
        }
    }
}