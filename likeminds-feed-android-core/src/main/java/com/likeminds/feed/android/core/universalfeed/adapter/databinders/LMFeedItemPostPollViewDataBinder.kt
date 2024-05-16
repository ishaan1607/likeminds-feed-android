package com.likeminds.feed.android.core.universalfeed.adapter.databinders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.likeminds.feed.android.core.databinding.LmFeedItemPostPollBinding
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalFeedAdapterListener
import com.likeminds.feed.android.core.universalfeed.model.LMFeedPostViewData
import com.likeminds.feed.android.core.universalfeed.util.LMFeedPostBinderUtils
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer
import com.likeminds.feed.android.core.utils.base.LMFeedViewDataBinder
import com.likeminds.feed.android.core.utils.base.model.ITEM_POST_POLL

class LMFeedItemPostPollViewDataBinder(
    private val universalFeedAdapterListener: LMFeedUniversalFeedAdapterListener
) : LMFeedViewDataBinder<LmFeedItemPostPollBinding, LMFeedPostViewData>() {

    override val viewType: Int
        get() = ITEM_POST_POLL

    override fun createBinder(parent: ViewGroup): LmFeedItemPostPollBinding {
        val binding = LmFeedItemPostPollBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        binding.apply {
            LMFeedPostBinderUtils.customizePostHeaderView(postHeader)

            LMFeedPostBinderUtils.customizePostContentView(tvPostContent)

            LMFeedPostBinderUtils.customizePostFooterView(postFooter)

            LMFeedPostBinderUtils.customizePostTopicsGroup(postTopicsGroup)

            setClickListeners(this)

            //sets poll media style to the poll view
            val postPollMediaViewStyle =
                LMFeedStyleTransformer.postViewStyle.postMediaViewStyle.postPollMediaStyle
                    ?: return@apply

            postPollView.setStyle(postPollMediaViewStyle)
        }

        return binding
    }

    override fun bindData(
        binding: LmFeedItemPostPollBinding,
        data: LMFeedPostViewData,
        position: Int
    ) {
        binding.apply {
            // set variables in the binding
            this.position = position
            postViewData = data

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
                postTopicsGroup,
                universalFeedAdapterListener,
                returnBinder = {
                    return@setPostBindData
                }, executeBinder = {
                    //sets the post poll media view
                    LMFeedPostBinderUtils.bindPostPollMediaView(
                        postPollView,
                        data.mediaViewData
                    )
                }
            )
        }
    }

    private fun setClickListeners(binding: LmFeedItemPostPollBinding) {
        binding.apply {

        }
    }
}