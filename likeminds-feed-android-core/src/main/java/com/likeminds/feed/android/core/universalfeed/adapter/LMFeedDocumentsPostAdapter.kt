package com.likeminds.feed.android.core.universalfeed.adapter

import com.likeminds.feed.android.core.post.model.LMFeedAttachmentViewData
import com.likeminds.feed.android.core.universalfeed.adapter.databinders.postdocuments.LMFeedItemDocumentViewDataBinder
import com.likeminds.feed.android.core.utils.base.*

class LMFeedDocumentsAdapter(
    private val documentsAdapterListener: LMFeedDocumentsPostAdapterListener
) : LMFeedBaseRecyclerAdapter<LMFeedBaseViewType>() {

    init {
        initViewDataBinders()
    }

    override fun getSupportedViewDataBinder(): MutableList<LMFeedViewDataBinder<*, *>> {
        val viewDataBinders = ArrayList<LMFeedViewDataBinder<*, *>>(1)

        val documentsBinder = LMFeedItemDocumentViewDataBinder(documentsAdapterListener)
        viewDataBinders.add(documentsBinder)

        return viewDataBinders
    }
}

interface LMFeedDocumentsPostAdapterListener {

    //triggered when the document media in the post is clicked
    fun onPostDocumentMediaClick(document: LMFeedAttachmentViewData)
}