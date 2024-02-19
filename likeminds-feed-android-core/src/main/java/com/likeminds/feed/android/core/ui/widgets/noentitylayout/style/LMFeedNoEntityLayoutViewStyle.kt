package com.likeminds.feed.android.core.ui.widgets.noentitylayout.style

import androidx.annotation.ColorRes

import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.utils.LMFeedViewStyle

class LMFeedNoEntityLayoutViewStyle private constructor(
    val imageStyle: LMFeedImageStyle?,
    val titleStyle: LMFeedTextStyle,
    val subtitleStyle: LMFeedTextStyle?,
    val actionStyle: LMFeedFABStyle?,
    @ColorRes val backgroundColor: Int,
) : LMFeedViewStyle {

    class Builder {
        private var imageStyle: LMFeedImageStyle? = null
        private var titleStyle: LMFeedTextStyle = LMFeedTextStyle.Builder()
            .textColor(R.color.lm_feed_black)
            .textSize(R.dimen.lm_feed_text_extra_large)
            .fontResource(R.font.lm_feed_roboto_medium)
            .textAllCaps(false)
            .maxLines(1)
            .build()

        private var subtitleStyle: LMFeedTextStyle? = null
        private var actionStyle: LMFeedFABStyle? = null

        @ColorRes
        private var backgroundColor: Int = R.color.lm_feed_cultured

        fun imageStyle(imageStyle: LMFeedImageStyle?) = apply { this.imageStyle = imageStyle }
        fun titleStyle(titleStyle: LMFeedTextStyle) = apply { this.titleStyle = titleStyle }

        fun subtitleStyle(subtitleStyle: LMFeedTextStyle?) =
            apply { this.subtitleStyle = subtitleStyle }

        fun actionStyle(actionStyle: LMFeedFABStyle?) = apply { this.actionStyle = actionStyle }

        fun backgroundColor(@ColorRes backgroundColor: Int) =
            apply { this.backgroundColor = backgroundColor }

        fun build(): LMFeedNoEntityLayoutViewStyle {
            return LMFeedNoEntityLayoutViewStyle(
                imageStyle,
                titleStyle,
                subtitleStyle,
                actionStyle,
                backgroundColor
            )
        }
    }

    fun toBuilder(): Builder {
        return Builder().imageStyle(imageStyle)
            .titleStyle(titleStyle)
            .subtitleStyle(subtitleStyle)
            .actionStyle(actionStyle)
            .backgroundColor(backgroundColor)
    }
}