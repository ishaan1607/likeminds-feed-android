package com.likeminds.feed.android.core.universalfeed.adapter.databinders.postmultiplemedia

import android.view.LayoutInflater
import android.view.ViewGroup
import com.likeminds.feed.android.core.databinding.LmFeedItemMultipleMediaVideoBinding
import com.likeminds.feed.android.core.post.model.LMFeedAttachmentViewData
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalFeedAdapterListener
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer
import com.likeminds.feed.android.core.utils.base.LMFeedViewDataBinder
import com.likeminds.feed.android.core.utils.base.model.ITEM_MULTIPLE_MEDIA_VIDEO

class LMFeedItemMultipleMediaVideoViewDataBinder(
    private val parentPosition: Int,
    private val listener: LMFeedUniversalFeedAdapterListener
) : LMFeedViewDataBinder<LmFeedItemMultipleMediaVideoBinding, LMFeedAttachmentViewData>() {

    override val viewType: Int
        get() = ITEM_MULTIPLE_MEDIA_VIDEO

    override fun createBinder(parent: ViewGroup): LmFeedItemMultipleMediaVideoBinding {
        val binding = LmFeedItemMultipleMediaVideoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        binding.apply {
            setClickListeners(this)

            //sets video media style to multiple media video view
            val postVideoMediaStyle =
                LMFeedStyleTransformer.postViewStyle.postMediaViewStyle.postVideoMediaStyle
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
            this.position = position
        }
    }

    private fun setClickListeners(binding: LmFeedItemMultipleMediaVideoBinding) {
        binding.apply {
            postVideoView.setOnClickListener {
                listener.onPostMultipleMediaVideoClicked(position, parentPosition)
            }
        }
    }
}