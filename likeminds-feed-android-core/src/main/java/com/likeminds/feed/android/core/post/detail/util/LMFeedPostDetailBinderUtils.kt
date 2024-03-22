package com.likeminds.feed.android.core.post.detail.util

import com.likeminds.feed.android.core.post.detail.model.LMFeedCommentViewData

object LMFeedPostDetailBinderUtils {

    //updates comment object for a see full content action and returns updated comment
    fun updateCommentForSeeFullContent(oldCommentViewData: LMFeedCommentViewData): LMFeedCommentViewData {
        //return updated comment view data
        return oldCommentViewData.toBuilder()
            .alreadySeenFullContent(true)
            .fromCommentLiked(false)
            .fromCommentEdited(false)
            .build()
    }

    //updates comment object for a like action and returns updated comment
    fun updateCommentForLike(oldCommentViewData: LMFeedCommentViewData): LMFeedCommentViewData {
        //new like count
        val newLikesCount = if (oldCommentViewData.isLiked) {
            oldCommentViewData.likesCount - 1
        } else {
            oldCommentViewData.likesCount + 1
        }

        //return updated comment view data
        return oldCommentViewData.toBuilder()
            .fromCommentLiked(true)
            .fromCommentEdited(false)
            .isLiked(!oldCommentViewData.isLiked)
            .likesCount(newLikesCount)
            .build()
    }
}