package com.likeminds.feed.android.core.poll.result.viewmodel

import androidx.lifecycle.*
import com.likeminds.feed.android.core.poll.result.model.LMFeedPollVoteViewData
import com.likeminds.feed.android.core.utils.LMFeedViewDataConvertor
import com.likeminds.feed.android.core.utils.coroutine.launchIO
import com.likeminds.likemindsfeed.LMFeedClient
import com.likeminds.likemindsfeed.poll.model.GetPollVotesRequest
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class LMFeedPollResultsViewModel : ViewModel() {

    private val lmFeedClient by lazy { LMFeedClient.getInstance() }

    sealed class ErrorMessageEvent {
        data class GetPollVotes(val errorMessage: String?) : ErrorMessageEvent()
    }

    private val errorEventChannel by lazy {
        Channel<ErrorMessageEvent>(Channel.BUFFERED)
    }
    val errorEventFlow by lazy { errorEventChannel.receiveAsFlow() }

    private val _pollVotesResponse by lazy { MutableLiveData<Pair<Int, LMFeedPollVoteViewData>>() }
    val pollVotesResponse by lazy { _pollVotesResponse }

    companion object {
        const val PAGE_SIZE = 20
    }

    fun getPollVotes(
        pollId: String,
        pollOptionId: String,
        page: Int
    ) {
        viewModelScope.launchIO {
            //get poll votes request
            val request = GetPollVotesRequest.Builder()
                .votes(listOf(pollOptionId))
                .pollId(pollId)
                .page(page)
                .pageSize(PAGE_SIZE)
                .build()

            val response = lmFeedClient.getPollVotes(request)

            if (response.success) {
                val data = response.data ?: return@launchIO
                _pollVotesResponse.postValue(
                    Pair(
                        page,
                        LMFeedViewDataConvertor.convertPollVotes(
                            data.votes,
                            data.users
                        )
                    )
                )
            } else {
                errorEventChannel.send(ErrorMessageEvent.GetPollVotes(response.errorMessage))
            }
        }
    }

}