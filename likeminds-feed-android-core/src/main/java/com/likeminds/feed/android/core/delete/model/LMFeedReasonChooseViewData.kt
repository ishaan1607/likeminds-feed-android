package com.likeminds.feed.android.core.delete.model

import com.likeminds.feed.android.core.utils.base.LMFeedBaseViewType
import com.likeminds.feed.android.core.utils.base.model.ITEM_REASON_CHOOSE

class LMFeedReasonChooseViewData private constructor(
    val value: String,
    val hideBottomLine: Boolean?
) : LMFeedBaseViewType {

    override val viewType: Int
        get() = ITEM_REASON_CHOOSE

    class Builder {
        private var value: String = ""
        private var hideBottomLine: Boolean? = null

        fun value(value: String) = apply { this.value = value }
        fun hideBottomLine(hideBottomLine: Boolean?) =
            apply { this.hideBottomLine = hideBottomLine }

        fun build() = LMFeedReasonChooseViewData(
            value,
            hideBottomLine
        )
    }

    fun toBuilder(): Builder {
        return Builder().value(value)
            .hideBottomLine(hideBottomLine)
    }
}