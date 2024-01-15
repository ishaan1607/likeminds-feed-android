package com.likeminds.feed.android.integration.universalfeed.view.adapter.databinders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.likeminds.feed.android.integration.databinding.LmFeedItemPostSingleImageBinding
import com.likeminds.feed.android.integration.universalfeed.model.LMFeedPostViewData
import com.likeminds.feed.android.integration.universalfeed.view.adapter.LMFeedUniversalFeedAdapterListener
import com.likeminds.feed.android.integration.util.base.LMFeedViewDataBinder
import com.likeminds.feed.android.integration.util.base.model.ITEM_POST_SINGLE_IMAGE

class LMFeedItemPostSingleImageViewDataBinder(
    val universalFeedAdapterListener: LMFeedUniversalFeedAdapterListener
) : LMFeedViewDataBinder<LmFeedItemPostSingleImageBinding, LMFeedPostViewData>() {

    override val viewType: Int
        get() = ITEM_POST_SINGLE_IMAGE

    override fun createBinder(parent: ViewGroup): LmFeedItemPostSingleImageBinding {
        return LmFeedItemPostSingleImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }

    override fun bindData(
        binding: LmFeedItemPostSingleImageBinding,
        data: LMFeedPostViewData,
        position: Int
    ) {
        TODO("Not yet implemented")
    }
}