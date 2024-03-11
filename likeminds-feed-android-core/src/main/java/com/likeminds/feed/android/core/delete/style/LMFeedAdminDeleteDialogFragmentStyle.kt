package com.likeminds.feed.android.core.delete.style

import android.graphics.Typeface
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.ui.widgets.alertdialog.style.LMFeedAlertDialogStyle

class LMFeedAdminDeleteDialogFragmentStyle private constructor(
    val adminDeleteDialogStyle: LMFeedAlertDialogStyle,
    val selectorIconStyle: LMFeedIconStyle,
    val reasonTextStyle: LMFeedTextStyle
) {

    class Builder {
        private var adminDeleteDialogStyle: LMFeedAlertDialogStyle =
            LMFeedAlertDialogStyle.Builder()
                .alertSubtitleText(
                    LMFeedTextStyle.Builder()
                        .textColor(R.color.lm_feed_grey)
                        .textSize(R.dimen.lm_feed_text_medium)
                        .typeface(Typeface.NORMAL)
                        .fontResource(R.font.lm_feed_roboto)
                        .build()
                )
                .alertNegativeButtonStyle(
                    LMFeedTextStyle.Builder()
                        .textAllCaps(true)
                        .textColor(R.color.lm_feed_black_40)
                        .textSize(R.dimen.lm_feed_text_small)
                        .typeface(Typeface.NORMAL)
                        .fontResource(R.font.lm_feed_roboto_medium)
                        .build()
                )
                .alertPositiveButtonStyle(
                    LMFeedTextStyle.Builder()
                        .textAllCaps(true)
                        .textColor(R.color.lm_feed_black_20)
                        .textSize(R.dimen.lm_feed_text_small)
                        .typeface(Typeface.NORMAL)
                        .fontResource(R.font.lm_feed_roboto_medium)
                        .build()
                )
                .alertSelectorStyle(
                    LMFeedTextStyle.Builder()
                        .textColor(R.color.lm_feed_black)
                        .drawableRightSrc(R.drawable.lm_feed_ic_arrow_drop_down)
                        .hintTextColor(R.color.lm_feed_black_20)
                        .textSize(R.dimen.lm_feed_text_small)
                        .typeface(Typeface.NORMAL)
                        .fontResource(R.font.lm_feed_roboto)
                        .build()
                )
                .alertInputStyle(
                    LMFeedEditTextStyle.Builder()
                        .inputTextStyle(
                            LMFeedTextStyle.Builder()
                                .maxLines(1)
                                .textColor(R.color.lm_feed_black)
                                .textSize(R.dimen.lm_feed_text_small)
                                .build()
                        )
                        .hintTextColor(R.color.lm_feed_black_20)
                        .build()
                )
                .alertBoxElevation(R.dimen.lm_feed_elevation_small)
                .alertBoxCornerRadius(R.dimen.lm_feed_corner_radius_small)
                .build()

        private var selectorIconStyle: LMFeedIconStyle = LMFeedIconStyle.Builder()
            .inActiveSrc(R.drawable.lm_feed_ic_radio_button_off)
            .build()

        private var reasonTextStyle: LMFeedTextStyle = LMFeedTextStyle.Builder()
            .textColor(R.color.lm_feed_black)
            .textSize(R.dimen.lm_feed_text_medium)
            .build()

        fun adminDeleteDialogStyle(adminDeleteDialogStyle: LMFeedAlertDialogStyle) = apply {
            this.adminDeleteDialogStyle = adminDeleteDialogStyle
        }

        fun selectorIconStyle(selectorIconStyle: LMFeedIconStyle) = apply {
            this.selectorIconStyle = selectorIconStyle
        }

        fun reasonTextStyle(reasonTextStyle: LMFeedTextStyle) = apply {
            this.reasonTextStyle = reasonTextStyle
        }

        fun build() = LMFeedAdminDeleteDialogFragmentStyle(
            adminDeleteDialogStyle,
            selectorIconStyle,
            reasonTextStyle
        )
    }

    fun toBuilder(): Builder {
        return Builder().adminDeleteDialogStyle(adminDeleteDialogStyle)
            .selectorIconStyle(selectorIconStyle)
            .reasonTextStyle(reasonTextStyle)
    }
}