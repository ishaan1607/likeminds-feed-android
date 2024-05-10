package com.likeminds.feed.android.core.poll.adapter.databinder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.likeminds.feed.android.core.databinding.LmFeedItemPollVoteResultsBinding
import com.likeminds.feed.android.core.poll.adapter.LMFeedPollVoteResultsAdapterListener
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer
import com.likeminds.feed.android.core.utils.base.LMFeedViewDataBinder
import com.likeminds.feed.android.core.utils.base.model.ITEM_USER
import com.likeminds.feed.android.core.utils.user.LMFeedUserViewData

class LMFeedItemPollVoteResultsViewDataBinder(
    private val listener: LMFeedPollVoteResultsAdapterListener
) : LMFeedViewDataBinder<LmFeedItemPollVoteResultsBinding, LMFeedUserViewData>() {

    override val viewType: Int
        get() = ITEM_USER

    override fun createBinder(parent: ViewGroup): LmFeedItemPollVoteResultsBinding {
        val binding = LmFeedItemPollVoteResultsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        binding.userView.setStyle(LMFeedStyleTransformer.pollResultsFragmentViewStyle.userViewStyle)
        setClickListeners(binding)

        return binding
    }

    override fun bindData(
        binding: LmFeedItemPollVoteResultsBinding,
        data: LMFeedUserViewData,
        position: Int
    ) {
        binding.apply {
            //set data to the binding
            this.position = position
            userVotedViewData = data

            userView.setUserImage(data)
            userView.setUserName(data.name)
            userView.setUserTitle(data.customTitle)
        }
    }

    private fun setClickListeners(binding: LmFeedItemPollVoteResultsBinding) {
        binding.apply {
            root.setOnClickListener {
                val userVotedData = userVotedViewData ?: return@setOnClickListener
                listener.onPollVoteResultItemClicked(position, userVotedData)
            }
        }
    }
}