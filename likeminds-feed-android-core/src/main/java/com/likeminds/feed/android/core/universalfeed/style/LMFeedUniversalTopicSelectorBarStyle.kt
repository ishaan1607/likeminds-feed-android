package com.likeminds.feed.android.core.universalfeed.style

import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.LMFeedIconStyle
import com.likeminds.feed.android.core.ui.base.styles.LMFeedTextStyle
import com.likeminds.feed.android.core.utils.LMFeedViewStyle

class LMFeedUniversalTopicSelectorBarStyle private constructor(
    //all topics selector style
    val allTopicsSelectorStyle: LMFeedTextStyle,
    //clear topic filter view style
    val clearTopicFilterStyle: LMFeedTextStyle,
    //selected topic name text style
    val selectedTopicTextStyle: LMFeedTextStyle,
    //remove selected topic icon style
    val removeSelectedTopicIconStyle: LMFeedIconStyle,
    //background color of the selector bar
    @ColorRes val backgroundColor: Int?,
    //elevation of the bar
    @DimenRes val elevation: Int?,
) : LMFeedViewStyle {
    class Builder {
        private var allTopicsSelectorStyle: LMFeedTextStyle = LMFeedTextStyle.Builder()
            .drawableRightSrc(R.drawable.lm_feed_ic_arrow_down)
            .textColor(R.color.lm_feed_grey)
            .textSize(R.dimen.lm_feed_text_large)
            .fontResource(R.font.lm_feed_roboto)
            .build()

        private var clearTopicFilterStyle: LMFeedTextStyle = LMFeedTextStyle.Builder()
            .textColor(R.color.lm_feed_majorelle_blue)
            .textSize(R.dimen.lm_feed_text_large)
            .fontResource(R.font.lm_feed_roboto)
            .build()

        private var selectedTopicTextStyle: LMFeedTextStyle = LMFeedTextStyle.Builder()
            .textColor(R.color.lm_feed_majorelle_blue)
            .textSize(R.dimen.lm_feed_text_large)
            .build()

        private var removeSelectedTopicIconStyle: LMFeedIconStyle = LMFeedIconStyle.Builder()
            .iconTint(R.color.lm_feed_majorelle_blue)
            .inActiveSrc(R.drawable.lm_feed_ic_cross_topics)
            .build()

        @ColorRes
        private var backgroundColor: Int? = null

        @DimenRes
        private var elevation: Int? = null

        fun allTopicsSelectorStyle(allTopicsSelectorStyle: LMFeedTextStyle) = apply {
            this.allTopicsSelectorStyle = allTopicsSelectorStyle
        }

        fun clearTopicFilterStyle(clearTopicFilterStyle: LMFeedTextStyle) = apply {
            this.clearTopicFilterStyle = clearTopicFilterStyle
        }

        fun selectedTopicTextStyle(selectedTopicTextStyle: LMFeedTextStyle) = apply {
            this.selectedTopicTextStyle = selectedTopicTextStyle
        }

        fun removeSelectedTopicIconStyle(removeSelectedTopicIconStyle: LMFeedIconStyle) = apply {
            this.removeSelectedTopicIconStyle = removeSelectedTopicIconStyle
        }

        fun backgroundColor(@ColorRes backgroundColor: Int?) = apply {
            this.backgroundColor = backgroundColor
        }

        fun elevation(@DimenRes elevation: Int?) = apply {
            this.elevation = elevation
        }

        fun build() = LMFeedUniversalTopicSelectorBarStyle(
            allTopicsSelectorStyle,
            clearTopicFilterStyle,
            selectedTopicTextStyle,
            removeSelectedTopicIconStyle,
            backgroundColor,
            elevation
        )
    }

    fun toBuilder(): Builder {
        return Builder().allTopicsSelectorStyle(allTopicsSelectorStyle)
            .clearTopicFilterStyle(clearTopicFilterStyle)
            .selectedTopicTextStyle(selectedTopicTextStyle)
            .removeSelectedTopicIconStyle(removeSelectedTopicIconStyle)
            .backgroundColor(backgroundColor)
            .elevation(elevation)
    }
}