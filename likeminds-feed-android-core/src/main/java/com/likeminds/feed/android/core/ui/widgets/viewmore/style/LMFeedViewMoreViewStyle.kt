package com.likeminds.feed.android.core.ui.widgets.viewmore.style

import androidx.annotation.*
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.LMFeedTextStyle
import com.likeminds.feed.android.core.utils.LMFeedViewStyle

/**
 * [LMFeedViewMoreStyle] helps you to customize the [LMFeedViewMoreView] with the following properties
 * @property viewMoreTextStyle : [LMFeedTextStyle] to customize the view more text in the view
 * @property visibleCountTextStyle: [LMFeedTextStyle] to customize the view that shows the visible count of the entity | set its value to [null] if you want to hide the visible count of the entity
 * @property backgroundColor: [Int] should be in format of [ColorRes] to customize the background color of the view | Default value = [null]
 **/
class LMFeedViewMoreViewStyle private constructor(
    //view more text style
    val viewMoreTextStyle: LMFeedTextStyle,
    //visible count text style
    val visibleCountTextStyle: LMFeedTextStyle?,
    //background color of view more view
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