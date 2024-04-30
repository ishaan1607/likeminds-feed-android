package com.likeminds.feed.android.core.ui.widgets.poll.style

import androidx.annotation.ColorRes
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.ui.theme.LMFeedTheme
import com.likeminds.feed.android.core.utils.LMFeedViewStyle

class LMFeedPostPollViewStyle private constructor(
    //poll question text style
    val pollQuestionTextStyle: LMFeedTextStyle,
    //poll option text style
    val pollOptionTextStyle: LMFeedTextStyle,
    //poll selected option color
    @ColorRes val pollSelectedOptionColor: Int,
    //poll other option color
    @ColorRes val pollOtherOptionColor: Int,
    //poll votes count text color
    val votesCountTextStyle: LMFeedTextStyle?,
    //members voted on poll count text style
    val membersVotedCountTextStyle: LMFeedTextStyle?,
    //poll info text style
    val pollInfoTextStyle: LMFeedTextStyle?,
    //submit poll button style
    val submitPollButtonStyle: LMFeedButtonStyle,
    //poll expiry text style
    val pollExpiryTextStyle: LMFeedTextStyle?,
    //add option on poll text style
    val addPollOptionTextStyle: LMFeedTextStyle,
    //poll option added by text style
    val pollOptionAddedByTextStyle: LMFeedTextStyle?,
    //edit poll icon style
    val editPollIconStyle: LMFeedIconStyle?,
    //clear poll icon style
    val clearPollIconStyle: LMFeedIconStyle?,
    //poll option check icon style
    val pollOptionCheckIconStyle: LMFeedIconStyle?
) : LMFeedViewStyle {

    class Builder {
        private var pollQuestionTextStyle: LMFeedTextStyle = LMFeedTextStyle.Builder()
            .textSize(R.dimen.lm_feed_text_large)
            .textColor(R.color.lm_feed_dark_grey)
            .fontAssetsPath("fonts/lm_feed_montserrat-medium.ttf")
            .build()

        private var pollOptionTextStyle: LMFeedTextStyle = LMFeedTextStyle.Builder()
            .textSize(R.dimen.lm_feed_text_large)
            .fontAssetsPath("fonts/lm_feed_montserrat-regular.ttf")
            .textColor(R.color.lm_feed_dark_grayish_blue)
            .build()

        @ColorRes
        private var pollSelectedOptionColor: Int = R.color.lm_feed_majorelle_blue_20

        @ColorRes
        private var pollOtherOptionColor: Int = R.color.lm_feed_light_grayish_blue

        private var votesCountTextStyle: LMFeedTextStyle? = null

        private var membersVotedCountTextStyle: LMFeedTextStyle? = null

        private var pollInfoTextStyle: LMFeedTextStyle? = null

        private var submitPollButtonStyle: LMFeedButtonStyle = LMFeedButtonStyle.Builder()
            .textStyle(
                LMFeedTextStyle.Builder()
                    .textColor(R.color.lm_feed_white)
                    .textSize(R.dimen.lm_feed_text_large)
                    .fontAssetsPath("fonts/lm_feed_montserrat-regular.ttf")
                    .build()
            )
            .backgroundColor(LMFeedTheme.getButtonColor())
            .cornerRadius(R.dimen.lm_feed_corner_radius_medium)
            .build()

        private var pollExpiryTextStyle: LMFeedTextStyle? = null

        private var addPollOptionTextStyle: LMFeedTextStyle = LMFeedTextStyle.Builder()
            .textSize(R.dimen.lm_feed_text_large)
            .fontAssetsPath("fonts/lm_feed_montserrat-regular.ttf")
            .textColor(R.color.lm_feed_dark_grayish_blue)
            .build()

        private var pollOptionAddedByTextStyle: LMFeedTextStyle? = null

        private var editPollIconStyle: LMFeedIconStyle? = null

        private var clearPollIconStyle: LMFeedIconStyle? = null

        private var pollOptionCheckIconStyle: LMFeedIconStyle? = null

        fun pollQuestionTextStyle(pollQuestionTextStyle: LMFeedTextStyle) = apply {
            this.pollQuestionTextStyle = pollQuestionTextStyle
        }

        fun pollOptionTextStyle(pollOptionTextStyle: LMFeedTextStyle) = apply {
            this.pollOptionTextStyle = pollOptionTextStyle
        }

        fun pollSelectedOptionColor(@ColorRes pollSelectedOptionColor: Int) = apply {
            this.pollSelectedOptionColor = pollSelectedOptionColor
        }

        fun pollOtherOptionColor(@ColorRes pollOtherOptionColor: Int) = apply {
            this.pollOtherOptionColor = pollOtherOptionColor
        }

        fun votesCountTextStyle(votesCountTextStyle: LMFeedTextStyle?) = apply {
            this.votesCountTextStyle = votesCountTextStyle
        }

        fun membersVotedCountTextStyle(membersVotedCountTextStyle: LMFeedTextStyle?) = apply {
            this.membersVotedCountTextStyle = membersVotedCountTextStyle
        }

        fun pollInfoTextStyle(pollInfoTextStyle: LMFeedTextStyle?) = apply {
            this.pollInfoTextStyle = pollInfoTextStyle
        }

        fun submitPollButtonStyle(submitPollButtonStyle: LMFeedButtonStyle) = apply {
            this.submitPollButtonStyle = submitPollButtonStyle
        }

        fun pollExpiryTextStyle(pollExpiryTextStyle: LMFeedTextStyle?) = apply {
            this.pollExpiryTextStyle = pollExpiryTextStyle
        }

        fun addPollOptionTextStyle(addPollOptionTextStyle: LMFeedTextStyle) = apply {
            this.addPollOptionTextStyle = addPollOptionTextStyle
        }

        fun pollOptionAddedByTextStyle(pollOptionAddedByTextStyle: LMFeedTextStyle?) = apply {
            this.pollOptionAddedByTextStyle = pollOptionAddedByTextStyle
        }

        fun editPollIconStyle(editPollIconStyle: LMFeedIconStyle?) = apply {
            this.editPollIconStyle = editPollIconStyle
        }

        fun clearPollIconStyle(clearPollIconStyle: LMFeedIconStyle?) = apply {
            this.clearPollIconStyle = clearPollIconStyle
        }

        fun pollOptionCheckIconStyle(pollOptionCheckIconStyle: LMFeedIconStyle?) = apply {
            this.pollOptionCheckIconStyle = pollOptionCheckIconStyle
        }

        fun build() = LMFeedPostPollViewStyle(
            pollQuestionTextStyle,
            pollOptionTextStyle,
            pollSelectedOptionColor,
            pollOtherOptionColor,
            votesCountTextStyle,
            membersVotedCountTextStyle,
            pollInfoTextStyle,
            submitPollButtonStyle,
            pollExpiryTextStyle,
            addPollOptionTextStyle,
            pollOptionAddedByTextStyle,
            editPollIconStyle,
            clearPollIconStyle,
            pollOptionCheckIconStyle
        )
    }

    fun toBuilder(): Builder {
        return Builder().pollQuestionTextStyle(pollQuestionTextStyle)
            .pollOptionTextStyle(pollOptionTextStyle)
            .pollSelectedOptionColor(pollSelectedOptionColor)
            .pollOtherOptionColor(pollOtherOptionColor)
            .votesCountTextStyle(votesCountTextStyle)
            .membersVotedCountTextStyle(membersVotedCountTextStyle)
            .pollInfoTextStyle(pollInfoTextStyle)
            .submitPollButtonStyle(submitPollButtonStyle)
            .pollExpiryTextStyle(pollExpiryTextStyle)
            .addPollOptionTextStyle(addPollOptionTextStyle)
            .pollOptionAddedByTextStyle(pollOptionAddedByTextStyle)
            .editPollIconStyle(editPollIconStyle)
            .clearPollIconStyle(clearPollIconStyle)
            .pollOptionCheckIconStyle(pollOptionCheckIconStyle)
    }
}