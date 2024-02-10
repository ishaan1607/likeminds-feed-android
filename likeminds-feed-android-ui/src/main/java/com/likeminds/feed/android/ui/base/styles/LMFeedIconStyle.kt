package com.likeminds.feed.android.ui.base.styles

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.likeminds.feed.android.ui.base.views.LMFeedIcon
import com.likeminds.feed.android.ui.utils.LMFeedImageBindingUtil
import com.likeminds.feed.android.ui.utils.LMFeedViewStyle

class LMFeedIconStyle private constructor(
    @DrawableRes val activeSrc: Int?,
    @DrawableRes val inActiveSrc: Int?,
    val isCircle: Boolean,
    val showGreyScale: Boolean,
    val cornerRadius: Int?,
    @ColorRes val imageTint: Int?,
    val alpha: Float?
) : LMFeedViewStyle {

    class Builder {
        @DrawableRes
        private var activeSrc: Int? = null

        @DrawableRes
        private var inActiveSrc: Int? = null
        private var isCircle: Boolean = false
        private var showGreyScale: Boolean = false
        private var cornerRadius: Int? = null

        @ColorRes
        private var imageTint: Int? = null
        private var alpha: Float? = null

        fun activeSrc(@DrawableRes activeSrc: Int?) = apply {
            this.activeSrc = activeSrc
        }

        fun inActiveSrc(@DrawableRes inActiveSrc: Int?) = apply {
            this.inActiveSrc = inActiveSrc
        }

        fun isCircle(isCircle: Boolean) = apply {
            this.isCircle = isCircle
        }

        fun showGreyScale(showGreyScale: Boolean) = apply {
            this.showGreyScale = showGreyScale
        }

        fun cornerRadius(cornerRadius: Int?) = apply {
            this.cornerRadius = cornerRadius
        }

        fun imageTint(@ColorRes imageTint: Int?) = apply {
            this.imageTint = imageTint
        }

        fun alpha(alpha: Float?) = apply { this.alpha = alpha }

        fun build() = LMFeedIconStyle(
            activeSrc,
            inActiveSrc,
            isCircle,
            showGreyScale,
            cornerRadius,
            imageTint,
            alpha
        )
    }

    fun toBuilder(): Builder {
        return Builder().activeSrc(activeSrc)
            .inActiveSrc(inActiveSrc)
            .isCircle(isCircle)
            .showGreyScale(showGreyScale)
            .cornerRadius(cornerRadius)
            .imageTint(imageTint)
            .alpha(alpha)
    }

    fun apply(icon: LMFeedIcon) {
        icon.apply {
            LMFeedImageBindingUtil.loadImage(
                this,
                inActiveSrc,
                null,
                isCircle,
                cornerRadius ?: 0,
                showGreyScale
            )

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