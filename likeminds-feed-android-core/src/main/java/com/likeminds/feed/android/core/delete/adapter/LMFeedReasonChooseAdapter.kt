package com.likeminds.feed.android.core.delete.adapter

import com.likeminds.feed.android.core.delete.adapter.databinder.LMFeedReasonChooseViewDataBinder
import com.likeminds.feed.android.core.delete.model.LMFeedReasonChooseViewData
import com.likeminds.feed.android.core.utils.base.*

class LMFeedReasonChooseAdapter (
    private val listener: LMFeedReasonChooseAdapterListener
) : LMFeedBaseRecyclerAdapter<LMFeedBaseViewType>() {

    init {
        initViewDataBinders()
    }

    override fun getSupportedViewDataBinder(): MutableList<LMFeedViewDataBinder<*, *>> {
        val viewDataBinders = ArrayList<LMFeedViewDataBinder<*, *>>(1)

        val reasonChooseViewDataBinder = LMFeedReasonChooseViewDataBinder(listener)
        viewDataBinders.add(reasonChooseViewDataBinder)

        return viewDataBinders
    }
}

interface LMFeedReasonChooseAdapterListener {
    fun onReasonSelected(position: Int, reasonViewData: LMFeedReasonChooseViewData)
}