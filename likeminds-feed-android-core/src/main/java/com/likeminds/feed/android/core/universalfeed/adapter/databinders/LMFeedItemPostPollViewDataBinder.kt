package com.likeminds.feed.android.core.universalfeed.adapter.databinders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.likeminds.feed.android.core.databinding.LmFeedItemPostPollBinding
import com.likeminds.feed.android.core.ui.widgets.poll.adapter.LMFeedPollOptionsAdapterListener
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalFeedAdapterListener
import com.likeminds.feed.android.core.universalfeed.model.LMFeedPostViewData
import com.likeminds.feed.android.core.universalfeed.util.LMFeedPostBinderUtils
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer
import com.likeminds.feed.android.core.utils.base.LMFeedViewDataBinder
import com.likeminds.feed.android.core.utils.base.model.ITEM_POST_POLL

class LMFeedItemPostPollViewDataBinder(
    private val universalFeedAdapterListener: LMFeedUniversalFeedAdapterListener,
    private val pollOptionsAdapter: LMFeedPollOptionsAdapterListener
) : LMFeedViewDataBinder<LmFeedItemPostPollBinding, LMFeedPostViewData>() {

    override val viewType: Int
        get() = ITEM_POST_POLL

    override fun createBinder(parent: ViewGroup): LmFeedItemPostPollBinding {
        val binding = LmFeedItemPostPollBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        binding.apply {
            LMFeedPostBinderUtils.customizePostHeaderView(postHeader)

            LMFeedPostBinderUtils.customizePostContentView(tvPostContent)

            LMFeedPostBinderUtils.customizePostFooterView(postFooter)

            LMFeedPostBinderUtils.customizePostTopicsGroup(postTopicsGroup)

            setClickListeners(this)

            //sets poll media style to the poll view
            val postPollMediaViewStyle =
                LMFeedStyleTransformer.postViewStyle.postMediaViewStyle.postPollMediaStyle
                    ?: return@apply

            postPollView.setStyle(postPollMediaViewStyle)
        }

        return binding
    }

    override fun bindData(
        binding: LmFeedItemPostPollBinding,
        data: LMFeedPostViewData,
        position: Int
    ) {
        binding.apply {
            // set variables in the binding
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
                postTopicsGroup,
                universalFeedAdapterListener,
                returnBinder = {
                    return@setPostBindData
                }, executeBinder = {
                    //sets the post poll media view
                    LMFeedPostBinderUtils.bindPostPollMediaView(
                        postPollView,
                        data.mediaViewData,
                        pollOptionsAdapter
                    )
                }
            )
        }
    }

    private fun setClickListeners(binding: LmFeedItemPostPollBinding) {
        binding.apply {
            postHeader.setMenuIconClickListener {
                val post = postViewData ?: return@setMenuIconClickListener
                universalFeedAdapterListener.onPostMenuIconClicked(
                    position,
                    postHeader.headerMenu,
                    post
                )
            }

            postHeader.setAuthorFrameClickListener {
                val post = this.postViewData ?: return@setAuthorFrameClickListener
                universalFeedAdapterListener.onPostAuthorHeaderClicked(position, post)
            }

            postPollView.setEditPollClicked {
                val post = this.postViewData ?: return@setEditPollClicked
                universalFeedAdapterListener.onPostEditPollClicked(position, post)
            }

            postPollView.setClearPollClicked {
                val post = this.postViewData ?: return@setClearPollClicked
                universalFeedAdapterListener.onPostClearPollClicked(position, post)
            }

            postPollView.setAddPollOptionClicked {
                val post = this.postViewData ?: return@setAddPollOptionClicked
                universalFeedAdapterListener.onPostAddPollOptionClicked(position, post)
            }

            postPollView.setSubmitPollVoteClicked {
                val post = this.postViewData ?: return@setSubmitPollVoteClicked
                universalFeedAdapterListener.onPostSubmitPollVoteClicked(position, post)
            }

            postPollView.setMemberVotedCountClicked {
                val post = this.postViewData ?: return@setMemberVotedCountClicked
                universalFeedAdapterListener.onPostMemberVotedCountClicked(position, post)
            }

            postPollView.setEditPollVoteClicked {
                val post = this.postViewData ?: return@setEditPollVoteClicked
                universalFeedAdapterListener.onPostEditPollVoteClicked(position, post)
            }

            postFooter.setLikeIconClickListener {
                val post = this.postViewData ?: return@setLikeIconClickListener
                val updatedPost = LMFeedPostBinderUtils.updatePostForLike(post)
                universalFeedAdapterListener.onPostLikeClicked(position, updatedPost)
            }

            postFooter.setLikesCountClickListener {
                val post = this.postViewData ?: return@setLikesCountClickListener
                if (post.footerViewData.likesCount > 0) {
                    universalFeedAdapterListener.onPostLikesCountClicked(position, post)
                } else {
                    return@setLikesCountClickListener
                }
            }

            postFooter.setCommentsCountClickListener {
                val post = this.postViewData ?: return@setCommentsCountClickListener
                universalFeedAdapterListener.onPostCommentsCountClicked(position, post)
            }

            postFooter.setSaveIconListener {
                val post = this.postViewData ?: return@setSaveIconListener
                val updatedPost = LMFeedPostBinderUtils.updatePostForSave(post)
                universalFeedAdapterListener.onPostSaveClicked(position, updatedPost)
            }

            postFooter.setShareIconListener {
                val post = this.postViewData ?: return@setShareIconListener
                universalFeedAdapterListener.onPostShareClicked(position, post)
            }
        }
    }
}