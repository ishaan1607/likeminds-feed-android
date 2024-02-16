package com.likeminds.feed.android.core.ui.widgets.postmedia.style

import android.text.TextUtils
import androidx.annotation.ColorRes
import com.likeminds.feed.android.integration.R
import com.likeminds.feed.android.ui.base.styles.LMFeedIconStyle
import com.likeminds.feed.android.ui.base.styles.LMFeedTextStyle

class LMFeedPostDocumentsMediaStyle private constructor(
    val documentNameStyle: LMFeedTextStyle,
    val documentIconStyle: LMFeedIconStyle?,
    val documentPageCountStyle: LMFeedTextStyle?,
    val documentSizeStyle: LMFeedTextStyle?,
    val documentTypeStyle: LMFeedTextStyle?,
    val visibleDocumentsLimit: Int?,
    @ColorRes val backgroundColor: Int?
) {

    class Builder {
        private var documentNameStyle: LMFeedTextStyle = LMFeedTextStyle.Builder()
            .maxLines(1)
            .textColor(com.likeminds.feed.android.ui.R.color.lm_feed_grey)
            .textSize(com.likeminds.feed.android.ui.R.dimen.lm_feed_text_large)
            .fontResource(R.font.lm_feed_core_roboto_medium)
            .ellipsize(TextUtils.TruncateAt.END)
            .build()

        private var documentIconStyle: LMFeedIconStyle? = null
        private var documentPageCountStyle: LMFeedTextStyle? = null
        private var documentSizeStyle: LMFeedTextStyle? = null
        private var documentTypeStyle: LMFeedTextStyle? = null
        private var visibleDocumentsLimit: Int? = null

        @ColorRes
        private var backgroundColor: Int? = null

        fun documentNameStyle(documentNameStyle: LMFeedTextStyle) =
            apply { this.documentNameStyle = documentNameStyle }

        fun documentIconStyle(documentIconStyle: LMFeedIconStyle?) =
            apply { this.documentIconStyle = documentIconStyle }

        fun documentPageCountStyle(documentPageCountStyle: LMFeedTextStyle?) =
            apply { this.documentPageCountStyle = documentPageCountStyle }

        fun documentSizeStyle(documentSizeStyle: LMFeedTextStyle?) =
            apply { this.documentSizeStyle = documentSizeStyle }

        fun documentTypeStyle(documentTypeStyle: LMFeedTextStyle?) =
            apply { this.documentTypeStyle = documentTypeStyle }

        fun visibleDocumentsLimit(visibleDocumentsLimit: Int?) =
            apply { this.visibleDocumentsLimit = visibleDocumentsLimit }

        fun backgroundColor(@ColorRes backgroundColor: Int?) =
            apply { this.backgroundColor = backgroundColor }

        fun build() = LMFeedPostDocumentsMediaStyle(
            documentNameStyle,
            documentIconStyle,
            documentPageCountStyle,
            documentSizeStyle,
            documentTypeStyle,
            visibleDocumentsLimit,
            backgroundColor
        )
    }

    fun toBuilder(): Builder {
        return Builder().documentNameStyle(documentNameStyle)
            .documentIconStyle(documentIconStyle)
            .documentPageCountStyle(documentPageCountStyle)
            .documentSizeStyle(documentSizeStyle)
            .documentTypeStyle(documentTypeStyle)
            .visibleDocumentsLimit(visibleDocumentsLimit)
            .backgroundColor(backgroundColor)
    }
}