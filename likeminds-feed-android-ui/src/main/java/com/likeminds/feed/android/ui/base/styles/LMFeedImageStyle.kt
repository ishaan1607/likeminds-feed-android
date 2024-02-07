package com.likeminds.feed.android.ui.base.styles

import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat
import com.likeminds.feed.android.ui.R
import com.likeminds.feed.android.ui.base.views.LMFeedImageView
import com.likeminds.feed.android.ui.utils.LMFeedImageBindingUtil
import com.likeminds.feed.android.ui.utils.LMFeedViewStyle

class LMFeedImageStyle private constructor(
    val imageSrc: Any,
    val placeholderSrc: Any?,
    val isCircle: Boolean,
    val showGreyScale: Boolean,
    val cornerRadius: Int?,
    @ColorRes val imageTint: Int?,
    val alpha: Float?
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

        fun build() = LMFeedImageStyle(
            imageSrc,
            placeholderSrc,
            isCircle,
            showGreyScale,
            cornerRadius,
            imageTint,
            alpha
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

            if (this@LMFeedImageStyle.imageTint != null) {
                val imageTint = ContextCompat.getColorStateList(this.context, R.color.lm_feed_azure)
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