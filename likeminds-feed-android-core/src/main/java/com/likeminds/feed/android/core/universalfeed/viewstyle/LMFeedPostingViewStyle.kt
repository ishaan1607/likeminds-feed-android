package com.likeminds.feed.android.core.universalfeed.viewstyle

import com.likeminds.feed.android.ui.base.styles.*

class LMFeedPostingViewStyle private constructor(
    val attachmentThumbnailImageStyle: LMFeedImageStyle?,
    val postingHeadingTextStyle: LMFeedTextStyle,
    val progressStyle: LMFeedProgressBarStyle,
    val retryButtonTextStyle: LMFeedTextStyle,
    val postingDoneImageStyle: LMFeedImageStyle
) {

    class Builder {
        private var attachmentThumbnailImageStyle: LMFeedImageStyle? = null
        private var postingHeadingTextStyle: LMFeedTextStyle = LMFeedTextStyle.Builder().build()
        private var progressStyle: LMFeedProgressBarStyle = LMFeedProgressBarStyle.Builder().build()
        private var retryButtonTextStyle: LMFeedTextStyle = LMFeedTextStyle.Builder().build()
        private var postingDoneImageStyle: LMFeedImageStyle = LMFeedImageStyle.Builder().build()


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

        fun build() = LMFeedPostingViewStyle(
            attachmentThumbnailImageStyle,
            postingHeadingTextStyle,
            progressStyle,
            retryButtonTextStyle,
            postingDoneImageStyle
        )
    }

    fun toBuilder(): Builder {
        return Builder().attachmentThumbnailImageStyle(attachmentThumbnailImageStyle)
            .postingDoneImageStyle(postingDoneImageStyle)
            .progressStyle(progressStyle)
            .retryButtonTextStyle(retryButtonTextStyle)
            .postingHeadingTextStyle(postingHeadingTextStyle)
    }
}