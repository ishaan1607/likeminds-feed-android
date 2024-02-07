package com.likeminds.feed.android.core.universalfeed.viewstyle

import android.text.TextUtils
import com.likeminds.feed.android.integration.R
import com.likeminds.feed.android.ui.base.styles.*
import com.likeminds.feed.android.ui.utils.LMFeedViewStyle
import com.likeminds.feed.android.ui.utils.model.LMFeedPadding
import com.likeminds.feed.android.ui.widgets.headerview.styles.LMFeedHeaderViewStyle
import com.likeminds.feed.android.ui.widgets.noentitylayout.style.LMFeedNoEntityLayoutViewStyle

class LMFeedUniversalFeedFragmentViewStyle private constructor(
    //create post button
    val createNewPostButtonViewStyle: LMFeedFABStyle,
    //header
    val headerViewStyle: LMFeedHeaderViewStyle,
    //no post layout
    val noPostLayoutViewStyle: LMFeedNoEntityLayoutViewStyle
) : LMFeedViewStyle {

    class Builder {

        private val createPostFabStyle: LMFeedFABStyle = LMFeedFABStyle.Builder()
            .isExtended(false)
            .backgroundColor(com.likeminds.feed.android.ui.R.color.lm_feed_majorelle_blue)
            .icon(R.drawable.lm_feed_core_ic_new_post_plus)
            .iconTint(com.likeminds.feed.android.ui.R.color.lm_feed_white)
            .iconSize(R.dimen.lm_feed_core_create_new_post_icon_size)
            .textStyle(
                LMFeedTextStyle.Builder()
                    .textColor(com.likeminds.feed.android.ui.R.color.lm_feed_white)
                    .textAllCaps(true)
                    .build()
            )
            .build()

        private var createNewPostButtonViewStyle = createPostFabStyle

        private var headerViewStyle: LMFeedHeaderViewStyle = LMFeedHeaderViewStyle.Builder()
            .titleTextStyle(
                LMFeedTextStyle.Builder()
                    .textColor(com.likeminds.feed.android.ui.R.color.lm_feed_black)
                    .textSize(com.likeminds.feed.android.ui.R.dimen.lm_feed_header_view_title_text_size)
                    .maxLines(1)
                    .ellipsize(TextUtils.TruncateAt.END)
                    .build()
            )
            .backgroundColor(com.likeminds.feed.android.ui.R.color.lm_feed_white)
            .searchIcon(R.drawable.lm_feed_core_ic_search_icon)
            .searchIconTint(com.likeminds.feed.android.ui.R.color.lm_feed_black)
            .searchIconPadding(
                LMFeedPadding(
                    R.dimen.lm_feed_core_icon_padding,
                    R.dimen.lm_feed_core_icon_padding,
                    R.dimen.lm_feed_core_icon_padding,
                    R.dimen.lm_feed_core_icon_padding
                )
            )
            .build()

        private var noPostLayoutViewStyle: LMFeedNoEntityLayoutViewStyle =
            LMFeedNoEntityLayoutViewStyle.Builder()
                .backgroundColor(com.likeminds.feed.android.ui.R.color.lm_feed_white)
                .actionStyle(createPostFabStyle)
                .titleStyle(
                    LMFeedTextStyle.Builder()
                        .textSize(com.likeminds.feed.android.ui.R.dimen.lm_feed_text_extra_large)
                        .textColor(com.likeminds.feed.android.ui.R.color.lm_feed_black)
                        .fontResource(R.font.lm_feed_core_roboto_medium)
                        .textAllCaps(false)
                        .maxLines(1)
                        .build()
                )
                .imageStyle(
                    LMFeedImageStyle.Builder()
                        .isCircle(false)
                        .showGreyScale(false)
                        .imageSrc(R.drawable.lm_feed_core_ic_post)
                        .alpha(0.6f)
                        .build()
                )
                .subtitleStyle(
                    LMFeedTextStyle.Builder()
                        .textAllCaps(false)
                        .textColor(com.likeminds.feed.android.ui.R.color.lm_feed_black)
                        .textSize(com.likeminds.feed.android.ui.R.dimen.lm_feed_text_small)
                        .textAllCaps(false)
                        .build()
                )
                .build()

        fun createNewPostButtonViewStyle(createNewPostButtonViewStyle: LMFeedFABStyle) = apply {
            this.createNewPostButtonViewStyle = createNewPostButtonViewStyle
        }

        fun headerViewStyle(headerViewStyle: LMFeedHeaderViewStyle) = apply {
            this.headerViewStyle = headerViewStyle
        }

        fun noPostLayoutViewStyle(noPostLayoutViewStyle: LMFeedNoEntityLayoutViewStyle) =
            apply { this.noPostLayoutViewStyle = noPostLayoutViewStyle }

        fun build() = LMFeedUniversalFeedFragmentViewStyle(
            createNewPostButtonViewStyle,
            headerViewStyle,
            noPostLayoutViewStyle
        )
    }

    fun toBuilder(): Builder {
        return Builder().headerViewStyle(headerViewStyle)
            .createNewPostButtonViewStyle(createNewPostButtonViewStyle)
            .noPostLayoutViewStyle(noPostLayoutViewStyle)
    }
}