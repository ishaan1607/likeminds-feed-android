package com.likeminds.feed.android.core.ui.widgets.post.postmedia.style

import android.text.TextUtils
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.utils.LMFeedViewStyle


class LMFeedPostLinkViewStyle private constructor(
    val linkTitleStyle: LMFeedTextStyle,
    val linkDescriptionStyle: LMFeedTextStyle?,
    val linkUrlStyle: LMFeedTextStyle?,
    val linkImageStyle: LMFeedImageStyle,
    val linkRemoveIconStyle: LMFeedIconStyle?,
    @DimenRes val linkBoxCornerRadius: Int?,
    @DimenRes val linkBoxElevation: Int?,
    @ColorRes val linkBoxStrokeColor: Int?,
    @DimenRes val linkBoxStrokeWidth: Int?,
    @ColorRes val backgroundColor: Int?
) : LMFeedViewStyle {

    class Builder {
        private var linkTitleStyle: LMFeedTextStyle = LMFeedTextStyle.Builder()
            .ellipsize(TextUtils.TruncateAt.END)
            .textSize(R.dimen.lm_feed_text_large)
            .maxLines(2)
            .fontResource(R.font.lm_feed_roboto_medium)
            .textColor(R.color.lm_feed_grey)
            .build()

        private var linkDescriptionStyle: LMFeedTextStyle? = null
        private var linkUrlStyle: LMFeedTextStyle? = null
        private var linkImageStyle: LMFeedImageStyle = LMFeedImageStyle.Builder()
            .placeholderSrc(R.drawable.lm_feed_ic_link)
            .isCircle(false)
            .build()

        private var linkRemoveIconStyle: LMFeedIconStyle? = null

        @DimenRes
        private var linkBoxCornerRadius: Int? = null

        @DimenRes
        private var linkBoxElevation: Int? = null

        @ColorRes
        private var linkBoxStrokeColor: Int? = null

        @DimenRes
        private var linkBoxStrokeWidth: Int? = null

        @ColorRes
        private var backgroundColor: Int? = null

        fun linkTitleStyle(linkTitleStyle: LMFeedTextStyle) = apply {
            this.linkTitleStyle = linkTitleStyle
        }

        fun linkDescriptionStyle(linkDescriptionStyle: LMFeedTextStyle?) = apply {
            this.linkDescriptionStyle = linkDescriptionStyle
        }

        fun linkUrlStyle(linkUrlStyle: LMFeedTextStyle?) = apply {
            this.linkUrlStyle = linkUrlStyle
        }

        fun linkImageStyle(linkImageStyle: LMFeedImageStyle) = apply {
            this.linkImageStyle = linkImageStyle
        }

        fun linkRemoveIconStyle(linkRemoveIconStyle: LMFeedIconStyle?) = apply {
            this.linkRemoveIconStyle = linkRemoveIconStyle
        }

        fun linkBoxCornerRadius(@DimenRes linkBoxCornerRadius: Int?) = apply {
            this.linkBoxCornerRadius = linkBoxCornerRadius
        }

        fun linkBoxElevation(@DimenRes linkBoxElevation: Int?) = apply {
            this.linkBoxElevation = linkBoxElevation
        }

        fun linkBoxStrokeColor(@ColorRes linkBoxStrokeColor: Int?) = apply {
            this.linkBoxStrokeColor = linkBoxStrokeColor
        }

        fun linkBoxStrokeWidth(@DimenRes linkBoxStrokeWidth: Int?) = apply {
            this.linkBoxStrokeWidth = linkBoxStrokeWidth
        }

        fun backgroundColor(@ColorRes backgroundColor: Int?) = apply {
            this.backgroundColor = backgroundColor
        }

        fun build() = LMFeedPostLinkViewStyle(
            linkTitleStyle,
            linkDescriptionStyle,
            linkUrlStyle,
            linkImageStyle,
            linkRemoveIconStyle,
            linkBoxCornerRadius,
            linkBoxElevation,
            linkBoxStrokeColor,
            linkBoxStrokeWidth,
            backgroundColor
        )
    }

    fun toBuilder(): Builder {
        return Builder().linkTitleStyle(linkTitleStyle)
            .linkDescriptionStyle(linkDescriptionStyle)
            .linkUrlStyle(linkUrlStyle)
            .linkImageStyle(linkImageStyle)
            .linkRemoveIconStyle(linkRemoveIconStyle)
            .linkBoxStrokeColor(linkBoxStrokeColor)
            .linkBoxStrokeWidth(linkBoxStrokeWidth)
            .backgroundColor(backgroundColor)
            .linkBoxCornerRadius(linkBoxCornerRadius)
            .linkBoxElevation(linkBoxElevation)
    }
}