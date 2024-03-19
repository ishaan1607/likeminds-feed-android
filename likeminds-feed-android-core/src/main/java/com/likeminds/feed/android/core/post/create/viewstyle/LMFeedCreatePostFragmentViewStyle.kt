package com.likeminds.feed.android.core.post.create.viewstyle

import android.text.TextUtils
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.LMFeedIconStyle
import com.likeminds.feed.android.core.ui.base.styles.LMFeedImageStyle
import com.likeminds.feed.android.core.ui.base.styles.LMFeedTextStyle
import com.likeminds.feed.android.core.ui.widgets.headerview.style.LMFeedHeaderViewStyle
import com.likeminds.feed.android.core.ui.widgets.post.postheaderview.style.LMFeedPostHeaderViewStyle
import com.likeminds.feed.android.core.utils.LMFeedViewStyle
import com.likeminds.feed.android.core.utils.model.LMFeedPadding

class LMFeedCreatePostFragmentViewStyle private constructor(
    //header view style
    val headerViewStyle: LMFeedHeaderViewStyle,
    //author frame view style
    val authorViewStyle: LMFeedPostHeaderViewStyle
) : LMFeedViewStyle {
    class Builder {
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
            .submitTextStyle(
                LMFeedTextStyle.Builder()
                    .textColor(R.color.lm_feed_grey)
                    .textSize(R.dimen.lm_feed_text_medium)
                    .fontResource(R.font.lm_feed_roboto_medium)
                    .build()
            )
            .activeSubmitColor(R.color.lm_feed_majorelle_blue)
            .build()

        private var authorViewStyle: LMFeedPostHeaderViewStyle = LMFeedPostHeaderViewStyle.Builder()
            .authorImageViewStyle(
                LMFeedImageStyle.Builder()
                    .isCircle(true)
                    .build()
            )
            .authorNameViewStyle(
                LMFeedTextStyle.Builder()
                    .textColor(R.color.lm_feed_raisin_black)
                    .textSize(R.dimen.lm_feed_text_large)
                    .maxLines(1)
                    .ellipsize(TextUtils.TruncateAt.END)
                    .build()
            )
            .build()

        fun headerViewStyle(headerViewStyle: LMFeedHeaderViewStyle) = apply {
            this.headerViewStyle = headerViewStyle
        }

        fun authorViewStyle(authorViewStyle: LMFeedPostHeaderViewStyle) =
            apply { this.authorViewStyle = authorViewStyle }

        fun build() = LMFeedCreatePostFragmentViewStyle(
            headerViewStyle,
            authorViewStyle
        )
    }

    fun toBuilder(): Builder {
        return Builder().headerViewStyle(headerViewStyle)
            .authorViewStyle(authorViewStyle)
    }
}