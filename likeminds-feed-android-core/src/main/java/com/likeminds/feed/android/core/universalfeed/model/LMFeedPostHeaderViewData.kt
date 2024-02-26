package com.likeminds.feed.android.core.universalfeed.model

// todo: add menu items here
class LMFeedPostHeaderViewData private constructor(
    val isEdited: Boolean,
    val isPinned: Boolean,
    val userId: String,
    val user: LMFeedUserViewData,
    val createdAt: Long
) {

    class Builder {
        private var isEdited: Boolean = false
        private var isPinned: Boolean = false
        private var userId: String = ""
        private var user: LMFeedUserViewData = LMFeedUserViewData.Builder().build()
        private var createdAt: Long = 0L

        fun isEdited(isEdited: Boolean) = apply { this.isEdited = isEdited }
        fun isPinned(isPinned: Boolean) = apply { this.isPinned = isPinned }
        fun userId(userId: String) = apply { this.userId = userId }
        fun user(user: LMFeedUserViewData) = apply { this.user = user }
        fun createdAt(createdAt: Long) = apply { this.createdAt = createdAt }

        fun build() = LMFeedPostHeaderViewData(
            isEdited,
            isPinned,
            userId,
            user,
            createdAt
        )
    }

    fun toBuilder(): Builder {
        return Builder()
            .isEdited(isEdited)
            .isPinned(isPinned)
            .userId(userId)
            .user(user)
            .createdAt(createdAt)
    }
}