package com.likeminds.feed.android.core.post.detail.model

import com.likeminds.feed.android.core.utils.base.LMFeedBaseViewType
import com.likeminds.feed.android.core.utils.base.model.ITEM_COMMENTS_COUNT

class LMFeedCommentsCountViewData private constructor(
    val commentsCount: Int
) : LMFeedBaseViewType {

    override val viewType: Int
        get() = ITEM_COMMENTS_COUNT

    class Builder {
        private var commentsCount: Int = 0

        fun commentsCount(commentsCount: Int) = apply { this.commentsCount = commentsCount }

        fun build() = LMFeedCommentsCountViewData(commentsCount)
    }

    fun toBuilder(): Builder {
        return Builder().commentsCount(commentsCount)
    }
}