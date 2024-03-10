package com.likeminds.feed.android.core.report.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.google.android.flexbox.*
import com.likeminds.feed.android.core.report.adapter.LMFeedReportTagAdapter
import com.likeminds.feed.android.core.report.adapter.LMFeedReportTagAdapterListener
import com.likeminds.feed.android.core.report.model.LMFeedReportTagViewData
import com.likeminds.feed.android.core.utils.base.LMFeedBaseViewType

class LMFeedReportTagListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private lateinit var reportTagAdapter: LMFeedReportTagAdapter

    init {
        setHasFixedSize(true)

        //set flexbox layout manager to the tag view
        val flexboxLayoutManager = FlexboxLayoutManager(context)
        flexboxLayoutManager.flexDirection = FlexDirection.ROW
        flexboxLayoutManager.justifyContent = JustifyContent.FLEX_START

        layoutManager = flexboxLayoutManager

        if (itemAnimator is SimpleItemAnimator)
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
    }

    fun setAdapter(listener: LMFeedReportTagAdapterListener) {
        reportTagAdapter = LMFeedReportTagAdapter(listener)
        adapter = reportTagAdapter
    }

    fun replaceReportTags(reportTags: List<LMFeedReportTagViewData>) {
        reportTagAdapter.replace(reportTags)
    }

    fun items(): List<LMFeedBaseViewType> {
        return reportTagAdapter.items()
    }
}