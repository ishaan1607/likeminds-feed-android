package com.likeminds.feed.android.core.ui.widgets.user.style

import android.text.TextUtils
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.LMFeedImageStyle
import com.likeminds.feed.android.core.ui.base.styles.LMFeedTextStyle
import com.likeminds.feed.android.core.utils.LMFeedViewStyle

/**
 * [LMFeedUserViewStyle] helps you to customize the likes fragment [LMFeedUserView]
 *
 * @property userImageViewStyle : [LMFeedImageStyle] this will help you to customize the user image in the user view
 * @property userNameViewStyle : [LMFeedTextStyle] this will help you to customize the user name text in the user view
 * @property userTitleViewStyle: [LMFeedTextStyle] this will help you to customize the user title text in the user view | set its value to [null] to hide the user title text
 * */
class LMFeedUserViewStyle private constructor(
    val userImageViewStyle: LMFeedImageStyle,
    val userNameViewStyle: LMFeedTextStyle,
    val userTitleViewStyle: LMFeedTextStyle?
) : LMFeedViewStyle {

    class Builder {
        private var userImageViewStyle: LMFeedImageStyle = LMFeedImageStyle.Builder()
            .isCircle(true)
            .build()

        private var userNameViewStyle: LMFeedTextStyle = LMFeedTextStyle.Builder()
            .ellipsize(TextUtils.TruncateAt.END)
            .maxLines(1)
            .textColor(R.color.lm_feed_dark_grey)
            .textSize(R.dimen.lm_feed_text_large)
            .fontAssetsPath("fonts/lm_feed_montserrat-medium.ttf")
            .build()

        private var userTitleViewStyle: LMFeedTextStyle? = null

        fun userImageViewStyle(userImageViewStyle: LMFeedImageStyle) = apply {
            this.userImageViewStyle = userImageViewStyle
        }

        fun userNameViewStyle(userNameViewStyle: LMFeedTextStyle) = apply {
            this.userNameViewStyle = userNameViewStyle
        }

        fun userTitleViewStyle(userTitleViewStyle: LMFeedTextStyle?) = apply {
            this.userTitleViewStyle = userTitleViewStyle
        }

        fun build() = LMFeedUserViewStyle(
            userImageViewStyle,
            userNameViewStyle,
            userTitleViewStyle
        )
    }

    fun toBuilder(): Builder {
        return Builder().userImageViewStyle(userImageViewStyle)
            .userNameViewStyle(userNameViewStyle)
            .userTitleViewStyle(userTitleViewStyle)
    }
}