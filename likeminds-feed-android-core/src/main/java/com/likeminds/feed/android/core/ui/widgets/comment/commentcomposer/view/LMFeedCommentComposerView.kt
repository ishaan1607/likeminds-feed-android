package com.likeminds.feed.android.core.ui.widgets.comment.commentcomposer.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.likeminds.feed.android.core.databinding.LmFeedCommentComposerViewBinding
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.ui.widgets.comment.commentcomposer.style.LMFeedCommentComposerStyle
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.hide

class LMFeedCommentComposerView : ConstraintLayout {

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

    private val binding: LmFeedCommentComposerViewBinding =
        LmFeedCommentComposerViewBinding.inflate(inflater, this, true)

    fun setStyle(commentComposerStyle: LMFeedCommentComposerStyle) {

        configureCommentInput(commentComposerStyle.commentInputStyle)
        configureCommentSend(commentComposerStyle.commentSendStyle)
        configureCommentRestricted(commentComposerStyle.commentRestrictedStyle)
        configureReplyingTo(commentComposerStyle.replyingToStyle)
        configureRemoveReplyingTo(commentComposerStyle.removeReplyingToStyle)
    }

    private fun configureCommentInput(commentInputStyle: LMFeedEditTextStyle) {
        binding.etComment.setStyle(commentInputStyle)
    }

    private fun configureCommentSend(commentSendStyle: LMFeedIconStyle) {
        binding.ivCommentSend.setStyle(commentSendStyle)
    }

    private fun configureCommentRestricted(commentRestrictedStyle: LMFeedTextStyle?) {
        binding.tvRestricted.apply {
            if (commentRestrictedStyle == null) {
                hide()
            } else {
                setStyle(commentRestrictedStyle)
            }
        }
    }

    private fun configureReplyingTo(replyingToStyle: LMFeedTextStyle?) {
        binding.tvReplyingTo.apply {
            if (replyingToStyle == null) {
                hide()
            } else {
                setStyle(replyingToStyle)
            }
        }
    }

    private fun configureRemoveReplyingTo(removeReplyingToStyle: LMFeedIconStyle?) {
        binding.ivRemoveReplyingTo.apply {
            if (removeReplyingToStyle == null) {
                hide()
            } else {
                setStyle(removeReplyingToStyle)
            }
        }
    }

    fun setCommentInputBoxHint(hint: String) {
        binding.etComment.hint = hint
    }
}