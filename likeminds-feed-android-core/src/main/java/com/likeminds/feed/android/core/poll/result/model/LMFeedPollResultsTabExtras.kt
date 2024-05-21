package com.likeminds.feed.android.core.poll.result.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class LMFeedPollResultsTabExtras private constructor(
    val pollId: String,
    val pollOptionId: String
) : Parcelable {

    class Builder {
        private var pollId: String = ""
        private var pollOptionId: String = ""

        fun pollId(pollId: String) = apply {
            this.pollId = pollId
        }

        fun pollOptionId(pollOptionId: String) = apply {
            this.pollOptionId = pollOptionId
        }

        fun build() = LMFeedPollResultsTabExtras(pollId, pollOptionId)
    }

    fun toBuilder(): Builder {
        return Builder().pollId(pollId)
            .pollOptionId(pollOptionId)
    }
}