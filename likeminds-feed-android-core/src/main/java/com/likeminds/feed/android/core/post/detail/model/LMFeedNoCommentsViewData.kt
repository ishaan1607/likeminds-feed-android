package com.likeminds.feed.android.core.post.detail.model

import com.likeminds.feed.android.core.utils.base.LMFeedBaseViewType
import com.likeminds.feed.android.core.utils.base.model.ITEM_NO_COMMENTS_FOUND

class LMFeedNoCommentsViewData private constructor() : LMFeedBaseViewType {

    override val viewType: Int
        get() = ITEM_NO_COMMENTS_FOUND

    class Builder {
        fun build() = LMFeedNoCommentsViewData()
    }

    fun toBuilder(): Builder {
        return Builder()
    }
}