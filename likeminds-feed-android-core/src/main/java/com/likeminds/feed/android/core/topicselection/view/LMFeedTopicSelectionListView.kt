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

    fun setAdapter(listener: LMFeedTopicSelectionAdapterListener) {
        topicSelectionAdapter = LMFeedTopicSelectionAdapter(listener)
        adapter = topicSelectionAdapter
    }

    fun setPaginationScrollListener(scrollListener: LMFeedEndlessRecyclerViewScrollListener) {
        paginationScrollListener = scrollListener
        addOnScrollListener(scrollListener)
    }

    fun allItems(): List<LMFeedBaseViewType> {
        return topicSelectionAdapter.items()
    }

    fun addAllItems(items: List<LMFeedBaseViewType>) {
        topicSelectionAdapter.addAll(items)
    }

    fun updateItem(position: Int, item: LMFeedBaseViewType) {
        topicSelectionAdapter.update(position, item)
    }

    fun clearAllItemsAndNotify() {
        topicSelectionAdapter.clearAndNotify()
    }
}