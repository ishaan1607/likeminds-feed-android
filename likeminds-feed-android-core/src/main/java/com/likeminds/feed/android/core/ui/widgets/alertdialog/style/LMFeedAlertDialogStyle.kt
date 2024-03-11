package com.likeminds.feed.android.core.ui.widgets.alertdialog.style

import android.graphics.Typeface
import android.text.TextUtils
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.LMFeedEditTextStyle
import com.likeminds.feed.android.core.ui.base.styles.LMFeedTextStyle
import com.likeminds.feed.android.core.utils.LMFeedViewStyle

class LMFeedAlertDialogStyle private constructor(
    val alertTitleText: LMFeedTextStyle,
    val alertSubtitleText: LMFeedTextStyle?,
    val alertNegativeButtonStyle: LMFeedTextStyle?,
    val alertPositiveButtonStyle: LMFeedTextStyle?,
    val alertSelectorStyle: LMFeedTextStyle?,
    val alertInputStyle: LMFeedEditTextStyle?,
    @ColorRes val alertActivePositiveButtonColor: Int,
    @DimenRes val alertBoxElevation: Int?,
    @DimenRes val alertBoxCornerRadius: Int?,
    @ColorRes val backgroundColor: Int?
) : LMFeedViewStyle {

    class Builder {
        private var alertTitleText: LMFeedTextStyle = LMFeedTextStyle.Builder()
            .ellipsize(TextUtils.TruncateAt.END)
            .textSize(R.dimen.lm_feed_text_medium)
            .textColor(R.color.lm_feed_black_87)
            .fontResource(R.font.lm_feed_roboto_medium)
            .typeface(Typeface.NORMAL)
            .build()

        private var alertSubtitleText: LMFeedTextStyle? = null

        private var alertNegativeButtonStyle: LMFeedTextStyle? = null

        private var alertPositiveButtonStyle: LMFeedTextStyle? = null

        private var alertSelectorStyle: LMFeedTextStyle? = null

        private var alertInputStyle: LMFeedEditTextStyle? = null

        @ColorRes
        private var alertActivePositiveButtonColor: Int = R.color.lm_feed_majorelle_blue

        @DimenRes
        private var alertBoxElevation: Int? = null

        @DimenRes
        private var alertBoxCornerRadius: Int? = null

        @ColorRes
        private var backgroundColor: Int? = null

        fun alertTitleText(alertTitleText: LMFeedTextStyle) = apply {
            this.alertTitleText = alertTitleText
        }

        fun alertSubtitleText(alertSubtitleText: LMFeedTextStyle?) = apply {
            this.alertSubtitleText = alertSubtitleText
        }

        fun alertNegativeButtonStyle(alertNegativeButtonStyle: LMFeedTextStyle?) = apply {
            this.alertNegativeButtonStyle = alertNegativeButtonStyle
        }

        fun alertPositiveButtonStyle(alertPositiveButtonStyle: LMFeedTextStyle?) = apply {
            this.alertPositiveButtonStyle = alertPositiveButtonStyle
        }

        fun alertSelectorStyle(alertSelectorStyle: LMFeedTextStyle?) = apply {
            this.alertSelectorStyle = alertSelectorStyle
        }

        fun alertInputStyle(alertInputStyle: LMFeedEditTextStyle?) = apply {
            this.alertInputStyle = alertInputStyle
        }

        fun alertActivePositiveButtonColor(@ColorRes alertActivePositiveButtonColor: Int) =
            apply {
                this.alertActivePositiveButtonColor = alertActivePositiveButtonColor
            }

        fun alertBoxElevation(@DimenRes alertBoxElevation: Int?) = apply {
            this.alertBoxElevation = alertBoxElevation
        }

        fun alertBoxCornerRadius(@DimenRes alertBoxCornerRadius: Int?) = apply {
            this.alertBoxCornerRadius = alertBoxCornerRadius
        }

        fun backgroundColor(@ColorRes backgroundColor: Int?) = apply {
            this.backgroundColor = backgroundColor
        }

        fun build() = LMFeedAlertDialogStyle(
            alertTitleText,
            alertSubtitleText,
            alertNegativeButtonStyle,
            alertPositiveButtonStyle,
            alertSelectorStyle,
            alertInputStyle,
            alertActivePositiveButtonColor,
            alertBoxElevation,
            alertBoxCornerRadius,
            backgroundColor
        )
    }

    fun toBuilder(): Builder {
        return Builder().alertTitleText(alertTitleText)
            .alertSubtitleText(alertSubtitleText)
            .alertNegativeButtonStyle(alertNegativeButtonStyle)
            .alertPositiveButtonStyle(alertPositiveButtonStyle)
            .alertSelectorStyle(alertSelectorStyle)
            .alertInputStyle(alertInputStyle)
            .alertActivePositiveButtonColor(alertActivePositiveButtonColor)
            .alertBoxElevation(alertBoxElevation)
            .alertBoxCornerRadius(alertBoxCornerRadius)
            .alertBoxCornerRadius(alertBoxCornerRadius)
            .backgroundColor(backgroundColor)
    }
}