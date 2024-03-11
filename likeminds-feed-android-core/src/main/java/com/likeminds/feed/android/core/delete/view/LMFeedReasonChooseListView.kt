package com.likeminds.feed.android.core.delete.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.*
import com.likeminds.feed.android.core.delete.adapter.LMFeedReasonChooseAdapter
import com.likeminds.feed.android.core.delete.adapter.LMFeedReasonChooseAdapterListener
import com.likeminds.feed.android.core.delete.model.LMFeedReasonChooseViewData

class LMFeedReasonChooseListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    val linearLayoutManager: LinearLayoutManager

    private lateinit var reasonChooseAdapter: LMFeedReasonChooseAdapter

    init {
        setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        layoutManager = linearLayoutManager

        if (itemAnimator is SimpleItemAnimator)
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
    }

    fun setAdapter(listener: LMFeedReasonChooseAdapterListener) {
        reasonChooseAdapter = LMFeedReasonChooseAdapter(listener)
        adapter = reasonChooseAdapter
    }

    fun replaceReasons(reasons: List<LMFeedReasonChooseViewData>) {
        reasonChooseAdapter.replace(reasons)
    }
}