package com.likeminds.feed.android.core.poll.create.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.databinding.LmFeedFragmentCreatePollBinding
import com.likeminds.feed.android.core.poll.create.viewmodel.LMFeedCreatePollViewModel
import com.likeminds.feed.android.core.poll.result.model.LMFeedPollViewData
import com.likeminds.feed.android.core.ui.widgets.headerview.view.LMFeedHeaderView
import com.likeminds.feed.android.core.ui.widgets.post.postheaderview.view.LMFeedPostHeaderView
import com.likeminds.feed.android.core.utils.LMFeedExtrasUtil
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.show
import com.likeminds.feed.android.core.utils.user.LMFeedUserViewData

open class LMFeedCreatePollFragment : Fragment() {

    private lateinit var binding: LmFeedFragmentCreatePollBinding
    private var poll: LMFeedPollViewData? = null

    private val viewModel: LMFeedCreatePollViewModel by viewModels()

    companion object {
        const val TAG = "LMFeedCreatePollFragment"
        const val LM_FEED_CREATE_POLL_FRAGMENT_EXTRAS = "LM_FEED_CREATE_POLL_FRAGMENT_EXTRAS"

        @JvmStatic
        fun getInstance(poll: LMFeedPollViewData?): LMFeedCreatePollFragment {
            val fragment = LMFeedCreatePollFragment()
            val args = Bundle()
            args.putParcelable(LM_FEED_CREATE_POLL_FRAGMENT_EXTRAS, poll)
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        receiveExtras()
    }

    private fun receiveExtras() {
        if (arguments == null || arguments?.containsKey(LM_FEED_CREATE_POLL_FRAGMENT_EXTRAS) == false) {
            requireActivity().supportFragmentManager.popBackStack()
            return
        } else {
            poll = LMFeedExtrasUtil.getParcelable(
                arguments,
                LM_FEED_CREATE_POLL_FRAGMENT_EXTRAS,
                LMFeedPollViewData::class.java
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LmFeedFragmentCreatePollBinding.inflate(inflater, container, false)

        binding.apply {
            customizeCreatePollHeader(headerViewCreatePoll)
            customizeAuthorView(authorView)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        fetchInitialData()
        observeData()
    }

    //customize the header view of the fragment
    protected open fun customizeCreatePollHeader(headerView: LMFeedHeaderView) {
        headerView.apply {
            setStyle(LMFeedStyleTransformer.createPollFragmentViewStyle.headerViewStyle)

            setTitleText(getString(R.string.lm_feed_new_poll))
            setSubmitText(getString(R.string.lm_feed_done))
            setSubmitButtonEnabled(false)
        }
    }

    //customize the author view of the fragment
    protected open fun customizeAuthorView(authorView: LMFeedPostHeaderView){
        authorView.apply {
            setStyle(LMFeedStyleTransformer.createPollFragmentViewStyle.authorViewStyle)
        }
    }

    //attaches the listeners
    private fun initListeners() {
        binding.apply {
            headerViewCreatePoll.setNavigationIconClickListener {
                onNavigationIconClicked()
            }
        }
    }

    //fetches the initial data
    private fun fetchInitialData() {
        viewModel.getLoggedInUser()
    }

    //observes the response data
    private fun observeData(){
        viewModel.loggedInUser.observe(viewLifecycleOwner){user ->
            initAuthorView(user)
        }
    }

    //initializes the author view
    private fun initAuthorView(user: LMFeedUserViewData) {
        binding.apply {
            nestedScrollCreatePoll.show()
            authorView.apply {
                setAuthorImage(user)
                setAuthorName(user.name)
            }
        }
    }

    //customize the navigation icon
    protected open fun onNavigationIconClicked() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }
}