package com.likeminds.feed.android.core.universalfeed.viewstyle

import android.text.TextUtils
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.ui.widgets.headerview.styles.LMFeedHeaderViewStyle
import com.likeminds.feed.android.core.ui.widgets.noentitylayout.style.LMFeedNoEntityLayoutViewStyle
import com.likeminds.feed.android.core.utils.LMFeedViewStyle
import com.likeminds.feed.android.core.utils.model.LMFeedPadding

class LMFeedUniversalFeedFragmentViewStyle private constructor(
    //create post button
    val createNewPostButtonViewStyle: LMFeedFABStyle,
    //header
    val headerViewStyle: LMFeedHeaderViewStyle,
    //no post layout
    val noPostLayoutViewStyle: LMFeedNoEntityLayoutViewStyle,
    //posting view
    val postingViewStyle: LMFeedPostingViewStyle
) : LMFeedViewStyle {

    class Builder {
        private var createNewPostButtonViewStyle = LMFeedFABStyle.Builder()
            .isExtended(false)
            .backgroundColor(R.color.lm_feed_majorelle_blue)
            .icon(R.drawable.lm_feed_ic_new_post_plus)
            .iconTint(R.color.lm_feed_white)
            .iconSize(R.dimen.lm_feed_create_new_post_icon_size)
            .textStyle(
                LMFeedTextStyle.Builder()
                    .textColor(R.color.lm_feed_white)
                    .textAllCaps(true)
                    .build()
            )
            .build()

        private var headerViewStyle: LMFeedHeaderViewStyle = LMFeedHeaderViewStyle.Builder()
            .titleTextStyle(
                LMFeedTextStyle.Builder()
                    .textColor(R.color.lm_feed_black)
                    .textSize(R.dimen.lm_feed_header_view_title_text_size)
                    .maxLines(1)
                    .ellipsize(TextUtils.TruncateAt.END)
                    .build()
            )
            .backgroundColor(R.color.lm_feed_white)
            .searchIcon(R.drawable.lm_feed_ic_search_icon)
            .searchIconTint(R.color.lm_feed_black)
            .searchIconPadding(
                LMFeedPadding(
                    R.dimen.lm_feed_icon_padding,
                    R.dimen.lm_feed_icon_padding,
                    R.dimen.lm_feed_icon_padding,
                    R.dimen.lm_feed_icon_padding
                )
            )
            .build()

        private var noPostLayoutViewStyle: LMFeedNoEntityLayoutViewStyle =
            LMFeedNoEntityLayoutViewStyle.Builder()
                .backgroundColor(R.color.lm_feed_white)
                .actionStyle(createNewPostButtonViewStyle)
                .titleStyle(
                    LMFeedTextStyle.Builder()
                        .textSize(R.dimen.lm_feed_text_extra_large)
                        .textColor(R.color.lm_feed_black)
                        .fontResource(R.font.lm_feed_roboto_medium)
                        .textAllCaps(false)
                        .maxLines(1)
                        .build()
                )
                .imageStyle(
                    LMFeedImageStyle.Builder()
                        .isCircle(false)
                        .showGreyScale(false)
                        .imageSrc(R.drawable.lm_feed_ic_post)
                        .alpha(0.6f)
                        .build()
                )
                .subtitleStyle(
                    LMFeedTextStyle.Builder()
                        .textAllCaps(false)
                        .textColor(R.color.lm_feed_black)
                        .textSize(R.dimen.lm_feed_text_small)
                        .textAllCaps(false)
                        .build()
                )
                .build()

        private var postingViewStyle: LMFeedPostingViewStyle = LMFeedPostingViewStyle.Builder()
            .build()

        fun createNewPostButtonViewStyle(createNewPostButtonViewStyle: LMFeedFABStyle) = apply {
            this.createNewPostButtonViewStyle = createNewPostButtonViewStyle
        }

        fun headerViewStyle(headerViewStyle: LMFeedHeaderViewStyle) = apply {
            this.headerViewStyle = headerViewStyle
        }

        fun noPostLayoutViewStyle(noPostLayoutViewStyle: LMFeedNoEntityLayoutViewStyle) =
            apply { this.noPostLayoutViewStyle = noPostLayoutViewStyle }

        fun postingViewStyle(postingViewStyle: LMFeedPostingViewStyle) =
            apply { this.postingViewStyle = postingViewStyle }

        fun build() = LMFeedUniversalFeedFragmentViewStyle(
            createNewPostButtonViewStyle,
            headerViewStyle,
            noPostLayoutViewStyle,
            postingViewStyle
        )
    }

    fun toBuilder(): Builder {
        return Builder().headerViewStyle(headerViewStyle)
            .createNewPostButtonViewStyle(createNewPostButtonViewStyle)
            .noPostLayoutViewStyle(noPostLayoutViewStyle)
            .postingViewStyle(postingViewStyle)
    }
}