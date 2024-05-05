package com.likeminds.feed.android.core.poll.view

import android.os.Bundle
import androidx.fragment.app.Fragment

class LMFeedPollResultsTabFragment : Fragment() {

    companion object {
        const val POLL_RESULT_TAB_EXTRA = "POLL_RESULT_TAB_EXTRA"

        @JvmStatic
        fun addFragment(pollData: PollResultTabExtra): LMFeedPollResultsTabFragment {
            val fragment = LMFeedPollResultsTabFragment()
            val bundle = Bundle()
            bundle.putParcelable(POLL_RESULT_TAB_EXTRA, pollData)
            fragment.arguments = bundle
            return fragment
        }
    }

}