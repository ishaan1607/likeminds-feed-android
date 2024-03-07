package com.likeminds.feed.android.core.activityfeed.model

import com.likeminds.feed.android.core.universalfeed.model.LMFeedUserViewData
import com.likeminds.feed.android.core.utils.base.LMFeedBaseViewType
import com.likeminds.feed.android.core.utils.base.model.ITEM_ACTIVITY_FEED

class LMFeedActivityViewData private constructor(
    val id: String,
    val isRead: Boolean,
    val actionOn: String,
    val actionBy: List<String>,
    @LMFeedActivityEntityType
    val entityType: Int,
    val entityId: String,
    val entityOwnerId: String,
    val action: Int,
    val cta: String,
    val activityText: String,
    val activityEntityData: LMFeedActivityEntityViewData?,
    val activityByUser: LMFeedUserViewData,
    val createdAt: Long,
    val updatedAt: Long,
    val uuid: String
) : LMFeedBaseViewType {

    override val viewType: Int
        get() = ITEM_ACTIVITY_FEED

    class Builder {
        private var id: String = ""
        private var isRead: Boolean = false
        private var actionOn: String = ""
        private var actionBy: List<String> = listOf()

        @LMFeedActivityEntityType
        private var entityType: Int = POST
        private var entityId: String = ""
        private var entityOwnerId: String = ""
        private var action: Int = -1
        private var cta: String = ""
        private var activityText: String = ""
        private var activityEntityData: LMFeedActivityEntityViewData? = null
        private var activityByUser: LMFeedUserViewData = LMFeedUserViewData.Builder().build()
        private var createdAt: Long = 0
        private var updatedAt: Long = 0
        private var uuid: String = ""

        fun id(id: String) = apply { this.id = id }
        fun isRead(isRead: Boolean) = apply { this.isRead = isRead }
        fun actionOn(actionOn: String) = apply { this.actionOn = actionOn }
        fun actionBy(actionBy: List<String>) = apply { this.actionBy = actionBy }
        fun entityType(@LMFeedActivityEntityType entityType: Int) =
            apply { this.entityType = entityType }

        fun entityId(entityId: String) = apply { this.entityId = entityId }
        fun entityOwnerId(entityOwnerId: String) = apply { this.entityOwnerId = entityOwnerId }
        fun action(action: Int) = apply { this.action = action }
        fun cta(cta: String) = apply { this.cta = cta }
        fun activityText(activityText: String) =
            apply { this.activityText = activityText }

        fun activityEntityData(activityEntityData: LMFeedActivityEntityViewData?) =
            apply { this.activityEntityData = activityEntityData }

        fun activityByUser(activityByUser: LMFeedUserViewData) =
            apply { this.activityByUser = activityByUser }

        fun createdAt(createdAt: Long) = apply { this.createdAt = createdAt }
        fun updatedAt(updatedAt: Long) = apply { this.updatedAt = updatedAt }
        fun uuid(uuid: String) = apply { this.uuid = uuid }

        fun build() = LMFeedActivityViewData(
            id,
            isRead,
            actionOn,
            actionBy,
            entityType,
            entityId,
            entityOwnerId,
            action,
            cta,
            activityText,
            activityEntityData,
            activityByUser,
            createdAt,
            updatedAt,
            uuid
        )
    }

    fun toBuilder(): Builder {
        return Builder().id(id)
            .isRead(isRead)
            .actionOn(actionOn)
            .actionBy(actionBy)
            .entityType(entityType)
            .entityId(entityId)
            .entityOwnerId(entityOwnerId)
            .action(action)
            .cta(cta)
            .activityText(activityText)
            .activityEntityData(activityEntityData)
            .activityByUser(activityByUser)
            .createdAt(createdAt)
            .updatedAt(updatedAt)
            .uuid(uuid)
    }
}