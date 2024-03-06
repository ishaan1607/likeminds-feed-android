package com.likeminds.feed.android.core.ui.widgets.comment.commentcomposer.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.likeminds.feed.android.core.databinding.LmFeedCommentComposerViewBinding
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.ui.base.views.LMFeedEditText
import com.likeminds.feed.android.core.ui.widgets.comment.commentcomposer.style.LMFeedCommentComposerStyle
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.hide
import com.likeminds.feed.android.core.utils.listeners.LMFeedOnClickListener

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

    //todo: confirm this
    val etComment: LMFeedEditText
        get() = binding.etComment

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

    /**
     * Sets active/inactive comment send icon.
     *
     * @param isEnabled - whether the comment send is enabled or not.
     */
    fun setCommentSendButton(isEnabled: Boolean = false) {
        val iconStyle =
            LMFeedStyleTransformer.postDetailFragmentViewStyle.commentComposerStyle.commentSendStyle

        val commentSendIcon = if (isEnabled) {
            iconStyle.activeSrc
        } else {
            iconStyle.inActiveSrc
        }

        if (commentSendIcon != null) {
            binding.ivCommentSend.setImageDrawable(
                ContextCompat.getDrawable(
                    context,
                    commentSendIcon
                )
            )
        }
    }

    /**
     * Sets click listener on the comment send icon
     *
     * @param listener [LMFeedOnClickListener] interface to have click listener
     */
    fun setCommentSendClickListener(listener: LMFeedOnClickListener) {
        binding.ivCommentSend.setOnClickListener {
            listener.onClick()
        }
    }

    /**
     * Sets click listener on the remove reply view
     *
     * @param listener [LMFeedOnClickListener] interface to have click listener
     */
    fun setRemoveReplyingToClickListener(listener: LMFeedOnClickListener) {
        binding.ivRemoveReplyingTo.setOnClickListener {
            listener.onClick()
        }
    }

    fun replyingVisibility(isVisible: Boolean) {
        binding.apply {
            tvReplyingTo.isVisible = isVisible
            ivRemoveReplyingTo.isVisible = isVisible
        }
    }
}