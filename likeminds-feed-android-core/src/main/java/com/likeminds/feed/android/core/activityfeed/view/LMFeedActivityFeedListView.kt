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

        if (itemAnimator is SimpleItemAnimator) {
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        }
    }

    //sets the adapter with the provided [listener] to the activity feed recycler view
    fun setAdapter(listener: LMFeedActivityFeedAdapterListener) {
        activityFeedAdapter = LMFeedActivityFeedAdapter(listener)
        adapter = activityFeedAdapter
    }

    //sets the pagination scroll listener to the activity feed recycler view
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

    //adds the provided [activities] in the activity feed feed adapter
    fun addActivities(activities: List<LMFeedActivityViewData>) {
        activityFeedAdapter.addAll(activities)
    }

    //updates the activity with the provided [updatedActivity] at provided [position] int the activity feed adapter
    fun updateActivity(position: Int, updatedActivity: LMFeedActivityViewData) {
        activityFeedAdapter.update(position, updatedActivity)
    }

    //replaces the [activities] in the activity feed adapter with the provided activities
    fun replaceActivities(activities: List<LMFeedActivityViewData>) {
        activityFeedAdapter.replace(activities)
    }
}