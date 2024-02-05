package com.likeminds.feed.android.ui.base.styles

import android.graphics.Typeface
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.likeminds.feed.android.ui.R
import com.likeminds.feed.android.ui.base.views.LMFeedEditText
import com.likeminds.feed.android.ui.utils.ViewStyle

class LMFeedEditTextStyle private constructor(
    val inputTextStyle: LMFeedTextStyle,
    @ColorRes
    val hintTextColor: Int?,
    val inputType: Int?
) : ViewStyle {

    class Builder {
        private var inputTextStyle: LMFeedTextStyle = LMFeedTextStyle.Builder()
            .textColor(R.color.lm_feed_white)
            .typeface(Typeface.NORMAL)
            .build()

        @ColorRes
        private var hintTextColor: Int? = null
        private var inputType: Int? = null

        fun inputTextStyle(inputTextStyle: LMFeedTextStyle) =
            apply { this.inputTextStyle = inputTextStyle }

        fun hintTextColor(@ColorRes hintTextColor: Int?) =
            apply { this.hintTextColor = hintTextColor }

        fun inputType(inputType: Int?) = apply { this.inputType = inputType }

        fun build() = LMFeedEditTextStyle(
            inputTextStyle,
            hintTextColor,
            inputType
        )

        fun toBuilder(): Builder {
            return Builder().inputTextStyle(inputTextStyle)
                .hintTextColor(hintTextColor)
                .inputType(inputType)
        }
    }

    fun apply(editText: LMFeedEditText) {
        editText.apply {
            inputTextStyle.apply(this)

            if (hintTextColor != null) {
                setHintTextColor(ContextCompat.getColor(this.context, hintTextColor))
            }

            if (this@LMFeedEditTextStyle.inputType != null) {
                inputType = this@LMFeedEditTextStyle.inputType
            }
        }
    }
}

fun LMFeedEditText.setStyle(viewStyle: LMFeedEditTextStyle) {
    viewStyle.apply(this)
}