package com.likeminds.feed.android.core.universalfeed.adapter

import com.likeminds.feed.android.core.post.model.LMFeedAttachmentViewData
import com.likeminds.feed.android.core.post.model.LMFeedLinkOGTagsViewData
import com.likeminds.feed.android.core.universalfeed.adapter.databinders.*
import com.likeminds.feed.android.core.util.base.*

class LMFeedUniversalFeedAdapter(
    private val universalFeedAdapterListener: LMFeedUniversalFeedAdapterListener
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
    //triggered when the user clicks on post content
    fun onPostContentClick(postId: String)

    //triggered when the user clicks on like icon
    fun onPostLikeClick(position: Int)

    //triggered when the user clicks on likes count
    fun onPostLikesCountClick(postId: String)

    //triggered when the user clicks on the comments count
    fun onPostCommentsCountClick(postId: String)

    //triggered when the user clicks on save post icon
    fun onPostSaveClick(postId: String)

    //triggered when the user clicks on share icon
    fun onPostShareClick(postId: String)

    //triggered to update the data with re-inflation of the item
    fun updateFromLikedSaved(position: Int)

    //triggered when the user clicks on "See More"
    fun updatePostSeenFullContent(position: Int, alreadySeenFullContent: Boolean)

    //triggered when a link from post content is clicked
    fun handleLinkClick(url: String)

    //triggered when the menu icon of the post is clicked
    fun onPostMenuIconClick()

    //triggered when the image media of the post is clicked
    fun onPostImageMediaClick()

    //triggered when the link media of the post is clicked
    fun onPostLinkMediaClick(linkOGTags: LMFeedLinkOGTagsViewData)

    //triggered when the document media in the post is clicker
    fun onPostDocumentMediaClick(document: LMFeedAttachmentViewData)
}