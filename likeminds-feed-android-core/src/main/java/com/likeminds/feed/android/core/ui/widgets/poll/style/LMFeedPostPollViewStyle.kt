package com.likeminds.feed.android.core.ui.widgets.poll.style

import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.ui.theme.LMFeedTheme
import com.likeminds.feed.android.core.utils.LMFeedViewStyle

/**
 * [LMFeedPostPollViewStyle] helps you to customize the post poll view [LMFeedPostPollView]
 *
 * @property pollTitleTextStyle : [LMFeedTextStyle] this will help you to customize the title text of the post poll
 * @property pollOptionsViewStyle : [LMFeedPostPollOptionViewStyle] this will help you to customize the options of the post poll
 * @property membersVotedCountTextStyle: [LMFeedTextStyle] this will help you to customize the members voted count text on the post poll
 * @property pollInfoTextStyle: [LMFeedTextStyle] this will help you to customize the info text on the post poll
 * @property submitPollVoteButtonStyle: [LMFeedButtonStyle] this will help you to customize the submit vote button on the post poll
 * @property pollExpiryTextStyle: [LMFeedTextStyle] this will help you to customize the expiry time text on the post poll
 * @property addPollOptionButtonStyle: [LMFeedButtonStyle] this will help you to customize the add option button on the post poll
 * @property editPollIconStyle: [LMFeedIconStyle] this will help you to customize the edit icon on the post poll
 * @property clearPollIconStyle: [LMFeedIconStyle] this will help you to customize the clear icon on the post poll
 * @property editPollVoteTextStyle: [LMFeedTextStyle] this will help you to customize the edit vote icon on the post poll
 * */
class LMFeedPostPollViewStyle private constructor(
    //poll question text style
    val pollTitleTextStyle: LMFeedTextStyle,
    //poll options view style
    val pollOptionsViewStyle: LMFeedPostPollOptionViewStyle,
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
    //edit poll icon style
    val editPollIconStyle: LMFeedIconStyle?,
    //clear poll icon style
    val clearPollIconStyle: LMFeedIconStyle?,
    //edit vote on poll text style
    val editPollVoteTextStyle: LMFeedTextStyle?
) : LMFeedViewStyle {

    class Builder {
        private var pollTitleTextStyle: LMFeedTextStyle = LMFeedTextStyle.Builder()
            .textSize(R.dimen.lm_feed_text_large)
            .textColor(R.color.lm_feed_dark_grey)
            .fontAssetsPath("fonts/lm_feed_montserrat-medium.ttf")
            .build()

        private var pollOptionsViewStyle: LMFeedPostPollOptionViewStyle =
            LMFeedPostPollOptionViewStyle.Builder().build()

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

        private var editPollIconStyle: LMFeedIconStyle? = null

        private var clearPollIconStyle: LMFeedIconStyle? = null

        private var editPollVoteTextStyle: LMFeedTextStyle? = null

        fun pollTitleTextStyle(pollTitleTextStyle: LMFeedTextStyle) = apply {
            this.pollTitleTextStyle = pollTitleTextStyle
        }

        fun pollOptionsViewStyle(pollOptionsViewStyle: LMFeedPostPollOptionViewStyle) = apply {
            this.pollOptionsViewStyle = pollOptionsViewStyle
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

        fun editPollIconStyle(editPollIconStyle: LMFeedIconStyle?) = apply {
            this.editPollIconStyle = editPollIconStyle
        }

        fun clearPollIconStyle(clearPollIconStyle: LMFeedIconStyle?) = apply {
            this.clearPollIconStyle = clearPollIconStyle
        }

        fun editPollVoteTextStyle(editPollVoteTextStyle: LMFeedTextStyle?) = apply {
            this.editPollVoteTextStyle = editPollVoteTextStyle
        }

        fun build() = LMFeedPostPollViewStyle(
            pollTitleTextStyle,
            pollOptionsViewStyle,
            membersVotedCountTextStyle,
            pollInfoTextStyle,
            submitPollVoteButtonStyle,
            pollExpiryTextStyle,
            addPollOptionButtonStyle,
            editPollIconStyle,
            clearPollIconStyle,
            editPollVoteTextStyle
        )
    }

    fun toBuilder(): Builder {
        return Builder().pollTitleTextStyle(pollTitleTextStyle)
            .pollOptionsViewStyle(pollOptionsViewStyle)
            .membersVotedCountTextStyle(membersVotedCountTextStyle)
            .pollInfoTextStyle(pollInfoTextStyle)
            .submitPollVoteButtonStyle(submitPollVoteButtonStyle)
            .pollExpiryTextStyle(pollExpiryTextStyle)
            .addPollOptionButtonStyle(addPollOptionButtonStyle)
            .editPollIconStyle(editPollIconStyle)
            .clearPollIconStyle(clearPollIconStyle)
            .editPollVoteTextStyle(editPollVoteTextStyle)
    }
}