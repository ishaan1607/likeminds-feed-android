package com.likeminds.feed.android.core.ui.widgets.acitivityfeed.style

import android.text.TextUtils
import androidx.annotation.ColorRes
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.ui.widgets.headerview.style.LMFeedHeaderViewStyle
import com.likeminds.feed.android.core.ui.widgets.noentitylayout.style.LMFeedNoEntityLayoutViewStyle
import com.likeminds.feed.android.core.utils.LMFeedViewStyle

/**
 * [LMFeedActivityViewStyle] helps you to customize the post detail fragment [LMFeedActivityView]
 *
 * @property userImageViewStyle : [LMFeedHeaderViewStyle] this will help you to customize the user image view in the activity view
 * @property activityTextStyle : [LMFeedFABStyle] this will help you to customize the text content in the activity view
 * @property postTypeBadgeStyle: [LMFeedNoEntityLayoutViewStyle] this will help you to customize the post type badge in the activity view | set its value to [null] if you want to hide the badge of post type in the activity view
 * @property timestampTextStyle: [LMFeedTextStyle] this will help you to customize the timestamp text in the activity view | set its value to [null] if you want to hide the timestamp in the activity view
 * @property readActivityBackgroundColor: [Int] should be in format of [ColorRes] to add background color of the activity view when it is read
 * @property unreadActivityBackgroundColor:  [Int] should be in format of [ColorRes] to add background color of the activity view when it is unread
 * */
class LMFeedActivityViewStyle private constructor(
    //user image style
    val userImageViewStyle: LMFeedImageStyle,
    //activity text style
    val activityTextStyle: LMFeedTextStyle,
    //post type badge style
    val postTypeBadgeStyle: LMFeedImageStyle?,
    //timestamp text style
    val timestampTextStyle: LMFeedTextStyle?,
    //read activity background color
    @ColorRes val readActivityBackgroundColor: Int?,
    //unread activity background color
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