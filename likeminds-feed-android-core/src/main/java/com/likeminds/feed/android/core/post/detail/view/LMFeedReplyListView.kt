package com.likeminds.feed.android.core.post.detail.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.*
import com.likeminds.feed.android.core.post.detail.adapter.LMFeedReplyAdapter
import com.likeminds.feed.android.core.post.detail.adapter.LMFeedReplyAdapterListener
import com.likeminds.feed.android.core.utils.base.LMFeedBaseViewType

class LMFeedReplyListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private val linearLayoutManager: LinearLayoutManager

    private lateinit var replyAdapter: LMFeedReplyAdapter

    init {
        setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        layoutManager = linearLayoutManager

        if (itemAnimator is SimpleItemAnimator) {
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        }
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