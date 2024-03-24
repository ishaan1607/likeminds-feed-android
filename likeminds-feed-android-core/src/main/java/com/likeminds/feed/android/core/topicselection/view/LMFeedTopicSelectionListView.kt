package com.likeminds.feed.android.core.topicselection.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.*
import com.likeminds.feed.android.core.topicselection.adapter.LMFeedTopicSelectionAdapter
import com.likeminds.feed.android.core.topicselection.adapter.LMFeedTopicSelectionAdapterListener
import com.likeminds.feed.android.core.utils.LMFeedEndlessRecyclerViewScrollListener
import com.likeminds.feed.android.core.utils.base.LMFeedBaseViewType

class LMFeedTopicSelectionListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    val linearLayoutManager: LinearLayoutManager
    private lateinit var paginationScrollListener: LMFeedEndlessRecyclerViewScrollListener

    private lateinit var topicSelectionAdapter: LMFeedTopicSelectionAdapter

    init {
        setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        layoutManager = linearLayoutManager

        if (itemAnimator is SimpleItemAnimator) {
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        }
    }

    //sets the adapter with the provided [listener] to the topic selection recycler view
    fun setAdapter(listener: LMFeedTopicSelectionAdapterListener) {
        topicSelectionAdapter = LMFeedTopicSelectionAdapter(listener)
        adapter = topicSelectionAdapter
    }

    //sets the pagination scroll listener to the topic selection recycler view
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

    //returns the list of all the topics in the adapter
    fun allTopics(): List<LMFeedBaseViewType> {
        return topicSelectionAdapter.items()
    }

    //adds all the provided [topics] to the adapter
    fun addAllTopics(topics: List<LMFeedBaseViewType>) {
        topicSelectionAdapter.addAll(topics)
    }

    //updated the topic at the provided [position]
    fun updateTopicAtIndex(index: Int, topic: LMFeedBaseViewType) {
        topicSelectionAdapter.update(index, topic)
    }

    //clears all the topics and notifies the recycler view
    fun clearAllTopicsAndNotify() {
        topicSelectionAdapter.clearAndNotify()
    }
}