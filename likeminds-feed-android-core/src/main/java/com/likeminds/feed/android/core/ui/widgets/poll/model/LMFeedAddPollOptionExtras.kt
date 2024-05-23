package com.likeminds.feed.android.core.ui.widgets.poll.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class LMFeedAddPollOptionExtras private constructor(
    val postId: String,
    val pollId: String
) : Parcelable {

    class Builder {
        private var postId: String = ""
        private var pollId: String = ""

        fun postId(postId: String) = apply {
            this.postId = postId
        }

        fun pollId(pollId: String) = apply {
            this.pollId = pollId
        }

        fun build() = LMFeedAddPollOptionExtras(postId, pollId)
    }

    fun toBuilder(): Builder {
        return Builder().postId(postId)
            .pollId(pollId)
    }
}