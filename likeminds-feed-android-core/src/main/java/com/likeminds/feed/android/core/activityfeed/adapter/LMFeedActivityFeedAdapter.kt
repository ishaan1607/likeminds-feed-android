package com.likeminds.feed.android.core.activityfeed.adapter

import com.likeminds.feed.android.core.activityfeed.adapter.databinder.LMFeedItemActivityFeedViewDataBinder
import com.likeminds.feed.android.core.activityfeed.model.LMFeedActivityViewData
import com.likeminds.feed.android.core.utils.base.*

class LMFeedActivityFeedAdapter(
    private val listener: LMFeedActivityFeedAdapterListener
) : LMFeedBaseRecyclerAdapter<LMFeedBaseViewType>() {

    init {
        initViewDataBinders()
    }

    override fun getSupportedViewDataBinder(): MutableList<LMFeedViewDataBinder<*, *>> {
        val viewDataBinders = ArrayList<LMFeedViewDataBinder<*, *>>(1)

        val itemNotificationFeedViewDataBinder = LMFeedItemActivityFeedViewDataBinder(listener)
        viewDataBinders.add(itemNotificationFeedViewDataBinder)

        return viewDataBinders
    }
}

interface LMFeedActivityFeedAdapterListener {
    fun onActivityFeedItemClicked(position: Int, activityViewData: LMFeedActivityViewData) {
        //triggered when the user clicks on activity feed item
    }
}