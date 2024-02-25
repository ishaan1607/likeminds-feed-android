package com.likeminds.feed.android.core.ui.base.styles

import android.graphics.Typeface
import android.text.TextUtils.TruncateAt
import android.util.TypedValue
import android.widget.TextView
import androidx.annotation.*
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.views.*
import com.likeminds.feed.android.core.ui.theme.LMFeedTheme
import com.likeminds.feed.android.core.utils.LMFeedViewStyle

class LMFeedTextStyle private constructor(
    @ColorRes val textColor: Int,
    @DimenRes val textSize: Int,
    val textAllCaps: Boolean,
    @FontRes val fontResource: Int?,
    val fontAssetsPath: String?,
    val typeface: Int,
    val maxLines: Int?,
    val ellipsize: TruncateAt?,
    @ColorRes val backgroundColor: Int?,
    val textAlignment: Int?,
    @DrawableRes val drawableLeftSrc: Int?,
    @DrawableRes val drawableTopSrc: Int?,
    @DrawableRes val drawableRightSrc: Int?,
    @DrawableRes val drawableBottomSrc: Int?,
    @DimenRes val drawablePadding: Int?
) : LMFeedViewStyle {

    class Builder {
        @ColorRes
        private var textColor: Int = R.color.lm_feed_black

        @DimenRes
        private var textSize: Int = R.dimen.lm_feed_text_small
        private var textAllCaps: Boolean = false

        @FontRes
        private var fontResource: Int? = null
        private var fontAssetsPath: String? = null
        private var typeface: Int = Typeface.NORMAL
        private var maxLines: Int? = null
        private var ellipsize: TruncateAt? = null

        @ColorRes
        private var backgroundColor: Int? = null
        private var textAlignment: Int? = null

        @DrawableRes
        private var drawableLeftSrc: Int? = null

        @DrawableRes
        private var drawableTopSrc: Int? = null

        @DrawableRes
        private var drawableRightSrc: Int? = null

        @DrawableRes
        private var drawableBottomSrc: Int? = null

        @DimenRes
        private var drawablePadding: Int? = null

        fun textColor(@ColorRes textColor: Int) = apply {
            this.textColor = textColor
        }

        fun textSize(@DimenRes textSize: Int) = apply {
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

        fun backgroundColor(@ColorRes backgroundColor: Int?) = apply {
            this.backgroundColor = backgroundColor
        }

        fun textAlignment(textAlignment: Int?) = apply {
            this.textAlignment = textAlignment
        }

        fun drawableLeftSrc(@DrawableRes drawableLeftSrc: Int?) = apply {
            this.drawableLeftSrc = drawableLeftSrc
        }

        fun drawableTopSrc(@DrawableRes drawableTopSrc: Int?) = apply {
            this.drawableTopSrc = drawableTopSrc
        }

        fun drawableRightSrc(@DrawableRes drawableRightSrc: Int?) = apply {
            this.drawableRightSrc = drawableRightSrc
        }

        fun drawableBottomSrc(@DrawableRes drawableBottomSrc: Int?) = apply {
            this.drawableBottomSrc = drawableBottomSrc
        }

        fun drawablePadding(@DimenRes drawablePadding: Int?) = apply {
            this.drawablePadding = drawablePadding
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
            backgroundColor,
            textAlignment,
            drawableLeftSrc,
            drawableTopSrc,
            drawableRightSrc,
            drawableBottomSrc,
            drawablePadding
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
            .backgroundColor(backgroundColor)
            .textAlignment(textAlignment)
            .drawableLeftSrc(drawableLeftSrc)
            .drawableTopSrc(drawableTopSrc)
            .drawableRightSrc(drawableRightSrc)
            .drawableBottomSrc(drawableRightSrc)
            .drawablePadding(drawablePadding)
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

            this.textAlignment = textAlignment

            this.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                context.resources.getDimension(this@LMFeedTextStyle.textSize)
            )

            this.isAllCaps = textAllCaps

            if (this@LMFeedTextStyle.maxLines != null && this@LMFeedTextStyle.ellipsize != null) {
                this.maxLines = this@LMFeedTextStyle.maxLines
                this.ellipsize = this@LMFeedTextStyle.ellipsize
            }

            if (this@LMFeedTextStyle.backgroundColor != null) {
                val backgroundColor =
                    ContextCompat.getColor(context, this@LMFeedTextStyle.backgroundColor)
                this.setBackgroundColor(backgroundColor)
            }

            // sets drawable to the text view
            setCompoundDrawablesWithIntrinsicBounds(
                drawableLeftSrc ?: 0,
                drawableTopSrc ?: 0,
                drawableRightSrc ?: 0,
                drawableBottomSrc ?: 0
            )

            // sets drawable padding
            if (drawablePadding != null) {
                compoundDrawablePadding = resources.getDimensionPixelOffset(drawablePadding)
            }

            setFont(this)
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
        """.trimIndent()
    }
}

fun LMFeedTextView.setStyle(viewStyle: LMFeedTextStyle) {
    viewStyle.apply(this)
}