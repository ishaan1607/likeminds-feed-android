package com.likeminds.feed.android.core.poll.view

import android.graphics.Typeface
import android.os.Bundle
import android.view.*
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.databinding.LmFeedFragmentPollResultsBinding
import com.likeminds.feed.android.core.poll.adapter.LMFeedPollResultsTabAdapter
import com.likeminds.feed.android.core.poll.model.LMFeedPollResultsExtras
import com.likeminds.feed.android.core.poll.view.LMFeedPollResultsActivity.Companion.LM_FEED_POLL_RESULTS_EXTRAS
import com.likeminds.feed.android.core.poll.viewmodel.LMFeedPollResultsViewModel
import com.likeminds.feed.android.core.ui.widgets.headerview.view.LMFeedHeaderView
import com.likeminds.feed.android.core.utils.*

open class LMFeedPollResultsFragment : Fragment() {

    private lateinit var binding: LmFeedFragmentPollResultsBinding
    private lateinit var pollResultsExtras: LMFeedPollResultsExtras

    private lateinit var pollResultsTabAdapter: LMFeedPollResultsTabAdapter

    companion object {
        const val TAG = "LMFeedPollResultsFragment"

        fun getInstance(pollResultsExtras: LMFeedPollResultsExtras): LMFeedPollResultsFragment {
            val pollResultsFragment = LMFeedPollResultsFragment()
            val bundle = Bundle()
            bundle.putParcelable(LM_FEED_POLL_RESULTS_EXTRAS, pollResultsExtras)
            pollResultsFragment.arguments = bundle
            return pollResultsFragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        receiveExtras()
    }

    private fun receiveExtras() {
        arguments?.let {
            pollResultsExtras = LMFeedExtrasUtil.getParcelable(
                it,
                LM_FEED_POLL_RESULTS_EXTRAS,
                LMFeedPollResultsExtras::class.java
            ) ?: throw emptyExtrasException(TAG)
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPollResultsTabLayout()
    }

    private fun initPollResultsTabLayout() {
        pollResultsTabAdapter = LMFeedPollResultsTabAdapter(
            this,
            pollResultsExtras.pollId,
            pollResultsExtras.pollOptions
        )

        binding.apply {
            viewPagerPollResults.adapter = pollResultsTabAdapter

            val screenWidth = LMFeedViewUtils.getDeviceDimension(requireContext()).first
            TabLayoutMediator(
                tabLayoutPollResults,
                viewPagerPollResults
            ) { tab, position ->
                val pollOption = pollResultsExtras.pollOptions[position]
                val tabView = LayoutPollResultTabBinding.inflate(layoutInflater)
                tabView.apply {
                    clLayout.maxWidth = (screenWidth * 0.48).toInt()
                    clLayout.minWidth = (screenWidth * 0.33).toInt()
                    pollCount = pollOption?.noVotes.toString()
                    pollText = pollOption?.text

                    tab.customView = root
                }
            }.attach()


            val tab = if (!pollResultsExtras.selectedPollOptionId.isNullOrEmpty()) {
                val selectedOptionPosition = pollResultsExtras.pollOptions.indexOfFirst {
                    it.id == pollResultsExtras.selectedPollOptionId
                }

                viewPagerPollResults.setCurrentItem(selectedOptionPosition, true)
                tabLayoutPollResults.setScrollPosition(
                    selectedOptionPosition,
                    0f,
                    true
                )
                tabLayoutPollResults.getTabAt(selectedOptionPosition)
            } else {
                tabLayoutPollResults.getTabAt(0)
            }

            tab?.select()
            val firstViewAtTab = tab?.customView?.findViewById<TextView>(R.id.tv_poll_count)
            firstViewAtTab?.apply {
                setTextColor(LMBranding.getButtonsColor())
                setTypeface(null, Typeface.BOLD)
            }

            tabLayoutPollResults.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    val view = tab?.customView?.findViewById<TextView>(R.id.tv_poll_count) ?: return
                    view.apply {
                        setTextColor(LMBranding.getButtonsColor())
                        setTypeface(null, Typeface.BOLD)
                    }

                    val pollData = pollInfoData.pollViewDataList?.get(tab.position)
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    val view = tab?.customView?.findViewById<TextView>(R.id.tv_poll_count) ?: return
                    view.apply {
                        setTextColor(ContextCompat.getColor(requireContext(), R.color.lm_chat_grey))
                        setTypeface(null, Typeface.NORMAL)
                    }
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    val view = tab?.customView?.findViewById<TextView>(R.id.tv_poll_count) ?: return
                    view.apply {
                        setTextColor(LMBranding.getButtonsColor())
                        setTypeface(null, Typeface.BOLD)
                    }
                }
            })
        }
    }
}