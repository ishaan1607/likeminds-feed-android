package com.likeminds.feed.android.core.activityfeed.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.*
import com.likeminds.feed.android.core.activityfeed.adapter.LMFeedActivityFeedAdapter
import com.likeminds.feed.android.core.activityfeed.adapter.LMFeedActivityFeedAdapterListener
import com.likeminds.feed.android.core.activityfeed.model.LMFeedActivityViewData
import com.likeminds.feed.android.core.utils.LMFeedEndlessRecyclerViewScrollListener

class LMFeedActivityFeedListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    val linearLayoutManager: LinearLayoutManager

    private lateinit var activityFeedAdapter: LMFeedActivityFeedAdapter
    private lateinit var paginationScrollListener: LMFeedEndlessRecyclerViewScrollListener

    init {
        setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        layoutManager = linearLayoutManager

        if (itemAnimator is SimpleItemAnimator)
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
    }

    fun setAdapter(listener: LMFeedActivityFeedAdapterListener) {
        activityFeedAdapter = LMFeedActivityFeedAdapter(listener)
        adapter = activityFeedAdapter
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

    fun addActivities(items: List<LMFeedActivityViewData>) {
        activityFeedAdapter.addAll(items)
    }

    fun updateActivity(position: Int, updatedItem: LMFeedActivityViewData) {
        activityFeedAdapter.update(position, updatedItem)
    }

    fun replaceActivities(items: List<LMFeedActivityViewData>) {
        activityFeedAdapter.replace(items)
    }
}