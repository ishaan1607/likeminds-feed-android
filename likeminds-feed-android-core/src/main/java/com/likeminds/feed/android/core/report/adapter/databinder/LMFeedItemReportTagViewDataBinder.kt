package com.likeminds.feed.android.core.report.adapter.databinder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.likeminds.feed.android.core.databinding.LmFeedItemReportTagBinding
import com.likeminds.feed.android.core.post.detail.adapter.LMFeedReplyAdapterListener
import com.likeminds.feed.android.core.report.model.LMFeedReportTagViewData
import com.likeminds.feed.android.core.utils.base.LMFeedViewDataBinder
import com.likeminds.feed.android.core.utils.base.model.ITEM_REPORT_TAG

class LMFeedItemReportTagViewDataBinder (
    private val listener: LMFeedReplyAdapterListener
) : LMFeedViewDataBinder<LmFeedItemReportTagBinding, LMFeedReportTagViewData>(){

    override val viewType: Int
        get() = ITEM_REPORT_TAG

    override fun createBinder(parent: ViewGroup): LmFeedItemReportTagBinding {
        val binding = LmFeedItemReportTagBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        setListeners(binding)

        return binding
    }

    override fun bindData(
        binding: LmFeedItemReportTagBinding,
        data: LMFeedReportTagViewData,
        position: Int
    ) {
        binding.apply {
            reportTagViewData = data
        }
    }

    private fun setListeners(binding: LmFeedItemReportTagBinding) {
        binding.apply {

        }
    }
}