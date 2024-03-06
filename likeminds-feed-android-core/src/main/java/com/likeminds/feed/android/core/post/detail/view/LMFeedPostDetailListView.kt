package com.likeminds.feed.android.core.post.detail.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.*
import com.likeminds.feed.android.core.post.detail.adapter.*
import com.likeminds.feed.android.core.post.detail.model.LMFeedCommentViewData
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalFeedAdapterListener
import com.likeminds.feed.android.core.utils.LMFeedEndlessRecyclerViewScrollListener
import com.likeminds.feed.android.core.utils.LMFeedViewUtils
import com.likeminds.feed.android.core.utils.base.LMFeedBaseViewType

class LMFeedPostDetailListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    val linearLayoutManager: LinearLayoutManager

    private lateinit var postDetailAdapter: LMFeedPostDetailAdapter
    private lateinit var paginationScrollListener: LMFeedEndlessRecyclerViewScrollListener

    init {
        setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(context)
        layoutManager = linearLayoutManager

        if (itemAnimator is SimpleItemAnimator) {
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        }
    }

    fun setAdapter(
        universalFeedAdapterListener: LMFeedUniversalFeedAdapterListener,
        postDetailAdapterListener: LMFeedPostDetailAdapterListener,
        replyAdapterListener: LMFeedReplyAdapterListener
    ) {
        postDetailAdapter = LMFeedPostDetailAdapter(
            universalFeedAdapterListener,
            postDetailAdapterListener,
            replyAdapterListener
        )
        adapter = postDetailAdapter
    }

    fun setPaginationScrollListener(scrollListener: LMFeedEndlessRecyclerViewScrollListener) {
        paginationScrollListener = scrollListener
        addOnScrollListener(scrollListener)
    }

    fun resetScrollListenerData() {
        if (::paginationScrollListener.isInitialized) {
            paginationScrollListener.resetData()
        }
    }

    fun addItem(position: Int, item: LMFeedBaseViewType) {
        postDetailAdapter.add(position, item)
    }

    fun updateItem(position: Int, updatedItem: LMFeedBaseViewType) {
        postDetailAdapter.update(position, updatedItem)
    }

    fun removeItem(position: Int) {
        postDetailAdapter.removeIndex(position)
    }

    fun getItem(position: Int): LMFeedBaseViewType? {
        return postDetailAdapter[position]
    }

    /**
     * Adapter Util Block
     **/

    //get index and post from the adapter using commentId
    fun getIndexAndCommentFromAdapter(commentId: String): Pair<Int, LMFeedCommentViewData>? {
        val index = postDetailAdapter.items().indexOfFirst {
            (it is LMFeedCommentViewData) && (it.id == commentId)
        }

        if (index == -1) {
            return null
        }

        val comment = getCommentFromAdapter(index) ?: return null

        return Pair(index, comment)
    }

    private fun getCommentFromAdapter(position: Int): LMFeedCommentViewData? {
        return postDetailAdapter.items()[position] as? LMFeedCommentViewData
    }

    //get index and reply from the parentComment using replyId
    fun getIndexAndReplyFromComment(
        parentComment: LMFeedCommentViewData,
        replyId: String
    ): Pair<Int, LMFeedCommentViewData>? {
        val index = parentComment.replies.indexOfFirst {
            it.id == replyId
        }

        if (index == -1) {
            return null
        }

        val reply = parentComment.replies[index]

        return Pair(index, reply)
    }

    /**
     * Scroll to a position with offset from the top header
     * @param position Index of the item to scroll to
     * @param offset value with which to scroll
     */
    fun scrollToPositionWithOffset(position: Int, offset: Int) {
        post {
            val px = (LMFeedViewUtils.dpToPx(offset) * 1.5).toInt()
            (layoutManager as? LinearLayoutManager)?.scrollToPositionWithOffset(
                position,
                px
            )
        }
    }
}