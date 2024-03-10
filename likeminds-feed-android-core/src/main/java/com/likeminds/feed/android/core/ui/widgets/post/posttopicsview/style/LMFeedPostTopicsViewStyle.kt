package com.likeminds.feed.android.core.ui.widgets.post.posttopicsview.style

import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.LMFeedChipGroupStyle
import com.likeminds.feed.android.core.ui.base.styles.LMFeedChipStyle
import com.likeminds.feed.android.core.utils.LMFeedViewStyle

class LMFeedPostTopicsViewStyle private constructor(
    val topicGroupStyle: LMFeedChipGroupStyle,
    val topicChipStyle: LMFeedChipStyle
) : LMFeedViewStyle {

    class Builder {
        private var topicGroupStyle: LMFeedChipGroupStyle = LMFeedChipGroupStyle.Builder()
            .isSingleLine(false)
            .chipGroupHorizontalSpacing(R.dimen.lm_feed_default_chip_group_horizontal_spacing)
            .chipGroupVerticalSpacing(R.dimen.lm_feed_default_chip_group_vertical_spacing)
            .build()

        private var topicChipStyle = LMFeedChipStyle.Builder()
            .build()

        fun topicGroupStyle(topicGroupStyle: LMFeedChipGroupStyle) = apply {
            this.topicGroupStyle = topicGroupStyle
        }

        fun topicChipStyle(topicChipStyle: LMFeedChipStyle) = apply {
            this.topicChipStyle = topicChipStyle
        }

        fun build() = LMFeedPostTopicsViewStyle(
            topicGroupStyle,
            topicChipStyle
        )
    }

    fun toBuilder(): Builder {
        return Builder().topicGroupStyle(topicGroupStyle)
            .topicChipStyle(topicChipStyle)
    }
}