package com.likeminds.feed.android.core.topicselection.model

import com.likeminds.feed.android.core.utils.base.LMFeedBaseViewType
import com.likeminds.feed.android.core.utils.base.model.ITEM_ALL_TOPICS

class LMFeedAllTopicsViewData private constructor(
    val isSelected: Boolean
) : LMFeedBaseViewType {

    override val viewType: Int
        get() = ITEM_ALL_TOPICS

    class Builder {

        private var isSelected: Boolean = true

        fun isSelected(isSelected: Boolean) = apply { this.isSelected = isSelected }

        fun build() = LMFeedAllTopicsViewData(isSelected)
    }

    fun toBuilder(): Builder {
        return Builder().isSelected(isSelected)
    }
}