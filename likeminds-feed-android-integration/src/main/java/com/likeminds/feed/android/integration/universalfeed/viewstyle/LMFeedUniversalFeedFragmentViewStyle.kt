package com.likeminds.feed.android.integration.universalfeed.viewstyle

import android.text.TextUtils
import com.likeminds.feed.android.integration.R
import com.likeminds.feed.android.ui.base.styles.LMFeedFABStyle
import com.likeminds.feed.android.ui.base.styles.LMFeedTextStyle
import com.likeminds.feed.android.ui.utils.LMFeedViewStyle
import com.likeminds.feed.android.ui.utils.model.LMFeedPadding
import com.likeminds.feed.android.ui.widgets.headerview.styles.LMFeedHeaderViewStyle

class LMFeedUniversalFeedFragmentViewStyle private constructor(
    val createNewPostButtonViewStyle: LMFeedFABStyle,
    //header
    val headerViewStyle: LMFeedHeaderViewStyle
) : LMFeedViewStyle {

    class Builder {

        private var createNewPostButtonViewStyle: LMFeedFABStyle = LMFeedFABStyle.Builder()
            .isExtended(false)
            .backgroundColor(com.likeminds.feed.android.ui.R.color.lm_feed_majorelle_blue)
            .icon(R.drawable.lm_feed_ic_new_post_plus)
            .iconTint(com.likeminds.feed.android.ui.R.color.lm_feed_white)
            .iconSize(R.dimen.lm_feed_core_create_new_post_icon_size)
            .textColor(com.likeminds.feed.android.ui.R.color.lm_feed_blue)
            .textAllCaps(true)
            .padding(
                LMFeedPadding(
                    R.dimen.lm_feed_core_create_new_post_padding,
                    0,
                    R.dimen.lm_feed_core_create_new_post_padding,
                    0
                )
            )
            .build()

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
            .searchIcon(R.drawable.lm_feed_ic_search_icon)
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

        fun createNewPostButtonViewStyle(createNewPostButtonViewStyle: LMFeedFABStyle) = apply {
            this.createNewPostButtonViewStyle = createNewPostButtonViewStyle
        }

        fun headerViewStyle(headerViewStyle: LMFeedHeaderViewStyle) = apply {
            this.headerViewStyle = headerViewStyle
        }

        fun build() = LMFeedUniversalFeedFragmentViewStyle(
            createNewPostButtonViewStyle,
            headerViewStyle
        )
    }

    fun toBuilder(): Builder {
        return Builder().headerViewStyle(headerViewStyle)
            .createNewPostButtonViewStyle(createNewPostButtonViewStyle)
    }
}