package com.likeminds.feed.android.core.ui.widgets.poll.adapter

import com.likeminds.feed.android.core.poll.result.model.LMFeedPollOptionViewData
import com.likeminds.feed.android.core.ui.widgets.poll.adapter.databinder.LMFeedItemPollOptionsViewDataBinder
import com.likeminds.feed.android.core.utils.base.*

class LMFeedPollOptionsAdapter(
    private val pollOptionsAdapterListener: LMFeedPollOptionsAdapterListener?
) : LMFeedBaseRecyclerAdapter<LMFeedBaseViewType>() {

    init {
        initViewDataBinders()
    }

    override fun getSupportedViewDataBinder(): MutableList<LMFeedViewDataBinder<*, *>> {
        val viewDataBinders = ArrayList<LMFeedViewDataBinder<*, *>>(1)

        val itemPollOptionViewDataBinder =
            LMFeedItemPollOptionsViewDataBinder(pollOptionsAdapterListener)
        viewDataBinders.add(itemPollOptionViewDataBinder)

        return viewDataBinders
    }
}

interface LMFeedPollOptionsAdapterListener {

    fun onPollOptionClicked(position: Int, pollOptionViewData: LMFeedPollOptionViewData) {
        //triggered when a poll option is clicked
    }

    fun onPollOptionVoteCountClicked(position: Int, pollOptionViewData: LMFeedPollOptionViewData) {
        //triggered when a poll option vote count is clicked
    }
}