package com.likeminds.feed.android.core.post.detail.view

import android.content.Context
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import androidx.recyclerview.widget.*
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.post.detail.adapter.LMFeedReplyAdapter
import com.likeminds.feed.android.core.post.detail.adapter.LMFeedReplyAdapterListener
import com.likeminds.feed.android.core.utils.base.LMFeedBaseViewType

class LMFeedReplyListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    val linearLayoutManager: LinearLayoutManager

    private lateinit var replyAdapter: LMFeedReplyAdapter

    init {
        setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        layoutManager = linearLayoutManager

        if (itemAnimator is SimpleItemAnimator) {
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        }

        addItemDecoration((object : ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: State
            ) {
                super.getItemOffsets(outRect, view, parent, state)
                outRect.top = R.dimen.lm_feed_reply_item_top_margin
            }
        }))
    }

    fun setAdapter(listener: LMFeedReplyAdapterListener) {
        replyAdapter = LMFeedReplyAdapter(listener)
        adapter = replyAdapter
    }

    fun replaceReplies(
        replies: List<LMFeedBaseViewType>
    ) {
        replyAdapter.replace(replies)
    }
}