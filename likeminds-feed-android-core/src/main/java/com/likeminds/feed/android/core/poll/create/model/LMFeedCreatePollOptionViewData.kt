package com.likeminds.feed.android.core.poll.create.model

import android.os.Parcelable
import com.likeminds.feed.android.core.utils.base.LMFeedBaseViewType
import com.likeminds.feed.android.core.utils.base.model.ITEM_CREATE_POLL_OPTION
import kotlinx.parcelize.Parcelize

@Parcelize
class LMFeedCreatePollOptionViewData private constructor(
    val text: String
) : LMFeedBaseViewType, Parcelable {
    override val viewType: Int
        get() = ITEM_CREATE_POLL_OPTION

    class Builder {
        private var text: String = ""

        fun text(text: String) = apply { this.text = text }

        fun build() = LMFeedCreatePollOptionViewData(text)
    }

    fun toBuilder(): Builder {
        return Builder()
            .text(text)
    }
}