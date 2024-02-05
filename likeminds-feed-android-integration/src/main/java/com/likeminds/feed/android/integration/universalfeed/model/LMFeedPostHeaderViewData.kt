package com.likeminds.feed.android.integration.universalfeed.model

import com.likeminds.feed.android.integration.util.base.LMFeedBaseViewType

class LMFeedPostHeaderViewData private constructor(
    val isEdited: Boolean,
    val isPinned: Boolean,
    val userId: String,
    val user: LMFeedUserViewData
) : LMFeedBaseViewType {

    override val viewType: Int
        get() = TODO("Not yet implemented")

    class Builder {
        private var isEdited: Boolean = false
        private var isPinned: Boolean = false
        private var userId: String = ""
        private var user: LMFeedUserViewData = LMFeedUserViewData.Builder().build()

        fun isEdited(isEdited: Boolean) = apply { this.isEdited = isEdited }
        fun isPinned(isPinned: Boolean) = apply { this.isPinned = isPinned }
        fun userId(userId: String) = apply { this.userId = userId }
        fun user(user: LMFeedUserViewData) = apply { this.user = user }

        fun build() = LMFeedPostHeaderViewData(
            isEdited,
            isPinned,
            userId,
            user
        )
    }

    fun toBuilder(): Builder {
        return Builder()
            .isEdited(isEdited)
            .isPinned(isPinned)
            .userId(userId)
            .user(user)
    }
}