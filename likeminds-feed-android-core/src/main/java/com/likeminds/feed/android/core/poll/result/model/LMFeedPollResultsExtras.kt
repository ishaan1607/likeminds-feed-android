package com.likeminds.feed.android.core.poll.result.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class LMFeedPollResultsExtras private constructor(
    val pollId: String,
    val pollOptions: List<LMFeedPollOptionViewData>,
    val selectedPollOptionId: String?,
) : Parcelable {

    class Builder {
        private var pollId: String = ""
        private var pollOptions: List<LMFeedPollOptionViewData> = emptyList()
        private var selectedPollOptionId: String? = null

        fun pollId(pollId: String) = apply {
            this.pollId = pollId
        }

        fun pollOptions(pollOptions: List<LMFeedPollOptionViewData>) = apply {
            this.pollOptions = pollOptions
        }

        fun selectedPollOptionId(selectedPollOptionId: String?) = apply {
            this.selectedPollOptionId = selectedPollOptionId
        }

        fun build() = LMFeedPollResultsExtras(
            pollId,
            pollOptions,
            selectedPollOptionId
        )
    }

    fun toBuilder(): Builder {
        return Builder().pollId(pollId)
            .pollOptions(pollOptions)
            .selectedPollOptionId(selectedPollOptionId)
    }
}