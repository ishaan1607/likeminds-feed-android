package com.likeminds.feed.android.core.ui.base.styles

import android.content.res.ColorStateList
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.likeminds.feed.android.core.ui.base.views.LMFeedProgressBar
import com.likeminds.feed.android.core.utils.LMFeedViewStyle

/**
 * [LMFeedProgressBarStyle] helps you customize a circular progress bar in the following way
 * @property progressColor: [Int] should be in format of [ColorRes] to add color to progress | Default value = [null]
 * @property isIndeterminate: [Boolean], this property defines whether progress should be determinate or indeterminate | Default value = [false]
 * @property maxProgress: [Int], this property defines the maximum value of progress, should be provided in case [isIndeterminate] is [true] | Default value = [null]
 **/
class LMFeedProgressBarStyle private constructor(
    @ColorRes val progressColor: Int?,
    val isIndeterminate: Boolean,
    val maxProgress: Int?
) : LMFeedViewStyle {

    class Builder {
        @ColorRes
        private var progressColor: Int? = null
        private var isIndeterminate: Boolean = false
        private var maxProgress: Int? = 100

        fun progressColor(progressColor: Int?) = apply { this.progressColor = progressColor }
        fun isIndeterminate(isIndeterminate: Boolean) =
            apply { this.isIndeterminate = isIndeterminate }

        fun maxProgress(maxProgress: Int?) = apply { this.maxProgress = maxProgress }

        fun build() = LMFeedProgressBarStyle(
            progressColor,
            isIndeterminate,
            maxProgress
        )
    }

    fun toBuilder(): Builder {
        return Builder().progressColor(progressColor)
            .isIndeterminate(isIndeterminate)
            .maxProgress(maxProgress)
    }

    fun apply(progressBar: LMFeedProgressBar) {
        progressBar.apply {

            //progress color
            progressColor?.let {
                this.progressTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        context,
                        progressColor
                    )
                )
            }

            //indeterminate
            this.isIndeterminate = this@LMFeedProgressBarStyle.isIndeterminate

            //max progress
            this@LMFeedProgressBarStyle.maxProgress?.let {
                this.max = it
            }
        }
    }
}

/**
 * Util functions to helps to apply all the styling [LMFeedProgressBarStyle] to [LMFeedProgressBar]
 **/
fun LMFeedProgressBar.setStyle(viewStyle: LMFeedProgressBarStyle) {
    viewStyle.apply(this)
}