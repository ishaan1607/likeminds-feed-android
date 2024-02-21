package com.likeminds.feed.android.core.ui.widgets.postmedia.style

import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.utils.LMFeedViewStyle
import com.zhpan.indicator.annotation.AIndicatorSlideMode
import com.zhpan.indicator.annotation.AIndicatorStyle

class LMFeedPostMultipleMediaViewStyle private constructor(
    @ColorRes val indicatorActiveColor: Int,
    @ColorRes val indicatorInActiveColor: Int,
    @DimenRes val indicatorActiveWidth: Int,
    @AIndicatorStyle val indicatorStyle: Int?,
    @DimenRes val indicatorInactiveWidth: Int?,
    @DimenRes val indicatorHeight: Int?,
    @DimenRes val indicatorSpacing: Int?,
    @AIndicatorSlideMode val indicatorSlideMode: Int?
) : LMFeedViewStyle {

    class Builder {
        @ColorRes
        private var indicatorActiveColor: Int = R.color.lm_feed_majorelle_blue

        @ColorRes
        private var indicatorInActiveColor: Int = R.color.lm_feed_cloudy_blue

        @DimenRes
        private var indicatorActiveWidth: Int = R.dimen.lm_feed_indicator_dot_size

        @AIndicatorStyle
        private var indicatorStyle: Int? = null

        @DimenRes
        private var indicatorInactiveWidth: Int? = null

        @DimenRes
        private var indicatorHeight: Int? = null

        @DimenRes
        private var indicatorSpacing: Int? = null

        @AIndicatorSlideMode
        private var indicatorSlideMode: Int? = null

        fun indicatorActiveColor(@ColorRes indicatorActiveColor: Int) =
            apply { this.indicatorActiveColor = indicatorActiveColor }

        fun indicatorInActiveColor(@ColorRes indicatorInActiveColor: Int) =
            apply { this.indicatorInActiveColor = indicatorInActiveColor }

        fun indicatorActiveWidth(@DimenRes indicatorActiveWidth: Int) =
            apply { this.indicatorActiveWidth = indicatorActiveWidth }

        fun indicatorInactiveWidth(@DimenRes indicatorInactiveWidth: Int?) =
            apply { this.indicatorInactiveWidth = indicatorInactiveWidth }

        fun indicatorStyle(@AIndicatorStyle indicatorStyle: Int?) =
            apply { this.indicatorStyle = indicatorStyle }

        fun indicatorHeight(@DimenRes indicatorHeight: Int?) =
            apply { this.indicatorHeight = indicatorHeight }

        fun indicatorSpacing(@DimenRes indicatorSpacing: Int?) =
            apply { this.indicatorSpacing = indicatorSpacing }

        fun indicatorSlideMode(@AIndicatorSlideMode indicatorSlideMode: Int?) =
            apply { this.indicatorSlideMode = indicatorSlideMode }

        fun build() = LMFeedPostMultipleMediaViewStyle(
            indicatorActiveColor,
            indicatorInActiveColor,
            indicatorActiveWidth,
            indicatorInactiveWidth,
            indicatorStyle,
            indicatorHeight,
            indicatorSpacing,
            indicatorSlideMode
        )
    }

    fun toBuilder(): Builder {
        return Builder().indicatorActiveColor(indicatorActiveColor)
            .indicatorInActiveColor(indicatorInActiveColor)
            .indicatorActiveWidth(indicatorActiveWidth)
            .indicatorInactiveWidth(indicatorInactiveWidth)
            .indicatorStyle(indicatorStyle)
            .indicatorHeight(indicatorHeight)
            .indicatorSpacing(indicatorSpacing)
            .indicatorSlideMode(indicatorSlideMode)
    }
}