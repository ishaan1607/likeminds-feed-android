package com.likeminds.feed.android.core.universalfeed.adapter

import com.likeminds.feed.android.core.universalfeed.adapter.databinders.*
import com.likeminds.feed.android.core.util.base.*

class LMFeedUniversalFeedAdapter constructor(
    val universalFeedAdapterListener: LMFeedUniversalFeedAdapterListener
) : LMFeedBaseRecyclerAdapter<LMFeedBaseViewType>() {

    init {
        initViewDataBinders()
    }

    override fun getSupportedViewDataBinder(): MutableList<LMFeedViewDataBinder<*, *>> {
        val viewDataBinders = ArrayList<LMFeedViewDataBinder<*, *>>(6)

        val itemPostTextOnlyBinder =
            LMFeedItemPostTextOnlyViewDataBinder(universalFeedAdapterListener)
        viewDataBinders.add(itemPostTextOnlyBinder)

        val itemPostSingleImageViewDataBinder =
            LMFeedItemPostSingleImageViewDataBinder(universalFeedAdapterListener)
        viewDataBinders.add(itemPostSingleImageViewDataBinder)

        val itemPostSingleVideoViewDataBinder =
            LMFeedItemPostSingleVideoViewDataBinder(universalFeedAdapterListener)
        viewDataBinders.add(itemPostSingleVideoViewDataBinder)

        val itemPostLinkViewDataBinder =
            LMFeedItemPostLinkViewDataBinder(universalFeedAdapterListener)
        viewDataBinders.add(itemPostLinkViewDataBinder)

        val lmFeedItemPostDocumentsViewDataBinder =
            LMFeedItemPostDocumentsViewDataBinder(universalFeedAdapterListener)
        viewDataBinders.add(lmFeedItemPostDocumentsViewDataBinder)

        val itemPostMultipleMediaViewDataBinder =
            LMFeedItemPostMultipleMediaViewDataBinder(universalFeedAdapterListener)
        viewDataBinders.add(itemPostMultipleMediaViewDataBinder)

        return viewDataBinders
    }
}

interface LMFeedUniversalFeedAdapterListener {
}