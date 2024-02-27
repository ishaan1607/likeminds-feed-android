package com.likeminds.feed.android.core.universalfeed.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.likeminds.feed.android.core.post.model.LMFeedAttachmentViewData
import com.likeminds.feed.android.core.post.model.LMFeedLinkOGTagsViewData
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalFeedAdapterListener
import com.likeminds.feed.android.core.universalfeed.model.LMFeedPostViewData
import com.likeminds.feed.android.core.universalfeed.view.LMFeedUniversalFeedListView
import com.likeminds.feed.android.core.utils.LMFeedEndlessRecyclerViewScrollListener

fun LMFeedUniversalFeedViewModel.bindView(
    view: LMFeedUniversalFeedListView,
    lifecycleOwner: LifecycleOwner,

) {
    //set scroll listener
    val paginationScrollListener =
        object : LMFeedEndlessRecyclerViewScrollListener(view.linearLayoutManager) {
            override fun onLoadMore(currentPage: Int) {
                if (currentPage > 0) {
                    Log.d("PUI", "load more is called $currentPage")
                    this@bindView.getFeed(currentPage, null)//todo set selected topics
                }
            }
        }
    view.setPaginationScrollListener(paginationScrollListener)

    //observe data
    this.universalFeedResponse.observe(lifecycleOwner) { response ->
        Log.d("PUI", "observer 1 binding")
        Log.d(
            "PUI", """
                observer 1
            response: ${response.second.size}
        """.trimIndent()
        )

        val page = response.first
        val posts = response.second

        if (page == 1) {
            view.replacePosts(posts)
        } else {
            view.addPosts(posts)
        }
    }

    val adapterListener= object : LMFeedUniversalFeedAdapterListener {
        override fun onPostContentClick(postId: String) {
            TODO("Not yet implemented")
        }

        override fun postLikeClicked(position: Int) {
            TODO("Not yet implemented")
        }

        override fun onPostLikesCountClick(postId: String) {
            TODO("Not yet implemented")
        }

        override fun onPostCommentsCountClick(postId: String) {
            TODO("Not yet implemented")
        }

        override fun onPostSaveClick(postId: String) {
            TODO("Not yet implemented")
        }

        override fun onPostShareClick(postId: String) {
            TODO("Not yet implemented")
        }

        override fun updateFromLikedSaved(position: Int) {
            TODO("Not yet implemented")
        }

        override fun updatePostSeenFullContent(position: Int, alreadySeenFullContent: Boolean) {
            TODO("Not yet implemented")
        }

        override fun handleLinkClick(url: String) {
            TODO("Not yet implemented")
        }

        override fun onPostMenuIconClick() {
            TODO("Not yet implemented")
        }

        override fun onPostImageMediaClick() {
            TODO("Not yet implemented")
        }

        override fun onPostLinkMediaClick(linkOGTags: LMFeedLinkOGTagsViewData) {
            TODO("Not yet implemented")
        }

        override fun onPostDocumentMediaClick(document: LMFeedAttachmentViewData) {
            TODO("Not yet implemented")
        }

        override fun onPostMultipleMediaImageClick(image: LMFeedAttachmentViewData) {
            TODO("Not yet implemented")
        }

        override fun onPostMultipleMediaVideoClick(video: LMFeedAttachmentViewData) {
            TODO("Not yet implemented")
        }

        override fun onPostMultipleMediaPageChangeCallback(position: Int) {
            TODO("Not yet implemented")
        }

        override fun onPostMultipleDocumentsExpanded(postData: LMFeedPostViewData, position: Int) {
            TODO("Not yet implemented")
        }
    }
}