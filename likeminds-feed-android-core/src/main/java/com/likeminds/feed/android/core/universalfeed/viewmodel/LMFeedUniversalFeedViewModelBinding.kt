package com.likeminds.feed.android.core.universalfeed.viewmodel

import androidx.lifecycle.LifecycleOwner
import com.likeminds.feed.android.core.universalfeed.view.LMFeedUniversalFeedListView
import com.likeminds.feed.android.core.utils.LMFeedEndlessRecyclerViewScrollListener

public fun LMFeedUniversalFeedViewModel.bindView(
    view: LMFeedUniversalFeedListView,
    lifecycleOwner: LifecycleOwner
) {

    //set scroll listener
    val paginationScrollListener =
        object : LMFeedEndlessRecyclerViewScrollListener(view.linearLayoutManager) {
            override fun onLoadMore(currentPage: Int) {
                if (currentPage > 0) {
                    this@bindView.getFeed(currentPage, null)//todo set selected topics
                }
            }
        }
    view.setPaginationScrollListener(paginationScrollListener)

    //observe data
    this.universalFeedResponse.observe(lifecycleOwner) { response ->
        val page = response.first
        val posts = response.second

        if (page == 1) {
            view.replacePosts(posts)
        } else {
            view.addPosts(posts)
        }
    }
}