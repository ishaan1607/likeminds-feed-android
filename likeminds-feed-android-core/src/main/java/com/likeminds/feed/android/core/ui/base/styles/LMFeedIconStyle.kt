package com.likeminds.feed.android.core.ui.base.styles

import android.widget.ImageView.ScaleType
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.likeminds.feed.android.core.ui.base.views.LMFeedIcon
import com.likeminds.feed.android.core.utils.LMFeedViewStyle
import com.likeminds.feed.android.core.utils.model.LMFeedPadding

class LMFeedIconStyle private constructor(
    @DrawableRes val activeSrc: Int?,
    @DrawableRes val inActiveSrc: Int?,
    @ColorRes val iconTint: Int?,
    val alpha: Float?,
    val scaleType: ScaleType?,
    val iconPadding: LMFeedPadding?
) : LMFeedViewStyle {

    class Builder {
        @DrawableRes
        private var activeSrc: Int? = null

        @DrawableRes
        private var inActiveSrc: Int? = null

        @ColorRes
        private var iconTint: Int? = null
        private var alpha: Float? = null
        private var scaleType: ScaleType? = null
        private var iconPadding: LMFeedPadding? = null

        fun activeSrc(@DrawableRes activeSrc: Int?) = apply {
            this.activeSrc = activeSrc
        }

        fun inActiveSrc(@DrawableRes inActiveSrc: Int?) = apply {
            this.inActiveSrc = inActiveSrc
        }

        fun iconTint(@ColorRes iconTint: Int?) = apply {
            this.iconTint = iconTint
        }

        fun alpha(alpha: Float?) = apply { this.alpha = alpha }
        fun scaleType(scaleType: ScaleType?) = apply { this.scaleType = scaleType }
        fun iconPadding(iconPadding: LMFeedPadding?) = apply { this.iconPadding = iconPadding }

        fun build() = LMFeedIconStyle(
            activeSrc,
            inActiveSrc,
            iconTint,
            alpha,
            scaleType,
            iconPadding
        )
    }

    fun toBuilder(): Builder {
        return Builder().activeSrc(activeSrc)
            .inActiveSrc(inActiveSrc)
            .iconTint(iconTint)
            .alpha(alpha)
            .scaleType(scaleType)
            .iconPadding(iconPadding)
    }

    fun apply(icon: LMFeedIcon) {
        icon.apply {
            if (inActiveSrc != null) {
                setImageDrawable(ContextCompat.getDrawable(context, inActiveSrc))
            }

            if (this@LMFeedIconStyle.scaleType != null) {
                this.scaleType = this@LMFeedIconStyle.scaleType
            }

            if (this@LMFeedIconStyle.iconTint != null) {
                val iconTint =
                    ContextCompat.getColorStateList(context, this@LMFeedIconStyle.iconTint)
                imageTintList = iconTint
            }

            if (this@LMFeedIconStyle.alpha != null) {
                alpha = this@LMFeedIconStyle.alpha
            }

            if (this@LMFeedIconStyle.iconPadding != null) {
                val padding = this@LMFeedIconStyle.iconPadding
                setPadding(
                    resources.getDimension(padding.paddingLeft).toInt(),
                    resources.getDimension(padding.paddingTop).toInt(),
                    resources.getDimension(padding.paddingRight).toInt(),
                    resources.getDimension(padding.paddingBottom).toInt()
                )
            }
        }
    }
}

fun LMFeedIcon.setStyle(viewStyle: LMFeedIconStyle) {
    viewStyle.apply(this)
}