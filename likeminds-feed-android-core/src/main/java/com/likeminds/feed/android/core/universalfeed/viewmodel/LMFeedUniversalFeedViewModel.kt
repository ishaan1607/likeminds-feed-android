package com.likeminds.feed.android.core.universalfeed.viewmodel

import com.likeminds.feed.android.core.utils.base.BaseViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class LMFeedUniversalFeedViewModel : BaseViewModel() {

    private val errorMessageChannel = Channel<ErrorMessageEvent>(Channel.BUFFERED)
    val errorMessageEventFlow = errorMessageChannel.receiveAsFlow()

    sealed class ErrorMessageEvent {
        data class UniversalFeed(val errorMessage: String?) : ErrorMessageEvent()
        data class AddPost(val errorMessage: String?) : ErrorMessageEvent()
        data class GetUnreadNotificationCount(val errorMessage: String?) : ErrorMessageEvent()
    }
}