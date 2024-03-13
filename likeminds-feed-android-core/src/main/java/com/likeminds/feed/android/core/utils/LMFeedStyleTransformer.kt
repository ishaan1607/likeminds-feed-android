package com.likeminds.feed.android.core.utils

import android.graphics.Typeface
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.activityfeed.style.LMFeedActivityFeedFragmentViewStyle
import com.likeminds.feed.android.core.delete.style.LMFeedAdminDeleteDialogFragmentStyle
import com.likeminds.feed.android.core.likes.style.LMFeedLikesFragmentViewStyle
import com.likeminds.feed.android.core.post.detail.viewstyle.LMFeedPostDetailFragmentViewStyle
import com.likeminds.feed.android.core.post.edit.style.LMFeedEditPostFragmentViewStyle
import com.likeminds.feed.android.core.post.style.LMFeedPostViewStyle
import com.likeminds.feed.android.core.report.style.LMFeedReportFragmentViewStyle
import com.likeminds.feed.android.core.ui.base.styles.LMFeedTextStyle
import com.likeminds.feed.android.core.ui.widgets.alertdialog.style.LMFeedAlertDialogStyle
import com.likeminds.feed.android.core.universalfeed.style.LMFeedUniversalFeedFragmentViewStyle

object LMFeedStyleTransformer {

    @JvmStatic
    var universalFeedFragmentViewStyle: LMFeedUniversalFeedFragmentViewStyle =
        LMFeedUniversalFeedFragmentViewStyle.Builder().build()

    @JvmStatic
    var postViewStyle: LMFeedPostViewStyle = LMFeedPostViewStyle.Builder().build()

    @JvmStatic
    var postDetailFragmentViewStyle: LMFeedPostDetailFragmentViewStyle =
        LMFeedPostDetailFragmentViewStyle.Builder().build()

    @JvmStatic
    var editPostFragmentViewStyle: LMFeedEditPostFragmentViewStyle =
        LMFeedEditPostFragmentViewStyle.Builder().build()

    @JvmStatic
    var activityFeedFragmentViewStyle: LMFeedActivityFeedFragmentViewStyle =
        LMFeedActivityFeedFragmentViewStyle.Builder().build()

    @JvmStatic
    var likesFragmentViewStyle: LMFeedLikesFragmentViewStyle =
        LMFeedLikesFragmentViewStyle.Builder().build()

    @JvmStatic
    var reportFragmentViewStyle: LMFeedReportFragmentViewStyle =
        LMFeedReportFragmentViewStyle.Builder().build()

    @JvmStatic
    var adminDeleteDialogFragmentStyle: LMFeedAdminDeleteDialogFragmentStyle =
        LMFeedAdminDeleteDialogFragmentStyle.Builder().build()

    @JvmStatic
    var selfDeleteDialogFragmentStyle: LMFeedAlertDialogStyle =
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
            .alertBoxElevation(R.dimen.lm_feed_elevation_small)
            .alertBoxCornerRadius(R.dimen.lm_feed_corner_radius_small)
            .build()
}