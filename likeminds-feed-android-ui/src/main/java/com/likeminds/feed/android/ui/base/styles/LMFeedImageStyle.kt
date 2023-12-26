package com.likeminds.feed.android.ui.base.styles

import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.core.content.ContextCompat
import com.likeminds.feed.android.ui.R
import com.likeminds.feed.android.ui.base.views.LMFeedImageView
import com.likeminds.feed.android.ui.utils.LMFeedImageBindingUtil
import com.likeminds.feed.android.ui.utils.ViewStyle
import com.likeminds.feed.android.ui.utils.model.LMFeedPadding

class LMFeedImageStyle private constructor(
    val imageSrc: Any,
    val drawableSrc: Any,
    val isCircle: Boolean,
    val showGreyScale: Boolean,
    @DimenRes val cornerRadius: Int?,
    val padding: LMFeedPadding?,
    @ColorRes val backgroundColor: Int?
) : ViewStyle {

    class Builder {
        private var imageSrc: Any = R.drawable.lm_feed_picture_placeholder
        private var drawableSrc: Any = R.drawable.lm_feed_picture_placeholder
        private var isCircle: Boolean = false
        private var showGreyScale: Boolean = false

        @DimenRes
        private var cornerRadius: Int? = null
        private var padding: LMFeedPadding? = null

        @ColorRes
        private var backgroundColor: Int? = null

        fun imageSrc(imageSrc: Any) = apply {
            this.imageSrc = imageSrc
        }

        fun drawableSrc(drawableSrc: Any) = apply {
            this.drawableSrc = drawableSrc
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

        fun build() = LMFeedImageStyle(
            imageSrc,
            drawableSrc,
            isCircle,
            showGreyScale,
            cornerRadius,
            padding,
            backgroundColor
        )
    }

    fun toBuilder(): Builder {
        return Builder().imageSrc(imageSrc)
            .drawableSrc(drawableSrc)
            .isCircle(isCircle)
            .showGreyScale(showGreyScale)
            .cornerRadius(cornerRadius)
            .padding(padding)
            .backgroundColor(backgroundColor)
    }

    fun apply(imageView: LMFeedImageView) {
        imageView.apply {
            LMFeedImageBindingUtil.loadImage(
                this,
                imageSrc,
                drawableSrc,
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
        }
    }
}