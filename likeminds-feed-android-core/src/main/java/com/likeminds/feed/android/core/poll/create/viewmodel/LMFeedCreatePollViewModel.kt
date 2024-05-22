package com.likeminds.feed.android.core.poll.create.viewmodel

import androidx.lifecycle.*
import com.likeminds.feed.android.core.utils.LMFeedViewDataConvertor
import com.likeminds.feed.android.core.utils.coroutine.launchIO
import com.likeminds.feed.android.core.utils.user.LMFeedUserViewData
import com.likeminds.likemindsfeed.LMFeedClient
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class LMFeedCreatePollViewModel : ViewModel() {

    val lmFeedClient = LMFeedClient.getInstance()

    private val _loggedInUser by lazy { MutableLiveData<LMFeedUserViewData>() }
    val loggedInUser: LiveData<LMFeedUserViewData> by lazy { _loggedInUser }

    private val errorEventChannel = Channel<ErrorEvent>(Channel.BUFFERED)
    val errorEvent = errorEventChannel.receiveAsFlow()

    sealed class ErrorEvent {
        data class GetLoggedInUserError(val message: String?) : ErrorEvent()
    }

    // Get the logged in user
    fun getLoggedInUser() {
        viewModelScope.launchIO {
            val response = lmFeedClient.getLoggedInUserWithRights()
            if (response.success) {
                // Get the logged in user
                val loggedInUser = response.data?.user

                // Convert the user to view data
                val userViewData = LMFeedViewDataConvertor.convertUser(loggedInUser)

                // Set the view data to send to the UI
                _loggedInUser.postValue(userViewData)
            } else {
                // Send the error event
                errorEventChannel.send(ErrorEvent.GetLoggedInUserError(response.errorMessage))
            }
        }
    }
}