package com.likeminds.feed.android.core.ui.widgets.poll.style

import androidx.annotation.ColorRes
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.LMFeedIconStyle
import com.likeminds.feed.android.core.ui.base.styles.LMFeedTextStyle
import com.likeminds.feed.android.core.utils.LMFeedViewStyle

class LMFeedPostPollOptionViewStyle private constructor(
    //poll option text style
    val pollOptionTextStyle: LMFeedTextStyle,
    //poll selected option color
    @ColorRes val pollSelectedOptionColor: Int,
    //poll other option color
    @ColorRes val pollOtherOptionColor: Int,
    //poll option votes count text color
    val pollOptionVotesCountTextStyle: LMFeedTextStyle?,
    //poll option added by text style
    val pollOptionAddedByTextStyle: LMFeedTextStyle?,
    //poll option check icon style
    val pollOptionCheckIconStyle: LMFeedIconStyle?
) : LMFeedViewStyle {

    class Builder {
        private var pollOptionTextStyle: LMFeedTextStyle = LMFeedTextStyle.Builder()
            .textSize(R.dimen.lm_feed_text_large)
            .fontAssetsPath("fonts/lm_feed_montserrat-regular.ttf")
            .textColor(R.color.lm_feed_dark_grayish_blue)
            .build()

        @ColorRes
        private var pollSelectedOptionColor: Int = R.color.lm_feed_majorelle_blue_20

        @ColorRes
        private var pollOtherOptionColor: Int = R.color.lm_feed_light_grayish_blue

        private var pollOptionVotesCountTextStyle: LMFeedTextStyle? = null

        private var pollOptionAddedByTextStyle: LMFeedTextStyle? = null

        private var pollOptionCheckIconStyle: LMFeedIconStyle? = null

        fun pollOptionTextStyle(pollOptionTextStyle: LMFeedTextStyle) = apply {
            this.pollOptionTextStyle = pollOptionTextStyle
        }

        fun pollSelectedOptionColor(@ColorRes pollSelectedOptionColor: Int) = apply {
            this.pollSelectedOptionColor = pollSelectedOptionColor
        }

        fun pollOtherOptionColor(@ColorRes pollOtherOptionColor: Int) = apply {
            this.pollOtherOptionColor = pollOtherOptionColor
        }

        fun pollOptionVotesCountTextStyle(pollOptionVotesCountTextStyle: LMFeedTextStyle?) = apply {
            this.pollOptionVotesCountTextStyle = pollOptionVotesCountTextStyle
        }

        fun pollOptionAddedByTextStyle(pollOptionAddedByTextStyle: LMFeedTextStyle?) = apply {
            this.pollOptionAddedByTextStyle = pollOptionAddedByTextStyle
        }

        fun pollOptionCheckIconStyle(pollOptionCheckIconStyle: LMFeedIconStyle?) = apply {
            this.pollOptionCheckIconStyle = pollOptionCheckIconStyle
        }

        fun build() = LMFeedPostPollOptionViewStyle(
            pollOptionTextStyle,
            pollSelectedOptionColor,
            pollOtherOptionColor,
            pollOptionVotesCountTextStyle,
            pollOptionAddedByTextStyle,
            pollOptionCheckIconStyle
        )
    }

    fun toBuilder(): Builder {
        return Builder().pollOptionTextStyle(pollOptionTextStyle)
            .pollSelectedOptionColor(pollSelectedOptionColor)
            .pollOtherOptionColor(pollOtherOptionColor)
            .pollOptionVotesCountTextStyle(pollOptionVotesCountTextStyle)
            .pollOptionAddedByTextStyle(pollOptionAddedByTextStyle)
            .pollOptionCheckIconStyle(pollOptionCheckIconStyle)
    }
}