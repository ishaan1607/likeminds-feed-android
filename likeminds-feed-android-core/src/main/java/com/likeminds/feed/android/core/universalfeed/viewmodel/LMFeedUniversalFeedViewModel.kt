package com.likeminds.feed.android.core.universalfeed.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.*
import androidx.work.WorkContinuation
import androidx.work.WorkManager
import com.likeminds.feed.android.core.post.create.util.LMFeedPostAttachmentUploadWorker
import com.likeminds.feed.android.core.post.model.IMAGE
import com.likeminds.feed.android.core.post.model.VIDEO
import com.likeminds.feed.android.core.topics.model.LMFeedTopicViewData
import com.likeminds.feed.android.core.universalfeed.model.LMFeedPostViewData
import com.likeminds.feed.android.core.utils.LMFeedViewDataConvertor
import com.likeminds.feed.android.core.utils.analytics.LMFeedAnalytics
import com.likeminds.feed.android.core.utils.base.LMFeedBaseViewType
import com.likeminds.feed.android.core.utils.base.model.*
import com.likeminds.feed.android.core.utils.coroutine.launchIO
import com.likeminds.feed.android.core.utils.user.LMFeedMemberRightsUtil
import com.likeminds.feed.android.core.utils.user.LMFeedUserViewData
import com.likeminds.likemindsfeed.LMFeedClient
import com.likeminds.likemindsfeed.post.model.*
import com.likeminds.likemindsfeed.topic.model.GetTopicRequest
import com.likeminds.likemindsfeed.universalfeed.model.GetFeedRequest
import com.likeminds.usertagging.util.UserTaggingDecoder
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class LMFeedUniversalFeedViewModel : ViewModel() {

    private val lmFeedClient: LMFeedClient by lazy {
        LMFeedClient.getInstance()
    }

    private val _universalFeedResponse by lazy {
        MutableLiveData<Pair<Int, List<LMFeedPostViewData>>>()
    }

    val universalFeedResponse: LiveData<Pair<Int, List<LMFeedPostViewData>>> by lazy {
        _universalFeedResponse
    }


    private val _postSavedResponse by lazy {
        MutableLiveData<LMFeedPostViewData>()
    }

    val postSavedResponse: LiveData<LMFeedPostViewData> by lazy {
        _postSavedResponse
    }

    private val _postPinnedResponse by lazy {
        MutableLiveData<LMFeedPostViewData>()
    }

    val postPinnedResponse: LiveData<LMFeedPostViewData> by lazy {
        _postPinnedResponse
    }

    private val _deletePostResponse by lazy {
        MutableLiveData<String>()
    }

    val deletePostResponse: LiveData<String> by lazy {
        _deletePostResponse
    }

    private val _showTopicFilter by lazy {
        MutableLiveData<Boolean>()
    }

    val showTopicFilter: LiveData<Boolean> by lazy {
        _showTopicFilter
    }

    private val _hasCreatePostRights by lazy {
        MutableLiveData(true)
    }

    val hasCreatePostRights: LiveData<Boolean> by lazy {
        _hasCreatePostRights
    }

    private val _unreadNotificationCount by lazy {
        MutableLiveData<Int>()
    }

    val unreadNotificationCount: LiveData<Int> by lazy {
        _unreadNotificationCount
    }

    private val _userResponse by lazy {
        MutableLiveData<LMFeedUserViewData>()
    }

    val userResponse: LiveData<LMFeedUserViewData> by lazy {
        _userResponse
    }

    private val errorMessageChannel by lazy {
        Channel<ErrorMessageEvent>(Channel.BUFFERED)
    }

    val errorMessageEventFlow by lazy {
        errorMessageChannel.receiveAsFlow()
    }

    sealed class ErrorMessageEvent {
        data class UniversalFeed(val errorMessage: String?) : ErrorMessageEvent()

        data class LikePost(val postId: String, val errorMessage: String?) : ErrorMessageEvent()

        data class SavePost(val postId: String, val errorMessage: String?) : ErrorMessageEvent()

        data class DeletePost(val errorMessage: String?) : ErrorMessageEvent()

        data class PinPost(val postId: String, val errorMessage: String?) : ErrorMessageEvent()

        data class GetTopic(val errorMessage: String?) : ErrorMessageEvent()

        data class GetUnreadNotificationCount(val errorMessage: String?) : ErrorMessageEvent()

        data class AddPost(val errorMessage: String?) : ErrorMessageEvent()
    }

    sealed class PostDataEvent {
        data class PostDbData(val post: LMFeedPostViewData) : PostDataEvent()

        data class PostResponseData(val post: LMFeedPostViewData) : PostDataEvent()
    }

    private val postDataEventChannel by lazy { Channel<PostDataEvent>(Channel.BUFFERED) }
    val postDataEventFlow by lazy { postDataEventChannel.receiveAsFlow() }

    companion object {
        const val PAGE_SIZE = 20
    }

    // fetches pending post data from db
    fun fetchPendingPostFromDB() {
        viewModelScope.launchIO {
            val currentUploadingPost = lmFeedClient.getCurrentUploadingPost()
            val data = currentUploadingPost.data ?: return@launchIO
            val post = data.post
            val topics = data.topics

            postDataEventChannel.send(
                PostDataEvent.PostDbData(
                    LMFeedViewDataConvertor.convertPost(
                        post,
                        topics,
                        emptyMap()
                    )
                )
            )
        }
    }

    // calls AddPost API and posts the response in LiveData
    fun addPost(postingData: LMFeedPostViewData) {
        viewModelScope.launchIO {
            val text = postingData.contentViewData.text
            val updatedText =
                if (text.isNullOrEmpty()) {
                    null
                } else {
                    text
                }

            val topicIds = postingData.topicsViewData.map {
                it.id
            }

            val request = AddPostRequest.Builder()
                .text(updatedText)
                .attachments(
                    LMFeedViewDataConvertor.createAttachments(
                        postingData.mediaViewData.attachments
                    )
                )
                .tempId(postingData.mediaViewData.temporaryId.toString())
                .topicIds(topicIds)
                .build()

            val response = lmFeedClient.addPost(request)
            if (response.success) {
                val data = response.data ?: return@launchIO
                val usersMap = data.users
                val topicsMap = data.topics
                val widgetsMap = data.widgets

                val postViewData = LMFeedViewDataConvertor.convertPost(
                    data.post,
                    usersMap,
                    topicsMap,
                    widgetsMap
                )

                // sends post creation completed event
                sendPostCreationCompletedEvent(postViewData)

                postDataEventChannel.send(PostDataEvent.PostResponseData(postViewData))
            } else {
                errorMessageChannel.send(ErrorMessageEvent.AddPost(response.errorMessage))
            }
        }
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
                val widgetsMap = data.widgets

                //convert to view data
                val listOfPostViewData =
                    LMFeedViewDataConvertor.convertUniversalFeedPosts(
                        posts,
                        usersMap,
                        topicsMap,
                        widgetsMap
                    )

                //send it to ui
                _universalFeedResponse.postValue(Pair(page, listOfPostViewData))
            } else {
                //for error
                errorMessageChannel.send(ErrorMessageEvent.UniversalFeed(response.errorMessage))
            }
        }
    }

    //for like/unlike a post
    fun likePost(
        postId: String,
        postLiked: Boolean,
        loggedInUUID: String
    ) {
        viewModelScope.launchIO {
            val request = LikePostRequest.Builder()
                .postId(postId)
                .build()

            //call like post api
            val response = lmFeedClient.likePost(request)

            //check for error
            if (response.success) {
                //sends event for post liked
                LMFeedAnalytics.sendPostLikedEvent(
                    uuid = loggedInUUID,
                    postId = postId,
                    postLiked = postLiked
                )
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
                //sends event for post saved/unsaved
                LMFeedAnalytics.sendPostSavedEvent(
                    uuid = postViewData.headerViewData.user.sdkClientInfoViewData.uuid,
                    postId = postViewData.id,
                    postSaved = postViewData.footerViewData.isSaved
                )

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
                //sends event for pin/unpin post
                LMFeedAnalytics.sendPostPinnedEvent(postViewData)

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
    fun deletePost(post: LMFeedPostViewData, reason: String? = null) {
        viewModelScope.launchIO {
            val request = DeletePostRequest.Builder()
                .postId(post.id)
                .deleteReason(reason)
                .build()

            //call delete post api
            val response = lmFeedClient.deletePost(request)

            if (response.success) {
                // sends post deleted event
                LMFeedAnalytics.sendPostDeletedEvent(post, reason)

                _deletePostResponse.postValue(post.id)
            } else {
                errorMessageChannel.send(ErrorMessageEvent.DeletePost(response.errorMessage))
            }
        }
    }

    //calls to topics api and check whether to show topics view or not
    fun getAllTopics(showEnabledTopicsOnly: Boolean) {
        viewModelScope.launchIO {
            val requestBuilder = GetTopicRequest.Builder()
                .page(1)
                .pageSize(10)

            if (showEnabledTopicsOnly) {
                requestBuilder.isEnabled(true)
            }

            val request = requestBuilder.build()

            val response = lmFeedClient.getTopics(request)

            if (response.success) {
                val topics = response.data?.topics
                if (topics.isNullOrEmpty()) {
                    _showTopicFilter.postValue(false)
                } else {
                    _showTopicFilter.postValue(true)
                }
            } else {
                _showTopicFilter.postValue(false)
                errorMessageChannel.send(ErrorMessageEvent.GetTopic(response.errorMessage))
            }
        }
    }

    //call member state api
    fun getCreatePostRights() {
        viewModelScope.launchIO {
            //get member state response
            // fetches user with rights from DB with user.id
            val response = lmFeedClient.getLoggedInUserWithRights()

            val loggedInUserData = response.data ?: return@launchIO

            val memberState = loggedInUserData.user.state
            val memberRights = loggedInUserData.rights

            _hasCreatePostRights.postValue(
                LMFeedMemberRightsUtil.hasCreatePostsRight(
                    memberState,
                    memberRights
                )
            )
        }
    }

    //get unread notification count
    fun getUnreadNotificationCount() {
        viewModelScope.launchIO {
            //call unread notification count api
            val response = lmFeedClient.getUnreadNotificationCount()

            if (response.success) {
                val data = response.data ?: return@launchIO
                val count = data.count

                _unreadNotificationCount.postValue(count)
            } else {
                //for error
                errorMessageChannel.send(ErrorMessageEvent.GetUnreadNotificationCount(response.errorMessage))
            }
        }
    }

    //gets logged in user
    fun getLoggedInUser() {
        viewModelScope.launchIO {
            val response = lmFeedClient.getLoggedInUserWithRights()

            if (response.success) {
                val user = response.data?.user ?: return@launchIO

                val userViewData = LMFeedViewDataConvertor.convertUser(user)

                //post the user response in LiveData
                _userResponse.postValue(userViewData)
            }
        }
    }

    //get ids from topic selected adapter
    fun getTopicIdsFromAdapterList(items: List<LMFeedBaseViewType>): List<String> {
        return items.map {
            (it as LMFeedTopicViewData).id
        }
    }

    // starts a media upload worker to retry failed uploads
    fun createRetryPostMediaWorker(
        context: Context,
        postId: Long?,
        attachmentCount: Int,
    ) {
        viewModelScope.launchIO {
            if (postId == null || attachmentCount <= 0) {
                return@launchIO
            }
            val uploadData = startMediaUploadWorker(context, postId, attachmentCount)
            val request = UpdatePostWorkerUUIDRequest.Builder()
                .temporaryId(postId.toString())
                .workerUUID(uploadData.second)
                .build()
            lmFeedClient.updatePostWorkerUUID(request)
            uploadData.first.enqueue()
            fetchPendingPostFromDB()
        }
    }

    // creates PostAttachmentUploadWorker to start media upload
    @SuppressLint("EnqueueWork")
    private fun startMediaUploadWorker(
        context: Context,
        postId: Long,
        filesCount: Int
    ): Pair<WorkContinuation, String> {
        val oneTimeWorkRequest = LMFeedPostAttachmentUploadWorker.getInstance(postId, filesCount)
        val workContinuation = WorkManager.getInstance(context).beginWith(oneTimeWorkRequest)
        return Pair(workContinuation, oneTimeWorkRequest.id.toString())
    }

    /**
     * Triggers when the user opens post is created successfully
     **/
    private fun sendPostCreationCompletedEvent(
        post: LMFeedPostViewData
    ) {
        val map = hashMapOf<String, String>()
        // fetches list of tagged users
        val taggedUsers =
            UserTaggingDecoder.decodeAndReturnAllTaggedMembers(post.contentViewData.text)
        val topics = post.topicsViewData

        // adds tagged user count and their ids in the map
        if (taggedUsers.isNotEmpty()) {
            map["user_tagged"] = "yes"
            map["tagged_users_count"] = taggedUsers.size.toString()
            val taggedUserIds =
                taggedUsers.joinToString {
                    it.first
                }
            map["tagged_users_id"] = taggedUserIds
        } else {
            map["user_tagged"] = "no"
        }

        if (topics.isNotEmpty()) {
            val topicsNameString = topics.joinToString(", ") { it.name }
            map["topics_added"] = "yes"
            map["topics"] = topicsNameString
        } else {
            map["topics_added"] = "no"
        }

        // gets event property key and corresponding value for post attachments
        val attachmentInfo = getEventAttachmentInfo(post)
        attachmentInfo.forEach {
            map[it.first] = it.second
        }
        LMFeedAnalytics.track(
            LMFeedAnalytics.Events.POST_CREATION_COMPLETED,
            map
        )
    }

    /**
     * @param post - view data of post
     * @return - a list of pair of event key and value
     * */
    private fun getEventAttachmentInfo(post: LMFeedPostViewData): List<Pair<String, String>> {
        val attachments = post.mediaViewData.attachments

        return when (post.viewType) {
            ITEM_POST_SINGLE_IMAGE -> {
                listOf(
                    Pair("image_attached", "1"),
                    Pair("video_attached", "no"),
                    Pair("document_attached", "no"),
                    Pair("link_attached", "no")
                )
            }

            ITEM_POST_SINGLE_VIDEO -> {
                listOf(
                    Pair("video_attached", "1"),
                    Pair("image_attached", "no"),
                    Pair("document_attached", "no"),
                    Pair("link_attached", "no")
                )
            }

            ITEM_POST_DOCUMENTS -> {
                listOf(
                    Pair("video_attached", "no"),
                    Pair("image_attached", "no"),
                    Pair("document_attached", attachments.size.toString()),
                    Pair("link_attached", "no")
                )
            }

            ITEM_POST_MULTIPLE_MEDIA -> {
                val imageCount = attachments.count {
                    it.attachmentType == IMAGE
                }
                val imageCountString = if (imageCount == 0) {
                    "no"
                } else {
                    imageCount.toString()
                }
                val videoCount = attachments.count {
                    it.attachmentType == VIDEO
                }
                val videoCountString = if (videoCount == 0) {
                    "no"
                } else {
                    videoCount.toString()
                }
                listOf(
                    Pair(
                        "image_attached",
                        imageCountString
                    ),
                    Pair(
                        "video_attached",
                        videoCountString
                    ),
                    Pair("document_attached", "no"),
                    Pair("link_attached", "no")
                )
            }

            else -> {
                return emptyList()
            }
        }
    }
}