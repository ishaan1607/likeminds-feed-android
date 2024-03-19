package com.likeminds.feed.android.core.universalfeed.adapter.databinders.postdocuments

import android.view.LayoutInflater
import android.view.ViewGroup
import com.likeminds.feed.android.core.databinding.LmFeedItemDocumentBinding
import com.likeminds.feed.android.core.post.model.LMFeedAttachmentViewData
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalFeedAdapterListener
import com.likeminds.feed.android.core.universalfeed.util.LMFeedPostBinderUtils
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer
import com.likeminds.feed.android.core.utils.base.LMFeedViewDataBinder
import com.likeminds.feed.android.core.utils.base.model.ITEM_POST_DOCUMENTS_ITEM

class LMFeedItemDocumentViewDataBinder(
    private val parentPosition: Int,
    private val universalFeedAdapter: LMFeedUniversalFeedAdapterListener
) : LMFeedViewDataBinder<LmFeedItemDocumentBinding, LMFeedAttachmentViewData>() {

    override val viewType: Int
        get() = ITEM_POST_DOCUMENTS_ITEM

    override fun createBinder(parent: ViewGroup): LmFeedItemDocumentBinding {
        val binding = LmFeedItemDocumentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        binding.apply {
            //sets click listener to the documents item
            setClickListeners(binding)

            val postDocumentMediaStyle =
                LMFeedStyleTransformer.postViewStyle.postMediaStyle.postDocumentsMediaStyle
                    ?: return@apply

            documentItem.setStyle(postDocumentMediaStyle)
        }

        return binding
    }

    override fun bindData(
        binding: LmFeedItemDocumentBinding,
        data: LMFeedAttachmentViewData,
        position: Int
    ) {
        binding.apply {
            //sets variables in the binding
            this.position = position

            //sets data on the documents view
            LMFeedPostBinderUtils.bindPostMediaDocument(
                documentItem,
                data
            )
        }
    }

    private fun setClickListeners(binding: LmFeedItemDocumentBinding) {
        binding.apply {
            documentItem.setDocumentClickListener {
                universalFeedAdapter.onPostDocumentMediaClick(position, parentPosition)
            }
        }
    }
}