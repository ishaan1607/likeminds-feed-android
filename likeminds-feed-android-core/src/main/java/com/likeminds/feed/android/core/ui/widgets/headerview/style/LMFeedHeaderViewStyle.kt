package com.likeminds.feed.android.core.ui.widgets.headerview.style

import android.graphics.Typeface
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.utils.LMFeedViewStyle

//todo: add a submit text and style
class LMFeedHeaderViewStyle private constructor(
    val titleTextStyle: LMFeedTextStyle,
    val subtitleTextStyle: LMFeedTextStyle?,
    @ColorRes val backgroundColor: Int,
    @DimenRes val elevation: Int,
    //submit text style
    val submitTextStyle: LMFeedTextStyle?,
    //submit button's active color
    @ColorRes val activeSubmitColor: Int?,
    //navigation icon style
    val navigationIconStyle: LMFeedIconStyle?,
    //search icon style
    val searchIconStyle: LMFeedIconStyle?,
    //profile image style in header
    val userProfileStyle: LMFeedImageStyle?,
    //notification icon style
    val notificationIconStyle: LMFeedIconStyle?,
    //notification count style
    val notificationCountTextStyle: LMFeedTextStyle?,
) : LMFeedViewStyle {

    class Builder {
        private var titleTextStyle: LMFeedTextStyle = LMFeedTextStyle.Builder()
            .textColor(R.color.lm_feed_black)
            .typeface(Typeface.NORMAL)
            .build()

        private var subtitleTextStyle: LMFeedTextStyle? = null

        @ColorRes
        private var backgroundColor: Int = R.color.lm_feed_white

        @DimenRes
        private var elevation: Int = R.dimen.lm_feed_elevation_small

        private var submitTextStyle: LMFeedTextStyle? = null

        @ColorRes
        private var activeSubmitColor: Int? = null

        private var navigationIconStyle: LMFeedIconStyle? = null

        private var searchIconStyle: LMFeedIconStyle? = null

        private var userProfileStyle: LMFeedImageStyle? = null

        private var notificationIconStyle: LMFeedIconStyle? = null

        private var notificationCountTextStyle: LMFeedTextStyle? = null

        fun titleTextStyle(titleTextStyle: LMFeedTextStyle) = apply {
            this.titleTextStyle = titleTextStyle
        }

        fun subtitleTextStyle(subtitleTextStyle: LMFeedTextStyle?) = apply {
            this.subtitleTextStyle = subtitleTextStyle
        }

        fun backgroundColor(@ColorRes backgroundColor: Int) = apply {
            this.backgroundColor = backgroundColor
        }

        fun elevation(@DimenRes elevation: Int) = apply {
            this.elevation = elevation
        }

        fun submitTextStyle(submitTextStyle: LMFeedTextStyle?) = apply {
            this.submitTextStyle = submitTextStyle
        }

        fun activeSubmitColor(@ColorRes activeSubmitColor: Int?) = apply {
            this.activeSubmitColor = activeSubmitColor
        }

        fun navigationIconStyle(navigationIconStyle: LMFeedIconStyle?) = apply {
            this.navigationIconStyle = navigationIconStyle
        }

        fun searchIconStyle(searchIconStyle: LMFeedIconStyle?) = apply {
            this.searchIconStyle = searchIconStyle
        }

        fun userProfileStyle(userProfileStyle: LMFeedImageStyle?) = apply {
            this.userProfileStyle = userProfileStyle
        }

        fun notificationIconStyle(notificationIconStyle: LMFeedIconStyle?) = apply {
            this.notificationIconStyle = notificationIconStyle
        }

        fun notificationCountTextStyle(notificationCountTextStyle: LMFeedTextStyle?) = apply {
            this.notificationCountTextStyle = notificationCountTextStyle
        }

        fun build() = LMFeedHeaderViewStyle(
            titleTextStyle,
            subtitleTextStyle,
            backgroundColor,
            elevation,
            submitTextStyle,
            activeSubmitColor,
            navigationIconStyle,
            searchIconStyle,
            userProfileStyle,
            notificationIconStyle,
            notificationCountTextStyle
        )
    }

    fun toBuilder(): Builder {
        return Builder().titleTextStyle(titleTextStyle)
            .subtitleTextStyle(subtitleTextStyle)
            .backgroundColor(backgroundColor)
            .elevation(elevation)
            .submitTextStyle(submitTextStyle)
            .activeSubmitColor(activeSubmitColor)
            .navigationIconStyle(navigationIconStyle)
            .searchIconStyle(searchIconStyle)
            .userProfileStyle(userProfileStyle)
            .notificationIconStyle(notificationIconStyle)
            .notificationCountTextStyle(notificationCountTextStyle)
    }
}