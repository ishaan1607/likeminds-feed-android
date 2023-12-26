package com.likeminds.feed.android.ui.base.styles

import android.content.res.ColorStateList
import androidx.annotation.*
import androidx.core.content.ContextCompat
import com.likeminds.feed.android.ui.R
import com.likeminds.feed.android.ui.base.views.LMFeedFAB
import com.likeminds.feed.android.ui.utils.ViewStyle
import kotlin.math.roundToInt

class LMFeedFABStyle(
    val isExtended: Boolean,
    val textStyle: LMFeedTextStyle?,

    //fab related
    @ColorRes val backgroundColor: Int,
    @ColorRes val strokeColor: Int?,
    @DimenRes val strokeWidth: Int?,
    @DimenRes val elevation: Int?,

    //icon related
    @DrawableRes val icon: Int?,
    @ColorRes val iconTint: Int?,
    @DimenRes val iconSize: Int?,
    @DimenRes val iconPadding: Int?
) : ViewStyle {

    class Builder {
        private var isExtended: Boolean = false
        private var textStyle: LMFeedTextStyle? = null

        //fab related
        @ColorRes
        private var backgroundColor: Int = R.color.majorelle_blue

        @ColorRes
        private var strokeColor: Int? = null

        @DimenRes
        private var strokeWidth: Int? = null

        @DimenRes
        private var elevation: Int? = null

        //icon related
        @DrawableRes
        private var icon: Int? = null

        @ColorRes
        private var iconTint: Int? = null

        @DimenRes
        private var iconSize: Int? = null

        @DimenRes
        private var iconPadding: Int? = null

        fun isExtended(isExtended: Boolean) = apply { this.isExtended = isExtended }
        fun textStyle(textStyle: LMFeedTextStyle?) = apply { this.textStyle = textStyle }

        fun backgroundColor(@ColorRes backgroundColor: Int) =
            apply { this.backgroundColor = backgroundColor }

        fun strokeColor(@ColorRes strokeColor: Int?) = apply { this.strokeColor = strokeColor }

        fun strokeWidth(@DimenRes strokeWidth: Int?) = apply { this.strokeWidth = strokeWidth }

        fun elevation(@DimenRes elevation: Int?) = apply { this.elevation = elevation }

        fun icon(icon: Int?) = apply { this.icon = icon }

        fun iconTint(iconTint: Int?) = apply { this.iconTint = iconTint }

        fun iconSize(iconSize: Int?) = apply { this.iconSize = iconSize }

        fun iconPadding(iconPadding: Int?) = apply { this.iconPadding = iconPadding }

        fun build() = LMFeedFABStyle(
            isExtended,
            textStyle,
            backgroundColor,
            strokeColor,
            strokeWidth,
            elevation,
            icon,
            iconTint,
            iconSize,
            iconPadding
        )
    }

    fun apply(fab: LMFeedFAB) {
        fab.apply {
            //text related
            textStyle?.apply(this)

            this.isExtended = this@LMFeedFABStyle.isExtended

            //button related styling
            backgroundTintList = ColorStateList.valueOf(
                ContextCompat.getColor(
                    context,
                    this@LMFeedFABStyle.backgroundColor
                )
            )

            //stroke color
            val strokeColor = this@LMFeedFABStyle.strokeColor
            if (strokeColor != null) {
                this.strokeColor = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        context,
                        strokeColor
                    )
                )
            }

            //stroke width
            val strokeWidth = this@LMFeedFABStyle.strokeWidth
            if (strokeWidth != null) {
                this.strokeWidth = resources.getDimension(strokeWidth).roundToInt()
            }

            //elevation
            val elevation = this@LMFeedFABStyle.elevation
            if (elevation != null) {
                this.elevation = resources.getDimension(elevation)
            }

            //icon related
            val icon = this@LMFeedFABStyle.icon
            if (icon != null) {
                this.icon = ContextCompat.getDrawable(context, icon)
            }

            //iconTint
            val iconTint = this@LMFeedFABStyle.iconTint
            if (iconTint != null) {
                this.iconTint = ColorStateList.valueOf(
                    ContextCompat.getColor(
                        context,
                        iconTint
                    )
                )
            }

            //iconSize
            val iconSize = this@LMFeedFABStyle.iconSize
            if (iconSize != null) {
                this.iconSize = resources.getDimension(iconSize).roundToInt()
            }

            //iconPadding
            val iconPadding = this@LMFeedFABStyle.iconPadding
            if (iconPadding != null) {
                this.iconPadding = resources.getDimension(iconPadding).roundToInt()
            }
        }
    }

    fun toBuilder(): Builder {
        return Builder().isExtended(isExtended)
            .textStyle(textStyle)
            .backgroundColor(backgroundColor)
            .strokeColor(strokeColor)
            .strokeWidth(strokeWidth)
            .elevation(elevation)
            .icon(icon)
            .iconTint(iconTint)
            .iconSize(iconSize)
            .iconPadding(iconPadding)
    }
}