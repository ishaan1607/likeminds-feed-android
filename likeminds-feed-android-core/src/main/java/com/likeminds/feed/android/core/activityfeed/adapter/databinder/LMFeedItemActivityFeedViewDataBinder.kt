package com.likeminds.feed.android.core.activityfeed.adapter.databinder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.likeminds.feed.android.core.activityfeed.adapter.LMFeedActivityFeedAdapterListener
import com.likeminds.feed.android.core.activityfeed.model.LMFeedActivityViewData
import com.likeminds.feed.android.core.databinding.LmFeedItemActivityFeedBinding
import com.likeminds.feed.android.core.utils.base.LMFeedViewDataBinder
import com.likeminds.feed.android.core.utils.base.model.ITEM_ACTIVITY_FEED

class LMFeedItemActivityFeedViewDataBinder(
    private val listener: LMFeedActivityFeedAdapterListener
) : LMFeedViewDataBinder<LmFeedItemActivityFeedBinding, LMFeedActivityViewData>() {

    companion object {
        private const val MAX_LINES = 3
    }

    override val viewType: Int
        get() = ITEM_ACTIVITY_FEED

    override fun createBinder(parent: ViewGroup): LmFeedItemActivityFeedBinding {
        val binding = LmFeedItemActivityFeedBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return binding
    }

    override fun bindData(
        binding: LmFeedItemActivityFeedBinding,
        data: LMFeedActivityViewData,
        position: Int
    ) {
        TODO("Not yet implemented")
    }
}