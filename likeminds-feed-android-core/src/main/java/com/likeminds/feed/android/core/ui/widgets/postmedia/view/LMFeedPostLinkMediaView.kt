package com.likeminds.feed.android.core.ui.widgets.postmedia.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.content.ContextCompat
import com.google.android.material.card.MaterialCardView
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.databinding.LmFeedPostLinkMediaViewBinding
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.ui.widgets.postmedia.style.LMFeedPostLinkViewStyle
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer
import com.likeminds.feed.android.core.utils.LMFeedValueUtils.isImageValid
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.hide
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.show
import com.likeminds.feed.android.core.utils.listeners.LMFeedOnClickListener
import java.util.Locale
import kotlin.math.roundToInt

class LMFeedPostLinkMediaView : MaterialCardView {

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
    }

    private val inflater =
        (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)

    private val binding = LmFeedPostLinkMediaViewBinding.inflate(inflater, this, true)

    //sets provided [postLinkViewStyle] to the link type post
    fun setStyle(postLinkViewStyle: LMFeedPostLinkViewStyle) {
        postLinkViewStyle.apply {
            //set background color of the link box
            if (backgroundColor != null) {
                setBackgroundColor(ContextCompat.getColor(context, backgroundColor))
            }

            //set corner radius of the link box
            if (linkBoxCornerRadius != null) {
                radius = resources.getDimension(linkBoxCornerRadius)
            }

            //set elevation of the link box
            if (linkBoxElevation != null) {
                elevation = resources.getDimension(linkBoxElevation)
            }

            //set stroke color of the link box
            if (linkBoxStrokeColor != null) {
                strokeColor = ContextCompat.getColor(context, linkBoxStrokeColor)
            }

            //set stroke color of the link box
            if (linkBoxStrokeWidth != null) {
                strokeWidth =
                    resources.getDimension(linkBoxStrokeWidth).roundToInt()
            }

            //configures each view of the link type post with the provided styles
            configureLinkTitle(linkTitleStyle)
            configureLinkDescription(linkDescriptionStyle)
            configureLinkUrl(linkUrlStyle)
            configureLinkImage(linkImageStyle)
        }
    }

    private fun configureLinkTitle(linkTitleStyle: LMFeedTextStyle) {
        binding.tvLinkTitle.setStyle(linkTitleStyle)
    }

    private fun configureLinkDescription(linkDescriptionStyle: LMFeedTextStyle?) {
        binding.tvLinkDescription.apply {
            if (linkDescriptionStyle == null) {
                hide()
            } else {
                show()
                setStyle(linkDescriptionStyle)
            }
        }
    }

    private fun configureLinkUrl(linkUrlStyle: LMFeedTextStyle?) {
        binding.tvLinkUrl.apply {
            if (linkUrlStyle == null) {
                hide()
            } else {
                show()
                setStyle(linkUrlStyle)
            }
        }
    }

    private fun configureLinkImage(linkImageStyle: LMFeedImageStyle) {
        binding.ivLink.setStyle(linkImageStyle)
    }

    /**
     * Sets the title for the link
     *
     * @param linkTitle - string to be set for title of the link.
     */
    fun setLinkTitle(linkTitle: String?) {
        val linkText = if (linkTitle.isNullOrBlank()) {
            context.getString(R.string.lm_feed_link)
        } else {
            linkTitle
        }

        binding.tvLinkTitle.text = linkText
    }

    /**
     * Sets the description for the link
     *
     * @param linkDescription - string to be set for description of the link.
     */
    fun setLinkDescription(linkDescription: String?) {
        binding.tvLinkDescription.apply {
            LMFeedStyleTransformer.postViewStyle.postMediaStyle.postLinkViewStyle?.linkDescriptionStyle
                ?: return@apply

            if (linkDescription.isNullOrBlank()) {
                hide()
            } else {
                show()
                text = linkDescription
            }
        }
    }

    /**
     * Sets the url for the link
     *
     * @param linkUrl - string to be set for url of the link.
     */
    fun setLinkUrl(linkUrl: String?) {
        binding.tvLinkUrl.apply {
            val linkUrlText = linkUrl?.lowercase(Locale.getDefault()) ?: ""

            if (linkUrlText.isBlank()) {
                hide()
            } else {
                show()
                text = linkUrl
            }
        }
    }

    /**
     * Sets image of the link.
     *
     * @param imageSrc - image source to be set as image of the link.
     */
    fun setLinkImage(imageSrc: String?) {
        val linkImageViewStyle =
            LMFeedStyleTransformer.postViewStyle.postMediaStyle.postLinkViewStyle?.linkImageStyle
                ?: return

        binding.ivLink.apply {
            if (imageSrc.isImageValid()) {
                show()
                binding.ivLink.setImage(imageSrc, linkImageViewStyle)
            } else {
                hide()
            }
        }
    }

    /**
     * Sets click listener on the link view
     *
     * @param listener [LMFeedOnClickListener] interface to have click listener
     */
    fun setLinkClickListener(listener: LMFeedOnClickListener) {
        binding.root.setOnClickListener {
            listener.onClick()
        }
    }
}