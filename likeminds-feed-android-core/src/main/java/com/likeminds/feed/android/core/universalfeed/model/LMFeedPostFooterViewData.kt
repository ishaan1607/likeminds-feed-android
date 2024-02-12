package com.likeminds.feed.android.core.universalfeed.model

import com.likeminds.feed.android.core.util.base.LMFeedBaseViewType

class LMFeedPostFooterViewData private constructor(
    val likesCount: Int,
    val commentsCount: Int,
    val isSaved: Boolean,
    val isLiked: Boolean
) : LMFeedBaseViewType {

    override val viewType: Int
        get() = TODO("Not yet implemented")

    class Builder {
        private var likesCount: Int = 0
        private var commentsCount: Int = 0
        private var isSaved: Boolean = false
        private var isLiked: Boolean = false

        fun likesCount(likesCount: Int) = apply { this.likesCount = likesCount }
        fun commentsCount(commentsCount: Int) = apply { this.commentsCount = commentsCount }
        fun isSaved(isSaved: Boolean) = apply { this.isSaved = isSaved }
        fun isLiked(isLiked: Boolean) = apply { this.isLiked = isLiked }

        fun build() = LMFeedPostFooterViewData(
            likesCount,
            commentsCount,
            isSaved,
            isLiked
        )
    }

    fun toBuilder(): Builder {
        return Builder()
            .likesCount(likesCount)
            .commentsCount(commentsCount)
            .isSaved(isSaved)
            .isLiked(isLiked)
    }
}