package com.likeminds.feed.android.core.topicselection.adapter

import com.likeminds.feed.android.core.topics.model.LMFeedTopicViewData
import com.likeminds.feed.android.core.topicselection.adapter.databinder.LMFeedItemAllTopicsViewDataBinder
import com.likeminds.feed.android.core.topicselection.adapter.databinder.LMFeedItemTopicViewDataBinder
import com.likeminds.feed.android.core.topicselection.model.LMFeedAllTopicsViewData
import com.likeminds.feed.android.core.utils.base.*

class LMFeedTopicSelectionAdapter(
    private val listener: LMFeedTopicSelectionAdapterListener
) : LMFeedBaseRecyclerAdapter<LMFeedBaseViewType>() {

    init {
        initViewDataBinders()
    }

    override fun getSupportedViewDataBinder(): MutableList<LMFeedViewDataBinder<*, *>> {
        val viewDataBinders = ArrayList<LMFeedViewDataBinder<*, *>>(2)

        val itemTopicViewDataBinder = LMFeedItemTopicViewDataBinder(listener)
        viewDataBinders.add(itemTopicViewDataBinder)

        val itemAllTopicsViewDataBinder = LMFeedItemAllTopicsViewDataBinder(listener)
        viewDataBinders.add(itemAllTopicsViewDataBinder)

        return viewDataBinders
    }
}

interface LMFeedTopicSelectionAdapterListener {
    fun onAllTopicsSelected(position: Int, allTopicsViewData: LMFeedAllTopicsViewData) {
        //triggered when user selects all topics
    }

    fun onTopicSelected(position: Int, topic: LMFeedTopicViewData) {
        //triggered when user selects any topic from the list of the topics
    }
}