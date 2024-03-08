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

        if (itemAnimator is SimpleItemAnimator)
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
    }

    fun setAdapter(likesAdapterListener: LMFeedLikesAdapterListener) {
        likesAdapter = LMFeedLikesAdapter(likesAdapterListener)
        adapter = likesAdapter
    }

    fun setPaginationScrollListener(scrollListener: LMFeedEndlessRecyclerViewScrollListener) {
        paginationScrollListener = scrollListener
        addOnScrollListener(scrollListener)
    }

    fun resetScrollListenerData() {
        if (::paginationScrollListener.isInitialized) {
            paginationScrollListener.resetData()
        }
    }

    fun addLikes(items: List<LMFeedLikeViewData>) {
        likesAdapter.addAll(items)
    }
}