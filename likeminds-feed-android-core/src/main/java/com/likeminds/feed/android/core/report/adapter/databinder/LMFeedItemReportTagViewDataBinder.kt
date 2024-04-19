package com.likeminds.feed.android.core.report.adapter.databinder

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.likeminds.feed.android.core.databinding.LmFeedItemReportTagBinding
import com.likeminds.feed.android.core.report.adapter.LMFeedReportTagAdapterListener
import com.likeminds.feed.android.core.report.model.LMFeedReportTagViewData
import com.likeminds.feed.android.core.ui.base.styles.setStyle
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer
import com.likeminds.feed.android.core.utils.LMFeedViewUtils
import com.likeminds.feed.android.core.utils.base.LMFeedViewDataBinder
import com.likeminds.feed.android.core.utils.base.model.ITEM_REPORT_TAG

class LMFeedItemReportTagViewDataBinder(
    private val listener: LMFeedReportTagAdapterListener
) : LMFeedViewDataBinder<LmFeedItemReportTagBinding, LMFeedReportTagViewData>() {

    override val viewType: Int
        get() = ITEM_REPORT_TAG

    override fun createBinder(parent: ViewGroup): LmFeedItemReportTagBinding {
        val binding = LmFeedItemReportTagBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        binding.apply {
            val reportTagTextStyle = LMFeedStyleTransformer.reportFragmentViewStyle.reportTagStyle
            tvReportTag.setStyle(reportTagTextStyle)

            setListeners(this)
        }

        return binding
    }

    override fun bindData(
        binding: LmFeedItemReportTagBinding,
        data: LMFeedReportTagViewData,
        position: Int
    ) {
        binding.apply {
            reportTagViewData = data
            setTagBackground(this)
        }
    }

    //set click listeners to the view data binder
    private fun setListeners(binding: LmFeedItemReportTagBinding) {
        binding.apply {
            tvReportTag.setOnClickListener {
                val reportTagViewData = reportTagViewData ?: return@setOnClickListener
                listener.onReportTagSelected(reportTagViewData)
            }
        }
    }

    // sets tag background to the buttons color
    private fun setTagBackground(binding: LmFeedItemReportTagBinding) {
        val drawable = binding.tvReportTag.background as GradientDrawable
        drawable.mutate()
        val width = LMFeedViewUtils.dpToPx(1)

        val reportFragmentViewStyle = LMFeedStyleTransformer.reportFragmentViewStyle

        binding.apply {
            if (reportTagViewData?.isSelected == true) {
                //set stroke color of report tag
                drawable.setStroke(
                    width,
                    ContextCompat.getColor(
                        root.context,
                        reportFragmentViewStyle.selectedReportTagColor
                    )
                )

                // set text color of report tag
                tvReportTag.setTextColor(
                    ContextCompat.getColor(
                        root.context,
                        reportFragmentViewStyle.selectedReportTagColor
                    )
                )
            } else {
                //set stroke color of report tag
                drawable.setStroke(
                    width,
                    ContextCompat.getColor(
                        root.context,
                        reportFragmentViewStyle.reportTagStyle.textColor
                    )
                )

                // set text color of report tag
                tvReportTag.setTextColor(
                    ContextCompat.getColor(
                        root.context,
                        reportFragmentViewStyle.reportTagStyle.textColor
                    )
                )
            }
        }
    }
}