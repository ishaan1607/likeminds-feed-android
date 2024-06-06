package com.likeminds.feed.android.core.ui.widgets.poll.style

import android.graphics.Typeface
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.LMFeedTextStyle
import com.likeminds.feed.android.core.ui.widgets.alertdialog.style.LMFeedAlertDialogViewStyle
import com.likeminds.feed.android.core.utils.LMFeedViewStyle

class LMFeedAnonymousPollDialogFragmentStyle private constructor(
    //anonymous dialog style
    val anonymousPollDialogStyle: LMFeedAlertDialogViewStyle
) : LMFeedViewStyle {

    class Builder {
        private var anonymousPollDialogStyle: LMFeedAlertDialogViewStyle =
            LMFeedAlertDialogViewStyle.Builder()
                .alertSubtitleText(
                    LMFeedTextStyle.Builder()
                        .textColor(R.color.lm_feed_grey)
                        .textSize(R.dimen.lm_feed_text_medium)
                        .typeface(Typeface.NORMAL)
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

        fun anonymousPollDialogStyle(anonymousPollDialogStyle: LMFeedAlertDialogViewStyle) =
            apply {
                this.anonymousPollDialogStyle = anonymousPollDialogStyle
            }

        fun build() = LMFeedAnonymousPollDialogFragmentStyle(anonymousPollDialogStyle)
    }

    fun toBuilder(): Builder {
        return Builder().anonymousPollDialogStyle(anonymousPollDialogStyle)
    }
}