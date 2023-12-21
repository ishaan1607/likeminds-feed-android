package com.likeminds.feed.android.ui.base.styles

import android.graphics.Typeface
import androidx.annotation.*
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton.IconGravity
import com.likeminds.feed.android.ui.R
import com.likeminds.feed.android.ui.base.views.LMFeedButton

class LMFeedButtonStyle private constructor(
    //text related
    val textStyle: LMFeedTextStyle,

    //button related
    val backgroundColor: Int,
    @ColorRes val strokeColor: Int?,
    @DimenRes val strokeWidth: Int?,
    @DimenRes val elevation: Int,

    //icon related
    @DrawableRes val icon: Int?,
    @ColorRes val iconTint: Int?,
    @DimenRes val iconSize: Int?,
    @IconGravity val iconGravity: Int?,
    ) {

    class Builder {
        private var textStyle: LMFeedTextStyle = LMFeedTextStyle.Builder()
            .textColor(R.color.white)
            .typeface(Typeface.BOLD)
            .build()

        private var backgroundColor: Int = R.color.majorelle_blue

        @DrawableRes
        private var icon: Int? = null

        @ColorRes
        private var iconTint: Int? = null

        @IconGravity
        private var iconGravity: Int? = null
    }

    fun apply(button: LMFeedButton) {
        button.apply {
            //all text related styling

            //text color
            val textColor =
                ContextCompat.getColor(context, this@LMFeedButtonStyle.textStyle.textColor)
            this.setTextColor(textColor)

            //text size
            this.textSize = this@LMFeedButtonStyle.textStyle.textSize.toFloat()

        }
    }
}