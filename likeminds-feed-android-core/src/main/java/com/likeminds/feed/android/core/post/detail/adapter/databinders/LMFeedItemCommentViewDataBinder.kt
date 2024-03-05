package com.likeminds.feed.android.core.post.detail.adapter.databinders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.likeminds.feed.android.core.databinding.LmFeedItemCommentBinding
import com.likeminds.feed.android.core.post.detail.model.LMFeedCommentViewData
import com.likeminds.feed.android.core.utils.base.LMFeedViewDataBinder
import com.likeminds.feed.android.core.utils.base.model.ITEM_COMMENT

class LMFeedItemCommentViewDataBinder :
    LMFeedViewDataBinder<LmFeedItemCommentBinding, LMFeedCommentViewData>() {

    override val viewType: Int
        get() = ITEM_COMMENT

    override fun createBinder(parent: ViewGroup): LmFeedItemCommentBinding {
        val binding = LmFeedItemCommentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return binding
    }

    override fun bindData(
        binding: LmFeedItemCommentBinding,
        data: LMFeedCommentViewData,
        position: Int
    ) {

    }
}