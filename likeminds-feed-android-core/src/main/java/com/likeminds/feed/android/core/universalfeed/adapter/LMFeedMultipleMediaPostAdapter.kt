package com.likeminds.feed.android.core.universalfeed.adapter

import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalFeedAdapterListener
import com.likeminds.feed.android.core.universalfeed.adapter.databinders.postmultiplemedia.LMFeedItemMultipleMediaImageViewDataBinder
import com.likeminds.feed.android.core.universalfeed.adapter.databinders.postmultiplemedia.LMFeedItemMultipleMediaVideoViewDataBinder
import com.likeminds.feed.android.core.utils.base.*

class LMFeedMultipleMediaPostAdapter(
    private val parentPosition: Int,
    private val listener: LMFeedUniversalFeedAdapterListener
) : LMFeedBaseRecyclerAdapter<LMFeedBaseViewType>() {

    init {
        initViewDataBinders()
    }

    override fun getSupportedViewDataBinder(): MutableList<LMFeedViewDataBinder<*, *>> {
        val viewDataBinders = ArrayList<LMFeedViewDataBinder<*, *>>(2)

        val multipleMediaImageBinder = LMFeedItemMultipleMediaImageViewDataBinder(parentPosition, listener)
        viewDataBinders.add(multipleMediaImageBinder)

        val multipleMediaVideoBinder = LMFeedItemMultipleMediaVideoViewDataBinder(parentPosition, listener)
        viewDataBinders.add(multipleMediaVideoBinder)

        return viewDataBinders
    }
}