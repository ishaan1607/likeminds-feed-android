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
    @ColorRes val activeChipBackgroundColor: Int?,
    @ColorRes val inActiveChipBackgroundColor: Int?,
    @ColorRes val activeChipStrokeColor: Int?,
    @ColorRes val inActiveChipStrokeColor: Int?,
    @ColorRes val activeChipTextColor: Int,
    @ColorRes val inActiveChipTextColor: Int,
    @DimenRes val chipStrokeWidth: Int?,
    @DimenRes val chipMinHeight: Int?,
    @DimenRes val chipStartPadding: Int?,
    @DimenRes val chipEndPadding: Int?,
    @DimenRes val chipCornerRadius: Int,
    @DimenRes val chipTextSize: Int
) : LMFeedViewStyle {

    class Builder {
        @ColorRes
        private var activeChipBackgroundColor: Int? = null

        @ColorRes
        private var inActiveChipBackgroundColor: Int? = null

        @ColorRes
        private var activeChipStrokeColor: Int? = null

        @ColorRes
        private var inActiveChipStrokeColor: Int? = null

        @ColorRes
        private var activeChipTextColor: Int = R.color.lm_feed_majorelle_blue

        @ColorRes
        private var inActiveChipTextColor: Int = R.color.lm_feed_grey

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

        fun activeChipBackgroundColor(@ColorRes activeChipBackgroundColor: Int?) = apply {
            this.activeChipBackgroundColor = activeChipBackgroundColor
        }

        fun inActiveChipBackgroundColor(@ColorRes inActiveChipBackgroundColor: Int?) = apply {
            this.inActiveChipBackgroundColor = inActiveChipBackgroundColor
        }

        fun activeChipStrokeColor(@ColorRes activeChipBackgroundColor: Int?) = apply {
            this.activeChipBackgroundColor = activeChipBackgroundColor
        }

        fun inActiveChipStrokeColor(@ColorRes inActiveChipBackgroundColor: Int?) = apply {
            this.inActiveChipBackgroundColor = inActiveChipBackgroundColor
        }

        fun activeChipTextColor(@ColorRes activeChipTextColor: Int) = apply {
            this.activeChipTextColor = activeChipTextColor
        }

        fun inActiveChipTextColor(@ColorRes inActiveChipTextColor: Int) = apply {
            this.inActiveChipTextColor = inActiveChipTextColor
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
            activeChipBackgroundColor,
            inActiveChipBackgroundColor,
            activeChipStrokeColor,
            inActiveChipStrokeColor,
            activeChipTextColor,
            inActiveChipTextColor,
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
            inActiveChipBackgroundColor?.let {
                setChipBackgroundColorResource(inActiveChipBackgroundColor)
            }

            //chip stroke width
            if (this@LMFeedChipStyle.chipStrokeWidth == null) {
                setChipStrokeWidthResource(R.dimen.zero_dp)
            } else {
                setChipStrokeWidthResource(this@LMFeedChipStyle.chipStrokeWidth)
            }

            //chip stroke color
            inActiveChipStrokeColor?.let {
                setChipStrokeColorResource(inActiveChipStrokeColor)
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
                        this@LMFeedChipStyle.inActiveChipTextColor
                    )
                )
            )

            setEnsureMinTouchTargetSize(false)
        }
    }

    fun toBuilder(): Builder {
        return Builder().activeChipBackgroundColor(activeChipBackgroundColor)
            .inActiveChipBackgroundColor(inActiveChipBackgroundColor)
            .activeChipTextColor(activeChipTextColor)
            .inActiveChipTextColor(inActiveChipTextColor)
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