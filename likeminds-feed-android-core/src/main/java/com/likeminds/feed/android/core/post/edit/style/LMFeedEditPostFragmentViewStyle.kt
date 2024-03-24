package com.likeminds.feed.android.core.post.edit.style

import android.text.TextUtils
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.ui.widgets.headerview.style.LMFeedHeaderViewStyle
import com.likeminds.feed.android.core.utils.LMFeedViewStyle
import com.likeminds.feed.android.core.utils.model.LMFeedPadding

/**
 * [LMFeedEditPostFragmentViewStyle] helps you to customize the universal feed fragment [LMFeedEditPostFragment]
 *
 * @property headerViewStyle : [LMFeedHeaderViewStyle] this will help you to customize the header view in the edit post fragment
 * @property postComposerStyle : [LMFeedEditTextStyle] this will help you to customize the post composer edit text in the edit post fragment
 * @property progressBarStyle: [LMFeedProgressBarStyle] this will help you to customize the progress bar in the edit post fragment
 * */
class LMFeedEditPostFragmentViewStyle private constructor(
    //header view style
    val headerViewStyle: LMFeedHeaderViewStyle,
    //post composer view style
    val postComposerStyle: LMFeedEditTextStyle,
    //progress bar view style
    val progressBarStyle: LMFeedProgressBarStyle
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

        private var postComposerStyle: LMFeedEditTextStyle = LMFeedEditTextStyle.Builder()
            .inputTextStyle(
                LMFeedTextStyle.Builder()
                    .ellipsize(TextUtils.TruncateAt.END)
                    .maxHeight(R.dimen.lm_feed_post_composer_max_height)
                    .minHeight(R.dimen.lm_feed_post_composer_min_height)
                    .textColor(R.color.lm_feed_dark_grey)
                    .textSize(R.dimen.lm_feed_text_medium)
                    .build()
            )
            .hintTextColor(R.color.lm_feed_maastricht_blue_40)
            .build()

        private var progressBarStyle: LMFeedProgressBarStyle = LMFeedProgressBarStyle.Builder()
            .isIndeterminate(false)
            .progressColor(R.color.lm_feed_majorelle_blue)
            .build()

        fun headerViewStyle(headerViewStyle: LMFeedHeaderViewStyle) = apply {
            this.headerViewStyle = headerViewStyle
        }

        fun postComposerStyle(postComposerStyle: LMFeedEditTextStyle) = apply {
            this.postComposerStyle = postComposerStyle
        }

        fun progressBarStyle(progressBarStyle: LMFeedProgressBarStyle) = apply {
            this.progressBarStyle = progressBarStyle
        }

        fun build() = LMFeedEditPostFragmentViewStyle(
            headerViewStyle,
            postComposerStyle,
            progressBarStyle
        )
    }

    fun toBuilder(): Builder {
        return Builder().headerViewStyle(headerViewStyle)
            .postComposerStyle(postComposerStyle)
            .progressBarStyle(progressBarStyle)
    }
}