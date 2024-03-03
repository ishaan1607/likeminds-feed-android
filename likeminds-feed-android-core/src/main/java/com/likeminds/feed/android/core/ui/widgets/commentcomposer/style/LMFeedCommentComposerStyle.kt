package com.likeminds.feed.android.core.ui.widgets.commentcomposer.style

import android.text.InputType
import android.text.TextUtils
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.*

class LMFeedCommentComposerStyle private constructor(
    val commentInputStyle: LMFeedEditTextStyle,
    val commentSendStyle: LMFeedIconStyle,
    val commentRestrictedStyle: LMFeedTextStyle?,
    val replyingToStyle: LMFeedTextStyle?,
    val removeReplyingToStyle: LMFeedIconStyle?
) {

    class Builder {
        private var commentInputStyle: LMFeedEditTextStyle = LMFeedEditTextStyle.Builder()
            .inputTextStyle(
                LMFeedTextStyle.Builder()
                    .ellipsize(TextUtils.TruncateAt.END)
                    .maxHeight(R.dimen.lm_feed_text_max_height)
                    .minHeight(R.dimen.lm_feed_text_min_height)
                    .textColor(R.color.lm_feed_dark_grey)
                    .build()
            )
            .elevation(R.dimen.lm_feed_elevation_small)
            .hintTextColor(R.color.lm_feed_maastricht_blue_40)
            .inputType((InputType.TYPE_TEXT_FLAG_CAP_SENTENCES or InputType.TYPE_TEXT_FLAG_MULTI_LINE))
            .backgroundColor(R.color.lm_feed_white)
            .build()
        private var commentSendStyle: LMFeedIconStyle = LMFeedIconStyle.Builder()
            .activeSrc(R.drawable.lm_feed_ic_comment_send_enable)
            .inActiveSrc(R.drawable.lm_feed_ic_comment_send_disable)
            .backgroundColor(R.color.lm_feed_white)
            .iconTint(R.color.lm_feed_majorelle_blue)
            .elevation(R.dimen.lm_feed_elevation_small)
            .build()
        private var commentRestrictedStyle: LMFeedTextStyle? = null
        private var replyingToStyle: LMFeedTextStyle? = null
        private var removeReplyingToStyle: LMFeedIconStyle? = null

        fun commentInputStyle(commentInputStyle: LMFeedEditTextStyle) =
            apply { this.commentInputStyle = commentInputStyle }

        fun commentSendStyle(commentSendStyle: LMFeedIconStyle) =
            apply { this.commentSendStyle = commentSendStyle }

        fun commentRestrictedStyle(commentRestrictedStyle: LMFeedTextStyle?) =
            apply { this.commentRestrictedStyle = commentRestrictedStyle }

        fun replyingToStyle(replyingToStyle: LMFeedTextStyle?) =
            apply { this.replyingToStyle = replyingToStyle }

        fun removeReplyingToStyle(removeReplyingToStyle: LMFeedIconStyle?) =
            apply { this.removeReplyingToStyle = removeReplyingToStyle }

        fun build() = LMFeedCommentComposerStyle(
            commentInputStyle,
            commentSendStyle,
            commentRestrictedStyle,
            replyingToStyle,
            removeReplyingToStyle
        )
    }

    fun toBuilder(): Builder {
        return Builder().commentInputStyle(commentInputStyle)
            .commentSendStyle(commentSendStyle)
            .commentRestrictedStyle(commentRestrictedStyle)
            .replyingToStyle(replyingToStyle)
            .removeReplyingToStyle(removeReplyingToStyle)
    }
}