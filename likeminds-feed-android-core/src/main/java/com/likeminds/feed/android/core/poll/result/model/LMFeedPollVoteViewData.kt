package com.likeminds.feed.android.core.poll.result.model

import com.likeminds.feed.android.core.utils.user.LMFeedUserViewData

class LMFeedPollVoteViewData private constructor(
    val id: String,
    val usersVoted: List<LMFeedUserViewData>
) {

    class Builder {
        private var id: String = ""
        private var usersVoted: List<LMFeedUserViewData> = emptyList()

        fun id(id: String) = apply {
            this.id = id
        }

        fun usersVoted(usersVoted: List<LMFeedUserViewData>) = apply {
            this.usersVoted = usersVoted
        }

        fun build() = LMFeedPollVoteViewData(id, usersVoted)
    }

    fun toBuilder(): Builder {
        return Builder().id(id).usersVoted(usersVoted)
    }
}