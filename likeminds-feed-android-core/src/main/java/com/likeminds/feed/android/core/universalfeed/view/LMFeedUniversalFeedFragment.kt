package com.likeminds.feed.android.core.universalfeed.view

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.databinding.LmFeedFragmentUniversalFeedBinding
import com.likeminds.feed.android.core.post.model.LMFeedAttachmentViewData
import com.likeminds.feed.android.core.post.model.LMFeedLinkOGTagsViewData
import com.likeminds.feed.android.core.ui.base.styles.setStyle
import com.likeminds.feed.android.core.ui.base.views.LMFeedFAB
import com.likeminds.feed.android.core.ui.widgets.headerview.views.LMFeedHeaderView
import com.likeminds.feed.android.core.ui.widgets.noentitylayout.view.LMFeedNoEntityLayoutView
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalFeedAdapterListener
import com.likeminds.feed.android.core.universalfeed.model.LMFeedPostViewData
import com.likeminds.feed.android.core.universalfeed.viewmodel.LMFeedUniversalFeedViewModel
import com.likeminds.feed.android.core.universalfeed.viewmodel.bindView
import com.likeminds.feed.android.core.utils.LMFeedProgressBarHelper
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.hide
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.show
import com.likeminds.feed.android.core.utils.base.LMFeedBaseViewType

open class LMFeedUniversalFeedFragment : Fragment(), LMFeedUniversalFeedAdapterListener {

    private lateinit var binding: LmFeedFragmentUniversalFeedBinding
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout


    protected val lmFeedUniversalFeedViewModel: LMFeedUniversalFeedViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LmFeedFragmentUniversalFeedBinding.inflate(layoutInflater)
        customizeCreateNewPostButton(binding.fabNewPost)
        customizeUniversalFeedHeaderView(binding.headerViewUniversal)
        customizeNoPostLayout(binding.layoutNoPost)
        customizePostingLayout(binding.layoutPosting)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        initListeners()
        observeResponses()
    }

    override fun onResume() {
        super.onResume()
        binding.rvUniversal.refreshAutoPlayer()
    }

    private fun initUI() {
        initUniversalFeedRecyclerView()
        initSwipeRefreshLayout()
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
        }
    }

    private fun observeResponses() {
        lmFeedUniversalFeedViewModel.universalFeedResponse.observe(viewLifecycleOwner) { response ->
            Log.d("PUI", "observer 2 fragment")
            Log.d(
                "PUI", """
                    observer 2
            response: ${response.second.size}
        """.trimIndent()
            )

            LMFeedProgressBarHelper.hideProgress(binding.progressBar)
            val page = response.first
            val posts = response.second

            if (mSwipeRefreshLayout.isRefreshing) {
                checkForNoPost(posts)
                binding.rvUniversal.apply {
                    replacePosts(posts)
                    scrollToPosition(0)
                    refreshAutoPlayer()
                }
                mSwipeRefreshLayout.isRefreshing = false
                return@observe
            }

            if (page == 1) {
                checkForNoPost(posts)
            } else {
                binding.rvUniversal.refreshAutoPlayer()
            }
        }
    }

    private fun initUniversalFeedRecyclerView() {
        binding.rvUniversal.apply {
            setAdapter(this@LMFeedUniversalFeedFragment)

            lmFeedUniversalFeedViewModel.bindView(this, viewLifecycleOwner)
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

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onPostContentClick(postId: String) {
//        TODO("Not yet implemented")
    }

    override fun onPostLikeClick(position: Int) {
//        TODO("Not yet implemented")
    }

    override fun onPostLikesCountClick(postId: String) {
//        TODO("Not yet implemented")
    }

    override fun onPostCommentsCountClick(postId: String) {
//        TODO("Not yet implemented")
    }

    override fun onPostSaveClick(postId: String) {
//        TODO("Not yet implemented")
    }

    override fun onPostShareClick(postId: String) {
//        TODO("Not yet implemented")
    }

    override fun updateFromLikedSaved(position: Int) {
//        TODO("Not yet implemented")
    }

    override fun updatePostSeenFullContent(position: Int, alreadySeenFullContent: Boolean) {
//        TODO("Not yet implemented")
    }

    override fun handleLinkClick(url: String) {
//        TODO("Not yet implemented")
    }

    override fun onPostMenuIconClick() {
//        TODO("Not yet implemented")
    }

    override fun onPostImageMediaClick() {
//        TODO("Not yet implemented")
    }

    override fun onPostLinkMediaClick(linkOGTags: LMFeedLinkOGTagsViewData) {
//        TODO("Not yet implemented")
    }

    override fun onPostDocumentMediaClick(document: LMFeedAttachmentViewData) {
//        TODO(TODO"Not yet implemented")
    }

    override fun onPostMultipleMediaImageClick(image: LMFeedAttachmentViewData) {
//        TODO("Not yet implemented")
    }

    override fun onPostMultipleMediaVideoClick(video: LMFeedAttachmentViewData) {
//        TODO("Not yet implemented")
    }

    override fun onPostMultipleMediaPageChangeCallback(position: Int) {
//        TODO("Not yet implemented")
    }

    override fun onPostMultipleDocumentsExpanded(postData: LMFeedPostViewData, position: Int) {
//        TODO("Not yet implemented")
    }

    protected open fun customizeCreateNewPostButton(fabNewPost: LMFeedFAB) {
        fabNewPost.apply {
            setStyle(LMFeedStyleTransformer.universalFeedFragmentViewStyle.createNewPostButtonViewStyle)
        }
    }

    protected open fun onCreateNewPostClick() {
        Log.d("PUI", "default onCreateNewPostClick")
    }

    protected open fun customizeUniversalFeedHeaderView(headerViewUniversal: LMFeedHeaderView) {
        headerViewUniversal.apply {
            setStyle(LMFeedStyleTransformer.universalFeedFragmentViewStyle.headerViewStyle)

            setTitleText(getString(R.string.lm_feed_feed))
        }
    }

    protected open fun onNavigationIconClick() {
        Log.d("PUI", "default onNavigationIconClick")
    }

    protected open fun onSearchIconClick() {
        Log.d("PUI", "default onSearchIconClick")
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

    protected open fun onRetryUploadClicked() {
        Log.d("PUI", "default onRetryUploadClicked")
    }

    protected open fun onFeedRefreshed() {
        mSwipeRefreshLayout.isRefreshing = true
        // reset data for scroll listener
        lmFeedUniversalFeedViewModel.getFeed(1, null)//todo change to selected topic adapter
    }
}