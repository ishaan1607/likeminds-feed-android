package com.likeminds.feed.android.core.ui.widgets.viewmore.style

import androidx.annotation.ColorRes
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.LMFeedTextStyle
import com.likeminds.feed.android.core.utils.LMFeedViewStyle

class LMFeedViewMoreViewStyle private constructor(
    val viewMoreTextStyle: LMFeedTextStyle,
    val visibleCountTextStyle: LMFeedTextStyle?,
    @ColorRes val backgroundColor: Int?
) : LMFeedViewStyle {

    class Builder {
        private var viewMoreTextStyle: LMFeedTextStyle = LMFeedTextStyle.Builder()
            .textColor(R.color.lm_feed_dark_grayish_blue)
            .textSize(R.dimen.lm_feed_text_medium)
            .fontResource(R.font.lm_feed_roboto_medium)
            .build()

        private var visibleCountTextStyle: LMFeedTextStyle? = null

        @ColorRes
        private var backgroundColor: Int? = null

        fun viewMoreTextStyle(viewMoreTextStyle: LMFeedTextStyle) = apply {
            this.viewMoreTextStyle = viewMoreTextStyle
        }

        fun visibleCountTextStyle(visibleCountTextStyle: LMFeedTextStyle?) = apply {
            this.visibleCountTextStyle = visibleCountTextStyle
        }

        fun backgroundColor(@ColorRes backgroundColor: Int?) = apply {
            this.backgroundColor = backgroundColor
        }

        fun build() = LMFeedViewMoreViewStyle(
            viewMoreTextStyle,
            visibleCountTextStyle,
            backgroundColor
        )
    }

    fun toBuilder(): Builder {
        return Builder().viewMoreTextStyle(viewMoreTextStyle)
            .visibleCountTextStyle(visibleCountTextStyle)
            .backgroundColor(backgroundColor)
    }
}