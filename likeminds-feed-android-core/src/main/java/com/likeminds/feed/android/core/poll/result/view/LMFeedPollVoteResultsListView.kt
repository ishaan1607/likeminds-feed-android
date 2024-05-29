package com.likeminds.feed.android.core.poll.result.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.*
import com.likeminds.feed.android.core.poll.result.adapter.LMFeedPollVoteResultsAdapter
import com.likeminds.feed.android.core.poll.result.adapter.LMFeedPollVoteResultsAdapterListener
import com.likeminds.feed.android.core.ui.widgets.poll.style.LMFeedPostPollViewStyle
import com.likeminds.feed.android.core.utils.LMFeedEndlessRecyclerViewScrollListener
import com.likeminds.feed.android.core.utils.user.LMFeedUserViewData

/**
 * Represents a recycler view with list of votes on the poll
 */
class LMFeedPollVoteResultsListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    val linearLayoutManager: LinearLayoutManager

    private lateinit var pollVoteResultsAdapter: LMFeedPollVoteResultsAdapter
    private lateinit var paginationScrollListener: LMFeedEndlessRecyclerViewScrollListener

    init {
        setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        layoutManager = linearLayoutManager

        if (itemAnimator is SimpleItemAnimator) {
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        }
    }

    //sets the adapter with the provided [listener] to the poll results recycler view
    fun setAdapter(pollVoteResultsAdapterListener: LMFeedPollVoteResultsAdapterListener) {
        pollVoteResultsAdapter = LMFeedPollVoteResultsAdapter(pollVoteResultsAdapterListener)
        adapter = pollVoteResultsAdapter
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

    //adds the provided [pollVotes] in the poll results adapter
    fun addPollVotes(pollVotes: List<LMFeedUserViewData>) {
        pollVoteResultsAdapter.addAll(pollVotes)
    }

    //replaces the provided [pollVotes] in the poll results adapter
    fun replacePollVotes(pollVotes: List<LMFeedUserViewData>) {
        pollVoteResultsAdapter.replace(pollVotes)
    }
}