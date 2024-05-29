package com.likeminds.feed.android.core.poll.create.style

import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.utils.LMFeedViewStyle
import com.likeminds.feed.android.core.utils.model.LMFeedPadding

/**
 * [LMFeedCreatePollOptionViewStyle] will help to customize the poll option while creating poll
 *
 * @property optionViewStyle [LMFeedEditTextStyle] to customize the option input view
 * @property removeOptionViewStyle [LMFeedIconStyle] to customize the remove option icon
 */
class LMFeedCreatePollOptionViewStyle private constructor(
    //option view style
    val optionViewStyle: LMFeedEditTextStyle,
    // remove option view style
    val removeOptionViewStyle: LMFeedIconStyle
) : LMFeedViewStyle {

    class Builder {
        private var optionViewStyle: LMFeedEditTextStyle = LMFeedEditTextStyle.Builder()
            .hintTextColor(R.color.lm_feed_black_20)
            .inputTextStyle(
                LMFeedTextStyle.Builder()
                    .textSize(R.dimen.lm_feed_text_large)
                    .textColor(R.color.lm_feed_black)
                    .fontResource(R.font.lm_feed_roboto)
                    .build()
            )
            .build()

        private var removeOptionViewStyle: LMFeedIconStyle = LMFeedIconStyle.Builder()
            .inActiveSrc(R.drawable.lm_feed_ic_remove_poll_option)
            .iconPadding(
                LMFeedPadding(
                    paddingLeft = R.dimen.lm_feed_regular_padding,
                    paddingTop = R.dimen.lm_feed_regular_padding,
                    paddingRight = R.dimen.lm_feed_regular_padding,
                    paddingBottom = R.dimen.lm_feed_regular_padding
                )
            )
            .build()

        fun optionViewStyle(optionViewStyle: LMFeedEditTextStyle) = apply {
            this.optionViewStyle = optionViewStyle
        }

        fun removeOptionViewStyle(removeOptionViewStyle: LMFeedIconStyle) = apply {
            this.removeOptionViewStyle = removeOptionViewStyle
        }

        fun build() = LMFeedCreatePollOptionViewStyle(optionViewStyle, removeOptionViewStyle)
    }

    fun toBuilder(): Builder {
        return Builder()
            .optionViewStyle(optionViewStyle)
            .removeOptionViewStyle(removeOptionViewStyle)
    }
}