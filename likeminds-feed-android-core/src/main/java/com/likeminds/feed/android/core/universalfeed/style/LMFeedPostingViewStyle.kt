package com.likeminds.feed.android.core.universalfeed.style

import com.likeminds.feed.android.core.utils.LMFeedViewStyle
import com.likeminds.feed.android.core.ui.base.styles.*
import android.text.TextUtils
import androidx.annotation.ColorRes
import com.likeminds.feed.android.core.R


class LMFeedPostingViewStyle private constructor(
    val attachmentThumbnailImageStyle: LMFeedImageStyle?,
    val postingHeadingTextStyle: LMFeedTextStyle,
    val progressStyle: LMFeedProgressBarStyle,
    val retryButtonTextStyle: LMFeedTextStyle,
    val postingDoneImageStyle: LMFeedImageStyle,
    @ColorRes val backgroundColor: Int?
) : LMFeedViewStyle {

    class Builder {
        private var attachmentThumbnailImageStyle: LMFeedImageStyle? = null
        private var postingHeadingTextStyle: LMFeedTextStyle = LMFeedTextStyle.Builder()
            .textSize(R.dimen.lm_feed_text_medium)
            .textColor(R.color.lm_feed_dark_grey)
            .ellipsize(TextUtils.TruncateAt.END)
            .build()
        private var progressStyle: LMFeedProgressBarStyle = LMFeedProgressBarStyle.Builder()
            .isIndeterminate(false)
            .progressColor(R.color.lm_feed_majorelle_blue)
            .build()
        private var retryButtonTextStyle: LMFeedTextStyle = LMFeedTextStyle.Builder()
            .textColor(R.color.lm_feed_grey)
            .textSize(R.dimen.lm_feed_text_medium)
            .ellipsize(TextUtils.TruncateAt.END)
            .build()
        private var postingDoneImageStyle: LMFeedImageStyle = LMFeedImageStyle.Builder()
            .isCircle(true)
            .imageSrc(R.drawable.lm_feed_ic_completed_green)
            .build()

        @ColorRes
        private var backgroundColor: Int? = null


        fun attachmentThumbnailImageStyle(attachmentThumbnailImageStyle: LMFeedImageStyle?) =
            apply { this.attachmentThumbnailImageStyle = attachmentThumbnailImageStyle }

        fun postingHeadingTextStyle(postingHeadingTextStyle: LMFeedTextStyle) =
            apply { this.postingHeadingTextStyle = postingHeadingTextStyle }

        fun progressStyle(progressStyle: LMFeedProgressBarStyle) =
            apply { this.progressStyle = progressStyle }

        fun retryButtonTextStyle(retryButtonTextStyle: LMFeedTextStyle) =
            apply { this.retryButtonTextStyle = retryButtonTextStyle }

        fun postingDoneImageStyle(postingDoneImageStyle: LMFeedImageStyle) =
            apply { this.postingDoneImageStyle = postingDoneImageStyle }

        fun backgroundColor(backgroundColor: Int?) = apply {
            this.backgroundColor = backgroundColor
        }

        fun build() = LMFeedPostingViewStyle(
            attachmentThumbnailImageStyle,
            postingHeadingTextStyle,
            progressStyle,
            retryButtonTextStyle,
            postingDoneImageStyle,
            backgroundColor
        )
    }

    fun toBuilder(): Builder {
        return Builder().attachmentThumbnailImageStyle(attachmentThumbnailImageStyle)
            .postingDoneImageStyle(postingDoneImageStyle)
            .progressStyle(progressStyle)
            .retryButtonTextStyle(retryButtonTextStyle)
            .postingHeadingTextStyle(postingHeadingTextStyle)
            .backgroundColor(backgroundColor)
    }
}