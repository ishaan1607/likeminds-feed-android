package com.likeminds.feed.android.core.ui.widgets.poll.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.likeminds.feed.android.core.databinding.LmFeedPostPollViewBinding
import com.likeminds.feed.android.core.poll.result.model.LMFeedPollOptionViewData
import com.likeminds.feed.android.core.poll.result.model.LMFeedPollViewData
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.ui.widgets.poll.adapter.LMFeedPollOptionsAdapterListener
import com.likeminds.feed.android.core.ui.widgets.poll.style.LMFeedPostPollViewStyle
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.hide
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.show
import com.likeminds.feed.android.core.utils.listeners.LMFeedOnClickListener

/**
 * Represents a poll view
 * To customize this view use [LMFeedPostPollViewStyle]
 */
class LMFeedPostPollView : ConstraintLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)

    constructor(context: Context, attributeSet: AttributeSet?, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle
    )

    private val inflater =
        (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)

    private val binding = LmFeedPostPollViewBinding.inflate(inflater, this, true)

    //sets provided [postPollViewStyle] to the post poll view
    fun setStyle(postPollViewStyle: LMFeedPostPollViewStyle) {
        postPollViewStyle.apply {

            //configures each view in the post poll view with the provided style
            configurePollTitleText(pollTitleTextStyle)
            configurePollInfoText(pollInfoTextStyle)
            configureMemberVotedCountText(membersVotedCountTextStyle)
            configureSubmitPollVoteButton(submitPollVoteButtonStyle)
            configurePollExpiryText(pollExpiryTextStyle)
            configureAddPollOptionButton(addPollOptionButtonStyle)
            configureEditPollVoteText(editPollVoteTextStyle)
            configureClearPollIcon(clearPollIconStyle)
            configureEditPollIcon(editPollIconStyle)
        }
    }

    private fun configurePollTitleText(pollTitleTextStyle: LMFeedTextStyle) {
        binding.tvPollTitle.setStyle(pollTitleTextStyle)
    }

    private fun configurePollInfoText(pollInfoTextStyle: LMFeedTextStyle?) {
        binding.tvPollInfo.apply {
            if (pollInfoTextStyle == null) {
                hide()
            } else {
                setStyle(pollInfoTextStyle)
                show()
            }
        }
    }

    private fun configureMemberVotedCountText(membersVotedCountTextStyle: LMFeedTextStyle?) {
        binding.tvMemberVotedCount.apply {
            if (membersVotedCountTextStyle == null) {
                binding.viewDotTimeLeft.hide()
                hide()
            } else {
                binding.viewDotTimeLeft.show()
                setStyle(membersVotedCountTextStyle)
            }
        }
    }

    private fun configureSubmitPollVoteButton(submitPollButtonStyle: LMFeedButtonStyle?) {
        binding.btnSubmitVote.apply {
            if (submitPollButtonStyle == null) {
                hide()
            } else {
                show()
                setStyle(submitPollButtonStyle)
            }
        }
    }

    private fun configurePollExpiryText(pollExpiryTextStyle: LMFeedTextStyle?) {
        binding.tvPollTimeLeft.apply {
            if (pollExpiryTextStyle == null) {
                hide()
            } else {
                setStyle(pollExpiryTextStyle)
                show()
            }
        }
    }

    private fun configureAddPollOptionButton(addPollOptionButtonStyle: LMFeedButtonStyle?) {
        binding.btnAddOption.apply {
            if (addPollOptionButtonStyle == null) {
                hide()
            } else {
                show()
                setStyle(addPollOptionButtonStyle)
            }
        }

    }

    private fun configureEditPollVoteText(editPollVoteTextStyle: LMFeedTextStyle?) {
        binding.apply {
            if (editPollVoteTextStyle == null) {
                viewDotEditVote.hide()
                tvPollEditVote.hide()
            } else {
                tvPollEditVote.setStyle(editPollVoteTextStyle)
                viewDotEditVote.show()
                tvPollEditVote.show()
            }
        }
    }

    private fun configureClearPollIcon(clearPollIconStyle: LMFeedIconStyle?) {
        binding.ivClearPoll.apply {
            if (clearPollIconStyle == null) {
                hide()
            } else {
                setStyle(clearPollIconStyle)
                show()
            }
        }
    }

    private fun configureEditPollIcon(editPollIconStyle: LMFeedIconStyle?) {
        binding.ivEditPoll.apply {
            if (editPollIconStyle == null) {
                hide()
            } else {
                setStyle(editPollIconStyle)
                show()
            }
        }
    }

    /**
     * Sets the title of the poll media in the post
     *
     * @param pollTitle - string to be set for title of the poll.
     */
    fun setPollTitle(pollTitle: String) {
        binding.tvPollTitle.text = pollTitle
    }

    /**
     * Sets the info of the poll media in the post
     *
     * @param pollInfo - string to be set for info of the poll.
     */
    fun setPollInfo(pollInfo: String?) {
        binding.tvPollInfo.apply {
            if (pollInfo.isNullOrEmpty()) {
                hide()
            } else {
                text = pollInfo
                show()
            }
        }
    }

    /**
     * Sets the member voted count of the poll media in the post
     *
     * @param pollAnswerText - string to be set for member voted count of the poll.
     */
    fun setMemberVotedCount(pollAnswerText: String) {
        binding.apply {
            tvMemberVotedCount.text = pollAnswerText
            viewDotTimeLeft.show()
        }
    }

    /**
     * Sets the time left in expiry of the poll media in the post
     *
     * @param timeLeft - string to be set for time left in expiry of the poll.
     */
    fun setTimeLeft(timeLeft: String) {
        binding.tvPollTimeLeft.text = timeLeft
    }

    /**
     * Sets the poll options in the poll media in the post
     *
     * @param pollPosition - position of the poll media post in the list.
     * @param options - list of the options in the poll.
     * @param listener - click listeners for the poll options.
     */
    fun setPollOptions(
        pollPosition: Int,
        options: List<LMFeedPollOptionViewData>,
        listener: LMFeedPollOptionsAdapterListener?
    ) {
        binding.rvPollOptions.apply {
            setAdapter(pollPosition, listener)
            replacePollOptions(options)
        }
    }

    /**
     * Sets the visibility of the submit button of the poll media in the post
     *
     * @param pollViewData - data of the poll media.
     */
    fun setSubmitButtonVisibility(pollViewData: LMFeedPollViewData) {
        binding.btnSubmitVote.apply {
            //hide submit button if poll is instant and already submitted or poll is deferred with single item selection
            if (pollViewData.isPollSubmitted || pollViewData.hasPollEnded()) {
                hide()
            } else {
                if (pollViewData.isMultiChoicePoll()) {
                    show()
                } else {
                    hide()
                }
            }
        }
    }

    /**
     * Sets the visibility of the add poll option button of the poll media in the post
     *
     * @param pollViewData - data of the poll media.
     */
    fun setAddPollOptionButtonVisibility(pollViewData: LMFeedPollViewData) {
        binding.btnAddOption.apply {
            if (pollViewData.isAddOptionAllowedForInstantPoll() || pollViewData.isAddOptionAllowedForDeferredPoll()) {
                show()
            } else {
                hide()
            }
        }
    }

    /**
     * Sets the visibility of the edit poll vote button of the poll media in the post
     *
     * @param pollViewData - data of the poll media.
     */
    fun setEditPollVoteVisibility(pollViewData: LMFeedPollViewData) {
        binding.apply {
            if (pollViewData.isDeferredPoll() && pollViewData.isPollSubmitted
                && !pollViewData.hasPollEnded()
            ) {
                viewDotEditVote.show()
                tvPollEditVote.show()
            } else {
                viewDotEditVote.hide()
                tvPollEditVote.hide()
            }
        }
    }

    /**
     * Sets click listener on the poll title
     *
     * @param listener [LMFeedOnClickListener] interface to have click listener
     */
    fun setPollTitleClicked(listener: LMFeedOnClickListener) {
        binding.tvPollTitle.setOnClickListener {
            listener.onClick()
        }
    }

    /**
     * Sets click listener on the edit poll icon
     *
     * @param listener [LMFeedOnClickListener] interface to have click listener
     */
    fun setEditPollClicked(listener: LMFeedOnClickListener) {
        binding.ivEditPoll.setOnClickListener {
            listener.onClick()
        }
    }

    /**
     * Sets click listener on the clear poll icon
     *
     * @param listener [LMFeedOnClickListener] interface to have click listener
     */
    fun setClearPollClicked(listener: LMFeedOnClickListener) {
        binding.ivClearPoll.setOnClickListener {
            listener.onClick()
        }
    }

    /**
     * Sets click listener on the add poll option button
     *
     * @param listener [LMFeedOnClickListener] interface to have click listener
     */
    fun setAddPollOptionClicked(listener: LMFeedOnClickListener) {
        binding.btnAddOption.setOnClickListener {
            listener.onClick()
        }
    }

    /**
     * Sets click listener on the submit vote button
     *
     * @param listener [LMFeedOnClickListener] interface to have click listener
     */
    fun setSubmitPollVoteClicked(listener: LMFeedOnClickListener) {
        binding.btnSubmitVote.setOnClickListener {
            listener.onClick()
        }
    }

    /**
     * Sets click listener on the member voted count text
     *
     * @param listener [LMFeedOnClickListener] interface to have click listener
     */
    fun setMemberVotedCountClicked(listener: LMFeedOnClickListener) {
        binding.tvMemberVotedCount.setOnClickListener {
            listener.onClick()
        }
    }

    /**
     * Sets click listener on the edit poll vote text
     *
     * @param listener [LMFeedOnClickListener] interface to have click listener
     */
    fun setEditPollVoteClicked(listener: LMFeedOnClickListener) {
        binding.tvPollEditVote.setOnClickListener {
            listener.onClick()
        }
    }
}