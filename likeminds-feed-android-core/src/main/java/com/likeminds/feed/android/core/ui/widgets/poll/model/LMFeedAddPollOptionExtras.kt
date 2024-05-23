package com.likeminds.feed.android.core.ui.widgets.poll.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class LMFeedAddPollOptionExtras private constructor(
    val pollId: String
) : Parcelable {

    class Builder {
        private var pollId: String = ""

        fun pollId(pollId: String) = apply {
            this.pollId = pollId
        }

        fun build() = LMFeedAddPollOptionExtras(pollId)
    }

    fun toBuilder(): Builder {
        return Builder().pollId(pollId)
    }
}