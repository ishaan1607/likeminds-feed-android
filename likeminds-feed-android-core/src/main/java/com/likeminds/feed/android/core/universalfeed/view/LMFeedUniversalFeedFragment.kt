package com.likeminds.feed.android.core.universalfeed.view

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.databinding.LmFeedFragmentUniversalFeedBinding
import com.likeminds.feed.android.core.ui.base.styles.setStyle
import com.likeminds.feed.android.core.ui.base.views.LMFeedFAB
import com.likeminds.feed.android.core.ui.widgets.headerview.views.LMFeedHeaderView
import com.likeminds.feed.android.core.ui.widgets.noentitylayout.view.LMFeedNoEntityLayoutView
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalFeedAdapterListener
import com.likeminds.feed.android.core.universalfeed.model.LMFeedPostViewData
import com.likeminds.feed.android.core.universalfeed.viewmodel.LMFeedUniversalFeedViewModel
import com.likeminds.feed.android.core.universalfeed.viewmodel.bindView
import com.likeminds.feed.android.core.utils.*
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.hide
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.show
import com.likeminds.feed.android.core.utils.base.LMFeedBaseViewType

open class LMFeedUniversalFeedFragment : Fragment(), LMFeedUniversalFeedAdapterListener {

    private lateinit var binding: LmFeedFragmentUniversalFeedBinding
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout

    private lateinit var postVideoAutoPlayHelper: LMFeedPostVideoAutoPlayHelper

    private val lmFeedUniversalFeedViewModel: LMFeedUniversalFeedViewModel by viewModels()

    companion object {
        private const val LOG_TAG = "LMFeedUniversalFeedFragment"
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

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

    override fun onPause() {
        super.onPause()
        binding.rvUniversal.destroyAutoPlayer()
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
        LMFeedProgressBarHelper.showProgress(binding.progressBar)
        lmFeedUniversalFeedViewModel.getFeed(1, null)
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

    override fun onPostContentClick(position: Int, postViewData: LMFeedPostViewData) {
//        TODO("Not yet implemented")
    }

    override fun onPostLikeClick(position: Int, postViewData: LMFeedPostViewData) {
//        TODO("Not yet implemented")
    }

    override fun onPostLikesCountClick(position: Int, postViewData: LMFeedPostViewData) {
//        TODO("Not yet implemented")
    }

    override fun onPostCommentsCountClick(position: Int, postViewData: LMFeedPostViewData) {
//        TODO("Not yet implemented")
    }

    override fun onPostSaveClick(position: Int, postViewData: LMFeedPostViewData) {
//        TODO("Not yet implemented")
    }

    override fun onPostShareClick(position: Int, postViewData: LMFeedPostViewData) {
//        TODO("Not yet implemented")
    }

    override fun updateFromLikedSaved(position: Int, postViewData: LMFeedPostViewData) {
//        TODO("Not yet implemented")
    }

    override fun updatePostSeenFullContent(
        position: Int,
        alreadySeenFullContent: Boolean,
        postViewData: LMFeedPostViewData
    ) {
        onSeeMoreClick(position, alreadySeenFullContent)
    }

    override fun handleLinkClick(url: String) {
//        TODO("Not yet implemented")
    }

    override fun onPostMenuIconClick() {
//        TODO("Not yet implemented")
    }

    override fun onPostImageMediaClick(position: Int, postViewData: LMFeedPostViewData) {
//        TODO("Not yet implemented")
    }

    override fun onPostVideoMediaClick(position: Int, postViewData: LMFeedPostViewData) {
        Log.d(
            "PUI",
            "onPostMultipleMediaVideoClick: position: $position parentPosition: ${postViewData.id}"
        )
        //todo:
    }

    override fun onPostLinkMediaClick(position: Int, postViewData: LMFeedPostViewData) {
//        TODO("Not yet implemented")
    }

    override fun onPostDocumentMediaClick(position: Int, parentPosition: Int) {
        onPostDocumentClick(position, parentPosition)
    }

    override fun onPostMultipleMediaImageClick(position: Int, parentPosition: Int) {
//        TODO("Not yet implemented")
    }

    override fun onPostMultipleMediaVideoClick(position: Int, parentPosition: Int) {
        Log.d(
            "PUI",
            "onPostMultipleMediaVideoClick: position: $position parentPosition: $parentPosition"
        )
//        TODO("Not yet implemented")
    }

    //called when the page in the multiple media post is changed
    override fun onPostMultipleMediaPageChangeCallback(position: Int, parentPosition: Int) {
        Log.d(
            "PUI",
            "onPostMultipleMediaPageChangeCallback: position: $position parentPosition: $parentPosition"
        )
        onPostMultipleMediaPageChanged(position, parentPosition)
    }

    //called when show more is clicked in the documents type post
    override fun onPostMultipleDocumentsExpanded(position: Int, postViewData: LMFeedPostViewData) {
        onPostDocumentsExpanded(position, postViewData)
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
        binding.rvUniversal.resetScrollListenerData()
        lmFeedUniversalFeedViewModel.getFeed(1, null)//todo change to selected topic adapter
    }

    //updates [alreadySeenFullContent] for the post
    protected open fun onSeeMoreClick(position: Int, alreadySeenFullContent: Boolean) {
        binding.rvUniversal.apply {
            //get post from adapter
            val postViewData = getPostAtIndex(position)
            // update the post view data
            val updatedPostViewData = postViewData.toBuilder()
                .contentViewData(
                    postViewData.contentViewData.toBuilder()
                        .alreadySeenFullContent(alreadySeenFullContent)
                        .build()
                )
                .fromPostSaved(false)
                .fromPostLiked(false)
                .build()

            //update the post item in the adapter
            update(position, updatedPostViewData)
        }
    }

    protected open fun onPostMultipleMediaPageChanged(position: Int, parentPosition: Int) {
        Log.d(LOG_TAG, "onPostMultipleMediaPageChanged: $position")

        // processes the current video whenever view pager's page is changed
        binding.rvUniversal.refreshAutoPlayer()
    }

    protected open fun onPostDocumentsExpanded(position: Int, postData: LMFeedPostViewData) {
        Log.d("PUI", "onPostDocumentsExpanded: $position")

        binding.rvUniversal.apply {
            if (position == itemCount - 1) {
                scrollToPositionWithOffset(position)
            }

            //updates the [isExpanded] for the document item to true
            update(
                position,
                postData.toBuilder()
                    .mediaViewData(postData.mediaViewData.toBuilder().isExpanded(true).build())
                    .fromPostSaved(false)
                    .fromPostLiked(false)
                    .build()
            )
        }
    }

    protected open fun onPostDocumentClick(position: Int, parentPosition: Int) {
        //open the pdf using Android's document view
        val postData = binding.rvUniversal.getPostAtIndex(parentPosition)
        val documentUrl = postData.mediaViewData.attachments[position].attachmentMeta.url ?: ""
        val pdfUri = Uri.parse(documentUrl)
        LMFeedAndroidUtils.startDocumentViewer(requireContext(), pdfUri)
    }
}