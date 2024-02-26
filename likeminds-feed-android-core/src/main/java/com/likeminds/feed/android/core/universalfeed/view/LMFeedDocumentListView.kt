package com.likeminds.feed.android.core.universalfeed.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.*
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.views.LMFeedTextView
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedDocumentsAdapter
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalFeedAdapterListener
import com.likeminds.feed.android.core.universalfeed.model.LMFeedMediaViewData
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.hide
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.show

class LMFeedDocumentListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val linearLayoutManager: LinearLayoutManager
    private val dividerDecoration: DividerItemDecoration =
        DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

    private lateinit var documentsAdapter: LMFeedDocumentsAdapter

    init {
        setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        layoutManager = linearLayoutManager

        //item decorator to add spacing between items
        val dividerDrawable = ContextCompat.getDrawable(
            context,
            R.drawable.lm_feed_document_item_divider
        )
        dividerDrawable?.let { drawable ->
            dividerDecoration.setDrawable(drawable)
        }

        // if separator is not there already, then only add
        if (itemDecorationCount < 1) {
            addItemDecoration(dividerDecoration)
        }
    }

    fun setAdapter(
        mediaViewData: LMFeedMediaViewData,
        tvShowMore: LMFeedTextView,
        listener: LMFeedUniversalFeedAdapterListener
    ) {
        documentsAdapter = LMFeedDocumentsAdapter(listener)
        adapter = documentsAdapter
        handleVisibleDocuments(mediaViewData, tvShowMore)
    }

    private fun handleVisibleDocuments(
        mediaViewData: LMFeedMediaViewData,
        tvShowMore: LMFeedTextView,
    ) {
        val postDocumentsMediaStyle =
            LMFeedStyleTransformer.postViewStyle.postMediaStyle.postDocumentsMediaStyle ?: return

        val visibleDocumentsLimit = postDocumentsMediaStyle.visibleDocumentsLimit

        Log.d("PUI", "handleVisibleDocuments: $visibleDocumentsLimit")

        val documents = mediaViewData.attachments

        if (mediaViewData.isExpanded || documents.size <= visibleDocumentsLimit) {
            Log.d("PUI", "isExpanded: ${documents.size}")
            tvShowMore.hide()
            documentsAdapter.replace(documents)
        } else {
            Log.d("PUI", "isExpanded not: ${documents.size}")
            tvShowMore.show()
            "+${documents.size - visibleDocumentsLimit} more".also { tvShowMore.text = it }
            documentsAdapter.replace(documents.take(visibleDocumentsLimit))
        }

        //if show more style is not provided then we will not show this view
        postDocumentsMediaStyle.documentShowMoreStyle ?: tvShowMore.hide()
    }
}