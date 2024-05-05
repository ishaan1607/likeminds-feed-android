package com.likeminds.feed.android.core.poll.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.likeminds.feed.android.core.poll.model.LMFeedPollResultsExtras
import com.likeminds.feed.android.core.poll.model.LMFeedPollViewData
import com.likeminds.feed.android.core.poll.view.LMFeedPollResultsTabFragment

class LMFeedPostResultsTabAdapter(
    val fragment: Fragment,
    private val pollViewData: LMFeedPollViewData
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return pollViewData.options.size
    }

    override fun createFragment(position: Int): Fragment {
        val pollResultsExtras = LMFeedPollResultsExtras.Builder()
            .build()

        return LMFeedPollResultsTabFragment.addFragment(pollResultsExtras)
    }
}