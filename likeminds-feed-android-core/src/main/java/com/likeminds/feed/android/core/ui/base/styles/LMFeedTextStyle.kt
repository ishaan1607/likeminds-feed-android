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

/**
 * [LMFeedTextStyle] helps you to customize a [LMFeedTextView] with the following properties
 * @property textColor : [Int] to customize the color of the text
 * @property textSize: [Int] should be in format of [DimenRes] to customize the size of the text | Default value =  [R.dimen.lm_feed_ui_text_small]
 * @property textAllCaps: [Boolean] to customize whether the text should be all caps or not | Default value = [false]
 * @property fontResource: [Int] should be in format of [FontRes] to customize the font of the text using font resources | Default value = [null]
 * @property fontAssetsPath: [String] to to customize the font of the text using the path of the font assets | Default value = [null]
 * @property typeface: [Int] to customize the typeface of the button | Default value = [null]
 * @property maxLines:[Int] to customize the max lines for a text | Default value = [null]
 * @property ellipsize: [TruncateAt] to customize how to ellipsize the text  | Default value = [null]
 * @property padding: [LMFeedPadding] to customize the padding of the text | Default value = [null]
 * @property backgroundColor: [Int] should be in format of [ColorRes] to customize the background color of the text | Default value = [null]
 **/
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

    // applies the [LMFeedTextStyle] to [LMFeedTextView]
    fun apply(lmFeedTextView: LMFeedTextView) {
        applyImpl(lmFeedTextView)
    }

    // applies the [LMFeedTextStyle] to [LMFeedEditText]
    fun apply(lmFeedEditText: LMFeedEditText) {
        applyImpl(lmFeedEditText)
    }

    // applies the [LMFeedTextStyle] to [LMFeedButton]
    fun apply(lmFeedButton: LMFeedButton) {
        applyImpl(lmFeedButton)
    }

    // applies the [LMFeedTextStyle] to [LMFeedFAB]
    fun apply(lmFeedFAB: LMFeedFAB) {
        applyImpl(lmFeedFAB)
    }

    private fun applyImpl(textView: TextView) {
        textView.apply {
            val textColor = ContextCompat.getColor(context, this@LMFeedTextStyle.textColor)

            // sets the text color
            this.setTextColor(textColor)

            this.textAlignment = textAlignment

            this.setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                context.resources.getDimension(this@LMFeedTextStyle.textSize)
            )

            // sets whether the text is all caps or not
            this.isAllCaps = textAllCaps

            if (this@LMFeedTextStyle.maxLines != null && this@LMFeedTextStyle.ellipsize != null) {
                this.maxLines = this@LMFeedTextStyle.maxLines
                this.ellipsize = this@LMFeedTextStyle.ellipsize
            }

            // sets background color of the text
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

    // sets font to the text view
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

/**
 * Util function that helps to apply all the styling [LMFeedTextStyle] to [LMFeedTextView]
 **/
fun LMFeedTextView.setStyle(viewStyle: LMFeedTextStyle) {
    viewStyle.apply(this)
}