package com.likeminds.feed.android.core.universalfeed.util

import android.text.*
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.text.util.Linkify
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.text.util.LinkifyCompat
import com.likeminds.feed.android.core.LMFeedCoreApplication
import com.likeminds.feed.android.core.post.model.LMFeedLinkOGTagsViewData
import com.likeminds.feed.android.core.ui.widgets.postfooterview.view.LMFeedPostFooterView
import com.likeminds.feed.android.core.ui.widgets.postheaderview.view.LMFeedPostHeaderView
import com.likeminds.feed.android.core.ui.widgets.postmedia.view.LMFeedPostLinkMediaView
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalFeedAdapterListener
import com.likeminds.feed.android.core.universalfeed.model.*
import com.likeminds.feed.android.core.util.LMFeedSeeMoreUtil
import com.likeminds.feed.android.core.util.LMFeedStyleTransformer
import com.likeminds.feed.android.core.util.LMFeedValueUtils.getValidTextForLinkify
import com.likeminds.feed.android.core.util.link.LMFeedLinkMovementMethod
import com.likeminds.feed.android.integration.R
import com.likeminds.feed.android.ui.base.styles.setStyle
import com.likeminds.feed.android.ui.base.views.LMFeedImageView
import com.likeminds.feed.android.ui.base.views.LMFeedTextView
import com.likeminds.feed.android.ui.theme.LMFeedTheme
import com.likeminds.feed.android.ui.utils.LMFeedImageBindingUtil
import com.likeminds.feed.android.ui.utils.LMFeedViewUtils.hide
import com.likeminds.feed.android.ui.utils.LMFeedViewUtils.show

object LMFeedPostBinderUtils {

    // customizes the header view of the post and attaches all the relevant listeners
    fun customizePostHeaderView(
        authorFrame: LMFeedPostHeaderView,
        universalFeedAdapterListener: LMFeedUniversalFeedAdapterListener,
        userViewData: LMFeedUserViewData?
    ) {
        val postHeaderViewStyle =
            LMFeedStyleTransformer.postViewStyle.postHeaderViewStyle

        authorFrame.setStyle(postHeaderViewStyle)

        authorFrame.setAuthorFrameClickListener {
            if (userViewData == null) {
                return@setAuthorFrameClickListener
            }

            val coreCallback = LMFeedCoreApplication.getLMFeedCoreCallback()
            coreCallback?.openProfile(userViewData)
        }

        authorFrame.setMenuIconClickListener {
            // todo: add the required parameters
            universalFeedAdapterListener.onPostMenuIconClick()
        }
    }

