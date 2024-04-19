package com.likeminds.feed.android.core.topicselection.adapter.databinder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.likeminds.feed.android.core.databinding.LmFeedItemAllTopicsBinding
import com.likeminds.feed.android.core.topicselection.adapter.LMFeedTopicSelectionAdapterListener
import com.likeminds.feed.android.core.topicselection.model.LMFeedAllTopicsViewData
import com.likeminds.feed.android.core.ui.base.styles.setStyle
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer
import com.likeminds.feed.android.core.utils.base.LMFeedViewDataBinder
import com.likeminds.feed.android.core.utils.base.model.ITEM_ALL_TOPICS

class LMFeedItemAllTopicsViewDataBinder(
    private val listener: LMFeedTopicSelectionAdapterListener
) : LMFeedViewDataBinder<LmFeedItemAllTopicsBinding, LMFeedAllTopicsViewData>() {

    override val viewType: Int
        get() = ITEM_ALL_TOPICS

    override fun createBinder(parent: ViewGroup): LmFeedItemAllTopicsBinding {
        val binding = LmFeedItemAllTopicsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        binding.apply {
            setListeners(this)

            //set style
            tvAllTopics.setStyle(LMFeedStyleTransformer.topicSelectionFragmentViewStyle.topicItemStyle)

            return this
        }
    }

    override fun bindData(
        binding: LmFeedItemAllTopicsBinding,
        data: LMFeedAllTopicsViewData,
        position: Int
    ) {
        //set data in binding
        binding.apply {
            this.position = position
            allTopicsViewData = data

            val style = LMFeedStyleTransformer.topicSelectionFragmentViewStyle.topicItemStyle

            //sets or remove drawables when the topic is selected or unselected
            if (data.isSelected) {
                tvAllTopics.setCompoundDrawablesWithIntrinsicBounds(
                    style.drawableLeftSrc ?: 0,
                    style.drawableTopSrc ?: 0,
                    style.drawableRightSrc ?: 0,
                    style.drawableBottomSrc ?: 0
                )
            } else {
                tvAllTopics.clearDrawables()
            }
        }
    }

    //set click listeners to the view data binder
    private fun setListeners(binding: LmFeedItemAllTopicsBinding) {
        binding.apply {
            root.setOnClickListener {
                val allTopicsViewData = allTopicsViewData ?: return@setOnClickListener
                listener.onAllTopicsSelected(position, allTopicsViewData)
            }
        }
    }
}