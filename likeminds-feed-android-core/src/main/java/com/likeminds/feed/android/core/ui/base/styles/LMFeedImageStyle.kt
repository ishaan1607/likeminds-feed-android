package com.likeminds.feed.android.core.ui.base.styles

import android.widget.ImageView.ScaleType
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.views.LMFeedImageView
import com.likeminds.feed.android.core.utils.LMFeedImageBindingUtil
import com.likeminds.feed.android.core.utils.LMFeedViewStyle

class LMFeedImageStyle private constructor(
    val imageSrc: Any,
    val placeholderSrc: Any?,
    val isCircle: Boolean,
    val showGreyScale: Boolean,
    val cornerRadius: Int?,
    @ColorRes val imageTint: Int?,
    val alpha: Float?,
    val scaleType: ScaleType?
) : LMFeedViewStyle {

    class Builder {
        private var imageSrc: Any = R.drawable.lm_feed_picture_placeholder
        private var placeholderSrc: Any? = null
        private var isCircle: Boolean = false
        private var showGreyScale: Boolean = false
        private var cornerRadius: Int? = null

        @ColorRes
        private var imageTint: Int? = null
        private var alpha: Float? = null
        private var scaleType: ScaleType? = null

        fun imageSrc(imageSrc: Any) = apply {
            this.imageSrc = imageSrc
        }

        fun placeholderSrc(drawableSrc: Any?) = apply {
            this.placeholderSrc = drawableSrc
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
        fun scaleType(scaleType: ScaleType?) = apply { this.scaleType = scaleType }

        fun build() = LMFeedImageStyle(
            imageSrc,
            placeholderSrc,
            isCircle,
            showGreyScale,
            cornerRadius,
            imageTint,
            alpha,
            scaleType
        )
    }

    fun toBuilder(): Builder {
        return Builder().imageSrc(imageSrc)
            .placeholderSrc(placeholderSrc)
            .isCircle(isCircle)
            .showGreyScale(showGreyScale)
            .cornerRadius(cornerRadius)
            .imageTint(imageTint)
            .alpha(alpha)
            .scaleType(scaleType)
    }

    fun apply(imageView: LMFeedImageView) {
        imageView.apply {
            LMFeedImageBindingUtil.loadImage(
                this,
                imageSrc,
                placeholderSrc,
                isCircle,
                cornerRadius ?: 0,
                showGreyScale
            )

            if (this@LMFeedImageStyle.scaleType != null) {
                this.scaleType = this@LMFeedImageStyle.scaleType
            }

            if (this@LMFeedImageStyle.imageTint != null) {
                val imageTint =
                    ContextCompat.getColorStateList(this.context, this@LMFeedImageStyle.imageTint)
                this.imageTintList = imageTint
            }

            if (this@LMFeedImageStyle.alpha != null) {
                this.alpha = this@LMFeedImageStyle.alpha
            }
        }
    }
}

fun LMFeedImageView.setStyle(viewStyle: LMFeedImageStyle) {
    viewStyle.apply(this)
}