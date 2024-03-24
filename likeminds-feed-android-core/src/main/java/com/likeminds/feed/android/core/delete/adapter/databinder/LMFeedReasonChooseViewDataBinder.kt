package com.likeminds.feed.android.core.delete.adapter.databinder

import android.view.LayoutInflater
import android.view.ViewGroup
import com.likeminds.feed.android.core.databinding.LmFeedItemReasonChooseBinding
import com.likeminds.feed.android.core.delete.adapter.LMFeedReasonChooseAdapterListener
import com.likeminds.feed.android.core.delete.model.LMFeedReasonChooseViewData
import com.likeminds.feed.android.core.ui.base.styles.setStyle
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer
import com.likeminds.feed.android.core.utils.base.LMFeedViewDataBinder
import com.likeminds.feed.android.core.utils.base.model.ITEM_REASON_CHOOSE

class LMFeedReasonChooseViewDataBinder(
    private val listener: LMFeedReasonChooseAdapterListener
) : LMFeedViewDataBinder<LmFeedItemReasonChooseBinding, LMFeedReasonChooseViewData>() {

    override val viewType: Int
        get() = ITEM_REASON_CHOOSE

    override fun createBinder(parent: ViewGroup): LmFeedItemReasonChooseBinding {
        val binding = LmFeedItemReasonChooseBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        binding.apply {
            val adminDeleteDialogFragmentStyle =
                LMFeedStyleTransformer.adminDeleteDialogFragmentStyle

            val reasonTextStyle = adminDeleteDialogFragmentStyle.reasonTextStyle
            tvTitle.setStyle(reasonTextStyle)

            val selectorStyle = adminDeleteDialogFragmentStyle.selectorIconStyle
            ivSelector.setStyle(selectorStyle)

            setClickListeners(this)
        }

        return binding
    }

    override fun bindData(
        binding: LmFeedItemReasonChooseBinding,
        data: LMFeedReasonChooseViewData,
        position: Int
    ) {
        binding.apply {
            this.position = position
            reasonChooseData = data
        }
    }

    private fun setClickListeners(binding: LmFeedItemReasonChooseBinding) {
        binding.apply {
            root.setOnClickListener {
                val viewData = reasonChooseData ?: return@setOnClickListener
                listener.onReasonSelected(position, viewData)
            }
        }
    }
}