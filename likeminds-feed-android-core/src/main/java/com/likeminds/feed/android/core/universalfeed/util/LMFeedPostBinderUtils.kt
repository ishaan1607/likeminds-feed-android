package com.likeminds.feed.android.core.universalfeed.util

import com.likeminds.feed.android.core.ui.widgets.postfooterview.view.LMFeedPostFooterView
import com.likeminds.feed.android.core.ui.widgets.postheaderview.view.LMFeedPostHeaderView
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalFeedAdapterListener
import com.likeminds.feed.android.core.universalfeed.model.*
import com.likeminds.feed.android.core.util.LMFeedStyleTransformer
import com.likeminds.feed.android.core.util.LMFeedValueUtils.getValidTextForLinkify
import com.likeminds.feed.android.integration.R
import com.likeminds.feed.android.ui.base.styles.setStyle
import com.likeminds.feed.android.ui.base.views.LMFeedTextView
import com.likeminds.feed.android.ui.utils.LMFeedViewUtils.hide
import com.likeminds.feed.android.ui.utils.LMFeedViewUtils.show

object LMFeedPostBinderUtils {

    // customizes the header view of the post and attaches all the relevant listeners
    fun customizePostHeaderView(
        authorFrame: LMFeedPostHeaderView,
        universalFeedAdapterListener: LMFeedUniversalFeedAdapterListener
    ) {
        val postHeaderViewStyle =
            LMFeedStyleTransformer.postViewStyle.postHeaderViewStyle

        authorFrame.setStyle(postHeaderViewStyle)

        // todo: set header click listeners and menu items with click listeners
    }

    // customizes the content view of the post and attaches all the relevant listeners
    fun customizePostContentView(
        tvPostContent: LMFeedTextView,
        universalFeedAdapterListener: LMFeedUniversalFeedAdapterListener
    ) {
        val postContentTextStyle =
            LMFeedStyleTransformer.postViewStyle.postContentTextStyle

        tvPostContent.setStyle(postContentTextStyle)

        tvPostContent.setOnClickListener {
            universalFeedAdapterListener.onPostContentClick()
        }
    }

    // customizes the footer view of the post and attaches all the relevant listeners
    fun customizePostFooterView(
        postActionsLayout: LMFeedPostFooterView,
        universalFeedAdapterListener: LMFeedUniversalFeedAdapterListener,
        postId: String,
        position: Int
    ) {
        val postFooterViewStyle =
            LMFeedStyleTransformer.postViewStyle.postFooterViewStyle

        postActionsLayout.setStyle(postFooterViewStyle)

        postActionsLayout.setLikeIconClickListener {
            universalFeedAdapterListener.onPostLikeClick(position)
        }

        postActionsLayout.setLikesCountClickListener {
            universalFeedAdapterListener.onPostLikesCountClick(postId)
        }

        postActionsLayout.setCommentsCountClickListener {
            universalFeedAdapterListener.onPostCommentsCountClick(postId)
        }

        postActionsLayout.setSaveIconListener {
            universalFeedAdapterListener.onPostSaveClick(postId)
        }

        postActionsLayout.setShareIconListener {
            universalFeedAdapterListener.onPostShareClick(postId)
        }
    }

    fun setPostBindData(
        headerView: LMFeedPostHeaderView,
        contentView: LMFeedTextView,
        data: LMFeedPostViewData,
        position: Int,
        listener: LMFeedUniversalFeedAdapterListener,
        returnBinder: () -> Unit,
        executeBinder: () -> Unit
    ) {
        if (data.fromPostLiked || data.fromPostSaved || data.fromVideoAction) {
            // update fromLiked/fromSaved variables and return from binder
            listener.updateFromLikedSaved(position)
            returnBinder()
        } else {
            // call all the common functions

            // sets data to the header view
            setPostHeaderViewData(
                headerView,
                data.headerViewData
            )

            // sets the text content of the post
            setPostContentViewData(
                contentView,
                data.contentViewData,
                position
            )

            executeBinder()
        }
    }

    // sets the data in the post header view
    private fun setPostHeaderViewData(
        headerView: LMFeedPostHeaderView,
        headerViewData: LMFeedPostHeaderViewData
    ) {
        headerView.apply {
            setPinIcon(headerViewData.isPinned)
            setPostEdited(headerViewData.isEdited)

            // todo: handle menu items

            // post author data
            val author = headerViewData.user
            setAuthorName(author.name)
            setAuthorCustomTitle(author.customTitle)
            setAuthorImage(author.imageUrl)

            setTimestamp(headerViewData.createdAt)
        }
    }

    // sets the data in the post content view
    private fun setPostContentViewData(
        contentView: LMFeedTextView,
        contentViewData: LMFeedPostContentViewData,
        position: Int
    ) {
        contentView.apply {
            val text = contentViewData.text ?: return

            /**
             * Text is modified as Linkify doesn't accept texts with these specific unicode characters
             * @see #Linkify.containsUnsupportedCharacters(String)
             */
            val textForLinkify = text.getValidTextForLinkify()

            var alreadySeenFullContent = contentViewData.alreadySeenFullContent == true

            if (textForLinkify.isEmpty()) {
                contentView.hide()
                return
            } else {
                contentView.show()
            }

            // todo: implement see more and tagging, also ask how we can handle deep links etc. here
        }
    }

    // sets the data in the post footer view
    fun setPostFooterViewData(
        footerView: LMFeedPostFooterView,
        footerViewData: LMFeedPostFooterViewData
    ) {
        footerView.apply {
            setLikesIcon(footerViewData.isLiked)
            setSaveIcon(footerViewData.isSaved)

            val likesCountText = if (footerViewData.likesCount == 0) {
                context.getString(R.string.lm_feed_like)
            } else {
                context.resources.getQuantityString(
                    R.plurals.lm_feed_likes,
                    footerViewData.likesCount,
                    footerViewData.likesCount
                )
            }
            setLikesCount(likesCountText)

            val commentsCountText = if (footerViewData.likesCount == 0) {
                context.getString(R.string.lm_feed_add_comment)
            } else {
                context.resources.getQuantityString(
                    R.plurals.lm_feed_comments,
                    footerViewData.commentsCount,
                    footerViewData.commentsCount
                )
            }
            setCommentsCount(commentsCountText)
        }
    }
}