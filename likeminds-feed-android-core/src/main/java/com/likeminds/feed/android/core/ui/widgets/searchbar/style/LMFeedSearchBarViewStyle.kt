package com.likeminds.feed.android.core.ui.widgets.searchbar.style

import android.graphics.Typeface
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.utils.LMFeedViewStyle

class LMFeedSearchBarViewStyle private constructor(
    val searchInputStyle: LMFeedEditTextStyle,
    val searchBackIconStyle: LMFeedIconStyle?,
    val searchCloseIconStyle: LMFeedIconStyle?,
    @ColorRes val backgroundColor: Int?,
    @DimenRes val elevation: Int?,
) : LMFeedViewStyle {

    class Builder {
        private var searchInputStyle: LMFeedEditTextStyle = LMFeedEditTextStyle.Builder()
            .inputTextStyle(
                LMFeedTextStyle.Builder()
                    .textSize(R.dimen.lm_feed_text_medium)
                    .textColor(R.color.lm_feed_black)
                    .typeface(Typeface.NORMAL)
                    .maxLines(1)
                    .build()
            )
            .hintTextColor(R.color.lm_feed_black_38)
            .backgroundColor(R.color.lm_feed_transparent)
            .build()

        private var searchBackIconStyle: LMFeedIconStyle? = null

        private var searchCloseIconStyle: LMFeedIconStyle? = null

        @ColorRes
        private var backgroundColor: Int? = null

        @DimenRes
        private var elevation: Int? = null

        fun searchInputStyle(searchInputStyle: LMFeedEditTextStyle) = apply {
            this.searchInputStyle = searchInputStyle
        }

        fun searchBackIconStyle(searchBackIconStyle: LMFeedIconStyle?) = apply {
            this.searchBackIconStyle = searchBackIconStyle
        }

        fun searchCloseIconStyle(searchCloseIconStyle: LMFeedIconStyle?) = apply {
            this.searchCloseIconStyle = searchCloseIconStyle
        }

        fun backgroundColor(@ColorRes backgroundColor: Int?) = apply {
            this.backgroundColor = backgroundColor
        }

        fun elevation(@DimenRes elevation: Int?) = apply {
            this.elevation = elevation
        }

        fun build() = LMFeedSearchBarViewStyle(
            searchInputStyle,
            searchBackIconStyle,
            searchCloseIconStyle,
            backgroundColor,
            elevation
        )
    }

    fun toBuilder(): Builder {
        return Builder()
            .searchInputStyle(searchInputStyle)
            .searchBackIconStyle(searchBackIconStyle)
            .searchCloseIconStyle(searchCloseIconStyle)
            .backgroundColor(backgroundColor)
            .elevation(elevation)
    }
}