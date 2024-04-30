package com.likeminds.feed.android.core.ui.widgets.poll.style

import androidx.annotation.ColorRes
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.ui.theme.LMFeedTheme
import com.likeminds.feed.android.core.utils.LMFeedViewStyle

class LMFeedPostPollViewStyle private constructor(
    //poll question text style
    val pollTitleTextStyle: LMFeedTextStyle,
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
    val submitPollVoteButtonStyle: LMFeedButtonStyle,
    //poll expiry text style
    val pollExpiryTextStyle: LMFeedTextStyle?,
    //add option on poll text style
    val addPollOptionButtonStyle: LMFeedButtonStyle,
    //poll option added by text style
    val pollOptionAddedByTextStyle: LMFeedTextStyle?,
    //edit poll icon style
    val editPollIconStyle: LMFeedIconStyle?,
    //clear poll icon style
    val clearPollIconStyle: LMFeedIconStyle?,
    //poll option check icon style
    val pollOptionCheckIconStyle: LMFeedIconStyle?,
    //edit vote on poll text style
    val editPollVoteTextStyle: LMFeedTextStyle?
) : LMFeedViewStyle {

    class Builder {
        private var pollTitleTextStyle: LMFeedTextStyle = LMFeedTextStyle.Builder()
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

        private var submitPollVoteButtonStyle: LMFeedButtonStyle = LMFeedButtonStyle.Builder()
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

        private var addPollOptionButtonStyle: LMFeedButtonStyle = LMFeedButtonStyle.Builder()
            .textStyle(
                LMFeedTextStyle.Builder()
                    .textSize(R.dimen.lm_feed_text_large)
                    .fontAssetsPath("fonts/lm_feed_montserrat-regular.ttf")
                    .textColor(R.color.lm_feed_dark_grayish_blue)
                    .textAllCaps(false)
                    .build()
            )
            .cornerRadius(R.dimen.lm_feed_corner_radius_medium)
            .strokeColor(R.color.lm_feed_dusty_grey_20)
            .build()

        private var pollOptionAddedByTextStyle: LMFeedTextStyle? = null

        private var editPollIconStyle: LMFeedIconStyle? = null

        private var clearPollIconStyle: LMFeedIconStyle? = null

        private var pollOptionCheckIconStyle: LMFeedIconStyle? = null

        private var editPollVoteTextStyle: LMFeedTextStyle? = null

        fun pollTitleTextStyle(pollTitleTextStyle: LMFeedTextStyle) = apply {
            this.pollTitleTextStyle = pollTitleTextStyle
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

        fun submitPollVoteButtonStyle(submitPollVoteButtonStyle: LMFeedButtonStyle) = apply {
            this.submitPollVoteButtonStyle = submitPollVoteButtonStyle
        }

        fun pollExpiryTextStyle(pollExpiryTextStyle: LMFeedTextStyle?) = apply {
            this.pollExpiryTextStyle = pollExpiryTextStyle
        }

        fun addPollOptionButtonStyle(addPollOptionButtonStyle: LMFeedButtonStyle) = apply {
            this.addPollOptionButtonStyle = addPollOptionButtonStyle
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

        fun editPollVoteTextStyle(editPollVoteTextStyle: LMFeedTextStyle?) = apply {
            this.editPollVoteTextStyle = editPollVoteTextStyle
        }

        fun build() = LMFeedPostPollViewStyle(
            pollTitleTextStyle,
            pollOptionTextStyle,
            pollSelectedOptionColor,
            pollOtherOptionColor,
            votesCountTextStyle,
            membersVotedCountTextStyle,
            pollInfoTextStyle,
            submitPollVoteButtonStyle,
            pollExpiryTextStyle,
            addPollOptionButtonStyle,
            pollOptionAddedByTextStyle,
            editPollIconStyle,
            clearPollIconStyle,
            pollOptionCheckIconStyle,
            editPollVoteTextStyle
        )
    }

    fun toBuilder(): Builder {
        return Builder().pollTitleTextStyle(pollTitleTextStyle)
            .pollOptionTextStyle(pollOptionTextStyle)
            .pollSelectedOptionColor(pollSelectedOptionColor)
            .pollOtherOptionColor(pollOtherOptionColor)
            .votesCountTextStyle(votesCountTextStyle)
            .membersVotedCountTextStyle(membersVotedCountTextStyle)
            .pollInfoTextStyle(pollInfoTextStyle)
            .submitPollVoteButtonStyle(submitPollVoteButtonStyle)
            .pollExpiryTextStyle(pollExpiryTextStyle)
            .addPollOptionButtonStyle(addPollOptionButtonStyle)
            .pollOptionAddedByTextStyle(pollOptionAddedByTextStyle)
            .editPollIconStyle(editPollIconStyle)
            .clearPollIconStyle(clearPollIconStyle)
            .pollOptionCheckIconStyle(pollOptionCheckIconStyle)
            .editPollVoteTextStyle(editPollVoteTextStyle)
    }
}