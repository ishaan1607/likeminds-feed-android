package com.likeminds.feed.android.core.post.create.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.likeminds.feed.android.core.universalfeed.model.LMFeedUserViewData
import com.likeminds.feed.android.core.utils.LMFeedViewDataConvertor
import com.likeminds.feed.android.core.utils.coroutine.launchIO
import com.likeminds.likemindsfeed.LMFeedClient
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class LMFeedCreatePostViewModel : ViewModel() {

    private val lmFeedClient = LMFeedClient.getInstance()

    private val _loggedInUser: MutableLiveData<LMFeedUserViewData> by lazy { MutableLiveData<LMFeedUserViewData>() }
    val loggedInUser: LiveData<LMFeedUserViewData> by lazy { _loggedInUser }

    sealed class ErrorMessageEvent {
        data class GetLoggedInUser(val errorMessage: String?) : ErrorMessageEvent()
    }

    private val errorEventChannel = Channel<ErrorMessageEvent>(Channel.BUFFERED)
    val errorEventFlow = errorEventChannel.receiveAsFlow()

    fun getLoggedInUser() {
        viewModelScope.launchIO {
            val response = lmFeedClient.getLoggedInUserWithRights()

            if (response.success) {
                //get user
                val loggedInUser = response.data?.user

                //convert it to [LMFeedUserViewData]
                val userViewData = LMFeedViewDataConvertor.convertUser(loggedInUser)

                //send to UI
                _loggedInUser.postValue(userViewData)
            } else {
                //send error message to UI
                errorEventChannel.send(ErrorMessageEvent.GetLoggedInUser(response.errorMessage))
            }
        }
    }
}