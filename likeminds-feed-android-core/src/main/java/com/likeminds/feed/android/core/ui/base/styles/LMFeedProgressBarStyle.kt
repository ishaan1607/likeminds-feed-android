package com.likeminds.feed.android.core.ui.base.styles

import android.content.res.ColorStateList
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.likeminds.feed.android.core.ui.base.views.LMFeedProgressBar
import com.likeminds.feed.android.core.utils.LMFeedViewStyle

class LMFeedProgressBarStyle private constructor(
    @ColorRes val progressColor: Int?,
    val isIndeterminate: Boolean
) : LMFeedViewStyle {

    class Builder {
        @ColorRes
        private var progressColor: Int? = null
        private var isIndeterminate: Boolean = false

        fun progressColor(progressColor: Int?) = apply { this.progressColor = progressColor }
        fun isIndeterminate(isIndeterminate: Boolean) =
            apply { this.isIndeterminate = isIndeterminate }

        fun build() = LMFeedProgressBarStyle(
            progressColor,
            isIndeterminate
        )
    }

    fun toBuilder(): Builder {
        return Builder().progressColor(progressColor)
            .isIndeterminate(isIndeterminate)
    }

    fun apply(progressBar: LMFeedProgressBar) {
        progressBar.apply {

            //progress color
            if (progressColor != null) {
                this.progressTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        context,
                        progressColor
                    )
                )

                this.indeterminateTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        context,
                        progressColor
                    )
                )
            }

            this.isIndeterminate = this@LMFeedProgressBarStyle.isIndeterminate
        }
    }
}

fun LMFeedProgressBar.setStyle(viewStyle: LMFeedProgressBarStyle) {
    viewStyle.apply(this)
}