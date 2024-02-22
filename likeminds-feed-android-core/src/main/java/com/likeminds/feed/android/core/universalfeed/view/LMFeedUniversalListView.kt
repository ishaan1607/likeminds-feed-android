package com.likeminds.feed.android.core.universalfeed.view

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.*
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalFeedAdapter
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalFeedAdapterListener

class LMFeedUniversalListView(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int
) : RecyclerView(context, attrs, defStyleAttr) {

    private val linearLayoutManager: LinearLayoutManager
    private val dividerDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

    private lateinit var universalFeedAdapter: LMFeedUniversalFeedAdapter

    init {
        setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        layoutManager = linearLayoutManager

        if (itemAnimator is SimpleItemAnimator)
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

        //item decorator to add spacing between items
        val dividerDrawable = ContextCompat.getDrawable(
            context,
            R.drawable.lm_feed_item_divider
        )
        dividerDrawable?.let { drawable ->
            dividerDecoration.setDrawable(drawable)
        }
    }

    fun setAdapter(
        listener: LMFeedUniversalFeedAdapterListener
    ) {
        universalFeedAdapter = LMFeedUniversalFeedAdapter(listener)
        adapter = universalFeedAdapter
    }
}