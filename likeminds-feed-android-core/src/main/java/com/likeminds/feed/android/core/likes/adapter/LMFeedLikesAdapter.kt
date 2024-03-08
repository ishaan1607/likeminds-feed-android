package com.likeminds.feed.android.core.likes.adapter

import com.likeminds.feed.android.core.likes.adapter.databinder.LMFeedItemLikesViewDataBinder
import com.likeminds.feed.android.core.likes.model.LMFeedLikeViewData
import com.likeminds.feed.android.core.utils.base.*

class LMFeedLikesAdapter(
    private val listener: LMFeedLikesAdapterListener
) : LMFeedBaseRecyclerAdapter<LMFeedBaseViewType>() {

    init {
        initViewDataBinders()
    }

    override fun getSupportedViewDataBinder(): MutableList<LMFeedViewDataBinder<*, *>> {
        val viewDataBinders = ArrayList<LMFeedViewDataBinder<*, *>>(1)

        val itemLikesViewDataBinder = LMFeedItemLikesViewDataBinder(listener)
        viewDataBinders.add(itemLikesViewDataBinder)

        return viewDataBinders
    }
}

interface LMFeedLikesAdapterListener {
    fun onUserLikeItemClicked(position: Int, likesViewData: LMFeedLikeViewData) {
        //triggered when the user clicks on the like item
    }
}