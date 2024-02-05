package com.likeminds.feed.android.ui.base.styles

import android.content.res.ColorStateList
import androidx.annotation.*
import androidx.core.content.ContextCompat
import com.likeminds.feed.android.ui.R
import com.likeminds.feed.android.ui.base.views.LMFeedFAB
import com.likeminds.feed.android.ui.utils.ViewStyle
import com.likeminds.feed.android.ui.utils.model.LMFeedPadding
import kotlin.math.roundToInt

class LMFeedFABStyle(
    val isExtended: Boolean,

    //fab related
    @ColorRes val backgroundColor: Int,
    @ColorRes val strokeColor: Int?,
    @DimenRes val strokeWidth: Int?,
    @DimenRes val elevation: Int?,

    //icon related
    @DrawableRes val icon: Int?,
    @ColorRes val iconTint: Int?,
    @DimenRes val iconSize: Int?,
    @DimenRes val iconPadding: Int?,

    //text related
    @ColorRes
    val textColor: Int,
    val textAllCaps: Boolean,

    //padding
    val padding: LMFeedPadding?
) : ViewStyle {

    class Builder {
        private var isExtended: Boolean = true

        //fab related
        @ColorRes
        private var backgroundColor: Int = R.color.lm_feed_majorelle_blue

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

        //text related
        @ColorRes
        private var textColor: Int = R.color.lm_feed_white

        private var textAllCaps: Boolean = false

        private var padding: LMFeedPadding? = null

        fun isExtended(isExtended: Boolean) = apply { this.isExtended = isExtended }

        fun backgroundColor(@ColorRes backgroundColor: Int) =
            apply { this.backgroundColor = backgroundColor }

        fun strokeColor(@ColorRes strokeColor: Int?) = apply { this.strokeColor = strokeColor }

        fun strokeWidth(@DimenRes strokeWidth: Int?) = apply { this.strokeWidth = strokeWidth }

        fun elevation(@DimenRes elevation: Int?) = apply { this.elevation = elevation }

        fun icon(@DrawableRes icon: Int?) = apply { this.icon = icon }

        fun iconTint(@ColorRes iconTint: Int?) = apply { this.iconTint = iconTint }

        fun iconSize(@DimenRes iconSize: Int?) = apply { this.iconSize = iconSize }

        fun iconPadding(@DimenRes iconPadding: Int?) = apply { this.iconPadding = iconPadding }

        fun textColor(@ColorRes textColor: Int) = apply { this.textColor = textColor }

        fun textAllCaps(textAllCaps: Boolean) = apply { this.textAllCaps = textAllCaps }

        fun padding(padding: LMFeedPadding?) = apply { this.padding = padding }

        fun build() = LMFeedFABStyle(
            isExtended,
            backgroundColor,
            strokeColor,
            strokeWidth,
            elevation,
            icon,
            iconTint,
            iconSize,
            iconPadding,
            textColor,
            textAllCaps,
            padding
        )
    }

    fun apply(fab: LMFeedFAB) {
        fab.apply {
            //text related
            this.setTextColor(ContextCompat.getColor(context, this@LMFeedFABStyle.textColor))
            this.isAllCaps = this@LMFeedFABStyle.textAllCaps

            if (padding != null) {
                setPadding(
                    this@LMFeedFABStyle.padding.paddingLeft,
                    this@LMFeedFABStyle.padding.paddingTop,
                    this@LMFeedFABStyle.padding.paddingRight,
                    this@LMFeedFABStyle.padding.paddingBottom
                )
            }


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
            .backgroundColor(backgroundColor)
            .strokeColor(strokeColor)
            .strokeWidth(strokeWidth)
            .elevation(elevation)
            .icon(icon)
            .iconTint(iconTint)
            .iconSize(iconSize)
            .iconPadding(iconPadding)
    }

    override fun toString(): String {
        return """
            isExtended: $isExtended
            backgroundColor: $backgroundColor
            icon: $icon
            iconTint: $iconTint
            iconSize: $iconSize
        """.trimIndent()
    }
}

fun LMFeedFAB.setStyle(viewStyle: LMFeedFABStyle) {
    viewStyle.apply(this)
}