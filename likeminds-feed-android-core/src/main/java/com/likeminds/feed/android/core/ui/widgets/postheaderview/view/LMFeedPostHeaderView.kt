package com.likeminds.feed.android.core.ui.widgets.postheaderview.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.likeminds.feed.android.core.ui.widgets.postheaderview.style.LMFeedPostHeaderViewStyle
import com.likeminds.feed.android.core.util.LMFeedStyleTransformer
import com.likeminds.feed.android.ui.base.styles.*
import com.likeminds.feed.android.ui.databinding.LmFeedPostHeaderViewBinding
import com.likeminds.feed.android.ui.utils.LMFeedImageBindingUtil
import com.likeminds.feed.android.ui.utils.LMFeedTimeUtil
import com.likeminds.feed.android.ui.utils.LMFeedViewUtils.hide
import com.likeminds.feed.android.ui.utils.LMFeedViewUtils.show

class LMFeedPostHeaderView : ConstraintLayout {

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

    private val binding = LmFeedPostHeaderViewBinding.inflate(inflater, this, true)

    // todo: ask how to set image through image view style or by using this
    /**
     * Sets author image view.
     *
     * @param imageSrc - image source to be set as author image.
     */
    fun setAuthorImage(imageSrc: Any) {
        val authorImageViewStyle =
            LMFeedStyleTransformer.postViewStyle.postHeaderViewStyle.authorImageViewStyle

        LMFeedImageBindingUtil.loadImage(
            binding.ivAuthorImage,
            imageSrc,
            authorImageViewStyle.placeholderSrc,
            authorImageViewStyle.isCircle,
            (authorImageViewStyle.cornerRadius ?: 0),
            authorImageViewStyle.showGreyScale,
        )
    }

    /**
     * Sets the name of the post author
     *
     * @param authorName - string to be set for author name.
     */
    fun setAuthorName(authorName: String) {
        binding.tvAuthorName.text = authorName
    }

    /**
     * Sets the time the post was created.
     *
     * @param createdAtTimeStamp - timestamp when the post was created.
     */
    fun setTimestamp(createdAtTimeStamp: Long) {
        binding.tvTime.text = LMFeedTimeUtil.getRelativeTimeInString(createdAtTimeStamp)
    }

    /**
     * Shows the `Edited` text if the post was edited.
     *
     * @param isEdited - whether the post was edited or not.
     */
    fun setPostEdited(isEdited: Boolean) {
        binding.viewDotEdited.isVisible = isEdited
        binding.tvEdited.isVisible = isEdited
    }

    /**
     * Sets the custom title of the post author
     *
     * @param customTitle - string to be set for author custom title.
     */
    fun setAuthorCustomTitle(customTitle: String) {
        binding.tvCustomTitle.text = customTitle
    }

    // todo: set menu items and click listeners

    fun setStyle(postHeaderViewStyle: LMFeedPostHeaderViewStyle) {

        //set background color
        if (postHeaderViewStyle.backgroundColor != null) {
            setBackgroundColor(ContextCompat.getColor(context, postHeaderViewStyle.backgroundColor))
        }

        configureAuthorImage(postHeaderViewStyle.authorImageViewStyle)
        configureAuthorName(postHeaderViewStyle.authorNameViewStyle)
        configureTimestamp(postHeaderViewStyle.timestampTextStyle)
        configureEditedTag(postHeaderViewStyle.postEditedTextStyle)
        configureAuthorCustomTitle(postHeaderViewStyle.authorCustomTitleTextStyle)
        configurePinIcon(postHeaderViewStyle.pinIconStyle)
        configureMenuIcon(postHeaderViewStyle.menuIconStyle)
    }

    private fun configureMenuIcon(menuIconStyle: LMFeedIconStyle?) {
        binding.ivPostMenu.apply {
            if (menuIconStyle == null) {
                hide()
            } else {
                setStyle(menuIconStyle)
            }
        }
    }

    private fun configurePinIcon(pinIconStyle: LMFeedIconStyle?) {
        binding.ivPin.apply {
            if (pinIconStyle == null) {
                hide()
            } else {
                setStyle(pinIconStyle)
            }
        }
    }

    private fun configureAuthorCustomTitle(authorCustomTitleTextStyle: LMFeedTextStyle?) {
        binding.tvCustomTitle.apply {
            if (authorCustomTitleTextStyle == null) {
                hide()
            } else {
                setStyle(authorCustomTitleTextStyle)
            }
        }
    }

    private fun configureEditedTag(postEditedTextStyle: LMFeedTextStyle?) {
        binding.apply {
            if (postEditedTextStyle == null) {
                tvEdited.hide()
                viewDotEdited.hide()
            } else {
                tvEdited.setStyle(postEditedTextStyle)
                viewDotEdited.show()
            }
        }
    }

    private fun configureTimestamp(timestampTextStyle: LMFeedTextStyle?) {
        binding.tvTime.apply {
            if (timestampTextStyle == null) {
                hide()
            } else {
                setStyle(timestampTextStyle)
            }
        }
    }

    private fun configureAuthorName(authorNameViewStyle: LMFeedTextStyle) {
        binding.tvAuthorName.setStyle(authorNameViewStyle)
    }

    private fun configureAuthorImage(authorImageViewStyle: LMFeedImageStyle) {
        binding.ivAuthorImage.setStyle(authorImageViewStyle)
    }
}