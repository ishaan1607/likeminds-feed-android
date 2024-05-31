package com.likeminds.feed.android.core.poll.create.viewmodel

import androidx.lifecycle.*
import com.likeminds.feed.android.core.databinding.LmFeedItemCreatePollOptionBinding
import com.likeminds.feed.android.core.poll.create.model.LMFeedCreatePollOptionViewData
import com.likeminds.feed.android.core.poll.result.model.LMFeedPollOptionViewData
import com.likeminds.feed.android.core.poll.result.model.LMFeedPollViewData
import com.likeminds.feed.android.core.utils.LMFeedViewDataConvertor
import com.likeminds.feed.android.core.utils.coroutine.launchIO
import com.likeminds.feed.android.core.utils.user.LMFeedUserViewData
import com.likeminds.likemindsfeed.LMFeedClient
import com.likeminds.likemindsfeed.post.model.PollMultiSelectState
import com.likeminds.likemindsfeed.post.model.PollType
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import java.util.Date

class LMFeedCreatePollViewModel : ViewModel() {

    val lmFeedClient = LMFeedClient.getInstance()

    var pollExpiryTime: Long? = null
        private set

    private val pollOptionItemBindingMap by lazy {
        LinkedHashMap<Int, LmFeedItemCreatePollOptionBinding>()
    }

    private val _loggedInUser by lazy { MutableLiveData<LMFeedUserViewData>() }
    val loggedInUser: LiveData<LMFeedUserViewData> by lazy { _loggedInUser }

    private val _poll by lazy { MutableLiveData<LMFeedPollViewData>() }
    val poll: LiveData<LMFeedPollViewData> by lazy { _poll }

    private val errorEventChannel = Channel<ErrorEvent>(Channel.BUFFERED)
    val errorEvent = errorEventChannel.receiveAsFlow()

    sealed class ErrorEvent {
        data class GetLoggedInUserError(val message: String?) : ErrorEvent()
        data class CreatePollError(val message: String?) : ErrorEvent()
    }

