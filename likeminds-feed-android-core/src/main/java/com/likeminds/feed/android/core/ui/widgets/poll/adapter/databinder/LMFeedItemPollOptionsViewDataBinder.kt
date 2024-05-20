package com.likeminds.feed.android.core.ui.widgets.poll.adapter.databinder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.likeminds.feed.android.core.databinding.LmFeedItemPollOptionsBinding
import com.likeminds.feed.android.core.poll.model.LMFeedPollOptionViewData
import com.likeminds.feed.android.core.ui.widgets.poll.adapter.LMFeedPollOptionsAdapterListener
import com.likeminds.feed.android.core.ui.widgets.poll.view.LMFeedPollOptionView
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer
import com.likeminds.feed.android.core.utils.base.LMFeedViewDataBinder
import com.likeminds.feed.android.core.utils.base.model.ITEM_POST_POLL_OPTIONS

class LMFeedItemPollOptionsViewDataBinder(
    private val listener: LMFeedPollOptionsAdapterListener
) : LMFeedViewDataBinder<LmFeedItemPollOptionsBinding, LMFeedPollOptionViewData>() {

    override val viewType: Int
        get() = ITEM_POST_POLL_OPTIONS

    override fun createBinder(parent: ViewGroup): LmFeedItemPollOptionsBinding {
        val binding = LmFeedItemPollOptionsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        //set styles to the poll option view
        val postMediaStyle = LMFeedStyleTransformer.postViewStyle.postMediaViewStyle
        val pollOptionViewStyle = postMediaStyle.postPollMediaStyle?.pollOptionsViewStyle
            ?: return binding

        binding.pollOptionView.setStyle(pollOptionViewStyle)

        return binding
    }

    override fun bindData(
        binding: LmFeedItemPollOptionsBinding,
        data: LMFeedPollOptionViewData,
        position: Int
    ) {
        binding.pollOptionView.apply {
            setClickListeners(this, data, position)

            val postMediaStyle = LMFeedStyleTransformer.postViewStyle.postMediaViewStyle
            val pollOptionViewStyle = postMediaStyle.postPollMediaStyle?.pollOptionsViewStyle
                ?: return

            setPollOptionText(data.text)
            setPollOptionAddedByText(data)
            setPollOptionBackgroundProgress(
                data,
                pollOptionViewStyle
            )
        }
    }

    private fun setClickListeners(
        pollOptionView: LMFeedPollOptionView,
        pollOptionViewData: LMFeedPollOptionViewData,
        position: Int
    ) {
        pollOptionView.apply {
            setPollOptionClicked {
                listener.onPollOptionClicked(position, pollOptionViewData)
            }

            setPollOptionVotesCountClicked {
                listener.onPollOptionVoteCountClicked(position, pollOptionViewData)
            }
        }
    }
}