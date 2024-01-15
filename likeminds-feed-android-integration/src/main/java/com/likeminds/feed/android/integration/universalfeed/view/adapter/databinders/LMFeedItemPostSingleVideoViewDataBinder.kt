package com.likeminds.feed.android.integration.universalfeed.view.adapter.databinders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.likeminds.feed.android.integration.databinding.LmFeedItemPostSingleVideoBinding
import com.likeminds.feed.android.integration.universalfeed.model.LMFeedPostViewData
import com.likeminds.feed.android.integration.universalfeed.view.adapter.LMFeedUniversalFeedAdapterListener
import com.likeminds.feed.android.integration.util.base.LMFeedViewDataBinder
import com.likeminds.feed.android.integration.util.base.model.ITEM_POST_SINGLE_VIDEO

class LMFeedItemPostSingleVideoViewDataBinder (
    val universalFeedAdapterListener: LMFeedUniversalFeedAdapterListener
) : LMFeedViewDataBinder<LmFeedItemPostSingleVideoBinding, LMFeedPostViewData>() {

    override val viewType: Int
        get() = ITEM_POST_SINGLE_VIDEO

    override fun createBinder(parent: ViewGroup): LmFeedItemPostSingleVideoBinding {
        return LmFeedItemPostSingleVideoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }

    override fun bindData(
        binding: LmFeedItemPostSingleVideoBinding,
        data: LMFeedPostViewData,
        position: Int
    ) {
        TODO("Not yet implemented")
    }
}