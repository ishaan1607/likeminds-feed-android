package com.likeminds.feed.android.core.ui.widgets.comment.commentlayout.style

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.likeminds.feed.android.core.databinding.LmFeedCommentViewBinding
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.ui.widgets.comment.commentlayout.view.LMFeedCommentViewStyle
import com.likeminds.feed.android.core.universalfeed.model.LMFeedUserViewData
import com.likeminds.feed.android.core.utils.*
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.hide
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.show
import com.likeminds.feed.android.core.utils.listeners.LMFeedOnClickListener
import com.likeminds.feed.android.core.utils.user.LMFeedUserImageUtil

class LMFeedCommentView : ConstraintLayout {

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

    private val binding = LmFeedCommentViewBinding.inflate(inflater, this, true)

    fun setStyle(commentViewStyle: LMFeedCommentViewStyle) {

        commentViewStyle.apply {
            //set background color
            backgroundColor?.let {
                setBackgroundColor(ContextCompat.getColor(context, backgroundColor))
            }

            configureCommenterImage(commenterImageViewStyle)
            configureCommenterName(commenterNameTextStyle)
            configureCommentContent(commentContentStyle)
            configureMenuIcon(menuIconStyle)
            configureLikeIcon(likeIconStyle)
            configureLikeText(likeTextStyle)
            configureReplyText(replyTextStyle)
            configureReplyCountText(replyCountTextStyle)
            configureTimestampText(timestampTextStyle)
            configureCommentEditedText(commentEditedTextStyle)
        }
    }

    private fun configureCommenterImage(commenterImageViewStyle: LMFeedImageStyle?) {
        binding.ivCommenterImage.apply {
            if (commenterImageViewStyle == null) {
                hide()
            } else {
                show()
                setStyle(commenterImageViewStyle)
            }
        }
    }

    private fun configureCommenterName(commenterNameTextStyle: LMFeedTextStyle) {
        binding.tvCommenterName.setStyle(commenterNameTextStyle)
    }

    private fun configureCommentContent(commentContentStyle: LMFeedTextStyle) {
        binding.tvCommentContent.setStyle(commentContentStyle)
    }

    private fun configureMenuIcon(menuIconStyle: LMFeedIconStyle?) {
        binding.ivCommentMenu.apply {
            if (menuIconStyle == null) {
                hide()
            } else {
                show()
                setStyle(menuIconStyle)
            }
        }
    }

    private fun configureLikeIcon(likeIconStyle: LMFeedIconStyle) {
        binding.ivLike.setStyle(likeIconStyle)
    }

    private fun configureLikeText(likeTextStyle: LMFeedTextStyle?) {
        binding.tvLikesCount.apply {
            if (likeTextStyle == null) {
                hide()
            } else {
                setStyle(likeTextStyle)
            }
        }
    }

    private fun configureReplyText(replyTextStyle: LMFeedTextStyle?) {
        binding.apply {
            if (replyTextStyle == null) {
                tvReply.hide()
                likesSeparator.hide()
            } else {
                tvReply.setStyle(replyTextStyle)
                tvReply.show()
                likesSeparator.show()
            }
        }
    }

    private fun configureReplyCountText(replyCountTextStyle: LMFeedTextStyle?) {
        binding.tvReplyCount.apply {
            if (replyCountTextStyle == null) {
                hide()
            } else {
                setStyle(replyCountTextStyle)
            }
        }
    }

    private fun configureTimestampText(timestampTextStyle: LMFeedTextStyle?) {
        binding.tvCommentTime.apply {
            if (timestampTextStyle == null) {
                hide()
            } else {
                setStyle(timestampTextStyle)
            }
        }
    }

    private fun configureCommentEditedText(commentEditedTextStyle: LMFeedTextStyle?) {
        binding.apply {
            if (commentEditedTextStyle == null) {
                tvEdited.hide()
                viewDotEdited.hide()
            } else {
                viewDotEdited.show()
                tvEdited.setStyle(commentEditedTextStyle)
            }
        }
    }

    /**
     * Sets commenter image view.
     *
     * @param user - data of the commenter.
     */
    fun setCommenterImage(user: LMFeedUserViewData) {
        var commenterImageViewStyle =
            LMFeedStyleTransformer.postDetailFragmentViewStyle.commentViewStyle.commenterImageViewStyle
                ?: return

        if (commenterImageViewStyle.placeholderSrc == null) {
            commenterImageViewStyle = commenterImageViewStyle.toBuilder().placeholderSrc(
                LMFeedUserImageUtil.getNameDrawable(
                    user.sdkClientInfoViewData.uuid,
                    user.name,
                    commenterImageViewStyle.isCircle,
                ).first
            ).build()
        }
        binding.ivCommenterImage.setImage(user.imageUrl, commenterImageViewStyle)
    }

    /**
     * Sets the name of the commenter author
     *
     * @param commenterName - string to be set for commenter's name.
     */
    fun setCommenterName(commenterName: String) {
        binding.tvCommenterName.text = commenterName
    }

    /**
     * Sets the time the comment was created.
     *
     * @param createdAtTimeStamp - timestamp when the comment was created.
     */
    fun setTimestamp(createdAtTimeStamp: Long) {
        binding.tvCommentTime.text = LMFeedTimeUtil.getRelativeTimeInString(createdAtTimeStamp)
    }

    /**
     * Shows the `Edited` text if the comment was edited.
     *
     * @param isEdited - whether the comment was edited or not.
     */
    fun setCommentEdited(isEdited: Boolean) {
        binding.viewDotEdited.isVisible = isEdited
        binding.tvEdited.isVisible = isEdited
    }

    /**
     * Sets active/inactive like icon.
     *
     * @param isLiked - whether the comment is liked or not.
     */
    fun setLikesIcon(isLiked: Boolean = false) {
        val iconStyle =
            LMFeedStyleTransformer.postDetailFragmentViewStyle.commentViewStyle.likeIconStyle

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
     * Sets likes count text to the likes text view.
     *
     * @param likesCount - string to be set for likes count.
     */
    fun setLikesCount(likesCount: String) {
        binding.tvLikesCount.apply {
            if (likesCount.isEmpty()) {
                hide()
            } else {
                text = likesCount
            }
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
    fun setLikeIconClickListener(listener: LMFeedOnClickListener) {
        binding.ivLike.setOnClickListener { view ->
            // bounce animation for like button
            LMFeedViewUtils.showBounceAnim(view.context, view)
            listener.onClick()
        }
    }

    /**
     * Sets click listener on the reply text
     *
     * @param listener [LMFeedOnClickListener] interface to have click listener
     */
    fun setReplyClickListener(listener: LMFeedOnClickListener) {
        binding.tvReply.setOnClickListener {
            listener.onClick()
        }
    }
}