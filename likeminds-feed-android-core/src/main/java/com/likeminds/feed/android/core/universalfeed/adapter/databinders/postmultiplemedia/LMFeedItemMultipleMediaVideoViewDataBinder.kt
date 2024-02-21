package com.likeminds.feed.android.core.universalfeed.adapter.databinders.postmultiplemedia

import android.view.LayoutInflater
import android.view.ViewGroup
import com.likeminds.feed.android.core.databinding.LmFeedItemMultipleMediaVideoBinding
import com.likeminds.feed.android.core.post.model.LMFeedAttachmentViewData
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalFeedAdapterListener
import com.likeminds.feed.android.core.universalfeed.util.LMFeedPostBinderUtils
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer
import com.likeminds.feed.android.core.utils.base.LMFeedViewDataBinder
import com.likeminds.feed.android.core.utils.base.model.ITEM_MULTIPLE_MEDIA_IMAGE

class LMFeedItemMultipleMediaVideoViewDataBinder(
    val listener: LMFeedUniversalFeedAdapterListener
) : LMFeedViewDataBinder<LmFeedItemMultipleMediaVideoBinding, LMFeedAttachmentViewData>() {

    override val viewType: Int
        get() = ITEM_MULTIPLE_MEDIA_IMAGE

    override fun createBinder(parent: ViewGroup): LmFeedItemMultipleMediaVideoBinding {
        val binding = LmFeedItemMultipleMediaVideoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        binding.apply {
            postVideoView.setOnClickListener {
                listener.onPostMultipleMediaVideoClick(video)
            }

            //sets video media style to multiple media video view
            val postVideoMediaStyle =
                LMFeedStyleTransformer.postViewStyle.postMediaStyle.postVideoMediaStyle
                    ?: return@apply

            postVideoView.setStyle(postVideoMediaStyle)
        }

        return binding
    }

    override fun bindData(
        binding: LmFeedItemMultipleMediaVideoBinding,
        data: LMFeedAttachmentViewData,
        position: Int
    ) {
        binding.apply {
            //set data to the binding
            video = data
        }
    }
}