package com.likeminds.feed.android.core.universalfeed.adapter

import android.view.View
import com.likeminds.feed.android.core.universalfeed.adapter.databinders.*
import com.likeminds.feed.android.core.universalfeed.model.LMFeedPostViewData
import com.likeminds.feed.android.core.utils.LMFeedValueUtils.getItemInList
import com.likeminds.feed.android.core.utils.base.*

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

    operator fun get(position: Int): LMFeedBaseViewType? {
        return items().getItemInList(position)
    }

    fun replaceTextOnlyBinder(viewDataBinder: LMFeedViewDataBinder<*, *>) {
        val view = viewDataBinder.viewType

        val indexOfViewData = this.supportedViewDataBinder.indexOfFirst {
            it.viewType == view
        }

        this.supportedViewDataBinder[indexOfViewData] = viewDataBinder
    }
}

interface LMFeedUniversalFeedAdapterListener {
    //triggered when the user clicks on post content
    fun onPostContentClick(position: Int, postViewData: LMFeedPostViewData) //todo

    //triggered when the user clicks on like icon
    fun onPostLikeClick(position: Int, postViewData: LMFeedPostViewData) //Ishaan

    //triggered when the user clicks on likes count
    fun onPostLikesCountClick(position: Int, postViewData: LMFeedPostViewData) //todo

    //triggered when the user clicks on the comments count
    fun onPostCommentsCountClick(position: Int, postViewData: LMFeedPostViewData) //todo

    //triggered when the user clicks on save post icon
    fun onPostSaveClick(position: Int, postViewData: LMFeedPostViewData) //ishaan

    //triggered when the user clicks on share icon
    fun onPostShareClick(position: Int, postViewData: LMFeedPostViewData) //sid

    //triggered to update the data with re-inflation of the item
    fun updateFromLikedSaved(position: Int, postViewData: LMFeedPostViewData) //sid

    //triggered when the user clicks on "See More"
    fun updatePostSeenFullContent(
        position: Int,
        alreadySeenFullContent: Boolean,
        postViewData: LMFeedPostViewData
    ) //sid

    //triggered when a link from post content is clicked
    fun handleLinkClick(url: String) //sid

    //triggered when the menu icon of the post is clicked
    fun onPostMenuIconClick(
        position: Int,
        anchorView: View,
        postViewData: LMFeedPostViewData
    ) //sid

    //triggered when the image media of the post is clicked
    fun onPostImageMediaClick(position: Int, postViewData: LMFeedPostViewData)

    //triggered when the video media of the post is clicked
    fun onPostVideoMediaClick(position: Int, postViewData: LMFeedPostViewData) //todo

    //triggered when the link media of the post is clicked
    fun onPostLinkMediaClick(position: Int, postViewData: LMFeedPostViewData) //sid

    //triggered when the document media in the post is clicked
    fun onPostDocumentMediaClick(position: Int, parentPosition: Int) //sid

    //triggered when the image media of multiple media is clicked
    fun onPostMultipleMediaImageClick(position: Int, parentPosition: Int) //todo

    //triggered when the video media of multiple media is clicked
    fun onPostMultipleMediaVideoClick(position: Int, parentPosition: Int) //todo

    //triggered when the page of the view pager is changed
    fun onPostMultipleMediaPageChangeCallback(position: Int, parentPosition: Int) //sid

    //triggered when a user clicks on "See More" of document type post
    fun onPostMultipleDocumentsExpanded(position: Int, postViewData: LMFeedPostViewData) //sid
}