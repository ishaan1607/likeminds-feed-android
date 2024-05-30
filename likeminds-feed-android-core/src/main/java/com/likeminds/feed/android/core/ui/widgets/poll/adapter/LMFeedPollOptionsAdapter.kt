package com.likeminds.feed.android.core.ui.widgets.poll.adapter

import com.likeminds.feed.android.core.poll.result.model.LMFeedPollOptionViewData
import com.likeminds.feed.android.core.ui.widgets.poll.adapter.databinder.LMFeedItemPollOptionsViewDataBinder
import com.likeminds.feed.android.core.ui.widgets.poll.style.LMFeedPostPollOptionViewStyle
import com.likeminds.feed.android.core.utils.base.*

class LMFeedPollOptionsAdapter(
    private val pollPosition: Int,
    private val optionStyle: LMFeedPostPollOptionViewStyle?,
    private val pollOptionsAdapterListener: LMFeedPollOptionsAdapterListener?
) : LMFeedBaseRecyclerAdapter<LMFeedBaseViewType>() {

    init {
        initViewDataBinders()
    }

    override fun getSupportedViewDataBinder(): MutableList<LMFeedViewDataBinder<*, *>> {
        val viewDataBinders = ArrayList<LMFeedViewDataBinder<*, *>>(1)

        val itemPollOptionViewDataBinder =
            LMFeedItemPollOptionsViewDataBinder(
                pollPosition,
                optionStyle,
                pollOptionsAdapterListener
            )
        viewDataBinders.add(itemPollOptionViewDataBinder)

        return viewDataBinders
    }
}

interface LMFeedPollOptionsAdapterListener {

    fun onPollOptionClicked(
        pollPosition: Int,
        pollOptionPosition: Int,
        pollOptionViewData: LMFeedPollOptionViewData
    ) {
        //triggered when a poll option is clicked
    }

    fun onPollOptionVoteCountClicked(
        pollPosition: Int,
        pollOptionPosition: Int,
        pollOptionViewData: LMFeedPollOptionViewData
    ) {
        //triggered when a poll option vote count is clicked
    }
}