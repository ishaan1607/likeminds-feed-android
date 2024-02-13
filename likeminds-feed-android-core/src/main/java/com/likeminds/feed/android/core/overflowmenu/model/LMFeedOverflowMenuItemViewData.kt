package com.likeminds.feed.android.core.overflowmenu.model

import com.likeminds.feed.android.core.util.base.LMFeedBaseViewType
import com.likeminds.feed.android.core.util.base.model.ITEM_OVERFLOW_MENU_ITEM

class LMFeedOverflowMenuItemViewData private constructor(
    @LMFeedOverflowMenuItemId
    val id: Int,
    val title: String
) : LMFeedBaseViewType {

    override val viewType: Int
        get() = ITEM_OVERFLOW_MENU_ITEM

    class Builder {
        @LMFeedOverflowMenuItemId
        private var id: Int = DELETE_POST_MENU_ITEM_ID
        private var title: String = ""

        fun id(@LMFeedOverflowMenuItemId id: Int) = apply { this.id = id }
        fun title(title: String) = apply { this.title = title }

        fun build() = LMFeedOverflowMenuItemViewData(
            id,
            title
        )
    }

    fun toBuilder(): Builder {
        return Builder().id(id).title(title)
    }
}