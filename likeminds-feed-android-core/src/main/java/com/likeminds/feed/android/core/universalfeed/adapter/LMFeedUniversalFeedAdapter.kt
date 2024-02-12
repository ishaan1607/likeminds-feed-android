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

// todo: add required parameters here
interface LMFeedUniversalFeedAdapterListener {
    //triggered when a user clicks on post content
    fun onPostContentClick()

    //triggered when a user clicks on like icon
    fun onPostLikeClick(position: Int)

    //triggered when a user clicks on likes count
    fun onPostLikesCountClick(postId: String)

    //triggered when a user clicks on the comments count
    fun onPostCommentsCountClick(postId: String)

    //triggered when a user clicks on save post icon
    fun onPostSaveClick(postId: String)

    //triggered when a user clicks on share icon
    fun onPostShareClick(postId: String)

    //triggered to update the data with re-inflation of the item
    fun updateFromLikedSaved(position: Int)
}