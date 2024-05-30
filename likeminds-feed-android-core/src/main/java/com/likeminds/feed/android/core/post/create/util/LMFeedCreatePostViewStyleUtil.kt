package com.likeminds.feed.android.core.post.create.util

import android.text.TextUtils
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.LMFeedIconStyle
import com.likeminds.feed.android.core.ui.base.styles.LMFeedTextStyle
import com.likeminds.feed.android.core.ui.widgets.poll.style.LMFeedPostPollOptionViewStyle
import com.likeminds.feed.android.core.ui.widgets.poll.style.LMFeedPostPollViewStyle

object LMFeedCreatePostViewStyleUtil {
    fun getUpdatedPollViewStyles(): LMFeedPostPollViewStyle {
        return LMFeedPostPollViewStyle.Builder()
            .pollTitleTextStyle(
                LMFeedTextStyle.Builder()
                    .maxLines(3)
                    .textColor(R.color.lm_feed_dark_grey)
                    .textSize(R.dimen.lm_feed_text_large)
                    .fontResource(R.font.lm_feed_roboto_medium)
                    .build()
            )
            .pollOptionsViewStyle(
                getUpdatedOptionViewStyle()
            )
            .membersVotedCountTextStyle(null)
            .pollInfoTextStyle(
                LMFeedTextStyle.Builder()
                    .maxLines(1)
                    .textColor(R.color.lm_feed_brown_grey)
                    .textSize(R.dimen.lm_feed_text_medium)
                    .fontResource(R.font.lm_feed_roboto)
                    .build()
            )
            .submitPollVoteButtonStyle(null)
            .addPollOptionButtonStyle(null)
            .pollExpiryTextStyle(
                LMFeedTextStyle.Builder()
                    .textColor(R.color.lm_feed_grey)
                    .textSize(R.dimen.lm_feed_text_small)
                    .fontResource(R.font.lm_feed_roboto)
                    .maxLines(1)
                    .build()
            )
            .editPollVoteTextStyle(null)
            .clearPollIconStyle(
                LMFeedIconStyle.Builder()
                    .inActiveSrc(R.drawable.lm_feed_ic_cross_circle)
                    .build()
            )
            .editPollIconStyle(
                LMFeedIconStyle.Builder()
                    .inActiveSrc(R.drawable.lm_feed_ic_edit_poll)
                    .iconTint(R.color.lm_feed_dark_grey)
                    .build()
            )
            .build()
    }

    fun getUpdatedOptionViewStyle(): LMFeedPostPollOptionViewStyle {
        return LMFeedPostPollOptionViewStyle.Builder()
            .pollOptionTextStyle(
                LMFeedTextStyle.Builder()
                    .textColor(R.color.lm_feed_dark_grey)
                    .textSize(R.dimen.lm_feed_text_large)
                    .fontResource(R.font.lm_feed_roboto)
                    .ellipsize(TextUtils.TruncateAt.END)
                    .maxLines(2)
                    .build()
            )
            .pollSelectedOptionColor(R.color.lm_feed_brown_grey)
            .pollOtherOptionColor(R.color.lm_feed_brown_grey)
            .pollOptionVotesCountTextStyle(null)
            .pollOptionAddedByTextStyle(null)
            .pollOptionCheckIconStyle(null)
            .build()
    }
}