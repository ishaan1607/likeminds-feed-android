package com.likeminds.feed.android.core.universalfeed.view

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.activityfeed.view.LMFeedActivityFeedActivity
import com.likeminds.feed.android.core.databinding.LmFeedFragmentUniversalFeedBinding
import com.likeminds.feed.android.core.delete.model.DELETE_TYPE_POST
import com.likeminds.feed.android.core.delete.model.LMFeedDeleteExtras
import com.likeminds.feed.android.core.delete.view.*
import com.likeminds.feed.android.core.likes.model.LMFeedLikesScreenExtras
import com.likeminds.feed.android.core.likes.model.POST
import com.likeminds.feed.android.core.likes.view.LMFeedLikesActivity
import com.likeminds.feed.android.core.overflowmenu.model.*
import com.likeminds.feed.android.core.post.detail.model.LMFeedPostDetailExtras
import com.likeminds.feed.android.core.post.detail.view.LMFeedPostDetailActivity
import com.likeminds.feed.android.core.post.edit.model.LMFeedEditPostExtras
import com.likeminds.feed.android.core.post.edit.view.LMFeedEditPostActivity
import com.likeminds.feed.android.core.post.model.LMFeedAttachmentViewData
import com.likeminds.feed.android.core.report.model.LMFeedReportExtras
import com.likeminds.feed.android.core.report.model.REPORT_TYPE_POST
import com.likeminds.feed.android.core.report.view.LMFeedReportActivity
import com.likeminds.feed.android.core.report.view.LMFeedReportFragment.Companion.LM_FEED_REPORT_RESULT
import com.likeminds.feed.android.core.report.view.LMFeedReportSuccessDialogFragment
import com.likeminds.feed.android.core.topics.model.LMFeedTopicViewData
import com.likeminds.feed.android.core.topicselection.model.LMFeedTopicSelectionExtras
import com.likeminds.feed.android.core.topicselection.model.LMFeedTopicSelectionResultExtras
import com.likeminds.feed.android.core.topicselection.view.LMFeedTopicSelectionActivity
import com.likeminds.feed.android.core.topicselection.view.LMFeedTopicSelectionActivity.Companion.LM_FEED_TOPIC_SELECTION_RESULT_EXTRAS
import com.likeminds.feed.android.core.ui.base.styles.setStyle
import com.likeminds.feed.android.core.ui.base.views.LMFeedFAB
import com.likeminds.feed.android.core.ui.widgets.headerview.view.LMFeedHeaderView
import com.likeminds.feed.android.core.ui.widgets.noentitylayout.view.LMFeedNoEntityLayoutView
import com.likeminds.feed.android.core.ui.widgets.overflowmenu.view.LMFeedOverflowMenu
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalFeedAdapterListener
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalSelectedTopicAdapterListener
import com.likeminds.feed.android.core.universalfeed.model.LMFeedPostViewData
import com.likeminds.feed.android.core.universalfeed.util.LMFeedPostBinderUtils
import com.likeminds.feed.android.core.universalfeed.viewmodel.LMFeedUniversalFeedViewModel
import com.likeminds.feed.android.core.universalfeed.viewmodel.bindView
import com.likeminds.feed.android.core.utils.*
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.hide
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.show
import com.likeminds.feed.android.core.utils.analytics.LMFeedAnalytics
import com.likeminds.feed.android.core.utils.base.LMFeedBaseViewType
import com.likeminds.feed.android.core.utils.coroutine.observeInLifecycle
import com.likeminds.feed.android.core.utils.user.LMFeedUserPreferences
import kotlinx.coroutines.flow.onEach

