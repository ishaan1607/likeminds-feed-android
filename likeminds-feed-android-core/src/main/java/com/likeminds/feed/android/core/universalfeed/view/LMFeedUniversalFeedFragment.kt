package com.likeminds.feed.android.core.universalfeed.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
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
import com.likeminds.feed.android.core.utils.LMFeedPostVideoAutoPlayHelper
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer

open class LMFeedUniversalFeedFragment : Fragment(), LMFeedUniversalFeedAdapterListener {
    private lateinit var binding: LmFeedFragmentUniversalFeedBinding

    private lateinit var postVideoAutoPlayHelper: LMFeedPostVideoAutoPlayHelper

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initUI()
    }

    override fun onResume() {
        super.onResume()

        initiateAutoPlayer()
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
        }
    }

    private fun initUI() {
        initUniversalFeedRecyclerView()
    }

    private fun initUniversalFeedRecyclerView() {
        binding.rvUniversal.apply {
            setAdapter(this@LMFeedUniversalFeedFragment)
        }
        //todo: remove this
        setFeedAndScrollToTop(listOf())
    }

    //set posts through diff utils and scroll to top of the feed
    private fun setFeedAndScrollToTop(feed: List<LMFeedPostViewData>) {
        binding.rvUniversal.apply {
            replace(feed)
            scrollToPosition(0)
        }
        refreshAutoPlayer()
    }

    /**
     * Initializes the [postVideoAutoPlayHelper] with the recyclerView
     * And starts observing
     **/
    private fun initiateAutoPlayer() {
        postVideoAutoPlayHelper = LMFeedPostVideoAutoPlayHelper.getInstance(binding.rvUniversal)
        postVideoAutoPlayHelper.attachScrollListenerForVideo()
        postVideoAutoPlayHelper.playMostVisibleItem()
    }

    // removes the old player and refreshes auto play
    private fun refreshAutoPlayer() {
        if (!::postVideoAutoPlayHelper.isInitialized) {
            initiateAutoPlayer()
        }
        postVideoAutoPlayHelper.removePlayer()
        postVideoAutoPlayHelper.playMostVisibleItem()
    }

    // removes the player and destroys the [postVideoAutoPlayHelper]
    private fun destroyAutoPlayer() {
        if (::postVideoAutoPlayHelper.isInitialized) {
            postVideoAutoPlayHelper.detachScrollListenerForVideo()
            postVideoAutoPlayHelper.destroy()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        destroyAutoPlayer()
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun onPostContentClick(postId: String) {
        //todo:
    }

    override fun onPostLikeClick(position: Int) {
        //todo:
    }

    override fun onPostLikesCountClick(postId: String) {
        //todo:
    }

    override fun onPostCommentsCountClick(postId: String) {
        //todo:
    }

    override fun onPostSaveClick(postId: String) {
        //todo:
    }

    override fun onPostShareClick(postId: String) {
        //todo:
    }

    override fun updateFromLikedSaved(position: Int) {
        //todo:
    }

    override fun updatePostSeenFullContent(position: Int, alreadySeenFullContent: Boolean) {
        //todo:
    }

    override fun handleLinkClick(url: String) {
        //todo:
    }

    override fun onPostMenuIconClick() {
        //todo:
    }

    override fun onPostImageMediaClick() {
        //todo:
    }

    override fun onPostLinkMediaClick(linkOGTags: LMFeedLinkOGTagsViewData) {
        //todo:
    }

    override fun onPostDocumentMediaClick(document: LMFeedAttachmentViewData) {
        //todo:
    }

    override fun onPostMultipleMediaImageClick(image: LMFeedAttachmentViewData) {
        //todo:
    }

    override fun onPostMultipleMediaVideoClick(video: LMFeedAttachmentViewData) {
        //todo:
    }

    override fun onPostMultipleMediaPageChangeCallback(position: Int) {
        //todo:
    }

    override fun onPostMultipleDocumentsExpanded(postData: LMFeedPostViewData, position: Int) {
        //todo:
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
}