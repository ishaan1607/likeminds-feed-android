package com.likeminds.feed.android.core.utils

import com.likeminds.feed.android.core.activityfeed.viewstyle.LMFeedActivityFeedFragmentViewStyle
import com.likeminds.feed.android.core.likes.viewstyle.LMFeedLikesFragmentViewStyle
import com.likeminds.feed.android.core.post.detail.viewstyle.LMFeedPostDetailFragmentViewStyle
import com.likeminds.feed.android.core.post.viewstyle.LMFeedPostViewStyle
import com.likeminds.feed.android.core.report.viewstyle.LMFeedReportFragmentViewStyle
import com.likeminds.feed.android.core.universalfeed.viewstyle.LMFeedUniversalFeedFragmentViewStyle

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
    var activityFeedFragmentViewStyle: LMFeedActivityFeedFragmentViewStyle =
        LMFeedActivityFeedFragmentViewStyle.Builder().build()

    @JvmStatic
    var likesFragmentViewStyle: LMFeedLikesFragmentViewStyle =
        LMFeedLikesFragmentViewStyle.Builder().build()

    @JvmStatic
    var reportFragmentViewStyle: LMFeedReportFragmentViewStyle =
        LMFeedReportFragmentViewStyle.Builder().build()
}