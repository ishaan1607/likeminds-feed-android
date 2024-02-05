package com.likeminds.feed.android.integration.universalfeed.adapter.databinders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.likeminds.feed.android.integration.databinding.LmFeedItemPostMultipleMediaBinding
import com.likeminds.feed.android.integration.universalfeed.model.LMFeedPostViewData
import com.likeminds.feed.android.integration.universalfeed.adapter.LMFeedUniversalFeedAdapterListener
import com.likeminds.feed.android.integration.util.base.LMFeedViewDataBinder
import com.likeminds.feed.android.integration.util.base.model.ITEM_POST_MULTIPLE_MEDIA

class LMFeedItemPostMultipleMediaViewDataBinder(
    val universalFeedAdapterListener: LMFeedUniversalFeedAdapterListener
) : LMFeedViewDataBinder<LmFeedItemPostMultipleMediaBinding, LMFeedPostViewData>() {

    override val viewType: Int
        get() = ITEM_POST_MULTIPLE_MEDIA

    override fun createBinder(parent: ViewGroup): LmFeedItemPostMultipleMediaBinding {
        return LmFeedItemPostMultipleMediaBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }

    override fun bindData(
        binding: LmFeedItemPostMultipleMediaBinding,
        data: LMFeedPostViewData,
        position: Int
    ) {
        TODO("Not yet implemented")
    }
}