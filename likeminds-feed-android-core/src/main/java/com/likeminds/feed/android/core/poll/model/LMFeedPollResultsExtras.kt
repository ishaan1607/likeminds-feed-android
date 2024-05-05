package com.likeminds.feed.android.core.poll.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class LMFeedPollResultsExtras private constructor(

) : Parcelable {

    class Builder {

        fun build() = LMFeedPollResultsExtras()
    }

    fun toBuilder(): Builder {
        return Builder()
    }
}