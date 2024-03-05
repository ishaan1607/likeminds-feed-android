package com.likeminds.feed.android.core.post.detail.adapter

import com.likeminds.feed.android.core.post.detail.adapter.databinders.*
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalFeedAdapterListener
import com.likeminds.feed.android.core.universalfeed.adapter.databinders.*
import com.likeminds.feed.android.core.utils.base.*

class LMFeedPostDetailAdapter(
    private val universalFeedAdapterListener: LMFeedUniversalFeedAdapterListener
) : LMFeedBaseRecyclerAdapter<LMFeedBaseViewType>() {

    init {
        initViewDataBinders()
    }

    override fun getSupportedViewDataBinder(): MutableList<LMFeedViewDataBinder<*, *>> {
        val viewDataBinders = ArrayList<LMFeedViewDataBinder<*, *>>(8)

        val itemCommentsCountViewDataBinder = LMFeedItemCommentsCountViewDataBinder()
        viewDataBinders.add(itemCommentsCountViewDataBinder)

        val itemCommentViewDataBinder = LMFeedItemCommentViewDataBinder()
        viewDataBinders.add(itemCommentViewDataBinder)

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

        val itemNoCommentsFoundBinder = LMFeedItemNoCommentsFoundViewDataBinder()
        viewDataBinders.add(itemNoCommentsFoundBinder)

        return viewDataBinders
    }
}

interface LMFeedPostDetailAdapterListener {

    fun onCommentContentSeeMoreClicked(

    ) {
        //triggered when the user clicks on "See More" on comment content
    }

    fun onCommentLiked(

    ) {
        //triggered when the user likes a comment
    }
}