    companion object {
        const val MULTIPLE_OPTION_STATE_MAX = "At max"
        const val MULTIPLE_OPTION_STATE_EXACTLY = "Exactly"
        const val MULTIPLE_OPTION_STATE_LEAST = "At least"
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

    // set the poll expiry time
    fun setPollExpiryTime(expiryTime: Long) {
        pollExpiryTime = expiryTime
    }

    //create the initial poll option list and clear the binding map
    fun createInitialPollOptionList(): MutableList<LMFeedCreatePollOptionViewData> {
        // Clear the map
        pollOptionItemBindingMap.clear()

        // create the list
        return mutableListOf(
            getEmptyPollOption(),
            getEmptyPollOption(),
        )
    }

    //create a empty poll option view data
    fun getEmptyPollOption(): LMFeedCreatePollOptionViewData {
        return LMFeedCreatePollOptionViewData.Builder().build()
    }

    //add the binding to the map
    fun addBindingToMap(position: Int, binding: LmFeedItemCreatePollOptionBinding) {
        pollOptionItemBindingMap[position] = binding
    }

    //remove the binding from the map
    fun removeBindingFromMap(position: Int) {
        pollOptionItemBindingMap.apply {
            if (containsKey(position)) {
                remove(position)
            }

            keys.filter {
                it > position
            }.forEach { position ->
                val oldItem = pollOptionItemBindingMap.remove(position)
                oldItem?.let { binding ->
                    pollOptionItemBindingMap[position - 1] = binding
                }
            }
        }
    }

    fun getPollOptionsFromBindingMap(): ArrayList<String> {
        val pollOptions = ArrayList<String>()

        //get poll options where option is entered
        pollOptionItemBindingMap.forEach { entry ->
            val binding = entry.value
            val pollOption = binding.etOption.text.toString()
            if (pollOption.isNotEmpty()) {
                pollOptions.add(pollOption)
            }
        }

        return pollOptions
    }

    //return multi state option list
    fun getMultipleOptionStateList(): ArrayList<String> {
        return arrayListOf(
            MULTIPLE_OPTION_STATE_MAX,
            MULTIPLE_OPTION_STATE_EXACTLY,
            MULTIPLE_OPTION_STATE_LEAST
        )
    }

    //return multi option list
    fun getMultipleOptionNoList(): ArrayList<String> {
        return arrayListOf(
            "1 option",
            "2 options",
            "3 options",
            "4 options",
            "5 options",
            "6 options",
            "7 options",
            "8 options",
            "9 options",
            "10 options"
        )
    }

    //create the poll
    fun createPoll(
        pollQuestion: String,
        pollMultiSelectState: PollMultiSelectState,
        pollType: PollType,
        pollMultiSelectNumber: Int,
        isPollAnonymous: Boolean,
        isPollAllowAddOption: Boolean,
        isAdvancedOptionsVisible: Boolean
    ) {
        viewModelScope.launchIO {
            val pollOptions = getPollOptionsFromBindingMap()

            if (pollQuestion.isEmpty()) {
                errorEventChannel.send(ErrorEvent.CreatePollError("Poll question cannot be empty"))
                return@launchIO
            }

            if (pollExpiryTime == null) {
                errorEventChannel.send(ErrorEvent.CreatePollError("Poll expiry time cannot be empty"))
                return@launchIO
            } else if (Date(pollExpiryTime ?: 0L).before(Date())) {
                errorEventChannel.send(ErrorEvent.CreatePollError("Poll expiry time cannot be in the past"))
                return@launchIO
            }

            val containsSimilarText = pollOptions.map {
                it.lowercase()
            }.groupBy {
                it
            }.values.firstOrNull {
                it.size > 1
            } != null

            if (containsSimilarText) {
                errorEventChannel.send(ErrorEvent.CreatePollError("Poll options cannot contain similar text"))
                return@launchIO
            }

            if (isAdvancedOptionsVisible) {
                when (pollMultiSelectState) {
                    PollMultiSelectState.EXACTLY, PollMultiSelectState.AT_LEAST -> {
                        if (pollOptions.size < pollMultiSelectNumber) {
                            errorEventChannel.send(ErrorEvent.CreatePollError("Poll options cannot be less than $pollMultiSelectNumber"))
                            return@launchIO
                        }
                    }

                    PollMultiSelectState.AT_MAX -> {
                        //do nothing
                    }
                }
            }

            val optionViewData = pollOptions.map { option ->
                LMFeedPollOptionViewData.Builder()
                    .text(option)
                    .build()
            }

            val pollViewData = LMFeedPollViewData.Builder()
                .title(pollQuestion)
                .options(optionViewData)
                .expiryTime(pollExpiryTime ?: 0L)
                .isAnonymous(isPollAnonymous)
                .allowAddOption(isPollAllowAddOption)
                .multipleSelectState(pollMultiSelectState)
                .multipleSelectNumber(pollMultiSelectNumber)
                .pollType(pollType)
                .build()

            _poll.postValue(pollViewData)
        }
    }

    //return [PollMultiSelectState] from string
    fun getPollMultiSelectStateValue(selectedValue: String): PollMultiSelectState {
        return when (selectedValue) {
            MULTIPLE_OPTION_STATE_MAX -> PollMultiSelectState.AT_MAX
            MULTIPLE_OPTION_STATE_LEAST -> PollMultiSelectState.AT_LEAST
            MULTIPLE_OPTION_STATE_EXACTLY -> PollMultiSelectState.EXACTLY
            else -> PollMultiSelectState.EXACTLY
        }
    }

    //return string from [PollMultiSelectState]
    fun getStringFromPollMultiSelectState(pollMultiSelectState: PollMultiSelectState): String {
        return when (pollMultiSelectState) {
            PollMultiSelectState.AT_MAX -> MULTIPLE_OPTION_STATE_MAX
            PollMultiSelectState.AT_LEAST -> MULTIPLE_OPTION_STATE_LEAST
            PollMultiSelectState.EXACTLY -> MULTIPLE_OPTION_STATE_EXACTLY
        }
    }
}