package com.likeminds.feed.android.core.post.detail.model

import com.likeminds.feed.android.core.utils.base.LMFeedBaseViewType
import com.likeminds.feed.android.core.utils.base.model.ITEM_VIEW_MORE_REPLY

class LMFeedViewMoreReplyViewData private constructor(
    val currentCount: Int,
    val totalCommentsCount: Int,
    val parentCommentId: String,
    val page: Int
) : LMFeedBaseViewType {

    override val viewType: Int
        get() = ITEM_VIEW_MORE_REPLY

    class Builder {
        private var currentCount: Int = 0
        private var totalCommentsCount: Int = 0
        private var parentCommentId: String = ""
        private var page: Int = 0

        fun currentCount(currentCount: Int) = apply { this.currentCount = currentCount }
        fun totalCommentsCount(totalCommentsCount: Int) =
            apply { this.totalCommentsCount = totalCommentsCount }

        fun parentCommentId(parentCommentId: String) =
            apply { this.parentCommentId = parentCommentId }

        fun page(page: Int) = apply { this.page = page }

        fun build() = LMFeedViewMoreReplyViewData(
            currentCount,
            totalCommentsCount,
            parentCommentId,
            page
        )
    }

    fun toBuilder(): Builder {
        return Builder().currentCount(currentCount)
            .totalCommentsCount(totalCommentsCount)
            .parentCommentId(parentCommentId)
            .page(page)
    }
}