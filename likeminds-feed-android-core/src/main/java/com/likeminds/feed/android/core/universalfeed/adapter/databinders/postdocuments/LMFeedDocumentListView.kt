package com.likeminds.feed.android.core.universalfeed.adapter.databinders.postdocuments

import android.content.Context
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.*
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.post.model.LMFeedAttachmentViewData
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedDocumentsAdapter
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedDocumentsAdapterListener

class LMFeedDocumentListView(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int
) : RecyclerView(context, attrs, defStyleAttr),
    LMFeedDocumentsAdapterListener {

    private val linearLayoutManager: LinearLayoutManager
    private val dividerDecoration: DividerItemDecoration =
        DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

    private lateinit var adapter: LMFeedDocumentsAdapter

    init {
        setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        layoutManager = linearLayoutManager

        val dividerDrawable = ContextCompat.getDrawable(
            context,
            R.drawable.lm_feed_item_divider
        )
        dividerDrawable?.let { drawable ->
            dividerDecoration.setDrawable(drawable)
        }

        if (itemAnimator is SimpleItemAnimator)
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false

        addItemDecoration(dividerDecoration)
    }

    private fun initAdapter() {
        adapter
    }

    override fun onPostDocumentMediaClick(document: LMFeedAttachmentViewData) {
        onPostDocumentMediaClick(document)
    }
}