package com.likeminds.feed.android.core.poll.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.likeminds.feed.android.core.databinding.LmFeedPollResultsTabBinding
import com.likeminds.feed.android.core.poll.model.LMFeedPollResultsTabExtras
import com.likeminds.feed.android.core.poll.viewmodel.LMFeedPollResultsViewModel
import com.likeminds.feed.android.core.utils.LMFeedExtrasUtil
import com.likeminds.feed.android.core.utils.emptyExtrasException

class LMFeedPollResultsTabFragment : Fragment() {

    private lateinit var pollResultsTabExtras: LMFeedPollResultsTabExtras

    private val pollResultsViewModel: LMFeedPollResultsViewModel by viewModels()

    companion object {
        const val TAG = "LMFeedPollResultsTabFragment"
        const val LM_FEED_POLL_RESULT_TAB_EXTRA = "LM_FEED_POLL_RESULT_TAB_EXTRA"

        @JvmStatic
        fun addFragment(pollResultsTabExtras: LMFeedPollResultsTabExtras): LMFeedPollResultsTabFragment {
            val fragment = LMFeedPollResultsTabFragment()
            val bundle = Bundle()
            bundle.putParcelable(LM_FEED_POLL_RESULT_TAB_EXTRA, pollResultsTabExtras)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        receiveExtras()
    }

    private fun receiveExtras() {
        arguments?.let {
            pollResultsTabExtras = LMFeedExtrasUtil.getParcelable(
                it,
                LM_FEED_POLL_RESULT_TAB_EXTRA,
                LMFeedPollResultsTabExtras::class.java
            ) ?: throw emptyExtrasException(TAG)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return LmFeedPollResultsTabBinding.inflate(
            inflater,
            container,
            false
        ).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fetchData()
        observeData()
    }

    private fun fetchData() {
        pollResultsViewModel.getPollVotes(
            pollResultsTabExtras.pollId,
            pollResultsTabExtras.pollOptionId,
            1
        )
    }

    private fun observeData() {
        pollResultsViewModel.usersVotedList.observe(viewLifecycleOwner) {
            // TODO: inflate list here
        }
    }
}