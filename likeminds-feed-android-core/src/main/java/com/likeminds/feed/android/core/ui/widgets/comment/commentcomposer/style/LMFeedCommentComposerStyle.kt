package com.likeminds.feed.android.core.ui.widgets.comment.commentcomposer.style

import android.graphics.Typeface
import android.text.TextUtils
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.*

class LMFeedCommentComposerStyle private constructor(
    val commentInputStyle: LMFeedEditTextStyle,
    val commentSendStyle: LMFeedIconStyle,
    val commentRestrictedStyle: LMFeedTextStyle?,
    val replyingToStyle: LMFeedTextStyle?,
    val removeReplyingToStyle: LMFeedIconStyle?,
    @DimenRes val elevation: Int?,
    @ColorRes val backgroundColor: Int?
) {
    class Builder {
        private var commentInputStyle: LMFeedEditTextStyle = LMFeedEditTextStyle.Builder()
            .inputTextStyle(
                LMFeedTextStyle.Builder()
                    .ellipsize(TextUtils.TruncateAt.END)
                    .maxHeight(R.dimen.lm_feed_text_max_height)
                    .minHeight(R.dimen.lm_feed_text_min_height)
                    .textColor(R.color.lm_feed_dark_grey)
                    .typeface(Typeface.NORMAL)
                    .fontResource(R.font.lm_feed_roboto)
                    .backgroundColor(R.color.lm_feed_white)
                    .build()
            )
            .backgroundColor(R.color.lm_feed_white)
            .hintTextColor(R.color.lm_feed_maastricht_blue_40)
            .build()

        private var commentSendStyle: LMFeedIconStyle = LMFeedIconStyle.Builder()
            .activeSrc(R.drawable.lm_feed_ic_comment_send_enable)
            .inActiveSrc(R.drawable.lm_feed_ic_comment_send_disable)
            .backgroundColor(R.color.lm_feed_white)
            .iconTint(R.color.lm_feed_majorelle_blue)
            .build()

        private var commentRestrictedStyle: LMFeedTextStyle? = null

        private var replyingToStyle: LMFeedTextStyle? = null

        private var removeReplyingToStyle: LMFeedIconStyle? = null

        @DimenRes
        private var elevation: Int? = null

        @ColorRes
        private var backgroundColor: Int? = null

        fun commentInputStyle(commentInputStyle: LMFeedEditTextStyle) = apply {
            this.commentInputStyle = commentInputStyle
        }

        fun commentSendStyle(commentSendStyle: LMFeedIconStyle) = apply {
            this.commentSendStyle = commentSendStyle
        }

        fun commentRestrictedStyle(commentRestrictedStyle: LMFeedTextStyle?) = apply {
            this.commentRestrictedStyle = commentRestrictedStyle
        }

        fun replyingToStyle(replyingToStyle: LMFeedTextStyle?) = apply {
            this.replyingToStyle = replyingToStyle
        }

        fun removeReplyingToStyle(removeReplyingToStyle: LMFeedIconStyle?) = apply {
            this.removeReplyingToStyle = removeReplyingToStyle
        }

        fun elevation(@DimenRes elevation: Int?) = apply {
            this.elevation = elevation
        }

        fun backgroundColor(@ColorRes backgroundColor: Int?) = apply {
            this.backgroundColor = backgroundColor
        }

        fun build() = LMFeedCommentComposerStyle(
            commentInputStyle,
            commentSendStyle,
            commentRestrictedStyle,
            replyingToStyle,
            removeReplyingToStyle,
            elevation,
            backgroundColor
        )
    }

    fun toBuilder(): Builder {
        return Builder().commentInputStyle(commentInputStyle)
            .commentSendStyle(commentSendStyle)
            .commentRestrictedStyle(commentRestrictedStyle)
            .replyingToStyle(replyingToStyle)
            .removeReplyingToStyle(removeReplyingToStyle)
            .elevation(elevation)
            .backgroundColor(backgroundColor)
    }
}