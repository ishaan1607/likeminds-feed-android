package com.likeminds.feed.android.core.universalfeed.model

import com.likeminds.feed.android.core.post.model.LMFeedAttachmentViewData
import com.likeminds.feed.android.core.utils.base.LMFeedBaseViewType
import com.likeminds.feed.android.core.utils.base.model.ITEM_POST_MEDIA

class LMFeedMediaViewData private constructor(
    val attachments: List<LMFeedAttachmentViewData>,
    val isExpanded: Boolean
) : LMFeedBaseViewType {

    override val viewType: Int
        get() = ITEM_POST_MEDIA

    class Builder {
        private var attachments: List<LMFeedAttachmentViewData> = listOf()
        private var isExpanded: Boolean = false

        fun attachments(attachments: List<LMFeedAttachmentViewData>) =
            apply { this.attachments = attachments }

        fun isExpanded(isExpanded: Boolean) = apply { this.isExpanded = isExpanded }

        fun build() = LMFeedMediaViewData(attachments, isExpanded)
    }

    fun toBuilder(): Builder {
        return Builder().attachments(attachments)
            .isExpanded(isExpanded)
    }
}