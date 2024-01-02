package com.likeminds.feed.android.ui.base.styles

import android.graphics.Typeface
import android.graphics.fonts.FontStyle
import android.text.TextUtils.TruncateAt
import android.widget.TextView
import androidx.annotation.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.likeminds.feed.android.ui.R
import com.likeminds.feed.android.ui.base.views.*
import com.likeminds.feed.android.ui.theme.LMFeedTheme
import com.likeminds.feed.android.ui.utils.ViewStyle
import com.likeminds.feed.android.ui.utils.model.LMFeedPadding

class LMFeedTextStyle private constructor(
    @ColorRes val textColor: Int,
    @DimenRes val textSize: Int,
    val textAllCaps: Boolean,
    @FontRes val fontResource: Int?,
    val fontAssetsPath: String?,
    val typeface: Int,
    val maxLines: Int?,
    val ellipsize: TruncateAt?,
    val padding: LMFeedPadding?,
    @ColorRes val backgroundColor: Int?
) : ViewStyle {

    class Builder {
        @ColorRes
        private var textColor: Int = R.color.black
        private var textSize: Int = R.dimen.lm_feed_ui_text_small
        private var textAllCaps: Boolean = false

        @FontRes
        private var fontResource: Int? = null
        private var fontAssetsPath: String? = null
        private var typeface: Int = Typeface.NORMAL
        private var maxLines: Int? = null
        private var ellipsize: TruncateAt? = null
        private var padding: LMFeedPadding? = null

        @ColorRes
        private var backgroundColor: Int? = null

        fun textColor(@ColorRes textColor: Int) = apply {
            this.textColor = textColor
        }

        fun textSize(textSize: Int) = apply {
            this.textSize = textSize
        }

        fun textAllCaps(textAllCaps: Boolean) = apply {
            this.textAllCaps = textAllCaps
        }

        fun fontResource(@FontRes fontResource: Int?) = apply {
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

        fun padding(padding: LMFeedPadding?) = apply {
            this.padding = padding
        }

        fun backgroundColor(@ColorRes backgroundColor: Int?) = apply {
            this.backgroundColor = backgroundColor
        }

        fun build() = LMFeedTextStyle(
            textColor,
            textSize,
            textAllCaps,
            fontResource,
            fontAssetsPath,
            typeface,
            maxLines,
            ellipsize,
            padding,
            backgroundColor
        )
    }

    fun toBuilder(): Builder {
        return Builder().textColor(textColor)
            .textSize(textSize)
            .textAllCaps(textAllCaps)
            .fontResource(fontResource)
            .fontAssetsPath(fontAssetsPath)
            .typeface(typeface)
            .maxLines(maxLines)
            .ellipsize(ellipsize)
            .padding(padding)
            .backgroundColor(backgroundColor)
    }

    fun apply(lmFeedTextView: LMFeedTextView) {
        applyImpl(lmFeedTextView)
    }

    fun apply(lmFeedEditText: LMFeedEditText) {
        applyImpl(lmFeedEditText)
    }

    fun apply(lmFeedButton: LMFeedButton) {
        applyImpl(lmFeedButton)
    }

    fun apply(lmFeedFAB: LMFeedFAB) {
        applyImpl(lmFeedFAB)
    }

    private fun applyImpl(textView: TextView) {
        textView.apply {
            val textColor = ContextCompat.getColor(context, this@LMFeedTextStyle.textColor)
            this.setTextColor(textColor)

            this.textSize = this@LMFeedTextStyle.textSize.toFloat()

            this.isAllCaps = textAllCaps

            if (this@LMFeedTextStyle.maxLines != null) {
                this.maxLines = this@LMFeedTextStyle.maxLines
            }

            if (this@LMFeedTextStyle.ellipsize != null) {
                this.ellipsize = this@LMFeedTextStyle.ellipsize
            }

            if (this@LMFeedTextStyle.backgroundColor != null) {
                val backgroundColor =
                    ContextCompat.getColor(context, this@LMFeedTextStyle.backgroundColor)
                this.setBackgroundColor(backgroundColor)
            }

            setFont(this)

            if (padding != null) {
                setPadding(
                    padding.paddingLeft,
                    padding.paddingTop,
                    padding.paddingRight,
                    padding.paddingBottom
                )
            }
        }
    }

    private fun setFont(textView: TextView) {
        textView.apply {
            val defaultFont = LMFeedTheme.getFontResources()
            when {
                fontResource != null -> {
                    val font = ResourcesCompat.getFont(context, fontResource)
                    setTypeface(font, this@LMFeedTextStyle.typeface)
                }

                fontAssetsPath != null -> {
                    val font = Typeface.createFromAsset(context.assets, fontAssetsPath)
                    setTypeface(font, this@LMFeedTextStyle.typeface)
                }

                defaultFont.first != null -> {
                    val font = ResourcesCompat.getFont(context, (defaultFont.first ?: 0))
                    setTypeface(font, this@LMFeedTextStyle.typeface)
                }

                else -> {
                    val font = Typeface.createFromAsset(context.assets, defaultFont.second)
                    setTypeface(font, this@LMFeedTextStyle.typeface)
                }
            }
        }
    }

    override fun toString(): String {
        return """
            textSize: $textSize
            textAllCaps: $textAllCaps
            textColor: $textColor
            fontResource: $fontResource
            padding: $padding
        """.trimIndent()
    }
}

fun LMFeedTextView.setStyle(viewStyle: LMFeedTextStyle) {
    viewStyle.apply(this)
}