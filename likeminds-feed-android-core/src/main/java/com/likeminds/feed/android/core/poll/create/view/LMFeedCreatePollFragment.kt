package com.likeminds.feed.android.core.poll.create.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.databinding.LmFeedFragmentCreatePollBinding
import com.likeminds.feed.android.core.poll.result.model.LMFeedPollViewData
import com.likeminds.feed.android.core.ui.widgets.headerview.view.LMFeedHeaderView
import com.likeminds.feed.android.core.utils.LMFeedExtrasUtil
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer

open class LMFeedCreatePollFragment : Fragment() {

    private lateinit var binding: LmFeedFragmentCreatePollBinding
    private var poll: LMFeedPollViewData? = null

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
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
    }


    protected open fun customizeCreatePollHeader(headerView: LMFeedHeaderView) {
        headerView.apply {
            setStyle(LMFeedStyleTransformer.createPollFragmentViewStyle.headerViewStyle)

            setTitleText(getString(R.string.lm_feed_new_poll))
            setSubmitText(getString(R.string.lm_feed_done))
            setSubmitButtonEnabled(false)
        }
    }

    private fun initListeners() {
        binding.apply {
            headerViewCreatePoll.setNavigationIconClickListener {
                onNavigationIconClicked()
            }
        }
    }

    //customize the navigation icon
    protected open fun onNavigationIconClicked() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }
}