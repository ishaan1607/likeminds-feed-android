package com.likeminds.feed.android.core.ui.widgets.postmedia.style

import android.text.TextUtils
import com.likeminds.feed.android.integration.R
import com.likeminds.feed.android.ui.base.styles.LMFeedImageStyle
import com.likeminds.feed.android.ui.base.styles.LMFeedTextStyle

class LMFeedLinkStyle private constructor(
    val linkTitleStyle: LMFeedTextStyle,
    val linkDescriptionStyle: LMFeedTextStyle?,
    val linkUrlStyle: LMFeedTextStyle?,
    val linkImageStyle: LMFeedImageStyle,
) {

    class Builder {
        private var linkTitleStyle: LMFeedTextStyle = LMFeedTextStyle.Builder()
            .ellipsize(TextUtils.TruncateAt.END)
            .textSize(com.likeminds.feed.android.ui.R.dimen.lm_feed_text_large)
            .maxLines(2)
            .fontResource(R.font.lm_feed_core_roboto_medium)
            .textColor(com.likeminds.feed.android.ui.R.color.lm_feed_grey)
            .build()

        private var linkDescriptionStyle: LMFeedTextStyle? = null
        private var linkUrlStyle: LMFeedTextStyle? = null
        private var linkImageStyle: LMFeedImageStyle = LMFeedImageStyle.Builder()
            .placeholderSrc(R.drawable.lm_feed_ic_link)
            .isCircle(false)
            .build()

        fun linkTitleStyle(linkTitleStyle: LMFeedTextStyle) =
            apply { this.linkTitleStyle = linkTitleStyle }

        fun linkDescriptionStyle(linkDescriptionStyle: LMFeedTextStyle?) =
            apply { this.linkDescriptionStyle = linkDescriptionStyle }

        fun linkUrlStyle(linkUrlStyle: LMFeedTextStyle?) =
            apply { this.linkUrlStyle = linkUrlStyle }

        fun linkImageStyle(linkImageStyle: LMFeedImageStyle) =
            apply { this.linkImageStyle = linkImageStyle }

        fun build() = LMFeedLinkStyle(
            linkTitleStyle,
            linkDescriptionStyle,
            linkUrlStyle,
            linkImageStyle
        )
    }

    fun toBuilder(): Builder {
        return Builder().linkTitleStyle(linkTitleStyle)
            .linkDescriptionStyle(linkDescriptionStyle)
            .linkUrlStyle(linkUrlStyle)
            .linkImageStyle(linkImageStyle)
    }
}