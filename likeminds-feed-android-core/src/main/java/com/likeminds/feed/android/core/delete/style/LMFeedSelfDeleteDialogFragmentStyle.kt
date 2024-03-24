package com.likeminds.feed.android.core.delete.style

import android.graphics.Typeface
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.LMFeedTextStyle
import com.likeminds.feed.android.core.ui.widgets.alertdialog.style.LMFeedAlertDialogViewStyle
import com.likeminds.feed.android.core.utils.LMFeedViewStyle

/**
 * [LMFeedSelfDeleteDialogFragmentStyle] helps you to customize the admin delete dialog fragment [LMFeedSelfDeleteDialogFragment]
 *
 * @property selfDeleteDialogStyle : [LMFeedAlertDialogViewStyle] this will help you to customize the self delete alert dialog
 * */
class LMFeedSelfDeleteDialogFragmentStyle private constructor(
    //self delete dialog style
    val selfDeleteDialogStyle: LMFeedAlertDialogViewStyle
) : LMFeedViewStyle {
    class Builder {
        private var selfDeleteDialogStyle = LMFeedAlertDialogViewStyle.Builder()
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
            .alertBoxElevation(R.dimen.lm_feed_elevation_small)
            .alertBoxCornerRadius(R.dimen.lm_feed_corner_radius_small)
            .build()

        fun selfDeleteDialogStyle(selfDeleteDialogStyle: LMFeedAlertDialogViewStyle) = apply {
            this.selfDeleteDialogStyle = selfDeleteDialogStyle
        }

        fun build() = LMFeedSelfDeleteDialogFragmentStyle(selfDeleteDialogStyle)
    }

    fun toBuilder(): Builder {
        return Builder().selfDeleteDialogStyle(selfDeleteDialogStyle)
    }
}