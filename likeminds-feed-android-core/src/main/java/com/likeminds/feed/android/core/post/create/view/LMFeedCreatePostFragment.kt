package com.likeminds.feed.android.core.post.create.view

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.databinding.LmFeedFragmentCreatePostBinding
import com.likeminds.feed.android.core.post.create.model.LMFeedCreatePostExtras
import com.likeminds.feed.android.core.post.create.view.LMFeedCreatePostActivity.Companion.LM_FEED_CREATE_POST_EXTRAS
import com.likeminds.feed.android.core.post.create.viewmodel.LMFeedCreatePostViewModel
import com.likeminds.feed.android.core.topics.model.LMFeedTopicViewData
import com.likeminds.feed.android.core.topicselection.model.LMFeedTopicSelectionExtras
import com.likeminds.feed.android.core.topicselection.model.LMFeedTopicSelectionResultExtras
import com.likeminds.feed.android.core.topicselection.view.LMFeedTopicSelectionActivity
import com.likeminds.feed.android.core.ui.base.views.LMFeedChipGroup
import com.likeminds.feed.android.core.ui.widgets.headerview.view.LMFeedHeaderView
import com.likeminds.feed.android.core.ui.widgets.post.postheaderview.view.LMFeedPostHeaderView
import com.likeminds.feed.android.core.universalfeed.model.LMFeedUserViewData
import com.likeminds.feed.android.core.universalfeed.util.LMFeedPostBinderUtils
import com.likeminds.feed.android.core.utils.LMFeedExtrasUtil
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer
import com.likeminds.feed.android.core.utils.LMFeedViewUtils
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.show
import com.likeminds.feed.android.core.utils.coroutine.observeInLifecycle
import com.likeminds.feed.android.core.utils.emptyExtrasException
import kotlinx.coroutines.flow.onEach

class LMFeedCreatePostFragment : Fragment() {
    private lateinit var binding: LmFeedFragmentCreatePostBinding
    private lateinit var lmFeedCreatePostExtras: LMFeedCreatePostExtras

    private val createPostViewModel: LMFeedCreatePostViewModel by viewModels()

    private val selectedTopic by lazy {
        ArrayList<LMFeedTopicViewData>()
    }

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
            customizeAuthorView(authorView)
            customizeTopicsGroup(topicsGroup)
        }

        return binding.root
    }

    protected open fun customizeCreatePostHeaderView(headerViewCreatePost: LMFeedHeaderView) {
        headerViewCreatePost.apply {
            setStyle(LMFeedStyleTransformer.createPostFragmentViewStyle.headerViewStyle)

            setTitleText("Create %s")
            setSubmitText("Create")
            setSubmitButtonEnabled(false)
        }
    }

    protected open fun customizeAuthorView(authorView: LMFeedPostHeaderView) {
        authorView.apply {
            setStyle(LMFeedStyleTransformer.createPostFragmentViewStyle.authorViewStyle)
        }
    }

    protected open fun customizeTopicsGroup(topicsGroup: LMFeedChipGroup) {
        topicsGroup.apply {
            LMFeedPostBinderUtils.customizePostTopicsGroup(this)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchInitialData()
        observeData()
    }

    private fun fetchInitialData() {
        createPostViewModel.getLoggedInUser()
        createPostViewModel.getAllTopics()
    }

    private fun observeData() {
        createPostViewModel.loggedInUser.observe(viewLifecycleOwner) { user ->
            initAuthorFrame(user)
        }

        createPostViewModel.showTopicFilter.observe(viewLifecycleOwner) { toShow ->
            if (toShow) {
                handleTopicSelectionView(true)
                initTopicSelectionView()
            } else {
                handleTopicSelectionView(false)
            }
        }

        createPostViewModel.errorEventFlow.onEach { response ->
            when (response) {
                is LMFeedCreatePostViewModel.ErrorMessageEvent.GetLoggedInUser -> {
                    LMFeedViewUtils.showErrorMessageToast(requireContext(), response.errorMessage)
                }

                is LMFeedCreatePostViewModel.ErrorMessageEvent.GetTopic -> {
                    LMFeedViewUtils.showErrorMessageToast(requireContext(), response.errorMessage)
                }
            }
        }.observeInLifecycle(viewLifecycleOwner)
    }

    //hide/show topics related views
    private fun handleTopicSelectionView(showView: Boolean) {
        binding.apply {
            topicsGroup.isVisible = showView
            topicSeparator.isVisible = showView
        }
    }

    //init initial topic selection view with "Select Topic Chip"
    private fun initTopicSelectionView() {
        binding.topicsGroup.apply {
            removeAllChips()
            addChip(
                getString(R.string.lm_feed_select_topics),
                LMFeedStyleTransformer.createPostFragmentViewStyle.selectTopicsChipStyle
            ) {
                val extras = LMFeedTopicSelectionExtras.Builder()
                    .showAllTopicFilter(false)
                    .showEnabledTopicOnly(true)
                    .build()
                val intent = LMFeedTopicSelectionActivity.getIntent(context, extras)

                topicSelectionLauncher.launch(intent)
            }
        }
    }

    //start activity -> Topic Selection and check for result with selected topics
    private val topicSelectionLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val bundle = result.data?.extras
                val resultExtras = LMFeedExtrasUtil.getParcelable(
                    bundle,
                    LMFeedTopicSelectionActivity.LM_FEED_TOPIC_SELECTION_RESULT_EXTRAS,
                    LMFeedTopicSelectionResultExtras::class.java
                ) ?: return@registerForActivityResult

                val selectedTopics = resultExtras.selectedTopics
                if (selectedTopics.isNotEmpty()) {
                    addTopicsToGroup(selectedTopics)
                }
            }
        }

    //add selected topics to group and add edit chip as well in the end
    private fun addTopicsToGroup(newSelectedTopics: List<LMFeedTopicViewData>) {
        this.selectedTopic.apply {
            clear()
            addAll(newSelectedTopics)
        }
        binding.topicsGroup.apply {
            //clear view
            removeAllChips()

            //add topics to group
            newSelectedTopics.forEach { topic ->
                addChip(topic.name, LMFeedStyleTransformer.postViewStyle.postTopicChipsStyle)
            }

            // add edit chip at end
            addChip(chipStyle = LMFeedStyleTransformer.createPostFragmentViewStyle.editChipStyle) {
                val extras = LMFeedTopicSelectionExtras.Builder()
                    .showAllTopicFilter(false)
                    .selectedTopics(selectedTopic)
                    .showEnabledTopicOnly(true)
                    .build()

                val intent = LMFeedTopicSelectionActivity.getIntent(context, extras)
                topicSelectionLauncher.launch(intent)
            }
        }
    }

    //set logged in user data to post header frame
    private fun initAuthorFrame(user: LMFeedUserViewData) {
        binding.nestedScrollCreatePost.show()
        binding.authorView.apply {
            setAuthorImage(user)
            setAuthorName(user.name)
        }
    }
}