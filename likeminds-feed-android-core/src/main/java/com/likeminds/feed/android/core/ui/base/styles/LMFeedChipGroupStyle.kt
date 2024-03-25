package com.likeminds.feed.android.core.ui.base.styles

import androidx.annotation.*
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.views.*
import com.likeminds.feed.android.core.utils.LMFeedViewStyle

/**
 * [LMFeedChipGroupStyle] helps you to customize a [LMFeedChipGroup] with the following properties
 * @property isSingleLine : [Boolean] to customize whether the chip group is single line or not | Default value = [false]
 * @property chipGroupHorizontalSpacing: [Int] should be in format of [DimenRes] to customize the horizontal spacing of the chip group | Default value =  [R.dimen.lm_feed_default_chip_group_vertical_spacing]
 * @property chipGroupVerticalSpacing: [Int] should be in format of [DimenRes] to customize the vertical spacing of the chip group | Default value = [R.dimen.lm_feed_default_chip_group_vertical_spacing]
 *
 **/
class LMFeedChipGroupStyle private constructor(
    //whether the chip group is single line or not
    val isSingleLine: Boolean,
    //horizontal spacing for the chip group
    @DimenRes val chipGroupHorizontalSpacing: Int,
    //vertical spacing for the chip group
    @DimenRes val chipGroupVerticalSpacing: Int,
) : LMFeedViewStyle {

    class Builder {
        private var isSingleLine: Boolean = false

        @DimenRes
        private var chipGroupHorizontalSpacing: Int =
            R.dimen.lm_feed_default_chip_group_horizontal_spacing

        @DimenRes
        private var chipGroupVerticalSpacing: Int =
            R.dimen.lm_feed_default_chip_group_vertical_spacing

        fun isSingleLine(isSingleLine: Boolean) = apply {
            this.isSingleLine = isSingleLine
        }

        fun chipGroupHorizontalSpacing(@DimenRes chipGroupHorizontalSpacing: Int) = apply {
            this.chipGroupHorizontalSpacing = chipGroupHorizontalSpacing
        }

        fun chipGroupVerticalSpacing(@DimenRes chipGroupVerticalSpacing: Int) = apply {
            this.chipGroupVerticalSpacing = chipGroupVerticalSpacing
        }

        fun build() = LMFeedChipGroupStyle(
            isSingleLine,
            chipGroupHorizontalSpacing,
            chipGroupVerticalSpacing,
        )
    }

    fun apply(chipGroup: LMFeedChipGroup) {
        chipGroup.apply {
            isSingleLine = this@LMFeedChipGroupStyle.isSingleLine

            setChipSpacingHorizontalResource(this@LMFeedChipGroupStyle.chipGroupHorizontalSpacing)
            setChipSpacingVerticalResource(this@LMFeedChipGroupStyle.chipGroupVerticalSpacing)
        }
    }

    fun toBuilder(): Builder {
        return Builder().isSingleLine(isSingleLine)
            .chipGroupHorizontalSpacing(chipGroupHorizontalSpacing)
            .chipGroupVerticalSpacing(chipGroupVerticalSpacing)
    }
}

/**
 * Util function to helps to apply all the styling [LMFeedChipGroupStyle] to [LMFeedButton]
 **/
fun LMFeedChipGroup.setStyle(viewStyle: LMFeedChipGroupStyle) {
    viewStyle.apply(this)
}