package com.likeminds.feed.android.core.universalfeed.style

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
    val removeSelectedTopicIconStyle: LMFeedIconStyle
) : LMFeedViewStyle {
    class Builder {

        private var allTopicsSelectorStyle: LMFeedTextStyle = LMFeedTextStyle.Builder()
            .drawableRightSrc(R.drawable.lm_feed_ic_arrow_down)
            .textColor(R.color.lm_feed_grey)
            .textSize(R.dimen.lm_feed_text_medium)
            .build()

        private var clearTopicFilterStyle: LMFeedTextStyle = LMFeedTextStyle.Builder()
            .textColor(R.color.lm_feed_majorelle_blue)
            .textSize(R.dimen.lm_feed_text_medium)
            .build()

        private var selectedTopicTextStyle: LMFeedTextStyle = LMFeedTextStyle.Builder()
            .textColor(R.color.lm_feed_majorelle_blue)
            .textSize(R.dimen.lm_feed_text_medium)
            .build()

        private var removeSelectedTopicIconStyle: LMFeedIconStyle = LMFeedIconStyle.Builder()
            .iconTint(R.color.lm_feed_majorelle_blue)
            .inActiveSrc(R.drawable.lm_feed_ic_cross_topics)
            .build()

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

        fun build() = LMFeedUniversalTopicSelectorBarStyle(
            allTopicsSelectorStyle,
            clearTopicFilterStyle,
            selectedTopicTextStyle,
            removeSelectedTopicIconStyle
        )
    }

    fun toBuilder(): Builder {
        return Builder().allTopicsSelectorStyle(allTopicsSelectorStyle)
            .clearTopicFilterStyle(clearTopicFilterStyle)
            .selectedTopicTextStyle(selectedTopicTextStyle)
            .removeSelectedTopicIconStyle(removeSelectedTopicIconStyle)
    }
}