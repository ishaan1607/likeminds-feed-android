package com.likeminds.feed.android.core.report.style

import android.graphics.Typeface
import android.text.TextUtils
import androidx.annotation.ColorRes
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.ui.widgets.headerview.style.LMFeedHeaderViewStyle
import com.likeminds.feed.android.core.utils.LMFeedViewStyle
import com.likeminds.feed.android.core.utils.model.LMFeedPadding

class LMFeedReportFragmentViewStyle private constructor(
    //header view style
    val headerViewStyle: LMFeedHeaderViewStyle,
    //report header text style
    val reportHeaderStyle: LMFeedTextStyle,
    //report sub header text style
    val reportSubHeaderStyle: LMFeedTextStyle,
    //report reason input style
    val reportReasonInputStyle: LMFeedEditTextStyle,
    //report submit button style
    val reportButtonStyle: LMFeedButtonStyle,
    //report tag view style
    val reportTagStyle: LMFeedTextStyle,
    //selected report tag stroke and text color
    @ColorRes val selectedReportTagColor: Int,
    //report screen background color
    @ColorRes val backgroundColor: Int?
) : LMFeedViewStyle {

    class Builder {
        private var headerViewStyle: LMFeedHeaderViewStyle = LMFeedHeaderViewStyle.Builder()
            .titleTextStyle(
                LMFeedTextStyle.Builder()
                    .textColor(R.color.lm_feed_blood_orange)
                    .textSize(R.dimen.lm_feed_header_view_title_text_size)
                    .maxLines(1)
                    .ellipsize(TextUtils.TruncateAt.END)
                    .build()
            )
            .backgroundColor(R.color.lm_feed_white)
            .navigationIconStyle(
                LMFeedIconStyle.Builder()
                    .iconTint(R.color.lm_feed_black)
                    .inActiveSrc(R.drawable.lm_feed_ic_arrow_back_black_24dp)
                    .iconPadding(
                        LMFeedPadding(
                            R.dimen.lm_feed_icon_padding,
                            R.dimen.lm_feed_icon_padding,
                            R.dimen.lm_feed_icon_padding,
                            R.dimen.lm_feed_icon_padding
                        )
                    )
                    .build()
            )
            .build()

        private var reportHeaderStyle: LMFeedTextStyle = LMFeedTextStyle.Builder()
            .textSize(R.dimen.lm_feed_text_medium)
            .fontResource(R.font.lm_feed_roboto_medium)
            .build()

        private var reportSubHeaderStyle: LMFeedTextStyle = LMFeedTextStyle.Builder()
            .textSize(R.dimen.lm_feed_text_medium)
            .textColor(R.color.lm_feed_brown_grey)
            .fontResource(R.font.lm_feed_roboto)
            .build()

        private var reportReasonInputStyle: LMFeedEditTextStyle = LMFeedEditTextStyle.Builder()
            .hintTextColor(R.color.lm_feed_brown_grey)
            .inputTextStyle(
                LMFeedTextStyle.Builder()
                    .textSize(R.dimen.lm_feed_text_medium)
                    .fontResource(R.font.lm_feed_roboto)
                    .build()
            )
            .backgroundColor(R.color.lm_feed_majorelle_blue)
            .build()

        private var reportButtonStyle: LMFeedButtonStyle = LMFeedButtonStyle.Builder()
            .textStyle(
                LMFeedTextStyle.Builder()
                    .textAllCaps(true)
                    .fontResource(R.font.lm_feed_roboto_medium)
                    .textColor(R.color.lm_feed_white)
                    .textSize(R.dimen.lm_feed_text_medium)
                    .typeface(Typeface.BOLD)
                    .build()
            )
            .backgroundColor(R.color.lm_feed_blood_orange)
            .cornerRadius(R.dimen.lm_feed_report_button_corner_radius)
            .build()

        private var reportTagStyle: LMFeedTextStyle = LMFeedTextStyle.Builder()
            .ellipsize(TextUtils.TruncateAt.END)
            .maxLines(1)
            .textSize(R.dimen.lm_feed_text_large)
            .textColor(R.color.lm_feed_brown_grey)
            .build()

        @ColorRes
        private var selectedReportTagColor: Int = R.color.lm_feed_majorelle_blue

        @ColorRes
        private var backgroundColor: Int? = null

        fun headerViewStyle(headerViewStyle: LMFeedHeaderViewStyle) = apply {
            this.headerViewStyle = headerViewStyle
        }

        fun reportHeaderStyle(reportHeaderStyle: LMFeedTextStyle) = apply {
            this.reportHeaderStyle = reportHeaderStyle
        }

        fun reportSubHeaderStyle(reportSubHeaderStyle: LMFeedTextStyle) = apply {
            this.reportSubHeaderStyle = reportSubHeaderStyle
        }

        fun reportReasonInputStyle(reportReasonInputStyle: LMFeedEditTextStyle) = apply {
            this.reportReasonInputStyle = reportReasonInputStyle
        }

        fun reportButtonStyle(reportButtonStyle: LMFeedButtonStyle) = apply {
            this.reportButtonStyle = reportButtonStyle
        }

        fun reportTagStyle(reportTagStyle: LMFeedTextStyle) = apply {
            this.reportTagStyle = reportTagStyle
        }

        fun selectedReportTagColor(@ColorRes selectedReportTagColor: Int) = apply {
            this.selectedReportTagColor = selectedReportTagColor
        }

        fun backgroundColor(@ColorRes backgroundColor: Int?) = apply {
            this.backgroundColor = backgroundColor
        }

        fun build() = LMFeedReportFragmentViewStyle(
            headerViewStyle,
            reportHeaderStyle,
            reportSubHeaderStyle,
            reportReasonInputStyle,
            reportButtonStyle,
            reportTagStyle,
            selectedReportTagColor,
            backgroundColor
        )
    }

    fun toBuilder(): Builder {
        return Builder().headerViewStyle(headerViewStyle)
            .reportHeaderStyle(reportHeaderStyle)
            .reportSubHeaderStyle(reportSubHeaderStyle)
            .reportReasonInputStyle(reportReasonInputStyle)
            .reportButtonStyle(reportButtonStyle)
            .reportTagStyle(reportTagStyle)
            .selectedReportTagColor(selectedReportTagColor)
            .backgroundColor(backgroundColor)
    }
}