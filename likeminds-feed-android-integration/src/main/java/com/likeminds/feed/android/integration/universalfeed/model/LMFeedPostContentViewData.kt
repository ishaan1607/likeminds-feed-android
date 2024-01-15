package com.likeminds.feed.android.integration.universalfeed.model

import com.likeminds.feed.android.integration.util.base.LMFeedBaseViewType

class LMFeedPostContentViewData private constructor(
    val text: String?,
    val alreadySeenFullContent: Boolean?,
    val isExpanded: Boolean,
) : LMFeedBaseViewType {

    override val viewType: Int
        get() = TODO("Not yet implemented")

    class Builder {
        private var text: String? = null
        private var alreadySeenFullContent: Boolean? = null
        private var isExpanded: Boolean = false

        fun text(text: String?) = apply { this.text = text }
        fun alreadySeenFullContent(alreadySeenFullContent: Boolean?) =
            apply { this.alreadySeenFullContent = alreadySeenFullContent }

        fun isExpanded(isExpanded: Boolean) = apply { this.isExpanded = isExpanded }

        fun build() = LMFeedPostContentViewData(
            text,
            alreadySeenFullContent,
            isExpanded
        )
    }

    fun toBuilder(): Builder {
        return Builder().text(text)
            .alreadySeenFullContent(alreadySeenFullContent)
            .isExpanded(isExpanded)
    }
}