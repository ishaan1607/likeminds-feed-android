package com.likeminds.feed.android.core.ui.widgets.posttopicsview.style

import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.utils.LMFeedViewStyle

class LMFeedPostTopicsViewStyle private constructor(
    val isSingleLine: Boolean,
    @DimenRes val chipGroupHorizontalSpacing: Int,
    @DimenRes val chipGroupVerticalSpacing: Int,
    @ColorRes val chipBackgroundColor: Int,
    @DimenRes val chipStrokeWidth: Int?,
    @ColorRes val chipStrokeColor: Int?,
    @DimenRes val chipMinHeight: Int?,
    @DimenRes val chipStartPadding: Int?,
    @DimenRes val chipEndPadding: Int?,
    @ColorRes val chipTextColor: Int,
    @DimenRes val chipTextSize: Int
) : LMFeedViewStyle {

    class Builder {
        private var isSingleLine: Boolean = false

        @DimenRes
        private var chipGroupHorizontalSpacing: Int =
            R.dimen.lm_feed_topic_chip_group_horizontal_spacing

        @DimenRes
        private var chipGroupVerticalSpacing: Int =
            R.dimen.lm_feed_topic_chip_group_vertical_spacing

        @ColorRes
        private var chipBackgroundColor: Int = R.color.lm_feed_majorelle_blue_10

        @DimenRes
        private var chipStrokeWidth: Int? = null

        @ColorRes
        private var chipStrokeColor: Int? = null

        @DimenRes
        private var chipMinHeight: Int? = null

        @DimenRes
        private var chipStartPadding: Int? = null

        @DimenRes
        private var chipEndPadding: Int? = null

        @ColorRes
        private var chipTextColor: Int = R.color.lm_feed_majorelle_blue

        @DimenRes
        private var chipTextSize: Int = R.dimen.lm_feed_text_medium

        fun isSingleLine(isSingleLine: Boolean) = apply { this.isSingleLine = isSingleLine }
        fun chipGroupHorizontalSpacing(@DimenRes chipGroupHorizontalSpacing: Int) =
            apply { this.chipGroupHorizontalSpacing = chipGroupHorizontalSpacing }

        fun chipGroupVerticalSpacing(@DimenRes chipGroupVerticalSpacing: Int) =
            apply { this.chipGroupVerticalSpacing = chipGroupVerticalSpacing }

        fun chipBackgroundColor(@ColorRes chipBackgroundColor: Int) =
            apply { this.chipBackgroundColor = chipBackgroundColor }

        fun chipStrokeWidth(@DimenRes chipStrokeWidth: Int?) =
            apply { this.chipStrokeWidth = chipStrokeWidth }

        fun chipStrokeColor(@ColorRes chipStrokeColor: Int?) =
            apply { this.chipStrokeColor = chipStrokeColor }

        fun chipMinHeight(@DimenRes chipMinHeight: Int?) =
            apply { this.chipMinHeight = chipMinHeight }

        fun chipStartPadding(@DimenRes chipStartPadding: Int?) =
            apply { this.chipStartPadding = chipStartPadding }

        fun chipEndPadding(@DimenRes chipEndPadding: Int?) =
            apply { this.chipEndPadding = chipEndPadding }

        fun chipTextColor(@ColorRes chipTextColor: Int) =
            apply { this.chipTextColor = chipTextColor }

        fun chipTextSize(@DimenRes chipTextSize: Int) = apply { this.chipTextSize = chipTextSize }

        fun build() = LMFeedPostTopicsViewStyle(
            isSingleLine,
            chipGroupHorizontalSpacing,
            chipGroupVerticalSpacing,
            chipBackgroundColor,
            chipStrokeWidth,
            chipStrokeColor,
            chipMinHeight,
            chipStartPadding,
            chipEndPadding,
            chipTextColor,
            chipTextSize
        )
    }

    fun toBuilder(): Builder {
        return Builder().isSingleLine(isSingleLine)
            .chipGroupHorizontalSpacing(chipGroupHorizontalSpacing)
            .chipGroupVerticalSpacing(chipGroupVerticalSpacing)
            .chipBackgroundColor(chipBackgroundColor)
            .chipStrokeWidth(chipStrokeWidth)
            .chipStrokeColor(chipStrokeColor)
            .chipMinHeight(chipMinHeight)
            .chipStartPadding(chipStartPadding)
            .chipEndPadding(chipEndPadding)
            .chipTextColor(chipTextColor)
            .chipTextSize(chipTextSize)
    }
}