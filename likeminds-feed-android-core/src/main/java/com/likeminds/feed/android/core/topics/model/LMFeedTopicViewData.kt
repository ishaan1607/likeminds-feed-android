package com.likeminds.feed.android.core.topics.model

import com.likeminds.feed.android.core.utils.base.LMFeedBaseViewType
import com.likeminds.feed.android.core.utils.base.model.ITEM_TOPIC


class LMFeedTopicViewData private constructor(
    val id: String,
    val name: String,
    val isEnabled: Boolean,
    val isSelected: Boolean
) : LMFeedBaseViewType {
    override val viewType: Int
        get() = ITEM_TOPIC

    class Builder {
        private var id: String = ""
        private var name: String = ""
        private var isEnabled: Boolean = false
        private var isSelected: Boolean = false

        fun id(id: String) = apply { this.id = id }
        fun name(name: String) = apply { this.name = name }
        fun isEnabled(isEnabled: Boolean) = apply { this.isEnabled = isEnabled }
        fun isSelected(isSelected: Boolean) = apply { this.isSelected = isSelected }

        fun build() = LMFeedTopicViewData(id, name, isEnabled, isSelected)
    }

    fun toBuilder(): Builder {
        return Builder().id(id)
            .name(name)
            .isEnabled(isEnabled)
            .isSelected(isSelected)
    }
}