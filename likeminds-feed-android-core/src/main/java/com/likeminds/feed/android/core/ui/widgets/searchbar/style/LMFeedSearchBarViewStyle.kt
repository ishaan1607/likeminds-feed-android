package com.likeminds.feed.android.core.ui.widgets.searchbar.style

import android.graphics.Typeface
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.utils.LMFeedViewStyle

/**
 * [LMFeedSearchBarViewStyle] helps you to customize the likes fragment [LMFeedSearchBarView]
 *
 * @property searchInputStyle : [LMFeedEditTextStyle] this will help you to customize the search input field in the search bar view
 * @property searchBackIconStyle : [LMFeedIconStyle] this will help you to customize the back icon in the search bar view | set its value to [null] if you want to hide the back icon in the search bar view
 * @property searchCloseIconStyle : [LMFeedIconStyle] this will help you to customize the close icon in the search bar view | set its value to [null] if you want to hide the close icon in the search bar view
 * @property backgroundColor: [Int] should be in format of [ColorRes] this will help you to customize the background color of the search bar view | Default value = [null]
 * @property elevation : [Int] should be in format of [DimenRes] this will help you to customize the elevation of the search bar view | Default value = [null]
 * */
class LMFeedSearchBarViewStyle private constructor(
    //search input edit text style
    val searchInputStyle: LMFeedEditTextStyle,
    //search back icon style
    val searchBackIconStyle: LMFeedIconStyle?,
    //search close icon style
    val searchCloseIconStyle: LMFeedIconStyle?,
    //search bar background color
    @ColorRes val backgroundColor: Int?,
    //search bar elevation
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