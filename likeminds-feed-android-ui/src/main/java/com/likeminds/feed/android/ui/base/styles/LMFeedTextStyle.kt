package com.likeminds.feed.android.ui.base.styles

import android.graphics.Typeface
import android.text.TextUtils.TruncateAt
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.likeminds.feed.android.ui.R
import com.likeminds.feed.android.ui.base.views.LMFeedTextView
import com.likeminds.feed.android.ui.utils.ViewStyle

class LMFeedTextStyle private constructor(
    val textColor: Int,
    val textSize: Int,
    val fontResource: Int?,
    val fontAssetsPath: String?,
    val typeface: Int,
    val maxLines: Int?,
    val ellipsize: TruncateAt?
) : ViewStyle {

    class Builder {
        private var textColor: Int = R.color.black
        private var textSize: Int = R.dimen.lm_feed_ui_text_small
        private var fontResource: Int? = null
        private var fontAssetsPath: String? = null
        private var typeface: Int = Typeface.NORMAL
        private var maxLines: Int? = null
        private var ellipsize: TruncateAt? = null

        fun textColor(textColor: Int) = apply {
            this.textColor = textColor
        }

        fun textSize(textSize: Int) = apply {
            this.textSize = textSize
        }

        fun fontResource(fontResource: Int?) = apply {
            this.fontResource = fontResource
        }

        fun fontAssetsPath(fontAssetsPath: String?) = apply {
            this.fontAssetsPath = fontAssetsPath
        }

        fun typeface(typeface: Int) = apply {
            this.typeface = typeface
        }

        fun maxLines(maxLines: Int?) = apply {
            this.maxLines = maxLines
        }

        fun ellipsize(ellipsize: TruncateAt?) = apply {
            this.ellipsize = ellipsize
        }

        fun build() = LMFeedTextStyle(
            textColor,
            textSize,
            fontResource,
            fontAssetsPath,
            typeface,
            maxLines,
            ellipsize
        )
    }

    fun toBuilder(): Builder {
        return Builder().textColor(textColor)
            .textSize(textSize)
            .fontResource(fontResource)
            .fontAssetsPath(fontAssetsPath)
            .typeface(typeface)
            .maxLines(maxLines)
            .ellipsize(ellipsize)
    }

    fun apply(textView: LMFeedTextView) {
        textView.apply {
            val txtColor = ContextCompat.getColor(context, this@LMFeedTextStyle.textColor)
            this.setTextColor(txtColor)
            this.textSize = this@LMFeedTextStyle.textSize.toFloat()
        }
    }
}

fun LMFeedTextView.setStyle(viewStyle: LMFeedTextStyle) {
    viewStyle.apply(this)
}