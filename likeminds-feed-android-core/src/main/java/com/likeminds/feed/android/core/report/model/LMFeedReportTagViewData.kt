package com.likeminds.feed.android.core.report.model

import com.likeminds.feed.android.core.utils.base.LMFeedBaseViewType
import com.likeminds.feed.android.core.utils.base.model.ITEM_REPORT_TAG

class LMFeedReportTagViewData private constructor(
    val id: Int,
    val name: String,
    val isSelected: Boolean
) : LMFeedBaseViewType {

    override val viewType: Int
        get() = ITEM_REPORT_TAG

    class Builder {
        private var id: Int = -1
        private var name: String = ""
        private var isSelected: Boolean = false

        fun id(id: Int) = apply { this.id = id }
        fun name(name: String) = apply { this.name = name }
        fun isSelected(isSelected: Boolean) = apply { this.isSelected = isSelected }

        fun build() = LMFeedReportTagViewData(
            id,
            name,
            isSelected
        )
    }

    fun toBuilder(): Builder {
        return Builder().id(id)
            .name(name)
            .isSelected(isSelected)
    }
}