package com.likeminds.feed.android.core.ui.base.styles

import android.content.res.ColorStateList
import android.graphics.Typeface
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.views.LMFeedEditText
import com.likeminds.feed.android.core.utils.LMFeedViewStyle

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
            inputTextStyle.apply(this)

            if (hintTextColor != null) {
                setHintTextColor(ContextCompat.getColor(this.context, hintTextColor))
            }

            if (this@LMFeedEditTextStyle.elevation != null) {
                elevation = resources.getDimension(this@LMFeedEditTextStyle.elevation)
            }

            if (backgroundColor != null) {
                backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        context,
                        this@LMFeedEditTextStyle.backgroundColor
                    )
                )
            }
        }
    }
}

fun LMFeedEditText.setStyle(viewStyle: LMFeedEditTextStyle) {
    viewStyle.apply(this)
}