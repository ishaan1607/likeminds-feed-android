package com.likeminds.feed.android.core.universalfeed.adapter.databinders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.likeminds.feed.android.core.databinding.LmFeedItemPostDocumentsBinding
import com.likeminds.feed.android.core.universalfeed.model.LMFeedPostViewData
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalFeedAdapterListener
import com.likeminds.feed.android.core.util.base.LMFeedViewDataBinder
import com.likeminds.feed.android.core.util.base.model.ITEM_POST_DOCUMENTS

class LMFeedItemPostDocumentsViewDataBinder (
    val universalFeedAdapterListener: LMFeedUniversalFeedAdapterListener
) : LMFeedViewDataBinder<LmFeedItemPostDocumentsBinding, LMFeedPostViewData>()  {

    override val viewType: Int
        get() = ITEM_POST_DOCUMENTS

    override fun createBinder(parent: ViewGroup): LmFeedItemPostDocumentsBinding {
        return LmFeedItemPostDocumentsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    }

    override fun bindData(
        binding: LmFeedItemPostDocumentsBinding,
        data: LMFeedPostViewData,
        position: Int
    ) {
        TODO("Not yet implemented")
    }
}