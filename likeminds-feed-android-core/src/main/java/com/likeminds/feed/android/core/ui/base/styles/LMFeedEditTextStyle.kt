package com.likeminds.feed.android.core.ui.base.styles

import android.graphics.Typeface
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.views.LMFeedEditText
import com.likeminds.feed.android.core.utils.LMFeedViewStyle

/**
 * [LMFeedEditTextStyle] helps you to customize a [LMFeedEditText] with the following properties
 * @property inputTextStyle : [LMFeedTextStyle] to customize the text of the edit text
 *
 * @property hintTextColor: [Int] should be in format of [ColorRes] to customize the hint text color | Default value = [null]
 * @property elevation: [Int] should be in format of [DimenRes] to customize the elevation of the edit text | Default value = [null]
 * @property backgroundColor: [Int] should be in format of [ColorRes] to customize the background color of the edit text | Default value = [null]
 **/
class LMFeedEditTextStyle private constructor(
    val inputTextStyle: LMFeedTextStyle,
    @ColorRes val hintTextColor: Int?,
    @DimenRes val elevation: Int?,
    @ColorRes val backgroundColor: Int?
) : LMFeedViewStyle {

    class Builder {
        private var inputTextStyle: LMFeedTextStyle = LMFeedTextStyle.Builder()
            .textColor(R.color.lm_feed_white)
            .typeface(Typeface.NORMAL)
            .build()

        @ColorRes
        private var hintTextColor: Int? = null

        @DimenRes
        private var elevation: Int? = null

        @ColorRes
        private var backgroundColor: Int? = null

        fun inputTextStyle(inputTextStyle: LMFeedTextStyle) =
            apply { this.inputTextStyle = inputTextStyle }

        fun hintTextColor(@ColorRes hintTextColor: Int?) =
            apply { this.hintTextColor = hintTextColor }

        fun elevation(@DimenRes elevation: Int?) = apply { this.elevation = elevation }
        fun backgroundColor(@ColorRes backgroundColor: Int?) =
            apply { this.backgroundColor = backgroundColor }

        fun build() = LMFeedEditTextStyle(
            inputTextStyle,
            hintTextColor,
            elevation,
            backgroundColor
        )
    }

    fun toBuilder(): Builder {
        return Builder().inputTextStyle(inputTextStyle)
            .hintTextColor(hintTextColor)
            .elevation(elevation)
            .backgroundColor(backgroundColor)
    }

    fun apply(editText: LMFeedEditText) {
        editText.apply {
            // applies all text related styling
            inputTextStyle.apply(this)

            // sets hint text color
            if (hintTextColor != null) {
                setHintTextColor(ContextCompat.getColor(this.context, hintTextColor))
            }

            // sets input type of edit text
            if (this@LMFeedEditTextStyle.elevation != null) {
                elevation = resources.getDimension(this@LMFeedEditTextStyle.elevation)
            }

            if (backgroundColor != null) {
                setBackgroundColor(ContextCompat.getColor(context, backgroundColor))
            }
        }
    }
}

/**
 * Util function that helps to apply all the styling [LMFeedEditTextStyle] to [LMFeedEditText]
 **/
fun LMFeedEditText.setStyle(viewStyle: LMFeedEditTextStyle) {
    viewStyle.apply(this)
}