open class LMFeedUniversalFeedFragment :
    Fragment(),
    LMFeedUniversalFeedAdapterListener,
    LMFeedAdminDeleteDialogListener,
    LMFeedSelfDeleteDialogListener,
    LMFeedUniversalSelectedTopicAdapterListener {

    private lateinit var binding: LmFeedFragmentUniversalFeedBinding
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout

    private val universalFeedViewModel: LMFeedUniversalFeedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LmFeedFragmentUniversalFeedBinding.inflate(layoutInflater)

        binding.apply {
            customizeCreateNewPostButton(fabNewPost)
            customizeUniversalFeedHeaderView(headerViewUniversal)
            customizeNoPostLayout(layoutNoPost)
            customizePostingLayout(layoutPosting)
            customizeTopicSelectorBar(topicSelectorBar)
            return root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        initListeners()
        observeResponses()
    }

    override fun onResume() {
        super.onResume()
        // sends feed opened event
        LMFeedAnalytics.sendFeedOpenedEvent()

        binding.rvUniversal.refreshVideoAutoPlayer()
    }

    private fun initUI() {
        initUniversalFeedRecyclerView()
        initSwipeRefreshLayout()
        initSelectedTopicRecyclerView()
    }

    override fun onPause() {
        super.onPause()
        binding.rvUniversal.destroyVideoAutoPlayer()
    }

    private fun initListeners() {
        binding.apply {
            fabNewPost.setOnClickListener {
                onCreateNewPostClick()
            }

            headerViewUniversal.setNavigationIconClickListener {
                onNavigationIconClick()
            }

            headerViewUniversal.setSearchIconClickListener {
                onSearchIconClick()
            }

            layoutNoPost.setActionFABClickListener {
                onCreateNewPostClick()
            }

            layoutPosting.setRetryCTAClickListener {
                onRetryUploadClicked()
            }

            topicSelectorBar.setAllTopicsClickListener {
                onAllTopicsClicked()
            }
        }
    }

    private fun observeResponses() {
        universalFeedViewModel.universalFeedResponse.observe(viewLifecycleOwner) { response ->
            LMFeedProgressBarHelper.hideProgress(binding.progressBar)
            val page = response.first
            val posts = response.second

            if (mSwipeRefreshLayout.isRefreshing) {
                checkPostsAndReplace(posts)
                mSwipeRefreshLayout.isRefreshing = false
                return@observe
            }

            if (page == 1) {
                checkPostsAndReplace(posts)
            } else {
                binding.rvUniversal.refreshVideoAutoPlayer()
            }
        }

        universalFeedViewModel.postSavedResponse.observe(viewLifecycleOwner) { post ->
            //todo: post variable
            //show toast message
            val toastMessage = if (post.footerViewData.isSaved) {
                getString(R.string.lm_feed_s_saved)
            } else {
                getString(R.string.lm_feed_s_unsaved)
            }
            LMFeedViewUtils.showShortToast(requireContext(), toastMessage)
        }

        universalFeedViewModel.postPinnedResponse.observe(viewLifecycleOwner) { post ->
            //todo: post variable
            //show toast message
            val toastMessage = if (post.headerViewData.isPinned) {
                getString(R.string.lm_feed_s_pinned_to_top)
            } else {
                getString(R.string.lm_feed_s_unpinned)
            }
            LMFeedViewUtils.showShortToast(requireContext(), toastMessage)
        }

        // observes deletePostResponse LiveData
        universalFeedViewModel.deletePostResponse.observe(viewLifecycleOwner) { postId ->
            binding.rvUniversal.apply {
                val indexToRemove = getIndexAndPostFromAdapter(postId)?.first ?: return@observe
                removePostAtIndex(indexToRemove)
                checkForNoPost(allPosts())
                refreshVideoAutoPlayer()
                //todo:
                LMFeedViewUtils.showShortToast(
                    requireContext(),
                    getString(
                        R.string.lm_feed_s_deleted,
//                        lmFeedHelperViewModel.getPostVariable()
//                            .pluralizeOrCapitalize(WordAction.FIRST_LETTER_CAPITAL_SINGULAR)
                    )
                )
            }
        }

        universalFeedViewModel.showTopicFilter.observe(viewLifecycleOwner) { showTopicFilter ->
            binding.topicSelectorBar.apply {
                isVisible = showTopicFilter
                setAllTopicsTextVisibility(showTopicFilter)
                setSelectedTopicFilterVisibility(false)
            }
        }

        universalFeedViewModel.errorMessageEventFlow.onEach { response ->
            when (response) {
                is LMFeedUniversalFeedViewModel.ErrorMessageEvent.DeletePost -> {
                    val errorMessage = response.errorMessage
                    LMFeedViewUtils.showErrorMessageToast(requireContext(), errorMessage)
                }

                is LMFeedUniversalFeedViewModel.ErrorMessageEvent.LikePost -> {
                    val postId = response.postId

                    //get post and index
                    val pair =
                        binding.rvUniversal.getIndexAndPostFromAdapter(postId) ?: return@onEach
                    val post = pair.second
                    val index = pair.first

                    val footerData = post.footerViewData

                    val newLikesCount = if (footerData.isLiked) {
                        footerData.likesCount - 1
                    } else {
                        footerData.likesCount + 1
                    }

                    val updatedIsLiked = !footerData.isLiked

                    val updatedFooterData = footerData.toBuilder()
                        .isLiked(updatedIsLiked)
                        .likesCount(newLikesCount)
                        .build()

                    val updatedPostData = post.toBuilder()
                        .footerViewData(updatedFooterData)
                        .fromPostLiked(true)
                        .build()

                    binding.rvUniversal.updatePostItem(index, updatedPostData)

                    //show error message
                    LMFeedViewUtils.showSomethingWentWrongToast(requireContext())
                }

                is LMFeedUniversalFeedViewModel.ErrorMessageEvent.PinPost -> {
                    binding.rvUniversal.apply {
                        val postId = response.postId

                        //get post and index
                        val pair = getIndexAndPostFromAdapter(postId) ?: return@onEach
                        val post = pair.second
                        val index = pair.first

                        //update header view data
                        val updatedHeaderView = post.headerViewData.toBuilder()
                            .isPinned(!post.headerViewData.isPinned)
                            .build()

                        //update post view data
                        val updatedPostViewData = post.toBuilder()
                            .headerViewData(updatedHeaderView)
                            .build()

                        //update recycler view
                        updatePostItem(index, updatedPostViewData)

                        //show error message
                        LMFeedViewUtils.showSomethingWentWrongToast(requireContext())
                    }
                }

                is LMFeedUniversalFeedViewModel.ErrorMessageEvent.SavePost -> {
                    binding.rvUniversal.apply {
                        val postId = response.postId

                        //get post and index
                        val pair = getIndexAndPostFromAdapter(postId) ?: return@onEach
                        val post = pair.second
                        val index = pair.first

                        //update footer view data
                        val updatedFooterViewData = post.footerViewData.toBuilder()
                            .isSaved(!post.footerViewData.isSaved)
                            .build()

                        //update post view data
                        val updatedPostViewData = post.toBuilder()
                            .footerViewData(updatedFooterViewData)
                            .fromPostSaved(true)
                            .build()

                        //update recycler view
                        updatePostItem(index, updatedPostViewData)

                        //show error message
                        LMFeedViewUtils.showSomethingWentWrongToast(requireContext())
                    }
                }

                is LMFeedUniversalFeedViewModel.ErrorMessageEvent.GetTopic -> {
                    LMFeedViewUtils.showSomethingWentWrongToast(requireContext())
                }
            }
        }.observeInLifecycle(viewLifecycleOwner)
    }

    private fun checkPostsAndReplace(posts: List<LMFeedPostViewData>) {
        binding.rvUniversal.apply {
            checkForNoPost(posts)
            replacePosts(posts)
            scrollToPosition(0)
            refreshVideoAutoPlayer()
        }
    }

    private fun initUniversalFeedRecyclerView() {
        LMFeedProgressBarHelper.showProgress(binding.progressBar)
        universalFeedViewModel.getFeed(1, null)
        binding.rvUniversal.apply {
            setAdapter(this@LMFeedUniversalFeedFragment)
            universalFeedViewModel.bindView(this, viewLifecycleOwner)
        }
    }

    private fun initSwipeRefreshLayout() {
        mSwipeRefreshLayout = binding.swipeRefreshLayout
        mSwipeRefreshLayout.apply {
            setColorSchemeColors(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.lm_feed_majorelle_blue
                )
            )

            setOnRefreshListener {
                onFeedRefreshed()
            }
        }
    }

    //init selected topic recycler view
    private fun initSelectedTopicRecyclerView() {
        binding.topicSelectorBar.apply {
            universalFeedViewModel.getAllTopics(false)
            setSelectedTopicAdapter(this@LMFeedUniversalFeedFragment)

            setClearSelectedTopicsClickListener {
                clearSelectedTopics()
            }
        }
    }

    //clear all selected topics and reset data
    private fun clearSelectedTopics() {
        binding.apply {
            //call api
            topicSelectorBar.clearSelectedTopicsAndNotify()
            rvUniversal.resetScrollListenerData()
            LMFeedProgressBarHelper.showProgress(progressBar, true)
            universalFeedViewModel.getFeed(1, null)

            //show layout accordingly
            topicSelectorBar.setSelectedTopicFilterVisibility(false)
            topicSelectorBar.setAllTopicsTextVisibility(true)
        }
    }

    private fun checkForNoPost(feed: List<LMFeedBaseViewType>) {
        if (feed.isNotEmpty()) {
            binding.apply {
                layoutNoPost.hide()
                fabNewPost.show()
                rvUniversal.show()
            }
        } else {
            binding.apply {
                layoutNoPost.show()
                fabNewPost.hide()
                rvUniversal.hide()
            }
        }
    }

    override fun onPostContentClicked(position: Int, postViewData: LMFeedPostViewData) {
        // sends comment list open event
        LMFeedAnalytics.sendCommentListOpenEvent()

        //todo: test this
        val postDetailExtras = LMFeedPostDetailExtras.Builder()
            .postId(postViewData.id)
            .isEditTextFocused(false)
            .build()
        LMFeedPostDetailActivity.start(requireContext(), postDetailExtras)
    }

    override fun onPostLikeClicked(position: Int, postViewData: LMFeedPostViewData) {
        val userPreferences = LMFeedUserPreferences(requireContext())
        val loggedInUUID = userPreferences.getUUID()

        //call api
        universalFeedViewModel.likePost(
            postViewData.id,
            postViewData.footerViewData.isLiked,
            loggedInUUID
        )
        //update recycler
        binding.rvUniversal.updatePostItem(position, postViewData)
    }

    override fun onPostLikesCountClicked(position: Int, postViewData: LMFeedPostViewData) {
        //show the likes screen
        val likesScreenExtras = LMFeedLikesScreenExtras.Builder()
            .postId(postViewData.id)
            .entityType(POST)
            .build()
        LMFeedLikesActivity.start(requireContext(), likesScreenExtras)
    }

    override fun onPostCommentsCountClicked(position: Int, postViewData: LMFeedPostViewData) {
        // sends comment list open event
        LMFeedAnalytics.sendCommentListOpenEvent()

        val postDetailExtras = LMFeedPostDetailExtras.Builder()
            .postId(postViewData.id)
            .isEditTextFocused(true)
            .build()
        LMFeedPostDetailActivity.start(requireContext(), postDetailExtras)
    }

    override fun onPostSaveClicked(position: Int, postViewData: LMFeedPostViewData) {
        //todo: create toast message using post variable and show toast
        //call api
        universalFeedViewModel.savePost(postViewData)
        //update recycler
        binding.rvUniversal.updatePostItem(position, postViewData)
    }

    override fun onPostShareClicked(position: Int, postViewData: LMFeedPostViewData) {
        //todo: post as variable and take domain here
        LMFeedShareUtils.sharePost(
            requireContext(),
            postViewData.id,
            "https://take-this-in-config.com",
            ""
        )

        LMFeedAnalytics.sendPostShared(postViewData)
    }

    //updates the fromPostLiked/fromPostSaved variables and updates the rv list
    override fun updateFromLikedSaved(position: Int, postViewData: LMFeedPostViewData) {
        val updatedPostData = postViewData.toBuilder()
            .fromPostLiked(false)
            .fromPostSaved(false)
            .build()

        binding.rvUniversal.updatePostWithoutNotifying(position, updatedPostData)
    }

    //updates [alreadySeenFullContent] for the post
    override fun onPostContentSeeMoreClicked(position: Int, postViewData: LMFeedPostViewData) {
        binding.rvUniversal.apply {

            //update the post item in the adapter
            updatePostItem(position, postViewData)
        }
    }

    override fun onPostContentLinkClicked(url: String) {
        // creates a route and returns an intent to handle the link
        val intent = LMFeedRoute.handleDeepLink(requireContext(), url)
        if (intent != null) {
            try {
                // starts activity with the intent
                ActivityCompat.startActivity(requireContext(), intent, null)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onPostMenuIconClicked(
        position: Int,
        anchorView: View,
        postViewData: LMFeedPostViewData
    ) {
        val popupMenu = LMFeedOverflowMenu(requireContext(), anchorView)
        val menuItems = postViewData.headerViewData.menuItems
        popupMenu.addMenuItems(menuItems)

        popupMenu.setMenuItemClickListener { menuId ->
            onPostMenuItemClicked(position, menuId, postViewData)
        }

        popupMenu.show()
    }

    override fun onPostImageMediaClicked(position: Int, postViewData: LMFeedPostViewData) {
        // sends comment list open event
        LMFeedAnalytics.sendCommentListOpenEvent()

        val postDetailExtras = LMFeedPostDetailExtras.Builder()
            .postId(postViewData.id)
            .isEditTextFocused(false)
            .build()
        LMFeedPostDetailActivity.start(requireContext(), postDetailExtras)
    }

    override fun onPostVideoMediaClicked(position: Int, postViewData: LMFeedPostViewData) {
        // sends comment list open event
        LMFeedAnalytics.sendCommentListOpenEvent()

        val postDetailExtras = LMFeedPostDetailExtras.Builder()
            .postId(postViewData.id)
            .isEditTextFocused(false)
            .build()
        LMFeedPostDetailActivity.start(requireContext(), postDetailExtras)
    }

    override fun onPostLinkMediaClicked(position: Int, postViewData: LMFeedPostViewData) {
        // creates a route and returns an intent to handle the link
        val intent = LMFeedRoute.handleDeepLink(
            requireContext(),
            postViewData.mediaViewData.attachments.firstOrNull()?.attachmentMeta?.ogTags?.url
        )

        if (intent != null) {
            try {
                // starts activity with the intent
                ActivityCompat.startActivity(requireContext(), intent, null)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onPostDocumentMediaClicked(
        position: Int,
        parentPosition: Int,
        attachmentViewData: LMFeedAttachmentViewData
    ) {
        //open the pdf using Android's document view
        val documentUrl = attachmentViewData.attachmentMeta.url ?: ""
        val pdfUri = Uri.parse(documentUrl)
        LMFeedAndroidUtils.startDocumentViewer(requireContext(), pdfUri)
    }

    override fun onPostMultipleMediaImageClicked(
        position: Int,
        parentPosition: Int,
        attachmentViewData: LMFeedAttachmentViewData
    ) {
        // sends comment list open event
        LMFeedAnalytics.sendCommentListOpenEvent()

        val postDetailExtras = LMFeedPostDetailExtras.Builder()
            .postId(attachmentViewData.postId)
            .isEditTextFocused(false)
            .build()
        LMFeedPostDetailActivity.start(requireContext(), postDetailExtras)
    }

    override fun onPostMultipleMediaVideoClicked(
        position: Int,
        parentPosition: Int,
        attachmentViewData: LMFeedAttachmentViewData
    ) {
        // sends comment list open event
        LMFeedAnalytics.sendCommentListOpenEvent()

        val postDetailExtras = LMFeedPostDetailExtras.Builder()
            .postId(attachmentViewData.postId)
            .isEditTextFocused(false)
            .build()
        LMFeedPostDetailActivity.start(requireContext(), postDetailExtras)
    }

    //called when the page in the multiple media post is changed
    override fun onPostMultipleMediaPageChangeCallback(position: Int, parentPosition: Int) {
        //processes the current video whenever view pager's page is changed
        binding.rvUniversal.refreshVideoAutoPlayer()
    }

    //called when show more is clicked in the documents type post
    override fun onPostMultipleDocumentsExpanded(position: Int, postViewData: LMFeedPostViewData) {
        binding.rvUniversal.apply {
            if (position == itemCount - 1) {
                scrollToPositionWithOffset(position)
            }

            val updatedMediaViewData = postViewData.mediaViewData
                .toBuilder()
                .isExpanded(true)
                .build()

            val updatedPostViewData = postViewData.toBuilder()
                .mediaViewData(updatedMediaViewData)
                .fromPostSaved(false)
                .fromPostLiked(false)
                .build()

            //updates the [isExpanded] for the document item to true
            updatePostItem(position, updatedPostViewData)
        }
    }

    override fun onEntityDeletedByAdmin(deleteExtras: LMFeedDeleteExtras, reason: String) {
        val post =
            binding.rvUniversal.getIndexAndPostFromAdapter(deleteExtras.postId)?.second ?: return
        universalFeedViewModel.deletePost(post, reason)
    }

    override fun onEntityDeletedByAuthor(deleteExtras: LMFeedDeleteExtras) {
        val post =
            binding.rvUniversal.getIndexAndPostFromAdapter(deleteExtras.postId)?.second ?: return
        universalFeedViewModel.deletePost(post)
    }

    override fun onTopicRemoved(position: Int, topicViewData: LMFeedTopicViewData) {
        super.onTopicRemoved(position, topicViewData)

        binding.apply {
            val selectedTopics = topicSelectorBar.getAllSelectedTopics()
            if (selectedTopics.size == 1) {
                clearSelectedTopics()
            } else {
                //remove from adapter
                topicSelectorBar.removeTopicAndNotify(position)

                //call apis
                rvUniversal.resetScrollListenerData()
                rvUniversal.clearPostsAndNotify()
                LMFeedProgressBarHelper.showProgress(binding.progressBar, true)
                universalFeedViewModel.getFeed(
                    1,
                    universalFeedViewModel.getTopicIdsFromAdapterList(selectedTopics)
                )
            }
        }
    }

    protected open fun customizeCreateNewPostButton(fabNewPost: LMFeedFAB) {
        fabNewPost.apply {
            setStyle(LMFeedStyleTransformer.universalFeedFragmentViewStyle.createNewPostButtonViewStyle)
        }
    }

    protected open fun onCreateNewPostClick() {
    }

    protected open fun customizeUniversalFeedHeaderView(headerViewUniversal: LMFeedHeaderView) {
        headerViewUniversal.apply {
            setStyle(LMFeedStyleTransformer.universalFeedFragmentViewStyle.headerViewStyle)

            setTitleText(getString(R.string.lm_feed_feed))
        }
    }

    protected open fun onNavigationIconClick() {
    }

    protected open fun onSearchIconClick() {
        //todo: change this
        LMFeedAnalytics.sendNotificationPageOpenedEvent()
        LMFeedActivityFeedActivity.start(requireContext())
    }

    protected open fun customizeNoPostLayout(layoutNoPost: LMFeedNoEntityLayoutView) {
        layoutNoPost.apply {
            setStyle(LMFeedStyleTransformer.universalFeedFragmentViewStyle.noPostLayoutViewStyle)

            setTitleText(getString(R.string.lm_feed_no_s_to_show))
            setSubtitleText(getString(R.string.lm_feed_be_the_first_one_to_s_here))
            setActionCTAText(getString(R.string.lm_feed_new_s))
        }
    }

    protected open fun customizePostingLayout(layoutPosting: LMFeedPostingView) {
        layoutPosting.apply {
            setStyle(LMFeedStyleTransformer.universalFeedFragmentViewStyle.postingViewStyle)

            setPostingText(getString(R.string.lm_feed_creating_s))
            setRetryCTAText(getString(R.string.lm_feed_retry))
        }
    }

    protected open fun customizeTopicSelectorBar(topicSelectorBar: LMFeedUniversalTopicSelectorBarView) {
        topicSelectorBar.apply {
            setStyle(LMFeedStyleTransformer.universalFeedFragmentViewStyle.topicSelectorBarStyle)

            setAllTopicsText(getString(R.string.lm_feed_all_topics))
            setClearTopicsText(getString(R.string.lm_feed_clear))
        }
    }

    protected open fun onRetryUploadClicked() {
    }

    private val topicSelectionLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val bundle = result.data?.extras
                val resultExtras = LMFeedExtrasUtil.getParcelable(
                    bundle,
                    LM_FEED_TOPIC_SELECTION_RESULT_EXTRAS,
                    LMFeedTopicSelectionResultExtras::class.java
                ) ?: return@registerForActivityResult

                handleTopicSelectionResult(resultExtras)
            }
        }

    //handles result after selecting filters and show recyclers views
    private fun handleTopicSelectionResult(resultExtras: LMFeedTopicSelectionResultExtras) {
        binding.apply {
            rvUniversal.resetScrollListenerData()
            rvUniversal.clearPostsAndNotify()

            if (resultExtras.isAllTopicSelected) {
                //show layouts accordingly
                topicSelectorBar.setAllTopicsTextVisibility(true)
                topicSelectorBar.setSelectedTopicFilterVisibility(false)

                //call api
                LMFeedProgressBarHelper.showProgress(progressBar, true)
                universalFeedViewModel.getFeed(1, null)
            } else {
                //show layouts accordingly
                topicSelectorBar.setAllTopicsTextVisibility(false)
                topicSelectorBar.setSelectedTopicFilterVisibility(true)

                //set selected topics to filter
                val selectedTopics = resultExtras.selectedTopics
                topicSelectorBar.replaceSelectedTopics(selectedTopics)

                //call api
                LMFeedProgressBarHelper.showProgress(progressBar, true)
                universalFeedViewModel.getFeed(
                    1,
                    universalFeedViewModel.getTopicIdsFromAdapterList(selectedTopics)
                )
            }
        }
    }

    protected open fun onAllTopicsClicked() {
        //show topics selecting screen with All topic filter
        val intent = LMFeedTopicSelectionActivity.getIntent(
            requireContext(),
            LMFeedTopicSelectionExtras.Builder()
                .showAllTopicFilter(true)
                .showEnabledTopicOnly(false)
                .build()
        )

        topicSelectionLauncher.launch(intent)
    }

    protected open fun onFeedRefreshed() {
        binding.apply {
            mSwipeRefreshLayout.isRefreshing = true
            rvUniversal.resetScrollListenerData()
            universalFeedViewModel.getFeed(
                1,
                universalFeedViewModel.getTopicIdsFromAdapterList(topicSelectorBar.getAllSelectedTopics())
            )
        }
    }

    //callback when post menu items are clicked
    protected open fun onPostMenuItemClicked(
        position: Int,
        menuId: Int,
        postViewData: LMFeedPostViewData
    ) {
        when (menuId) {
            EDIT_POST_MENU_ITEM_ID -> {
                onEditPostMenuClicked(
                    position,
                    menuId,
                    postViewData
                )
            }

            DELETE_POST_MENU_ITEM_ID -> {
                onDeletePostMenuClicked(
                    position,
                    menuId,
                    postViewData
                )
            }

            REPORT_POST_MENU_ITEM_ID -> {
                onReportPostMenuClicked(
                    position,
                    menuId,
                    postViewData
                )
            }

            PIN_POST_MENU_ITEM_ID -> {
                val updatedPostViewData =
                    LMFeedPostBinderUtils.updatePostForPin(requireContext(), postViewData)

                updatedPostViewData?.let {
                    onPinPostMenuClicked(
                        position,
                        menuId,
                        it
                    )
                }
            }

            UNPIN_POST_MENU_ITEM_ID -> {
                val updatedPost =
                    LMFeedPostBinderUtils.updatePostForUnpin(requireContext(), postViewData)

                updatedPost?.let {
                    onUnpinPostMenuClicked(
                        position,
                        menuId,
                        it
                    )
                }
            }
        }
    }

    protected open fun onEditPostMenuClicked(
        position: Int,
        menuId: Int,
        post: LMFeedPostViewData
    ) {
        val editPostExtras = LMFeedEditPostExtras.Builder()
            .postId(post.id)
            .build()
        val intent = LMFeedEditPostActivity.getIntent(requireContext(), editPostExtras)
        startActivity(intent)
    }

    protected open fun onDeletePostMenuClicked(
        position: Int,
        menuId: Int,
        post: LMFeedPostViewData
    ) {
        val deleteExtras = LMFeedDeleteExtras.Builder()
            .postId(post.id)
            .entityType(DELETE_TYPE_POST)
            //todo:
//            .postAsVariable(lmFeedHelperViewModel.getPostVariable())
            .build()

        val postCreatorUUID = post.headerViewData.user.sdkClientInfoViewData.uuid

        val userPreferences = LMFeedUserPreferences(requireContext())
        val loggedInUUID = userPreferences.getUUID()

        if (postCreatorUUID == loggedInUUID) {
            // if the post was created by current user
            LMFeedSelfDeleteDialogFragment.showDialog(
                childFragmentManager,
                deleteExtras
            )
        } else {
            // if the post was not created by current user and they are admin
            LMFeedAdminDeleteDialogFragment.showDialog(
                childFragmentManager,
                deleteExtras
            )
        }
    }

    // launcher to start [ReportActivity] and show success dialog for result
    private val reportPostLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data?.getStringExtra(LM_FEED_REPORT_RESULT)
                //todo:
                val entityType = "Post"
//                val entityType = if (data == "Post") {
//                    lmFeedHelperViewModel.getPostVariable()
//                        .pluralizeOrCapitalize(WordAction.FIRST_LETTER_CAPITAL_SINGULAR)
//                } else {
//                    data
//                }
                LMFeedReportSuccessDialogFragment(entityType).show(
                    childFragmentManager,
                    LMFeedReportSuccessDialogFragment.TAG
                )
            }
        }

    protected open fun onReportPostMenuClicked(
        position: Int,
        menuId: Int,
        post: LMFeedPostViewData
    ) {
        //create extras for [ReportActivity]
        val reportExtras = LMFeedReportExtras.Builder()
            .entityId(post.id)
            .uuid(post.headerViewData.user.sdkClientInfoViewData.uuid)
            .entityType(REPORT_TYPE_POST)
            .postViewType(post.viewType)
            .build()

        //get Intent for [ReportActivity]
        val intent = LMFeedReportActivity.getIntent(requireContext(), reportExtras)

        //start [ReportActivity] and check for result
        reportPostLauncher.launch(intent)
    }

    protected open fun onPinPostMenuClicked(
        position: Int,
        menuId: Int,
        post: LMFeedPostViewData
    ) {
        //call api
        universalFeedViewModel.pinPost(post)

        //update recycler
        binding.rvUniversal.updatePostItem(position, post)
    }

    protected open fun onUnpinPostMenuClicked(
        position: Int,
        menuId: Int,
        post: LMFeedPostViewData
    ) {
        //call api
        universalFeedViewModel.pinPost(post)

        //update recycler
        binding.rvUniversal.updatePostItem(position, post)
    }
}
