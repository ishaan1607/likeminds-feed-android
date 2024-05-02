package com.likeminds.feed.android.core.poll.view

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.databinding.LmFeedFragmentPollResultsBinding
import com.likeminds.feed.android.core.ui.widgets.headerview.view.LMFeedHeaderView
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer

open class LMFeedPollResultsFragment : Fragment() {

    private lateinit var binding: LmFeedFragmentPollResultsBinding

    companion object {
        const val TAG = "LMFeedPollResultsFragment"

        fun getInstance(): LMFeedPollResultsFragment {
            return LMFeedPollResultsFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LmFeedFragmentPollResultsBinding.inflate(inflater, container, false)

        binding.apply {
            customizePollResultsHeaderView(headerViewPollResults)

            //set background color
            val backgroundColor =
                LMFeedStyleTransformer.pollResultsFragmentViewStyle.backgroundColor
            backgroundColor?.let { color ->
                root.setBackgroundColor(
                    ContextCompat.getColor(
                        requireContext(),
                        color
                    )
                )
            }
        }
        return binding.root
    }

    //customizes the header view of the poll results fragment with the header style set for the poll results fragment
    protected open fun customizePollResultsHeaderView(headerViewPollResults: LMFeedHeaderView) {
        headerViewPollResults.apply {
            setStyle(LMFeedStyleTransformer.pollResultsFragmentViewStyle.headerViewStyle)

            setTitleText(getString(R.string.lm_feed_poll_results))
        }
    }
}