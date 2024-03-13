package com.likeminds.feed.android.core.universalfeed.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.*
import com.likeminds.feed.android.core.topics.model.LMFeedTopicViewData
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalSelectedTopicAdapter
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalSelectedTopicAdapterListener
import com.likeminds.feed.android.core.utils.base.LMFeedBaseViewType

class LMFeedUniversalSelectedTopicListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    val linearLayoutManager: LinearLayoutManager

    private lateinit var selectedTopicAdapter: LMFeedUniversalSelectedTopicAdapter

    init {
        setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        layoutManager = linearLayoutManager

        if (itemAnimator is SimpleItemAnimator)
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
    }

    fun setAdapter(listener: LMFeedUniversalSelectedTopicAdapterListener) {
        selectedTopicAdapter = LMFeedUniversalSelectedTopicAdapter(listener)
        adapter = selectedTopicAdapter
    }

    fun allSelectedTopics(): List<LMFeedBaseViewType> {
        return selectedTopicAdapter.items()
    }

    fun replaceSelectedTopics(selectedTopics: List<LMFeedTopicViewData>) {
        selectedTopicAdapter.replace(selectedTopics)
    }

    fun removeSelectedTopicWithNotifying(position: Int) {
        selectedTopicAdapter.removeIndexWithNotifyDataSetChanged(position)
    }

    fun clearAllSelectedTopicsAndNotify() {
        selectedTopicAdapter.clearAndNotify()
    }
}