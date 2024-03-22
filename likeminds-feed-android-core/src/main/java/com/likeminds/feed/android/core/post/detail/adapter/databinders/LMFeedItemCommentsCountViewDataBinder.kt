package com.likeminds.feed.android.core.post.detail.adapter.databinders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.databinding.LmFeedItemCommentsCountBinding
import com.likeminds.feed.android.core.post.detail.model.LMFeedCommentsCountViewData
import com.likeminds.feed.android.core.ui.base.styles.setStyle
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer
import com.likeminds.feed.android.core.utils.base.LMFeedViewDataBinder
import com.likeminds.feed.android.core.utils.base.model.ITEM_COMMENTS_COUNT

class LMFeedItemCommentsCountViewDataBinder :
    LMFeedViewDataBinder<LmFeedItemCommentsCountBinding, LMFeedCommentsCountViewData>() {

    override val viewType: Int
        get() = ITEM_COMMENTS_COUNT

    override fun createBinder(parent: ViewGroup): LmFeedItemCommentsCountBinding {
        val binding = LmFeedItemCommentsCountBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        binding.tvCommentsCount.setStyle(LMFeedStyleTransformer.postDetailFragmentViewStyle.commentsCountViewStyle)

        return binding
    }

    override fun bindData(
        binding: LmFeedItemCommentsCountBinding,
        data: LMFeedCommentsCountViewData,
        position: Int
    ) {
        val context = binding.root.context
        binding.tvCommentsCount.text = context.resources.getQuantityString(
            R.plurals.lm_feed_comments,
            data.commentsCount,
            data.commentsCount
        )
    }
}