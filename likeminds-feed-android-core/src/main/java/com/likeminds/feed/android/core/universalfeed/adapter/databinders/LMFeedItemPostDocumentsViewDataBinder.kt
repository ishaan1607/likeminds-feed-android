package com.likeminds.feed.android.core.universalfeed.adapter.databinders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalFeedAdapterListener
import com.likeminds.feed.android.core.universalfeed.model.LMFeedPostViewData
import com.likeminds.feed.android.core.universalfeed.util.LMFeedPostBinderUtils
import com.likeminds.feed.android.core.util.base.LMFeedViewDataBinder
import com.likeminds.feed.android.core.util.base.model.ITEM_POST_DOCUMENTS
import com.likeminds.feed.android.integration.databinding.LmFeedItemPostDocumentsBinding

class LMFeedItemPostDocumentsViewDataBinder(
    private val universalFeedAdapterListener: LMFeedUniversalFeedAdapterListener
) : LMFeedViewDataBinder<LmFeedItemPostDocumentsBinding, LMFeedPostViewData>() {

    override val viewType: Int
        get() = ITEM_POST_DOCUMENTS

    override fun createBinder(parent: ViewGroup): LmFeedItemPostDocumentsBinding {
        val binding = LmFeedItemPostDocumentsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        binding.apply {
            LMFeedPostBinderUtils.customizePostHeaderView(
                postHeader,
                universalFeedAdapterListener
            )

            LMFeedPostBinderUtils.customizePostContentView(
                tvPostContent,
                universalFeedAdapterListener
            )

            LMFeedPostBinderUtils.customizePostFooterView(
                postFooter,
                universalFeedAdapterListener,
                postId,
                position
            )
        }

        return binding
    }

    override fun bindData(
        binding: LmFeedItemPostDocumentsBinding,
        data: LMFeedPostViewData,
        position: Int
    ) {
        // updates the data in the post footer view
        LMFeedPostBinderUtils.setPostFooterViewData(
            binding.postFooter,
            data.footerViewData
        )

        // checks whether to bind complete data or not and execute corresponding lambda function
        LMFeedPostBinderUtils.setPostBindData(
            binding.postHeader,
            binding.tvPostContent,
            data,
            position,
            universalFeedAdapterListener,
            returnBinder = {
                return@setPostBindData
            }, executeBinder = {
                // todo: initialize documents view here
            }
        )
    }
}