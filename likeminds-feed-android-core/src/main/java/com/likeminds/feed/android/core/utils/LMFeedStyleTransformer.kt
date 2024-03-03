package com.likeminds.feed.android.core.utils

import com.likeminds.feed.android.core.post.detail.viewstyle.LMFeedPostDetailFragmentViewStyle
import com.likeminds.feed.android.core.post.viewstyle.LMFeedPostViewStyle
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
}