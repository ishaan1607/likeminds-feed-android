package com.likeminds.feed.android.core.ui.widgets.acitivityfeed.style

import android.text.TextUtils
import androidx.annotation.ColorRes
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.utils.LMFeedViewStyle

class LMFeedActivityViewStyle private constructor(
    val userImageViewStyle: LMFeedImageStyle,
    val activityTextStyle: LMFeedTextStyle,
    val postTypeBadgeStyle: LMFeedImageStyle?,
    val timestampTextStyle: LMFeedTextStyle?,
    @ColorRes val readActivityBackgroundColor: Int?,
    @ColorRes val unreadActivityBackgroundColor: Int?,
) : LMFeedViewStyle {

    class Builder {
        private var userImageViewStyle: LMFeedImageStyle = LMFeedImageStyle.Builder()
            .isCircle(true)
            .build()

        private var activityTextStyle: LMFeedTextStyle = LMFeedTextStyle.Builder()
            .ellipsize(TextUtils.TruncateAt.END)
            .textColor(R.color.lm_feed_dark_grey)
            .textSize(R.dimen.lm_feed_text_medium)
            .fontResource(R.font.lm_feed_roboto)
            .build()

        private var postTypeBadgeStyle: LMFeedImageStyle? = null

        private var timestampTextStyle: LMFeedTextStyle? = null

        @ColorRes
        private var readActivityBackgroundColor: Int? = null

        @ColorRes
        private var unreadActivityBackgroundColor: Int? = null

        fun userImageViewStyle(userImageViewStyle: LMFeedImageStyle) = apply {
            this.userImageViewStyle = userImageViewStyle
        }

        fun activityTextStyle(activityTextStyle: LMFeedTextStyle) = apply {
            this.activityTextStyle = activityTextStyle
        }

        fun postTypeBadgeStyle(postTypeBadgeStyle: LMFeedImageStyle?) = apply {
            this.postTypeBadgeStyle = postTypeBadgeStyle
        }

        fun timestampTextStyle(timestampTextStyle: LMFeedTextStyle?) = apply {
            this.timestampTextStyle = timestampTextStyle
        }

        fun readActivityBackgroundColor(@ColorRes readActivityBackgroundColor: Int?) = apply {
            this.readActivityBackgroundColor = readActivityBackgroundColor
        }

        fun unreadActivityBackgroundColor(@ColorRes unreadActivityBackgroundColor: Int?) = apply {
            this.unreadActivityBackgroundColor = unreadActivityBackgroundColor
        }

        fun build() = LMFeedActivityViewStyle(
            userImageViewStyle,
            activityTextStyle,
            postTypeBadgeStyle,
            timestampTextStyle,
            readActivityBackgroundColor,
            unreadActivityBackgroundColor
        )
    }

    fun toBuilder(): Builder {
        return Builder().userImageViewStyle(userImageViewStyle)
            .activityTextStyle(activityTextStyle)
            .postTypeBadgeStyle(postTypeBadgeStyle)
            .timestampTextStyle(timestampTextStyle)
            .readActivityBackgroundColor(readActivityBackgroundColor)
            .unreadActivityBackgroundColor(unreadActivityBackgroundColor)
    }
}