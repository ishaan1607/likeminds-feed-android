package com.likeminds.feed.android.core.likes.model

import com.likeminds.feed.android.core.universalfeed.model.LMFeedUserViewData
import com.likeminds.feed.android.core.utils.base.LMFeedBaseViewType
import com.likeminds.feed.android.core.utils.base.model.ITEM_LIKES_SCREEN

class LMFeedLikeViewData private constructor(
    val id: String,
    val userId: String,
    val createdAt: Long,
    val updatedAt: Long,
    val user: LMFeedUserViewData,
    val uuid: String
) : LMFeedBaseViewType {

    override val viewType: Int
        get() = ITEM_LIKES_SCREEN

    class Builder {
        private var id: String = ""
        private var userId: String = ""
        private var createdAt: Long = 0
        private var updatedAt: Long = 0
        private var user: LMFeedUserViewData = LMFeedUserViewData.Builder().build()
        private var uuid: String = ""

        fun id(id: String) = apply { this.id = id }
        fun userId(userId: String) = apply { this.userId = userId }
        fun createdAt(createdAt: Long) = apply { this.createdAt = createdAt }
        fun updatedAt(updatedAt: Long) = apply { this.updatedAt = updatedAt }
        fun user(user: LMFeedUserViewData) = apply { this.user = user }
        fun uuid(uuid: String) = apply { this.uuid = uuid }

        fun build() = LMFeedLikeViewData(
            id,
            userId,
            createdAt,
            updatedAt,
            user,
            uuid
        )
    }

    fun toBuilder(): Builder {
        return Builder().id(id)
            .userId(userId)
            .createdAt(createdAt)
            .updatedAt(updatedAt)
            .user(user)
            .uuid(uuid)
    }
}