    // customizes the content view of the post and attaches all the relevant listeners
    fun customizePostContentView(
        tvPostContent: LMFeedTextView,
        universalFeedAdapterListener: LMFeedUniversalFeedAdapterListener,
        postId: String
    ) {
        tvPostContent.apply {
            val postContentTextStyle = LMFeedStyleTransformer.postViewStyle.postContentTextStyle
            setStyle(postContentTextStyle)

            // todo: test this otherwise move this to setTextContent function
            setOnClickListener {
                universalFeedAdapterListener.onPostContentClick(postId)
            }

            val linkifyLinks =
                (Linkify.WEB_URLS or Linkify.EMAIL_ADDRESSES or Linkify.PHONE_NUMBERS)
            LinkifyCompat.addLinks(this, linkifyLinks)
            movementMethod = LMFeedLinkMovementMethod { url ->
                setOnClickListener {
                    return@setOnClickListener
                }

                universalFeedAdapterListener.handleLinkClick(url)
                true
            }
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
        universalFeedAdapterListener: LMFeedUniversalFeedAdapterListener,
        returnBinder: () -> Unit,
        executeBinder: () -> Unit
    ) {
        if (data.fromPostLiked || data.fromPostSaved || data.fromVideoAction) {
            // update fromLiked/fromSaved variables and return from binder
            universalFeedAdapterListener.updateFromLikedSaved(position)
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
                universalFeedAdapterListener,
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
        universalFeedAdapterListener: LMFeedUniversalFeedAdapterListener,
        position: Int
    ) {
        contentView.apply {
            val text = contentViewData.text ?: return
            val maxLines = (LMFeedStyleTransformer.postViewStyle.postContentTextStyle.maxLines
                ?: LMFeedTheme.DEFAULT_POST_MAX_LINES)

            /**
             * Text is modified as Linkify doesn't accept texts with these specific unicode characters
             * @see #Linkify.containsUnsupportedCharacters(String)
             */
            val textForLinkify = text.getValidTextForLinkify()

            var alreadySeenFullContent = contentViewData.alreadySeenFullContent == true

            if (textForLinkify.isEmpty()) {
                hide()
                return
            } else {
                show()
            }

            val seeMoreColor = ContextCompat.getColor(
                context,
                com.likeminds.feed.android.ui.R.color.lm_feed_brown_grey
            )

            val seeMore = SpannableStringBuilder(context.getString(R.string.lm_feed_see_more))
            seeMore.setSpan(
                ForegroundColorSpan(seeMoreColor),
                0,
                seeMore.length,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )

            val seeMoreClickableSpan = object : ClickableSpan() {
                override fun onClick(view: View) {
                    setOnClickListener {
                        return@setOnClickListener
                    }

                    alreadySeenFullContent = true
                    universalFeedAdapterListener.updatePostSeenFullContent(position, true)
                }

                override fun updateDrawState(textPaint: TextPaint) {
                    textPaint.isUnderlineText = false
                }
            }

            // post is used here to get lines count in the text view
            post {
                // todo: add member tagging decoder here

                val shortText: String? = LMFeedSeeMoreUtil.getShortContent(
                    contentView,
                    maxLines,
                    LMFeedTheme.getPostCharacterLimit()
                )

                val trimmedText =
                    if (!alreadySeenFullContent && !shortText.isNullOrEmpty()) {
                        editableText.subSequence(0, shortText.length)
                    } else {
                        editableText
                    }

                val seeMoreSpannableStringBuilder = SpannableStringBuilder()
                if (!alreadySeenFullContent && !shortText.isNullOrEmpty()) {
                    seeMoreSpannableStringBuilder.append("...")
                    seeMoreSpannableStringBuilder.append(seeMore)
                    seeMoreSpannableStringBuilder.setSpan(
                        seeMoreClickableSpan,
                        3,
                        seeMore.length + 3,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }

                this.text = TextUtils.concat(
                    trimmedText,
                    seeMoreSpannableStringBuilder
                )
            }
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

    fun bindPostSingleImage(
        ivPost: LMFeedImageView,
        mediaData: LMFeedMediaViewData,
        adapterListener: LMFeedUniversalFeedAdapterListener
    ) {
        val postImageMediaStyle =
            LMFeedStyleTransformer.postViewStyle.postMediaStyle.postImageMediaStyle ?: return

        LMFeedImageBindingUtil.loadImage(
            ivPost,
            mediaData.attachments.first().attachmentMeta.url,
            placeholder = postImageMediaStyle.placeholderSrc
        )

        ivPost.setOnClickListener {
            // todo: add required data here
            adapterListener.onPostImageMediaClick()
        }
    }

    fun bindPostMediaLinkView(
        linkView: LMFeedPostLinkMediaView,
        linkOgTags: LMFeedLinkOGTagsViewData,
        adapterListener: LMFeedUniversalFeedAdapterListener
    ) {
        linkView.apply {
            setLinkTitle(linkOgTags.title)
            setLinkDescription(linkOgTags.description)
            setLinkImage(linkOgTags.url)
            setLinkUrl(linkOgTags.url)

            setLinkClickListener {
                adapterListener.onPostLinkMediaClick(linkOgTags)
            }
        }
    }
}