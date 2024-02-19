package com.likeminds.feed.android.core.universalfeed.adapter.databinders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.likeminds.feed.android.core.databinding.LmFeedItemPostTextOnlyBinding
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalFeedAdapterListener
import com.likeminds.feed.android.core.universalfeed.model.LMFeedPostViewData
import com.likeminds.feed.android.core.universalfeed.util.LMFeedPostBinderUtils
import com.likeminds.feed.android.core.utils.base.LMFeedViewDataBinder
import com.likeminds.feed.android.core.utils.base.model.ITEM_POST_TEXT_ONLY

class LMFeedItemPostTextOnlyViewDataBinder(
    private val universalFeedAdapterListener: LMFeedUniversalFeedAdapterListener
) : LMFeedViewDataBinder<LmFeedItemPostTextOnlyBinding, LMFeedPostViewData>() {

    override val viewType: Int
        get() = ITEM_POST_TEXT_ONLY

    override fun createBinder(parent: ViewGroup): LmFeedItemPostTextOnlyBinding {
        val binding = LmFeedItemPostTextOnlyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        binding.apply {
            LMFeedPostBinderUtils.customizePostHeaderView(
                postHeader,
                universalFeedAdapterListener,
                user
            )

            LMFeedPostBinderUtils.customizePostContentView(
                tvPostContent,
                universalFeedAdapterListener,
                (postId ?: "")
            )

            LMFeedPostBinderUtils.customizePostFooterView(
                postFooter,
                universalFeedAdapterListener,
                (postId ?: ""),
                position
            )
        }

        return binding
    }

    override fun bindData(
        binding: LmFeedItemPostTextOnlyBinding,
        data: LMFeedPostViewData,
        position: Int
    ) {
        binding.apply {
            this.position = position
            postId = data.id
            user = data.headerViewData.user

            // updates the data in the post footer view
            LMFeedPostBinderUtils.setPostFooterViewData(
                postFooter,
                data.footerViewData
            )

            // checks whether to bind complete data or not and execute corresponding lambda function
            LMFeedPostBinderUtils.setPostBindData(
                postHeader,
                tvPostContent,
                data,
                position,
                universalFeedAdapterListener,
                returnBinder = {
                    return@setPostBindData
                }, executeBinder = {}
            )
        }
    }
}