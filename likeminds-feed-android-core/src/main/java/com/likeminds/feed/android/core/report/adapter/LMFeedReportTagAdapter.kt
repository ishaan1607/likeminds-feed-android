package com.likeminds.feed.android.core.report.adapter

import com.likeminds.feed.android.core.report.adapter.databinder.LMFeedItemReportTagViewDataBinder
import com.likeminds.feed.android.core.report.model.LMFeedReportTagViewData
import com.likeminds.feed.android.core.utils.base.*

class LMFeedReportTagAdapter(
    private val listener: LMFeedReportTagAdapterListener
) : LMFeedBaseRecyclerAdapter<LMFeedBaseViewType>() {

    init {
        initViewDataBinders()
    }

    override fun getSupportedViewDataBinder(): MutableList<LMFeedViewDataBinder<*, *>> {
        val viewDataBinders = ArrayList<LMFeedViewDataBinder<*, *>>(1)

        val itemReportTag = LMFeedItemReportTagViewDataBinder(listener)
        viewDataBinders.add(itemReportTag)

        return viewDataBinders
    }

}

interface LMFeedReportTagAdapterListener {
    fun onReportTagSelected(reportTagViewData: LMFeedReportTagViewData) {
        //triggered when a user selects a tag
    }
}