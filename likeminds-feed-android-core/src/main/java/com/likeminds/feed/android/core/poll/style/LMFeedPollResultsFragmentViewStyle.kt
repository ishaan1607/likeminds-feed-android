package com.likeminds.feed.android.core.poll.style

import android.graphics.Typeface
import android.text.TextUtils
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.ui.widgets.headerview.style.LMFeedHeaderViewStyle
import com.likeminds.feed.android.core.ui.widgets.noentitylayout.style.LMFeedNoEntityLayoutViewStyle
import com.likeminds.feed.android.core.utils.LMFeedViewStyle
import com.likeminds.feed.android.core.utils.model.LMFeedPadding

/**
 * [LMFeedPollResultsFragmentViewStyle] helps you to customize the poll results fragment [LMFeedPollResultsFragment]
 * @property headerViewStyle : [LMFeedHeaderViewStyle] this will help you to customize the header view in the poll results fragment
 * @property noResultsLayoutViewStyle : [LMFeedNoEntityLayoutViewStyle] this will help you to customize the no poll results layout in the poll results fragment
 * @property backgroundColor: [Int] should be in format of [ColorRes] this will help you to customize the background color of the poll results fragment | Default value = [null]
 * */
class LMFeedPollResultsFragmentViewStyle private constructor(
    //header view style
    val headerViewStyle: LMFeedHeaderViewStyle,
    //elevation of poll results tab layout
    @DimenRes val pollResultsTabElevation: Int,
    //no activity layout style
    val noResultsLayoutViewStyle: LMFeedNoEntityLayoutViewStyle,
    //background color of the screen
    @ColorRes val backgroundColor: Int?
) : LMFeedViewStyle {

    class Builder {
        private var headerViewStyle: LMFeedHeaderViewStyle = LMFeedHeaderViewStyle.Builder()
            .titleTextStyle(
                LMFeedTextStyle.Builder()
                    .textColor(R.color.lm_feed_black)
                    .textSize(R.dimen.lm_feed_header_view_title_text_size)
                    .maxLines(1)
                    .fontAssetsPath("fonts/lm_feed_montserrat-medium.ttf")
                    .ellipsize(TextUtils.TruncateAt.END)
                    .build()
            )
            .backgroundColor(R.color.lm_feed_white)
            .navigationIconStyle(
                LMFeedIconStyle.Builder()
                    .iconTint(R.color.lm_feed_black)
                    .inActiveSrc(R.drawable.lm_feed_ic_arrow_back_black_24dp)
                    .iconPadding(
                        LMFeedPadding(
                            R.dimen.lm_feed_icon_padding,
                            R.dimen.lm_feed_icon_padding,
                            R.dimen.lm_feed_icon_padding,
                            R.dimen.lm_feed_icon_padding
                        )
                    )
                    .build()
            )
            .build()

        @DimenRes
        private var pollResultsTabElevation: Int = R.dimen.lm_feed_elevation_extra_small

        private var noResultsLayoutViewStyle: LMFeedNoEntityLayoutViewStyle =
            LMFeedNoEntityLayoutViewStyle.Builder()
                .imageStyle(
                    LMFeedImageStyle.Builder()
                        .imageSrc(R.drawable.lm_feed_ic_not_found)
                        .build()
                )
                .titleStyle(
                    LMFeedTextStyle.Builder()
                        .textColor(R.color.lm_feed_dark_grey)
                        .textSize(R.dimen.lm_feed_text_medium)
                        .typeface(Typeface.NORMAL)
                        .fontAssetsPath("fonts/lm_feed_montserrat-medium.ttf")
                        .build()
                )
                .build()

        @ColorRes
        private var backgroundColor: Int? = null

        fun headerViewStyle(headerViewStyle: LMFeedHeaderViewStyle) = apply {
            this.headerViewStyle = headerViewStyle
        }

        fun pollResultsTabElevation(@DimenRes pollResultsTabElevation: Int) = apply {
            this.pollResultsTabElevation = pollResultsTabElevation
        }

        fun noResultsLayoutViewStyle(noResultsLayoutViewStyle: LMFeedNoEntityLayoutViewStyle) =
            apply {
                this.noResultsLayoutViewStyle = noResultsLayoutViewStyle
            }

        fun backgroundColor(@ColorRes backgroundColor: Int?) = apply {
            this.backgroundColor = backgroundColor
        }

        fun build() = LMFeedPollResultsFragmentViewStyle(
            headerViewStyle,
            pollResultsTabElevation,
            noResultsLayoutViewStyle,
            backgroundColor
        )
    }

    fun toBuilder(): Builder {
        return Builder()
            .headerViewStyle(headerViewStyle)
            .pollResultsTabElevation(pollResultsTabElevation)
            .noResultsLayoutViewStyle(noResultsLayoutViewStyle)
            .backgroundColor(backgroundColor)
    }
}