package com.likeminds.feed.android.core.ui.widgets.postfooterview.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.likeminds.feed.android.core.databinding.LmFeedPostFooterViewBinding
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.ui.widgets.postfooterview.style.LMFeedPostFooterViewStyle
import com.likeminds.feed.android.core.utils.listeners.LMFeedOnClickListener
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.hide

class LMFeedPostFooterView : ConstraintLayout {

    constructor(context: Context) : super(context) {

    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {

    }

    constructor(context: Context, attributeSet: AttributeSet?, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle
    ) {

    }

    private val inflater =
        (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)

    private val binding = LmFeedPostFooterViewBinding.inflate(inflater, this, true)

    fun setStyle(postFooterViewStyle: LMFeedPostFooterViewStyle) {

        //set background color
        if (postFooterViewStyle.backgroundColor != null) {
            setBackgroundColor(ContextCompat.getColor(context, postFooterViewStyle.backgroundColor))
        }

        configuresLikeText(postFooterViewStyle.likeTextStyle)
        configureLikesIcon(postFooterViewStyle.likeIconStyle)
        configureCommentsText(postFooterViewStyle.commentTextStyle)
        configureSaveIcon(postFooterViewStyle.saveIconStyle)
        configureShareIcon(postFooterViewStyle.shareIconStyle)
    }

    private fun configuresLikeText(likeTextStyle: LMFeedTextStyle?) {
        binding.tvLikesCount.apply {
            if (likeTextStyle == null) {
                hide()
            } else {
                setStyle(likeTextStyle)
            }
        }
    }

    private fun configureLikesIcon(likeIconStyle: LMFeedIconStyle) {
        binding.ivLike.setStyle(likeIconStyle)
    }

    private fun configureCommentsText(commentTextStyle: LMFeedTextStyle) {
        binding.tvCommentsCount.setStyle(commentTextStyle)
    }

    private fun configureSaveIcon(saveIconStyle: LMFeedIconStyle?) {
        if (saveIconStyle != null) {
            binding.ivSave.setStyle(saveIconStyle)
        }
    }

    private fun configureShareIcon(shareIconStyle: LMFeedIconStyle?) {
        if (shareIconStyle != null) {
            binding.ivShare.setStyle(shareIconStyle)
        }
    }

    /**
     * Sets likes count text to the likes text view.
     *
     * @param likesCount - string to be set for likes count.
     */
    fun setLikesCount(likesCount: String) {
        binding.tvLikesCount.text = likesCount
    }

    /**
     * Sets comments count text to the comments text view.
     *
     * @param commentsCount - string to be set for comments count.
     */
    fun setCommentsCount(commentsCount: String) {
        binding.tvCommentsCount.text = commentsCount
    }

    /**
     * Sets active/inactive like icon.
     *
     * @param isLiked - whether the post is liked or not.
     */
    fun setLikesIcon(isLiked: Boolean = false) {
        val iconStyle = LMFeedStyleTransformer.postViewStyle.postFooterViewStyle.likeIconStyle

        val likeIcon = if (isLiked) {
            iconStyle.activeSrc
        } else {
            iconStyle.inActiveSrc
        }

        if (likeIcon != null) {
            binding.ivLike.setImageDrawable(ContextCompat.getDrawable(context, likeIcon))
        }
    }

    /**
     * Sets click listener on the like icon
     *
     * @param listener [LMFeedOnClickListener] interface to have click listener
     */
    fun setLikeIconClickListener(listener: LMFeedOnClickListener) {
        binding.ivLike.setOnClickListener {
            listener.onClick()
        }
    }

    /**
     * Sets click listener on the like icon
     *
     * @param listener [LMFeedOnClickListener] interface to have click listener
     */
    fun setLikesCountClickListener(listener: LMFeedOnClickListener) {
        binding.tvLikesCount.setOnClickListener {
            listener.onClick()
        }
    }

    /**
     * Sets click listener on the like icon
     *
     * @param listener [LMFeedOnClickListener] interface to have click listener
     */
    fun setCommentsCountClickListener(listener: LMFeedOnClickListener) {
        binding.tvCommentsCount.setOnClickListener {
            listener.onClick()
        }
    }

    /**
     * Sets active/inactive save icon.
     *
     * @param isSaved - whether the post is saved or not.
     */
    fun setSaveIcon(isSaved: Boolean = false) {
        binding.ivSave.apply {
            val iconStyle = LMFeedStyleTransformer.postViewStyle.postFooterViewStyle.saveIconStyle

            if (iconStyle == null) {
                hide()
                return
            }

            val saveIcon = if (isSaved) {
                iconStyle.activeSrc
            } else {
                iconStyle.inActiveSrc
            }

            if (saveIcon != null) {
                setImageDrawable(ContextCompat.getDrawable(context, saveIcon))
            }
        }
    }

    /**
     * Sets click listener on the save icon
     *
     * @param listener [LMFeedOnClickListener] interface to have click listener
     */
    fun setSaveIconListener(listener: LMFeedOnClickListener) {
        binding.ivSave.setOnClickListener {
            listener.onClick()
        }
    }

    /**
     * Sets click listener on the share icon
     *
     * @param listener [LMFeedOnClickListener] interface to have click listener
     */
    fun setShareIconListener(listener: LMFeedOnClickListener) {
        binding.ivShare.setOnClickListener {
            listener.onClick()
        }
    }
}