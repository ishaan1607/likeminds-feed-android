package com.likeminds.feed.android.core.ui.base.styles

import android.content.res.ColorStateList
import android.util.TypedValue
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.views.LMFeedChip
import com.likeminds.feed.android.core.utils.LMFeedViewStyle

class LMFeedChipStyle private constructor(
    @ColorRes val chipBackgroundColor: Int?,
    @ColorRes val chipStrokeColor: Int?,
    @ColorRes val chipTextColor: Int,
    @DimenRes val chipStrokeWidth: Int?,
    @DimenRes val chipMinHeight: Int?,
    @DimenRes val chipStartPadding: Int?,
    @DimenRes val chipEndPadding: Int?,
    @DimenRes val chipCornerRadius: Int,
    @DimenRes val chipTextSize: Int
) : LMFeedViewStyle {

    class Builder {
        @ColorRes
        private var chipBackgroundColor: Int? = null

        @ColorRes
        private var chipStrokeColor: Int? = null

        @ColorRes
        private var chipTextColor: Int = R.color.lm_feed_majorelle_blue

        @DimenRes
        private var chipStrokeWidth: Int? = null

        @DimenRes
        private var chipMinHeight: Int? = null

        @DimenRes
        private var chipStartPadding: Int? = null

        @DimenRes
        private var chipEndPadding: Int? = null

        @DimenRes
        private var chipCornerRadius: Int = R.dimen.lm_feed_corner_radius_regular

        @DimenRes
        private var chipTextSize: Int = R.dimen.lm_feed_text_medium

        fun chipBackgroundColor(@ColorRes chipBackgroundColor: Int?) = apply {
            this.chipBackgroundColor = chipBackgroundColor
        }

        fun chipStrokeColor(@ColorRes chipStrokeColor: Int?) = apply {
            this.chipStrokeColor = chipStrokeColor
        }

        fun chipTextColor(@ColorRes chipTextColor: Int) = apply {
            this.chipTextColor = chipTextColor
        }

        fun chipStrokeWidth(@DimenRes chipStrokeWidth: Int?) = apply {
            this.chipStrokeWidth = chipStrokeWidth
        }

        fun chipMinHeight(@DimenRes chipMinHeight: Int?) = apply {
            this.chipMinHeight = chipMinHeight
        }

        fun chipStartPadding(@DimenRes chipStartPadding: Int?) = apply {
            this.chipStartPadding = chipStartPadding
        }

        fun chipEndPadding(@DimenRes chipEndPadding: Int?) = apply {
            this.chipEndPadding = chipEndPadding
        }

        fun chipCornerRadius(@DimenRes chipCornerRadius: Int) = apply {
            this.chipCornerRadius = chipCornerRadius
        }

        fun chipTextSize(@DimenRes chipTextSize: Int) = apply {
            this.chipTextSize = chipTextSize
        }

        fun build() = LMFeedChipStyle(
            chipBackgroundColor,
            chipStrokeColor,
            chipTextColor,
            chipStrokeWidth,
            chipMinHeight,
            chipStartPadding,
            chipEndPadding,
            chipCornerRadius,
            chipTextSize
        )
    }

    fun apply(chip: LMFeedChip) {
        chip.apply {
            //chip background color
            this@LMFeedChipStyle.chipBackgroundColor?.let {
                setChipBackgroundColorResource(this@LMFeedChipStyle.chipBackgroundColor)
            }

            //chip stroke width
            if (this@LMFeedChipStyle.chipStrokeWidth == null) {
                setChipStrokeWidthResource(R.dimen.zero_dp)
            } else {
                setChipStrokeWidthResource(this@LMFeedChipStyle.chipStrokeWidth)
            }

            //chip stroke color
            this@LMFeedChipStyle.chipStrokeColor?.let {
                setChipStrokeColorResource(this@LMFeedChipStyle.chipStrokeColor)
            }

            //chip min height
            this@LMFeedChipStyle.chipMinHeight?.let {
                setChipMinHeightResource(it)
            }

            //chip start padding
            this@LMFeedChipStyle.chipStartPadding?.let {
                setChipStartPaddingResource(it)
            }

            //chip end padding
            this@LMFeedChipStyle.chipEndPadding?.let {
                setChipEndPaddingResource(it)
            }

            val shape = this.shapeAppearanceModel.toBuilder()
                .setAllCornerSizes(context.resources.getDimension(this@LMFeedChipStyle.chipCornerRadius))
                .build()

            shapeAppearanceModel = shape

            //chip text size
            setTextSize(
                TypedValue.COMPLEX_UNIT_PX,
                context.resources.getDimension(this@LMFeedChipStyle.chipTextSize)
            )

            //chip text color
            setTextColor(
                ColorStateList.valueOf(
                    ContextCompat.getColor(
                        context,
                        this@LMFeedChipStyle.chipTextColor
                    )
                )
            )

            setEnsureMinTouchTargetSize(false)
        }
    }

    fun toBuilder(): Builder {
        return Builder().chipBackgroundColor(chipBackgroundColor)
            .chipTextColor(chipTextColor)
            .chipStrokeColor(chipStrokeColor)
            .chipStrokeWidth(chipStrokeWidth)
            .chipMinHeight(chipMinHeight)
            .chipStartPadding(chipStartPadding)
            .chipEndPadding(chipEndPadding)
            .chipCornerRadius(chipCornerRadius)
            .chipTextSize(chipTextSize)
    }
}

/**
 * Util function to helps to apply all the styling [LMFeedChipStyle] to [LMFeedChip]
 **/
fun LMFeedChip.setStyle(viewStyle: LMFeedChipStyle) {
    viewStyle.apply(this)
}