package com.likeminds.feed.android.ui.base.styles

import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat
import com.likeminds.feed.android.ui.R
import com.likeminds.feed.android.ui.base.views.LMFeedImageView
import com.likeminds.feed.android.ui.utils.LMFeedImageBindingUtil
import com.likeminds.feed.android.ui.utils.LMFeedViewStyle
import com.likeminds.feed.android.ui.utils.model.LMFeedPadding

class LMFeedImageStyle private constructor(
    val imageSrc: Any,
    val placeholderSrc: Any?,
    val isCircle: Boolean,
    val showGreyScale: Boolean,
    @DimenRes val cornerRadius: Int?,
    val padding: LMFeedPadding?,
    @ColorRes val backgroundColor: Int?,
    val alpha: Float?
) : LMFeedViewStyle {

    class Builder {
        private var imageSrc: Any = R.drawable.lm_feed_picture_placeholder
        private var placeholderSrc: Any? = null
        private var isCircle: Boolean = false
        private var showGreyScale: Boolean = false

        @DimenRes
        private var cornerRadius: Int? = null
        private var padding: LMFeedPadding? = null

        @ColorRes
        private var backgroundColor: Int? = null
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

        fun cornerRadius(@DimenRes cornerRadius: Int?) = apply {
            this.cornerRadius = cornerRadius
        }

        fun padding(padding: LMFeedPadding?) = apply {
            this.padding = padding
        }

        fun backgroundColor(@ColorRes backgroundColor: Int?) = apply {
            this.backgroundColor = backgroundColor
        }

        fun alpha(alpha: Float?) = apply { this.alpha = alpha }

        fun build() = LMFeedImageStyle(
            imageSrc,
            placeholderSrc,
            isCircle,
            showGreyScale,
            cornerRadius,
            padding,
            backgroundColor,
            alpha
        )
    }

    fun toBuilder(): Builder {
        return Builder().imageSrc(imageSrc)
            .placeholderSrc(placeholderSrc)
            .isCircle(isCircle)
            .showGreyScale(showGreyScale)
            .cornerRadius(cornerRadius)
            .padding(padding)
            .backgroundColor(backgroundColor)
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

            if (this@LMFeedImageStyle.backgroundColor != null) {
                val backgroundColor =
                    ContextCompat.getColor(context, this@LMFeedImageStyle.backgroundColor)
                this.setBackgroundColor(backgroundColor)
            }

            if (padding != null) {
                setPadding(
                    padding.paddingLeft,
                    padding.paddingTop,
                    padding.paddingRight,
                    padding.paddingBottom
                )
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