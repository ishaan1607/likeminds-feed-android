package com.likeminds.feed.android.core.universalfeed.adapter.databinders

import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.util.LinkifyCompat
import com.likeminds.feed.android.core.LMFeedCoreApplication
import com.likeminds.feed.android.core.databinding.LmFeedItemPostLinkBinding
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalFeedAdapterListener
import com.likeminds.feed.android.core.universalfeed.model.LMFeedPostViewData
import com.likeminds.feed.android.core.universalfeed.util.LMFeedPostBinderUtils
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer
import com.likeminds.feed.android.core.utils.base.LMFeedViewDataBinder
import com.likeminds.feed.android.core.utils.base.model.ITEM_POST_LINK
import com.likeminds.feed.android.core.utils.link.LMFeedLinkMovementMethod

class LMFeedItemPostLinkViewDataBinder(
    private val universalFeedAdapterListener: LMFeedUniversalFeedAdapterListener
) : LMFeedViewDataBinder<LmFeedItemPostLinkBinding, LMFeedPostViewData>() {

    override val viewType: Int
        get() = ITEM_POST_LINK

    override fun createBinder(parent: ViewGroup): LmFeedItemPostLinkBinding {
        val binding = LmFeedItemPostLinkBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        binding.apply {
            LMFeedPostBinderUtils.customizePostHeaderView(postHeader)

            LMFeedPostBinderUtils.customizePostContentView(tvPostContent)

            LMFeedPostBinderUtils.customizePostFooterView(postFooter)

            setClickListeners(this)

            //sets link media style to post link view
            val postLinkViewStyle =
                LMFeedStyleTransformer.postViewStyle.postMediaStyle.postLinkViewStyle
                    ?: return@apply

            postLinkView.setStyle(postLinkViewStyle)
        }

        return binding
    }

    override fun bindData(
        binding: LmFeedItemPostLinkBinding,
        data: LMFeedPostViewData,
        position: Int
    ) {
        binding.apply {
            val linkAttachment = data.mediaViewData.attachments.first()
            val ogTags = linkAttachment.attachmentMeta.ogTags

            //sets variables in the binding
            this.position = position
            postViewData = data

            // updates the data in the post footer view
            LMFeedPostBinderUtils.setPostFooterViewData(
                postFooter,
                data.footerViewData
            )

            // checks whether to bind complete data or not and execute corresponding lambda function
            LMFeedPostBinderUtils.setPostBindData(
                postHeader,
                tvPostContent,
                data,
                position,
                universalFeedAdapterListener,
                returnBinder = {
                    return@setPostBindData
                }, executeBinder = {
                    //handles the link view
                    LMFeedPostBinderUtils.bindPostMediaLinkView(
                        postLinkView,
                        ogTags
                    )
                }
            )
        }
    }

    private fun setClickListeners(binding: LmFeedItemPostLinkBinding) {
        binding.apply {
            postHeader.setMenuIconClickListener {
                val post = postViewData ?: return@setMenuIconClickListener
                universalFeedAdapterListener.onPostMenuIconClick(
                    position,
                    postHeader.headerMenu,
                    post
                )
            }

            // todo: test this otherwise move this to setTextContent function
            tvPostContent.setOnClickListener {
                val post = postViewData ?: return@setOnClickListener
                universalFeedAdapterListener.onPostContentClick(position, post)
            }

            val linkifyLinks =
                (Linkify.WEB_URLS or Linkify.EMAIL_ADDRESSES or Linkify.PHONE_NUMBERS)
            LinkifyCompat.addLinks(tvPostContent, linkifyLinks)
            tvPostContent.movementMethod = LMFeedLinkMovementMethod { url ->
                tvPostContent.setOnClickListener {
                    return@setOnClickListener
                }

                universalFeedAdapterListener.handleLinkClick(url)
                true
            }

            postHeader.setAuthorFrameClickListener {
                val post = postViewData ?: return@setAuthorFrameClickListener
                val coreCallback = LMFeedCoreApplication.getLMFeedCoreCallback()
                coreCallback?.openProfile(post.headerViewData.user)
            }

            postLinkView.setLinkClickListener {
                val post = postViewData ?: return@setLinkClickListener
                universalFeedAdapterListener.onPostLinkMediaClick(position, post)
            }

            postFooter.setLikeIconClickListener {
                val post = postViewData ?: return@setLikeIconClickListener
                universalFeedAdapterListener.onPostLikeClick(position, post)
            }

            postFooter.setLikesCountClickListener {
                val post = postViewData ?: return@setLikesCountClickListener
                universalFeedAdapterListener.onPostLikesCountClick(position, post)
            }

            postFooter.setCommentsCountClickListener {
                val post = postViewData ?: return@setCommentsCountClickListener
                universalFeedAdapterListener.onPostCommentsCountClick(position, post)
            }

            postFooter.setSaveIconListener {
                val post = postViewData ?: return@setSaveIconListener
                universalFeedAdapterListener.onPostSaveClick(position, post)
            }

            postFooter.setShareIconListener {
                val post = postViewData ?: return@setShareIconListener
                universalFeedAdapterListener.onPostShareClick(position, post)
            }
        }
    }
}