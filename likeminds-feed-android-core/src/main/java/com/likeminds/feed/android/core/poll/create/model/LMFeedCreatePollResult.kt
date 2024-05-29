package com.likeminds.feed.android.core.poll.create.model

import android.os.Parcelable
import com.likeminds.feed.android.core.poll.result.model.LMFeedPollViewData
import kotlinx.parcelize.Parcelize

@Parcelize
class LMFeedCreatePollResult private constructor(
    val pollViewData: LMFeedPollViewData
) : Parcelable {
    class Builder {
        private var pollViewData: LMFeedPollViewData = LMFeedPollViewData.Builder().build()

        fun pollViewData(pollViewData: LMFeedPollViewData) = apply {
            this.pollViewData = pollViewData
        }

        fun build() = LMFeedCreatePollResult(pollViewData)
    }

    fun toBuilder(): Builder {
        return Builder().pollViewData(pollViewData)
    }
}