package com.likeminds.feed.android.core.post.detail.adapter.databinders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.databinding.LmFeedItemCommentBinding
import com.likeminds.feed.android.core.post.detail.adapter.LMFeedPostDetailAdapterListener
import com.likeminds.feed.android.core.post.detail.adapter.LMFeedReplyAdapterListener
import com.likeminds.feed.android.core.post.detail.model.LMFeedCommentViewData
import com.likeminds.feed.android.core.post.detail.model.LMFeedViewMoreReplyViewData
import com.likeminds.feed.android.core.post.detail.util.LMFeedPostDetailBinderUtils
import com.likeminds.feed.android.core.post.detail.viewmodel.LMFeedPostDetailViewModel
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.hide
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.show
import com.likeminds.feed.android.core.utils.base.LMFeedBaseViewType
import com.likeminds.feed.android.core.utils.base.LMFeedViewDataBinder
import com.likeminds.feed.android.core.utils.base.model.ITEM_COMMENT

class LMFeedItemCommentViewDataBinder(
    private val postDetailAdapterListener: LMFeedPostDetailAdapterListener,
    private val replyAdapterListener: LMFeedReplyAdapterListener
) :
    LMFeedViewDataBinder<LmFeedItemCommentBinding, LMFeedCommentViewData>() {

    override val viewType: Int
        get() = ITEM_COMMENT

    override fun createBinder(parent: ViewGroup): LmFeedItemCommentBinding {
        val binding = LmFeedItemCommentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        binding.commentView.setStyle(LMFeedStyleTransformer.postDetailFragmentViewStyle.commentViewStyle)
        setClickListeners(binding)

        return binding
    }

    override fun bindData(
        binding: LmFeedItemCommentBinding,
        data: LMFeedCommentViewData,
        position: Int
    ) {
        initCommentsView(
            binding,
            data,
            position
        )
    }

    // sets the data to comments item and handles the replies click and rv
    private fun initCommentsView(
        binding: LmFeedItemCommentBinding,
        data: LMFeedCommentViewData,
        position: Int
    ) {
        binding.apply {
            // set variables in the binding
            this.position = position
            commentViewData = data

            val context = root.context

            commentView.setCommenterImage(data.user)
            commentView.setCommenterName(data.user.name)
            commentView.setTimestamp(data.createdAt)
            commentView.setCommentEdited(data.isEdited)
            commentView.setLikesIcon(data.isLiked)

            val likesCountText = if (data.likesCount == 0) {
                ""
            } else {
                context.resources.getQuantityString(
                    R.plurals.lm_feed_likes,
                    data.likesCount,
                    data.likesCount
                )
            }
            commentView.setLikesCount(likesCountText)

            if (data.fromCommentLiked || data.fromCommentEdited) {
                return
            } else {
                val repliesCountText = if (data.repliesCount == 0) {
                    ""
                } else {
                    context.resources.getQuantityString(
                        R.plurals.lm_feed_replies,
                        data.repliesCount,
                        data.repliesCount
                    )
                }

                commentView.setRepliesCount(repliesCountText)
                rvReplies.setAdapter(replyAdapterListener)

                if (data.replies.isNotEmpty()) {
                    rvReplies.show()
                    commentSeparator.hide()
                    handleViewMore(this, data)
                } else {
                    rvReplies.hide()
                    commentSeparator.show()
                }
            }
        }
    }

    private fun setClickListeners(binding: LmFeedItemCommentBinding) {
        binding.apply {
            commentView.linkifyCommentContent { url ->
                postDetailAdapterListener.onCommentContentLinkClicked(url)
                true
            }

            commentView.setLikesCountClickListener {
                val comment = commentViewData ?: return@setLikesCountClickListener
                postDetailAdapterListener.onCommentLikesCountClicked(position, comment)
            }

            commentView.setLikeIconClickListener {
                val comment = commentViewData ?: return@setLikeIconClickListener
                val updatedComment = LMFeedPostDetailBinderUtils.updateCommentForLike(comment)
                postDetailAdapterListener.onCommentLiked(position, updatedComment)
            }

            commentView.setReplyClickListener {
                val comment = commentViewData ?: return@setReplyClickListener
                postDetailAdapterListener.onCommentReplyClicked(position, comment)
            }

            commentView.setMenuIconClickListener {
                val comment = commentViewData ?: return@setMenuIconClickListener
                postDetailAdapterListener.onCommentMenuIconClicked(
                    position,
                    commentView.commentMenu,
                    comment
                )
            }
        }
    }

    //adds ViewMoreReply view when required
    private fun handleViewMore(binding: LmFeedItemCommentBinding, data: LMFeedCommentViewData) {
        binding.rvReplies.apply {
            val repliesList = data.replies.toMutableList() as MutableList<LMFeedBaseViewType>
            if (repliesList.size >= data.repliesCount) {
                // if all replies are fetched then only replace the data
                replaceReplies(repliesList)
            } else {
                // if a subset of replies are fetched then also add [ViewMoreReplyViewData]
                val nextPage = (repliesList.size / LMFeedPostDetailViewModel.REPLIES_THRESHOLD) + 1
                val viewMoreReply = LMFeedViewMoreReplyViewData.Builder()
                    .totalCommentsCount(data.repliesCount)
                    .currentCount(data.replies.size)
                    .parentCommentId(data.id)
                    .page(nextPage)
                    .build()
                repliesList.add(viewMoreReply)

                replaceReplies(repliesList)
            }
        }
    }
}