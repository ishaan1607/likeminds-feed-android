package com.likeminds.feed.android.core.post.detail.view

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.databinding.LmFeedFragmentPostDetailBinding
import com.likeminds.feed.android.core.ui.widgets.commentcomposer.view.LMFeedCommentComposerView
import com.likeminds.feed.android.core.ui.widgets.headerview.view.LMFeedHeaderView
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer

open class LMFeedPostDetailFragment : Fragment() {

    private lateinit var binding: LmFeedFragmentPostDetailBinding
    private lateinit var mSwipeRefreshLayout: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LmFeedFragmentPostDetailBinding.inflate(layoutInflater)
        customizePostDetailHeaderView(binding.headerViewPostDetail)
        customizeCommentComposer(binding.commentComposer)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    protected open fun customizePostDetailHeaderView(headerViewPostDetail: LMFeedHeaderView) {
        headerViewPostDetail.apply {
            setStyle(LMFeedStyleTransformer.postDetailFragmentViewStyle.headerViewStyle)

            setTitleText(getString(R.string.lm_feed_feed))
        }
    }

    protected open fun customizeCommentComposer(commentComposer: LMFeedCommentComposerView) {
        commentComposer.setStyle(LMFeedStyleTransformer.postDetailFragmentViewStyle.commentComposerStyle)
    }
}