package com.likeminds.feed.android.core.ui.widgets.poll.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.likeminds.feed.android.core.databinding.LmFeedPostPollViewBinding
import com.likeminds.feed.android.core.poll.model.LMFeedPollOptionViewData
import com.likeminds.feed.android.core.poll.model.LMFeedPollViewData
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.ui.widgets.poll.adapter.LMFeedPollOptionsAdapterListener
import com.likeminds.feed.android.core.ui.widgets.poll.style.LMFeedPostPollViewStyle
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.hide
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.show
import com.likeminds.feed.android.core.utils.listeners.LMFeedOnClickListener

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
                hide()
            } else {
                setStyle(membersVotedCountTextStyle)
            }
        }
    }

    private fun configureSubmitPollVoteButton(submitPollButtonStyle: LMFeedButtonStyle) {
        binding.btnSubmitVote.setStyle(submitPollButtonStyle)
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

    private fun configureAddPollOptionButton(addPollOptionButtonStyle: LMFeedButtonStyle) {
        binding.btnAddOption.setStyle(addPollOptionButtonStyle)
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

    fun setPollTitle(pollTitle: String) {
        binding.tvPollTitle.text = pollTitle
    }

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

    fun setMemberVotedCount(pollAnswerText: String) {
        binding.apply {
            tvMemberVotedCount.text = pollAnswerText
            viewDotTimeLeft.show()
        }
    }

    fun setTimeLeft(timeLeft: String) {
        binding.tvPollTimeLeft.text = timeLeft
    }

    fun setPollOptions(
        pollPosition: Int,
        options: List<LMFeedPollOptionViewData>,
        listener: LMFeedPollOptionsAdapterListener
    ) {
        binding.rvPollOptions.apply {
            setAdapter(pollPosition, listener)
            replacePollOptions(options)
        }
    }

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

    fun setAddPollOptionButtonVisibility(pollViewData: LMFeedPollViewData) {
        binding.btnAddOption.apply {
            if (pollViewData.isAddOptionAllowedForInstantPoll() || pollViewData.isAddOptionAllowedForDeferredPoll()) {
                show()
            } else {
                hide()
            }
        }
    }

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