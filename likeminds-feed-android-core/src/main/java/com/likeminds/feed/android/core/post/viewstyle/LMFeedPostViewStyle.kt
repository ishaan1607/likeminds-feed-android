package com.likeminds.feed.android.core.post.viewstyle

import android.text.TextUtils
import android.view.View
import com.likeminds.feed.android.integration.R
import com.likeminds.feed.android.ui.base.styles.*
import com.likeminds.feed.android.ui.utils.LMFeedViewStyle
import com.likeminds.feed.android.core.ui.widgets.postfooterview.style.LMFeedPostFooterViewStyle
import com.likeminds.feed.android.core.ui.widgets.postheaderview.style.LMFeedPostHeaderViewStyle

class LMFeedPostViewStyle private constructor(
    //post header layout
    val postHeaderViewStyle: LMFeedPostHeaderViewStyle,
    //post footer layout
    val postFooterViewStyle: LMFeedPostFooterViewStyle
) : LMFeedViewStyle {

    class Builder {
        private var postHeaderViewStyle: LMFeedPostHeaderViewStyle =
            LMFeedPostHeaderViewStyle.Builder()
                .authorImageViewStyle(
                    LMFeedImageStyle.Builder()
                        .isCircle(true)
                        .build()
                )
                .authorNameViewStyle(
                    LMFeedTextStyle.Builder()
                        .textColor(com.likeminds.feed.android.ui.R.color.lm_feed_raisin_black)
                        .textSize(com.likeminds.feed.android.ui.R.dimen.lm_feed_text_large)
                        .maxLines(1)
                        .ellipsize(TextUtils.TruncateAt.END)
                        .build()
                )
                .timestampTextStyle(
                    LMFeedTextStyle.Builder()
                        .textColor(com.likeminds.feed.android.ui.R.color.lm_feed_grey)
                        .textSize(com.likeminds.feed.android.ui.R.dimen.lm_feed_text_small)
                        .fontResource(R.font.lm_feed_core_roboto)
                        .build()
                )
                .postEditedTextStyle(
                    LMFeedTextStyle.Builder()
                        .textColor(com.likeminds.feed.android.ui.R.color.lm_feed_grey)
                        .textSize(com.likeminds.feed.android.ui.R.dimen.lm_feed_text_small)
                        .fontResource(R.font.lm_feed_core_roboto)
                        .maxLines(1)
                        .build()
                )
                .authorCustomTitleTextStyle(
                    LMFeedTextStyle.Builder()
                        .textColor(com.likeminds.feed.android.ui.R.color.lm_feed_white)
                        .textSize(com.likeminds.feed.android.ui.R.dimen.lm_feed_text_small)
                        .fontResource(R.font.lm_feed_core_roboto)
                        .maxLines(1)
                        .fontResource(R.font.lm_feed_core_roboto_medium)
                        .build()
                )
                .pinIconStyle(
                    LMFeedIconStyle.Builder()
                        .inActiveSrc(com.likeminds.feed.android.ui.R.drawable.lm_feed_ic_pin)
                        .build()
                )
                .menuIconStyle(
                    LMFeedIconStyle.Builder()
                        .inActiveSrc(com.likeminds.feed.android.ui.R.drawable.lm_feed_ic_overflow_menu)
                        .build()
                )
                .build()

        private var postFooterViewStyle: LMFeedPostFooterViewStyle =
            LMFeedPostFooterViewStyle.Builder()
                .likeIconStyle(
                    LMFeedIconStyle.Builder()
                        .activeSrc(com.likeminds.feed.android.ui.R.drawable.lm_feed_ic_like_filled)
                        .inActiveSrc(com.likeminds.feed.android.ui.R.drawable.lm_feed_ic_like_unfilled)
                        .isCircle(false)
                        .showGreyScale(false)
                        .build()
                )
                .likeTextStyle(
                    LMFeedTextStyle.Builder()
                        .textColor(com.likeminds.feed.android.ui.R.color.lm_feed_grey)
                        .textSize(com.likeminds.feed.android.ui.R.dimen.lm_feed_text_medium)
                        .fontResource(com.likeminds.feed.android.ui.R.font.lm_feed_ui_roboto)
                        .textAllCaps(false)
                        .textAlignment(View.TEXT_ALIGNMENT_CENTER)
                        .build()
                )
                .commentTextStyle(
                    LMFeedTextStyle.Builder()
                        .textColor(com.likeminds.feed.android.ui.R.color.lm_feed_grey)
                        .textSize(com.likeminds.feed.android.ui.R.dimen.lm_feed_text_medium)
                        .fontResource(com.likeminds.feed.android.ui.R.font.lm_feed_ui_roboto)
                        .textAllCaps(false)
                        .textAlignment(View.TEXT_ALIGNMENT_CENTER)
                        .drawableLeftSrc(com.likeminds.feed.android.ui.R.drawable.lm_feed_ic_comment)
                        .drawablePadding(com.likeminds.feed.android.ui.R.dimen.lm_feed_padding_big)
                        .build()
                )
                .saveIconStyle(
                    LMFeedIconStyle.Builder()
                        .activeSrc(com.likeminds.feed.android.ui.R.drawable.lm_feed_ic_bookmark_filled)
                        .inActiveSrc(com.likeminds.feed.android.ui.R.drawable.lm_feed_ic_bookmark_unfilled)
                        .isCircle(false)
                        .showGreyScale(false)
                        .build()
                )
                .shareIconStyle(
                    LMFeedIconStyle.Builder()
                        .inActiveSrc(com.likeminds.feed.android.ui.R.drawable.lm_feed_ic_share)
                        .isCircle(false)
                        .showGreyScale(false)
                        .build()
                )
                .build()

        fun postHeaderViewStyle(postHeaderViewStyle: LMFeedPostHeaderViewStyle) = apply {
            this.postHeaderViewStyle = postHeaderViewStyle
        }

        fun postFooterViewStyle(postFooterViewStyle: LMFeedPostFooterViewStyle) = apply {
            this.postFooterViewStyle = postFooterViewStyle
        }

        fun build() = LMFeedPostViewStyle(
            postHeaderViewStyle,
            postFooterViewStyle
        )
    }

    fun toBuilder(): Builder {
        return Builder().postHeaderViewStyle(postHeaderViewStyle)
            .postFooterViewStyle(postFooterViewStyle)
    }
}