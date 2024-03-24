package com.likeminds.feed.android.core.ui.base.styles

import android.widget.ImageView.ScaleType
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.views.LMFeedImageView
import com.likeminds.feed.android.core.utils.LMFeedViewStyle

/**
 * [LMFeedImageStyle] helps you to customize an image
 *
 * @property imageSrc : [Any]] sets the provided image source to the image view, use this to set a static source to the image | Default value [R.drawable.lm_feed_picture_placeholder]
 * @property placeholderSrc : [Any] sets the placeholder source to the image view while the image is loading, use this to set a static placeholder to the image | Default value [null]
 * @property isCircle : [Boolean] this will define whether the image view should be circular or not | Default value [false]
 * @property showGreyScale : [Boolean] whether to show grey scale to the image view or not | Default value [false]
 * @property cornerRadius : [Int] this will help to customize the corner radius of the image view  | Default value [null]
 * @property imageTint : [Int] should be in format of [ColorRes] this will help to customize the tint of the image view  | Default value [null]
 * @property alpha : [Float] this will help to customize the alpha of the image view  | Default value [null]
 * @property scaleType : [ScaleType] this will help to customize the scale type of the image view  | Default value [null]
 * */
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
            setImage(imageSrc, this@LMFeedImageStyle)

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

//sets the provided view style to the image view
fun LMFeedImageView.setStyle(viewStyle: LMFeedImageStyle) {
    viewStyle.apply(this)
}