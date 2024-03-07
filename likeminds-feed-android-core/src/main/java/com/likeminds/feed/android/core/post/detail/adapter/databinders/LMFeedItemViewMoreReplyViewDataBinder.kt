package com.likeminds.feed.android.core.post.detail.adapter.databinders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.databinding.LmFeedItemViewMoreReplyBinding
import com.likeminds.feed.android.core.post.detail.adapter.LMFeedReplyAdapterListener
import com.likeminds.feed.android.core.post.detail.model.LMFeedViewMoreReplyViewData
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer
import com.likeminds.feed.android.core.utils.base.LMFeedViewDataBinder
import com.likeminds.feed.android.core.utils.base.model.ITEM_VIEW_MORE_REPLY

class LMFeedItemViewMoreReplyViewDataBinder(
    private val listener: LMFeedReplyAdapterListener
) : LMFeedViewDataBinder<LmFeedItemViewMoreReplyBinding, LMFeedViewMoreReplyViewData>() {

    override val viewType: Int
        get() = ITEM_VIEW_MORE_REPLY

    override fun createBinder(parent: ViewGroup): LmFeedItemViewMoreReplyBinding {
        val binding = LmFeedItemViewMoreReplyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        binding.viewMoreReply.setStyle(LMFeedStyleTransformer.postDetailFragmentViewStyle.viewMoreReplyStyle)
        setClickListeners(binding)

        return binding
    }

    override fun bindData(
        binding: LmFeedItemViewMoreReplyBinding,
        data: LMFeedViewMoreReplyViewData,
        position: Int
    ) {
        binding.apply {
            // set variables in the binding
            this.position = position
            viewMoreData = data

            val context = root.context

            viewMoreReply.setViewMoreText(context.getString(R.string.lm_feed_view_more_replies))
            viewMoreReply.setVisibleCount(
                context.getString(
                    R.string.lm_feed_placeholder_replies,
                    data.currentCount,
                    data.totalCommentsCount
                )
            )
        }
    }

    private fun setClickListeners(binding: LmFeedItemViewMoreReplyBinding) {
        binding.apply {
            viewMoreReply.setViewMoreClickListener {
                val viewMoreData = viewMoreData ?: return@setViewMoreClickListener
                listener.onViewMoreRepliesClicked(position, viewMoreData)
            }
        }
    }
}