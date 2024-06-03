package com.likeminds.feed.android.core.poll.result.adapter

import com.likeminds.feed.android.core.poll.result.adapter.databinder.LMFeedItemPollVoteResultsViewDataBinder
import com.likeminds.feed.android.core.utils.base.*
import com.likeminds.feed.android.core.utils.user.LMFeedUserViewData

class LMFeedPollVoteResultsAdapter(
    private val listener: LMFeedPollVoteResultsAdapterListener
) : LMFeedBaseRecyclerAdapter<LMFeedBaseViewType>() {

    init {
        initViewDataBinders()
    }

    override fun getSupportedViewDataBinder(): MutableList<LMFeedViewDataBinder<*, *>> {
        val viewDataBinders = ArrayList<LMFeedViewDataBinder<*, *>>(1)

        val itemPollResultViewDataBinder = LMFeedItemPollVoteResultsViewDataBinder(listener)
        viewDataBinders.add(itemPollResultViewDataBinder)

        return viewDataBinders
    }
}

interface LMFeedPollVoteResultsAdapterListener {
    fun onPollVoteResultItemClicked(position: Int, pollVoteResultItem: LMFeedUserViewData) {
        //triggered when a poll result item is clicked
    }
}