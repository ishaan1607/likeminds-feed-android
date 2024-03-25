package com.likeminds.feed.android.core.topicselection.adapter.databinder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.likeminds.feed.android.core.databinding.LmFeedItemTopicBinding
import com.likeminds.feed.android.core.topics.model.LMFeedTopicViewData
import com.likeminds.feed.android.core.topicselection.adapter.LMFeedTopicSelectionAdapterListener
import com.likeminds.feed.android.core.ui.base.styles.setStyle
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer
import com.likeminds.feed.android.core.utils.base.LMFeedViewDataBinder
import com.likeminds.feed.android.core.utils.base.model.ITEM_TOPIC

class LMFeedItemTopicViewDataBinder(
    private val listener: LMFeedTopicSelectionAdapterListener
) : LMFeedViewDataBinder<LmFeedItemTopicBinding, LMFeedTopicViewData>() {

    override val viewType: Int
        get() = ITEM_TOPIC

    override fun createBinder(parent: ViewGroup): LmFeedItemTopicBinding {
        val binding = LmFeedItemTopicBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        binding.apply {
            setListeners(this)

            //set style
            tvTopicName.setStyle(LMFeedStyleTransformer.topicSelectionFragmentViewStyle.topicItemStyle)

            return this
        }
    }

    override fun bindData(
        binding: LmFeedItemTopicBinding,
        data: LMFeedTopicViewData,
        position: Int
    ) {
        binding.apply {
            this.position = position
            topicViewData = data

            //set topic name
            tvTopicName.text = data.name

            val style = LMFeedStyleTransformer.topicSelectionFragmentViewStyle.topicItemStyle

            //sets or remove drawables when the topic is selected or unselected
            if (data.isSelected) {
                tvTopicName.setCompoundDrawablesWithIntrinsicBounds(
                    style.drawableLeftSrc ?: 0,
                    style.drawableTopSrc ?: 0,
                    style.drawableRightSrc ?: 0,
                    style.drawableBottomSrc ?: 0
                )
            } else {
                tvTopicName.clearDrawables()
            }
        }
    }

    private fun setListeners(binding: LmFeedItemTopicBinding) {
        binding.apply {
            root.setOnClickListener {
                val topicViewData = topicViewData ?: return@setOnClickListener
                listener.onTopicSelected(position, topicViewData)
            }
        }
    }
}