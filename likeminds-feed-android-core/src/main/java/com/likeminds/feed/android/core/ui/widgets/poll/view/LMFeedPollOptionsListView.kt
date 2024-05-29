package com.likeminds.feed.android.core.ui.widgets.poll.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.*
import com.likeminds.feed.android.core.poll.result.model.LMFeedPollOptionViewData
import com.likeminds.feed.android.core.ui.widgets.poll.adapter.LMFeedPollOptionsAdapter
import com.likeminds.feed.android.core.ui.widgets.poll.adapter.LMFeedPollOptionsAdapterListener

/**
 * Represents a recycler view with list of options in the poll
 */
class LMFeedPollOptionsListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    val linearLayoutManager: LinearLayoutManager

    private lateinit var pollOptionsAdapter: LMFeedPollOptionsAdapter

    init {
        setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        layoutManager = linearLayoutManager

        if (itemAnimator is SimpleItemAnimator) {
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        }
    }

    //sets the adapter with the provided [listener] to the poll options recycler view
    fun setAdapter(pollPosition: Int, listener: LMFeedPollOptionsAdapterListener?) {
        //setting adapter
        pollOptionsAdapter = LMFeedPollOptionsAdapter(pollPosition, listener)
        adapter = pollOptionsAdapter
    }

    //replaces the [pollOptions] in the poll options adapter with the provided poll options
    fun replacePollOptions(pollOptions: List<LMFeedPollOptionViewData>) {
        pollOptionsAdapter.replace(pollOptions)
    }
}