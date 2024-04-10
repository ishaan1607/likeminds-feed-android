package com.likeminds.feed.android.core.universalfeed.model

import com.likeminds.feed.android.core.post.model.LMFeedAttachmentViewData
import com.likeminds.feed.android.core.utils.base.LMFeedBaseViewType
import com.likeminds.feed.android.core.utils.base.model.ITEM_POST_MEDIA

class LMFeedMediaViewData private constructor(
    val attachments: List<LMFeedAttachmentViewData>,
    val isExpanded: Boolean,
    val temporaryId: Long?,
    val workerUUID: String,
    val thumbnail: String?
) : LMFeedBaseViewType {

    override val viewType: Int
        get() = ITEM_POST_MEDIA

    class Builder {
        private var attachments: List<LMFeedAttachmentViewData> = listOf()
        private var isExpanded: Boolean = false
        private var temporaryId: Long? = null
        private var thumbnail: String? = null
        private var workerUUID: String = ""

        fun attachments(attachments: List<LMFeedAttachmentViewData>) =
            apply { this.attachments = attachments }

        fun isExpanded(isExpanded: Boolean) = apply { this.isExpanded = isExpanded }
        fun temporaryId(temporaryId: Long?) = apply { this.temporaryId = temporaryId }
        fun workerUUID(workerUUID: String) = apply { this.workerUUID = workerUUID }
        fun thumbnail(thumbnail: String?) = apply { this.thumbnail = thumbnail }

        fun build() = LMFeedMediaViewData(
            attachments,
            isExpanded,
            temporaryId,
            workerUUID,
            thumbnail
        )
    }

    fun toBuilder(): Builder {
        return Builder().attachments(attachments)
            .isExpanded(isExpanded)
            .temporaryId(temporaryId)
            .workerUUID(workerUUID)
            .thumbnail(thumbnail)
    }
}