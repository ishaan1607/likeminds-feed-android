package com.likeminds.feed.android.core.post.create.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.likeminds.feed.android.core.databinding.LmFeedFragmentCreatePostBinding
import com.likeminds.feed.android.core.post.create.model.LMFeedCreatePostExtras
import com.likeminds.feed.android.core.post.create.view.LMFeedCreatePostActivity.Companion.LM_FEED_CREATE_POST_EXTRAS
import com.likeminds.feed.android.core.post.create.viewmodel.LMFeedCreatePostViewModel
import com.likeminds.feed.android.core.ui.widgets.headerview.view.LMFeedHeaderView
import com.likeminds.feed.android.core.universalfeed.model.LMFeedUserViewData
import com.likeminds.feed.android.core.utils.LMFeedExtrasUtil
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer
import com.likeminds.feed.android.core.utils.emptyExtrasException

class LMFeedCreatePostFragment : Fragment() {
    private lateinit var binding: LmFeedFragmentCreatePostBinding
    private lateinit var lmFeedCreatePostExtras: LMFeedCreatePostExtras

    private val createPostViewModel: LMFeedCreatePostViewModel by viewModels()

    companion object {
        const val TAG = "LMFeedCreatePostFragment"
        const val LM_FEED_CREATE_POST_FRAGMENT_EXTRAS = "LM_FEED_CREATE_POST_FRAGMENT_EXTRAS"

        fun getInstance(createPostExtras: LMFeedCreatePostExtras): LMFeedCreatePostFragment {
            val createPostFragment = LMFeedCreatePostFragment()
            val bundle = Bundle()
            bundle.putParcelable(LM_FEED_CREATE_POST_FRAGMENT_EXTRAS, createPostExtras)
            createPostFragment.arguments = bundle
            return createPostFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //receive extras
        receiveExtras()
    }

    private fun receiveExtras() {
        if (arguments == null || arguments?.containsKey(LM_FEED_CREATE_POST_EXTRAS) == false) {
            requireActivity().supportFragmentManager.popBackStack()
            return
        }

        lmFeedCreatePostExtras = LMFeedExtrasUtil.getParcelable(
            arguments,
            LM_FEED_CREATE_POST_FRAGMENT_EXTRAS,
            LMFeedCreatePostExtras::class.java
        ) ?: throw emptyExtrasException(TAG)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LmFeedFragmentCreatePostBinding.inflate(layoutInflater)

        //add customizations
        binding.apply {
            customizeCreatePostHeaderView(headerViewCreatePost)
        }

        return binding.root
    }

    protected open fun customizeCreatePostHeaderView(headerViewCreatePost: LMFeedHeaderView) {
        headerViewCreatePost.apply {
            setStyle(LMFeedStyleTransformer.createPostFragmentViewStyle.headerViewStyle)

            setTitleText("Create %s")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchUserDetails()
        observeData()
    }

    private fun fetchUserDetails() {
        createPostViewModel.getLoggedInUser()
    }

    private fun observeData() {
        createPostViewModel.loggedInUser.observe(viewLifecycleOwner) { user ->
            initAuthorFrame(user)
        }
    }

    //set logged in user data to post header frame
    private fun initAuthorFrame(user: LMFeedUserViewData) {
        binding.postHeader.apply {
            setAuthorImage(user)
            setAuthorName(user.name)
        }
    }
}