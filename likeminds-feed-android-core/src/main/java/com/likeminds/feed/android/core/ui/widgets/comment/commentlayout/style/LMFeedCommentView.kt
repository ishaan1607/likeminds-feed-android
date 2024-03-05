package com.likeminds.feed.android.core.ui.widgets.comment.commentlayout.style

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.likeminds.feed.android.core.databinding.LmFeedCommentViewBinding
import com.likeminds.feed.android.core.ui.widgets.comment.commentlayout.view.LMFeedCommentViewStyle
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.hide
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.show

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
}