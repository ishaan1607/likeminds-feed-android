package com.likeminds.feed.android.core.post.detail.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.databinding.LmFeedFragmentPostDetailBinding
import com.likeminds.feed.android.core.post.detail.adapter.LMFeedPostDetailAdapterListener
import com.likeminds.feed.android.core.post.detail.adapter.LMFeedReplyAdapterListener
import com.likeminds.feed.android.core.post.detail.model.*
import com.likeminds.feed.android.core.post.detail.view.LMFeedPostDetailActivity.Companion.LM_FEED_POST_DETAIL_EXTRAS
import com.likeminds.feed.android.core.post.detail.viewmodel.LMFeedPostDetailViewModel
import com.likeminds.feed.android.core.ui.widgets.comment.commentcomposer.view.LMFeedCommentComposerView
import com.likeminds.feed.android.core.ui.widgets.headerview.view.LMFeedHeaderView
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalFeedAdapterListener
import com.likeminds.feed.android.core.universalfeed.model.LMFeedPostViewData
import com.likeminds.feed.android.core.utils.*
import com.likeminds.feed.android.core.utils.analytics.LMFeedAnalytics

open class LMFeedPostDetailFragment :
    Fragment(),
    LMFeedUniversalFeedAdapterListener,
    LMFeedPostDetailAdapterListener,
    LMFeedReplyAdapterListener {

    private lateinit var binding: LmFeedFragmentPostDetailBinding
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout

    private lateinit var postDetailExtras: LMFeedPostDetailExtras

    private val postDetailViewModel: LMFeedPostDetailViewModel by viewModels()

    private var parentCommentIdToReply: String? = null
    private var toFindComment: Boolean = false

    // fixed position of viewTypes in adapter
    private val postDataPosition = 0
    private val commentsCountPosition = 1
    private val commentsStartPosition = 2

    // variables to handle comment/reply edit action
    private var editCommentId: String? = null
    private var parentId: String? = null

    companion object {
        const val TAG = "PostDetailFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LmFeedFragmentPostDetailBinding.inflate(layoutInflater)
        customizePostDetailHeaderView(binding.headerViewPostDetail)
        customizeCommentComposer(binding.commentComposer)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        receiveExtras()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        fetchData()
        initCommentEditText()
        initListeners()
    }

    override fun onResume() {
        super.onResume()
        initiateAutoPlayer()
    }

    override fun onPause() {
        super.onPause()
        destroyAutoPlayer()
    }

    protected open fun customizePostDetailHeaderView(headerViewPostDetail: LMFeedHeaderView) {
        headerViewPostDetail.apply {
            setStyle(LMFeedStyleTransformer.postDetailFragmentViewStyle.headerViewStyle)

            setTitleText(getString(R.string.lm_feed_feed))
        }
    }

    protected open fun customizeCommentComposer(commentComposer: LMFeedCommentComposerView) {
        binding.commentComposer.setCommentInputBoxHint(getString(R.string.lm_feed_write_a_comment))
        commentComposer.setStyle(LMFeedStyleTransformer.postDetailFragmentViewStyle.commentComposerStyle)
    }

    private fun receiveExtras() {
        postDetailExtras = LMFeedExtrasUtil.getParcelable(
            arguments,
            LM_FEED_POST_DETAIL_EXTRAS,
            LMFeedPostDetailExtras::class.java
        ) ?: throw emptyExtrasException(TAG)
    }

    private fun initUI() {
        initPostDetailRecyclerView()
        //todo:
//        initMemberTaggingView()
        initSwipeRefreshLayout()
    }

    private fun initPostDetailRecyclerView() {
        fetchPostData()
        binding.rvPostDetails.apply {
            setAdapter(
                this@LMFeedPostDetailFragment,
                this@LMFeedPostDetailFragment,
                this@LMFeedPostDetailFragment
            )

            //set scroll listener
            val paginationScrollListener =
                object : LMFeedEndlessRecyclerViewScrollListener(linearLayoutManager) {
                    override fun onLoadMore(currentPage: Int) {
                        if (currentPage > 0) {
                            Log.d("PUI", "load more is called $currentPage")
                            postDetailViewModel.getPost(postDetailExtras.postId, currentPage)
                        }
                    }
                }
            setPaginationScrollListener(paginationScrollListener)
        }
    }

    private fun initSwipeRefreshLayout() {
        mSwipeRefreshLayout = binding.swipeRefreshLayout
        mSwipeRefreshLayout.apply {
            setColorSchemeColors(
                //todo: change this color as per the style
                ContextCompat.getColor(
                    requireContext(),
                    R.color.lm_feed_majorelle_blue
                )
            )

            setOnRefreshListener {
                refreshPostData()
            }
        }
    }

    // refreshes the whole post detail screen
    private fun refreshPostData() {
        mSwipeRefreshLayout.isRefreshing = true
        binding.rvPostDetails.resetScrollListenerData()
        fetchPostData(true)
    }

    private fun fetchData() {
        fetchPostData()
        checkCommentsRight()
    }

    //fetches post data to set initial data
    private fun fetchPostData(fromRefresh: Boolean = false) {
        if (!fromRefresh) {
            //show progress bar
            LMFeedProgressBarHelper.showProgress(binding.progressBar)
        }

        //if source is notification/deep link, then call initiate first and then other apis
        if (postDetailExtras.source == LMFeedAnalytics.Source.NOTIFICATION ||
            postDetailExtras.source == LMFeedAnalytics.Source.DEEP_LINK
        ) {
            //todo: ask where should we implement this
//            initiateViewModel.initiateUser(
//                requireContext(),
//                userPreferences.getApiKey(),
//                userPreferences.getUserName(),
//                userPreferences.getUserUniqueId(),
//                userPreferences.getIsGuest()
//            )
        } else {
            postDetailViewModel.getPost(postDetailExtras.postId, 1)
        }
    }

    //check if user has comment rights or not
    private fun checkCommentsRight() {
        postDetailViewModel.checkCommentRights()
    }

    // initializes comment edittext with TextWatcher and focuses the keyboard
    private fun initCommentEditText() {
        binding.commentComposer.apply {
            if (postDetailExtras.isEditTextFocused) {
                etComment.focusAndShowKeyboard()
            }

            etComment.doAfterTextChanged {
                if (it?.trim().isNullOrEmpty()) {
                    setCommentSendButton(false)
                } else {
                    setCommentSendButton(true)
                }
            }
        }
    }

    // initializes text-watcher and click listeners
    private fun initListeners() {
        binding.commentComposer.apply {
            setCommentSendClickListener {
                val text = etComment.text
                //todo:
                val updatedText = "$text"
//                val updatedText = memberTagging.replaceSelectedMembers(text).trim()
                val postId = postDetailExtras.postId
                when {
                    parentCommentIdToReply != null -> {
                        addReply(updatedText)
                    }

                    editCommentId != null -> {
                        editCommentLocally(updatedText)
                    }

                    else -> {
                        //input text is a comment
                        addComment(postId, updatedText)
                    }
                }
                LMFeedViewUtils.hideKeyboard(this)
                etComment.text = null
            }
            setCommentSendButton(false)

            setRemoveReplyingToClickListener {
                hideReplyingToView()
            }
        }
    }

    // adds the comment locally and calls api
    private fun addComment(postId: String, updatedText: String) {
        binding.rvPostDetails.apply {
            val createdAt = System.currentTimeMillis()
            val tempId = "-${createdAt}"

            // calls api
            postDetailViewModel.addComment(postId, tempId, updatedText)

            // adds comment locally
            val commentViewData = postDetailViewModel.getCommentViewDataForLocalHandling(
                postId,
                createdAt,
                tempId,
                updatedText,
                null
            )

            // remove NoCommentsViewData if visible
            if (getItem(commentsCountPosition) is LMFeedNoCommentsViewData) {
                removeItem(commentsCountPosition)
            }
            // gets old [CommentsCountViewData] from adapter
            if (getItem(commentsCountPosition) != null) {
                val oldCommentsCountViewData =
                    (getItem(commentsCountPosition) as LMFeedCommentsCountViewData)

                // updates old [CommentsCountViewData] by adding to [commentsCount]
                val updatedCommentsCountViewData = oldCommentsCountViewData.toBuilder()
                    .commentsCount(oldCommentsCountViewData.commentsCount + 1)
                    .build()

                //updates [CommentsCountViewData]
                updateItem(commentsCountPosition, updatedCommentsCountViewData)
            } else {
                // creates new [CommentsCountViewData] when the added comment is first
                val newCommentsCountViewData = LMFeedCommentsCountViewData.Builder()
                    .commentsCount(1)
                    .build()
                addItem(commentsCountPosition, newCommentsCountViewData)
            }

            // gets post from adapter
            var post = getItem(postDataPosition) as LMFeedPostViewData

            //update the footer view data
            val updatedFooterView = post.footerViewData.toBuilder()
                .commentsCount(post.footerViewData.commentsCount + 1)
                .build()

            //updated the post
            post = post.toBuilder()
                .footerViewData(updatedFooterView)
                .build()

            // notifies the subscribers about the change in post data
            //todo:
//            postEvent.notify(Pair(post.id, post))

            // updates comments count on header
            updateCommentsCount(post.footerViewData.commentsCount)

            //adds new comment to adapter
            addItem(commentsStartPosition, commentViewData)

            //scroll to comment's position
            scrollToPositionWithOffset(commentsStartPosition, 75)

            //updates comment data in post
            updateItem(postDataPosition, post)
        }
    }

    // adds the reply locally and calls api
    private fun addReply(updatedText: String) {
        binding.rvPostDetails.apply {
            val createdAt = System.currentTimeMillis()
            val tempId = "-${createdAt}"
            val postId = postDetailExtras.postId

            // input text is reply to a comment
            val parentCommentId = parentCommentIdToReply ?: return
            val parentComment = getIndexAndCommentFromAdapter(parentCommentId)?.second
                ?: return
            val parentCommentCreatorUUID = parentComment.user.sdkClientInfoViewData.uuid
            postDetailViewModel.replyComment(
                parentCommentCreatorUUID,
                postId,
                parentCommentId,
                updatedText,
                tempId
            )
            hideReplyingToView()

            // view data of comment with level-1
            val replyViewData = postDetailViewModel.getCommentViewDataForLocalHandling(
                postId,
                createdAt,
                tempId,
                updatedText,
                parentCommentId,
                level = 1
            )

            //adds reply to the adapter
            addReplyToAdapter(parentCommentId, replyViewData)
        }
    }

    // edits the comment locally and calls api
    private fun editCommentLocally(updatedText: String) {
        // when an existing comment is edited
        val commentId = editCommentId ?: return

        // calls api
        postDetailViewModel.editComment(
            postDetailExtras.postId,
            commentId,
            updatedText
        )

        binding.rvPostDetails.apply {

            if (parentId == null) {
                // edited comment is of level-0

                val pair = getIndexAndCommentFromAdapter(commentId) ?: return
                val commentPosition = pair.first
                val comment = pair.second

                //update comment view data
                val updatedComment = comment.toBuilder()
                    .fromCommentLiked(false)
                    .fromCommentEdited(true)
                    .isEdited(true)
                    .text(updatedText)
                    .build()

                updateItem(commentPosition, updatedComment)
            } else {
                // edited comment is of level-1 (reply)

                val pair = getIndexAndCommentFromAdapter(parentId ?: "") ?: return
                val parentIndex = pair.first
                val parentCommentInAdapter = pair.second

                // finds index of the reply inside the comment
                val replyPair =
                    getIndexAndReplyFromComment(parentCommentInAdapter, commentId) ?: return

                val index = replyPair.first
                val reply = replyPair.second.toBuilder()
                    .isEdited(true)
                    .text(updatedText)
                    .fromCommentEdited(true)
                    .build()

                if (index == -1) return

                parentCommentInAdapter.replies[index] = reply

                val newViewData = parentCommentInAdapter.toBuilder()
                    .fromCommentLiked(false)
                    .fromCommentEdited(false)
                    .build()

                //updates the parentComment with edited reply
                updateItem(parentIndex, newViewData)
            }
        }
        editCommentId = null
        parentId = null
    }

    // adds the reply to its parentComment
    private fun addReplyToAdapter(parentCommentId: String, reply: LMFeedCommentViewData) {
        binding.rvPostDetails.apply {
            // gets the parentComment from adapter
            val parentComment = getIndexAndCommentFromAdapter(parentCommentId) ?: return
            val parentIndex = parentComment.first
            val parentCommentViewData = parentComment.second

            // adds the reply at first
            parentCommentViewData.replies.add(0, reply)

            val newCommentViewData = parentCommentViewData.toBuilder()
                .fromCommentLiked(false)
                .fromCommentEdited(false)
                .repliesCount(parentCommentViewData.repliesCount + 1)
                .build()

            //updates the parentComment with added reply
            updateItem(parentIndex, newCommentViewData)

            // scroll to comment's position
            scrollToPositionWithOffset(parentIndex, 75)
        }
    }

    // hides the replying to view
    private fun hideReplyingToView() {
        binding.commentComposer.apply {
            parentCommentIdToReply = null
            replyingVisibility(false)
        }
    }

    // updates the comments count on toolbar
    private fun updateCommentsCount(commentsCount: Int) {
        binding.headerViewPostDetail.setSubTitleText(
            resources.getQuantityString(
                R.plurals.lm_feed_comments_small,
                commentsCount,
                commentsCount
            )
        )
    }
}