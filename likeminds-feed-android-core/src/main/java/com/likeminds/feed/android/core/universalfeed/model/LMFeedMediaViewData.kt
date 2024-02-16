package com.likeminds.feed.android.core.universalfeed.model

import com.likeminds.feed.android.core.post.model.*
import com.likeminds.feed.android.core.util.base.LMFeedBaseViewType
import com.likeminds.feed.android.core.util.base.model.*

class LMFeedMediaViewData private constructor(
    val attachments: List<LMFeedAttachmentViewData>,
) : LMFeedBaseViewType {

    override val viewType: Int
        get() = ITEM_POST_MEDIA

    class Builder {
        private var attachments: List<LMFeedAttachmentViewData> = listOf()

        fun attachments(attachments: List<LMFeedAttachmentViewData>) =
            apply { this.attachments = attachments }

        fun build() = LMFeedMediaViewData(attachments)
    }

    fun toBuilder(): Builder {
        return Builder().attachments(attachments)
    }
}