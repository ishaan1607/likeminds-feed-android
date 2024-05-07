package com.likeminds.feed.android.core.poll.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.likeminds.feed.android.core.poll.model.LMFeedPollOptionViewData
import com.likeminds.feed.android.core.poll.model.LMFeedPollResultsTabExtras
import com.likeminds.feed.android.core.poll.view.LMFeedPollResultsTabFragment

class LMFeedPollResultsTabAdapter(
    fragment: Fragment,
    private val pollId: String,
    private val pollOptions: List<LMFeedPollOptionViewData>
) : FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return pollOptions.size
    }

    override fun createFragment(position: Int): Fragment {
        val pollResultsTabExtras = LMFeedPollResultsTabExtras.Builder()
            .pollId(pollId)
            .pollOptionId(pollOptions[position].id)
            .build()

        return LMFeedPollResultsTabFragment.addFragment(pollResultsTabExtras)
    }
}