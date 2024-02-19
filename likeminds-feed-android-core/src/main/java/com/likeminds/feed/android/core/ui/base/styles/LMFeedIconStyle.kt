package com.likeminds.feed.android.core.ui.base.styles

import android.widget.ImageView.ScaleType
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.likeminds.feed.android.core.ui.base.views.LMFeedIcon
import com.likeminds.feed.android.core.utils.LMFeedViewStyle

class LMFeedIconStyle private constructor(
    @DrawableRes val activeSrc: Int?,
    @DrawableRes val inActiveSrc: Int?,
    @ColorRes val imageTint: Int?,
    val alpha: Float?,
    val scaleType: ScaleType?
) : LMFeedViewStyle {

    class Builder {
        @DrawableRes
        private var activeSrc: Int? = null

        @DrawableRes
        private var inActiveSrc: Int? = null

        @ColorRes
        private var imageTint: Int? = null
        private var alpha: Float? = null
        private var scaleType: ScaleType? = null

        fun activeSrc(@DrawableRes activeSrc: Int?) = apply {
            this.activeSrc = activeSrc
        }

        fun inActiveSrc(@DrawableRes inActiveSrc: Int?) = apply {
            this.inActiveSrc = inActiveSrc
        }

        fun imageTint(@ColorRes imageTint: Int?) = apply {
            this.imageTint = imageTint
        }

        fun alpha(alpha: Float?) = apply { this.alpha = alpha }
        fun scaleType(scaleType: ScaleType?) = apply { this.scaleType = scaleType }

        fun build() = LMFeedIconStyle(
            activeSrc,
            inActiveSrc,
            imageTint,
            alpha,
            scaleType
        )
    }

    fun toBuilder(): Builder {
        return Builder().activeSrc(activeSrc)
            .inActiveSrc(inActiveSrc)
            .imageTint(imageTint)
            .alpha(alpha)
            .scaleType(scaleType)
    }

    fun apply(icon: LMFeedIcon) {
        icon.apply {
            if (inActiveSrc != null) {
                this.setImageDrawable(ContextCompat.getDrawable(context, inActiveSrc))
            }

            if (this@LMFeedIconStyle.scaleType != null) {
                this.scaleType = this@LMFeedIconStyle.scaleType
            }

            if (this@LMFeedIconStyle.imageTint != null) {
                val imageTint =
                    ContextCompat.getColorStateList(this.context, this@LMFeedIconStyle.imageTint)
                this.imageTintList = imageTint
            }

            if (this@LMFeedIconStyle.alpha != null) {
                this.alpha = this@LMFeedIconStyle.alpha
            }
        }
    }
}

fun LMFeedIcon.setStyle(viewStyle: LMFeedIconStyle) {
    viewStyle.apply(this)
}