package com.likeminds.feed.android.core.ui.widgets.postmedia.style

import android.text.TextUtils
import com.likeminds.feed.android.core.util.LMFeedMediaUtils
import com.likeminds.feed.android.integration.R
import com.likeminds.feed.android.ui.base.styles.LMFeedIconStyle
import com.likeminds.feed.android.ui.base.styles.LMFeedTextStyle

class LMFeedDocumentsStyle private constructor(
    val documentNameStyle: LMFeedTextStyle,
    val documentIconStyle: LMFeedIconStyle?,
    val documentPageCountStyle: LMFeedTextStyle?,
    val documentSizeStyle: LMFeedTextStyle?,
    val documentTypeStyle: LMFeedTextStyle?,
    val documentsLimit: Int,
    val documentsSizeLimit: Long,
    val visibleDocumentsLimit: Int?,
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
        private var documentsLimit: Int = LMFeedMediaUtils.DEFAULT_DOCUMENTS_LIMIT
        private var documentsSizeLimit: Long = LMFeedMediaUtils.DEFAULT_DOCUMENTS_SIZE_LIMIT
        private var visibleDocumentsLimit: Int? = null

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

        fun documentsLimit(documentsLimit: Int) = apply { this.documentsLimit = documentsLimit }
        fun documentsSizeLimit(documentsSizeLimit: Long) =
            apply { this.documentsSizeLimit = documentsSizeLimit }

        fun visibleDocumentsLimit(visibleDocumentsLimit: Int?) =
            apply { this.visibleDocumentsLimit = visibleDocumentsLimit }

        fun build() = LMFeedDocumentsStyle(
            documentNameStyle,
            documentIconStyle,
            documentPageCountStyle,
            documentSizeStyle,
            documentTypeStyle,
            documentsLimit,
            documentsSizeLimit,
            visibleDocumentsLimit
        )
    }

    fun toBuilder(): Builder {
        return Builder().documentNameStyle(documentNameStyle)
            .documentIconStyle(documentIconStyle)
            .documentPageCountStyle(documentPageCountStyle)
            .documentSizeStyle(documentSizeStyle)
            .documentTypeStyle(documentTypeStyle)
            .documentsLimit(documentsLimit)
            .documentsSizeLimit(documentsSizeLimit)
            .visibleDocumentsLimit(visibleDocumentsLimit)
    }
}