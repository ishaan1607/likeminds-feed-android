package com.likeminds.feed.android.core.likes.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.*
import com.likeminds.feed.android.core.likes.adapter.LMFeedLikesAdapter
import com.likeminds.feed.android.core.likes.adapter.LMFeedLikesAdapterListener
import com.likeminds.feed.android.core.likes.model.LMFeedLikeViewData
import com.likeminds.feed.android.core.utils.LMFeedEndlessRecyclerViewScrollListener

class LMFeedLikesListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    val linearLayoutManager: LinearLayoutManager

    private lateinit var likesAdapter: LMFeedLikesAdapter
    private lateinit var paginationScrollListener: LMFeedEndlessRecyclerViewScrollListener

    init {
        setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        layoutManager = linearLayoutManager

        if (itemAnimator is SimpleItemAnimator) {
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        }
    }

    //sets the adapter with the provided [listener] to the likes recycler view
    fun setAdapter(likesAdapterListener: LMFeedLikesAdapterListener) {
        likesAdapter = LMFeedLikesAdapter(likesAdapterListener)
        adapter = likesAdapter
    }

    //sets the pagination scroll listener to the likes recycler view
    fun setPaginationScrollListener(scrollListener: LMFeedEndlessRecyclerViewScrollListener) {
        paginationScrollListener = scrollListener
        addOnScrollListener(scrollListener)
    }

    //resets the scroll listener data
    fun resetScrollListenerData() {
        if (::paginationScrollListener.isInitialized) {
            paginationScrollListener.resetData()
        }
    }

    //adds the provided [likes] in the likes adapter
    fun addLikes(likes: List<LMFeedLikeViewData>) {
        likesAdapter.addAll(likes)
    }
}