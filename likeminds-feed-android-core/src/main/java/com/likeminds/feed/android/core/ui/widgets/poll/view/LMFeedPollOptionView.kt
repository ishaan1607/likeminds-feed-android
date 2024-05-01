package com.likeminds.feed.android.core.ui.widgets.poll.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.likeminds.feed.android.core.databinding.LmFeedPollOptionViewBinding
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.ui.widgets.poll.style.LMFeedPostPollOptionViewStyle
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.hide
import com.likeminds.feed.android.core.utils.listeners.LMFeedOnClickListener

class LMFeedPollOptionView : ConstraintLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)

    constructor(context: Context, attributeSet: AttributeSet?, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle
    )

    private val inflater =
        (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)

    private val binding = LmFeedPollOptionViewBinding.inflate(inflater, this, true)

    //sets provided [postPollOptionViewStyle] to the post poll option view
    fun setStyle(postPollOptionViewStyle: LMFeedPostPollOptionViewStyle) {
        postPollOptionViewStyle.apply {
            configurePollOptionText(pollOptionTextStyle)
            configurePollOptionAddedByText(pollOptionAddedByTextStyle)
            configurePollOptionCheckedIcon(pollOptionCheckIconStyle)
            configurePollOptionVotesCountText(pollOptionVotesCountTextStyle)
        }
    }

    private fun configurePollOptionText(pollOptionTextStyle: LMFeedTextStyle) {
        binding.tvPollOption.setStyle(pollOptionTextStyle)
    }

    private fun configurePollOptionAddedByText(pollOptionAddedByTextStyle: LMFeedTextStyle?) {
        binding.tvAddedBy.apply {
            if (pollOptionAddedByTextStyle == null) {
                hide()
            } else {
                setStyle(pollOptionAddedByTextStyle)
            }
        }
    }

    private fun configurePollOptionCheckedIcon(pollOptionCheckIconStyle: LMFeedIconStyle?) {
        binding.ivChecked.apply {
            if (pollOptionCheckIconStyle == null) {
                hide()
            } else {
                setStyle(pollOptionCheckIconStyle)
            }
        }
    }

    private fun configurePollOptionVotesCountText(pollOptionVotesCountTextStyle: LMFeedTextStyle?) {
        binding.tvNoVotes.apply {
            if (pollOptionVotesCountTextStyle == null) {
                hide()
            } else {
                setStyle(pollOptionVotesCountTextStyle)
            }
        }
    }

    /**
     * Sets click listener on the poll option
     *
     * @param listener [LMFeedOnClickListener] interface to have click listener
     */
    fun setPollOptionClicked(listener: LMFeedOnClickListener) {
        binding.clPollOption.setOnClickListener {
            listener.onClick()
        }
    }

    /**
     * Sets click listener on the poll option votes count text
     *
     * @param listener [LMFeedOnClickListener] interface to have click listener
     */
    fun setPollOptionVotesCountClicked(listener: LMFeedOnClickListener) {
        binding.tvNoVotes.setOnClickListener {
            listener.onClick()
        }
    }
}