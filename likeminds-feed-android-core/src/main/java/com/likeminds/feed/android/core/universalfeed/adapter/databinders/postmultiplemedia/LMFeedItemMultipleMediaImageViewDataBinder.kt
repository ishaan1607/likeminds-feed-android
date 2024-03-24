package com.likeminds.feed.android.core.universalfeed.adapter.databinders.postmultiplemedia

import android.view.LayoutInflater
import android.view.ViewGroup
import com.likeminds.feed.android.core.databinding.LmFeedItemMultipleMediaImageBinding
import com.likeminds.feed.android.core.post.model.LMFeedAttachmentViewData
import com.likeminds.feed.android.core.ui.base.styles.setStyle
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalFeedAdapterListener
import com.likeminds.feed.android.core.universalfeed.util.LMFeedPostBinderUtils
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer
import com.likeminds.feed.android.core.utils.base.LMFeedViewDataBinder
import com.likeminds.feed.android.core.utils.base.model.ITEM_MULTIPLE_MEDIA_IMAGE

class LMFeedItemMultipleMediaImageViewDataBinder(
    private val parentPosition: Int,
    private val listener: LMFeedUniversalFeedAdapterListener
) : LMFeedViewDataBinder<LmFeedItemMultipleMediaImageBinding, LMFeedAttachmentViewData>() {

    override val viewType: Int
        get() = ITEM_MULTIPLE_MEDIA_IMAGE

    override fun createBinder(parent: ViewGroup): LmFeedItemMultipleMediaImageBinding {
        val binding = LmFeedItemMultipleMediaImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        binding.apply {
            setClickListeners(this)

            //sets image media style to multiple media image view
            val postImageMediaStyle =
                LMFeedStyleTransformer.postViewStyle.postMediaViewStyle.postImageMediaStyle
                    ?: return@apply

            ivPost.setStyle(postImageMediaStyle)
        }

        return binding
    }

    override fun bindData(
        binding: LmFeedItemMultipleMediaImageBinding,
        data: LMFeedAttachmentViewData,
        position: Int
    ) {
        binding.apply {
            //set data to the binding
            this.position = position
            this.attachmentViewData = data

            // loads post image inside the multiple media image view
            LMFeedPostBinderUtils.bindMultipleMediaImageView(ivPost, data)
        }
    }

    private fun setClickListeners(binding: LmFeedItemMultipleMediaImageBinding) {
        binding.apply {
            ivPost.setOnClickListener {
                val attachment = attachmentViewData ?: return@setOnClickListener
                listener.onPostMultipleMediaImageClicked(
                    position,
                    parentPosition,
                    attachment
                )
            }
        }
    }
}