package com.likeminds.feed.android.core.ui.widgets.overflowmenu.style

import com.likeminds.feed.android.core.utils.LMFeedViewStyle

class LMFeedOverflowMenuStyle private constructor(
    val gravity: Int?
) : LMFeedViewStyle {

    class Builder {
        private var gravity: Int? = null

        fun gravity(gravity: Int?) = apply { this.gravity = gravity }

        fun build() = LMFeedOverflowMenuStyle(gravity)
    }

    fun toBuilder(): Builder {
        return Builder().gravity(gravity)
    }
}