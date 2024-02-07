package com.likeminds.feed.android.core.universalfeed.adapter.databinders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.likeminds.feed.android.core.databinding.LmFeedItemPostLinkBinding
import com.likeminds.feed.android.core.universalfeed.model.LMFeedPostViewData
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalFeedAdapterListener
import com.likeminds.feed.android.core.util.base.LMFeedViewDataBinder
import com.likeminds.feed.android.core.util.base.model.ITEM_POST_LINK

class LMFeedItemPostLinkViewDataBinder(
    val universalFeedAdapterListener: LMFeedUniversalFeedAdapterListener
) : LMFeedViewDataBinder<LmFeedItemPostLinkBinding, LMFeedPostViewData>() {

    override val viewType: Int
        get() = ITEM_POST_LINK

    override fun createBinder(parent: ViewGroup): LmFeedItemPostLinkBinding {
        return LmFeedItemPostLinkBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }

    override fun bindData(
        binding: LmFeedItemPostLinkBinding,
        data: LMFeedPostViewData,
        position: Int
    ) {
        TODO("Not yet implemented")
    }
}