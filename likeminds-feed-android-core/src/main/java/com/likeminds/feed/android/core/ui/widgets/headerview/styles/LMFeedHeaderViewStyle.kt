package com.likeminds.feed.android.core.ui.widgets.headerview.styles

import android.graphics.Typeface
import androidx.annotation.*
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.LMFeedTextStyle

import com.likeminds.feed.android.core.utils.LMFeedViewStyle
import com.likeminds.feed.android.core.utils.model.LMFeedPadding

class LMFeedHeaderViewStyle private constructor(
    val titleTextStyle: LMFeedTextStyle,
    val subtitleTextStyle: LMFeedTextStyle?,
    @ColorRes val backgroundColor: Int,
    @DimenRes val elevation: Int,

    // icon related
    @DrawableRes val navigationIcon: Int?,
    @ColorRes val navigationIconTint: Int?,
    val navigationIconPadding: LMFeedPadding?,
    @DrawableRes val searchIcon: Int?,
    @ColorRes val searchIconTint: Int?,
    val searchIconPadding: LMFeedPadding?,
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

        @DrawableRes
        private var navigationIcon: Int? = null

        @ColorRes
        private var navigationIconTint: Int? = null

        private var navigationIconPadding: LMFeedPadding? = null

        @DrawableRes
        private var searchIcon: Int? = null

        @ColorRes
        private var searchIconTint: Int? = null

        private var searchIconPadding: LMFeedPadding? = null

        fun titleTextStyle(titleTextStyle: LMFeedTextStyle) =
            apply { this.titleTextStyle = titleTextStyle }

        fun subtitleTextStyle(subtitleTextStyle: LMFeedTextStyle?) =
            apply { this.subtitleTextStyle = subtitleTextStyle }

        fun backgroundColor(@ColorRes backgroundColor: Int) =
            apply { this.backgroundColor = backgroundColor }

        fun elevation(@DimenRes elevation: Int) = apply { this.elevation = elevation }

        fun navigationIcon(@DrawableRes navigationIcon: Int?) =
            apply { this.navigationIcon = navigationIcon }

        fun navigationIconTint(@ColorRes navigationIconTint: Int?) = apply {
            this.navigationIconTint = navigationIconTint
        }

        fun navigationIconPadding(navigationIconPadding: LMFeedPadding?) = apply {
            this.navigationIconPadding = navigationIconPadding
        }

        fun searchIcon(@DrawableRes searchIcon: Int?) =
            apply { this.searchIcon = searchIcon }

        fun searchIconTint(@ColorRes searchIconTint: Int?) = apply {
            this.searchIconTint = searchIconTint
        }

        fun searchIconPadding(searchIconPadding: LMFeedPadding?) = apply {
            this.searchIconPadding = searchIconPadding
        }

        fun build() = LMFeedHeaderViewStyle(
            titleTextStyle,
            subtitleTextStyle,
            backgroundColor,
            elevation,
            navigationIcon,
            navigationIconTint,
            navigationIconPadding,
            searchIcon,
            searchIconTint,
            searchIconPadding
        )
    }

    fun toBuilder(): Builder {
        return Builder().titleTextStyle(titleTextStyle)
            .subtitleTextStyle(subtitleTextStyle)
            .backgroundColor(backgroundColor)
            .elevation(elevation)
            .navigationIcon(navigationIcon)
            .navigationIconTint(navigationIconTint)
            .navigationIconPadding(navigationIconPadding)
            .searchIcon(searchIcon)
            .searchIconTint(searchIconTint)
            .searchIconPadding(searchIconPadding)
    }
}