package com.likeminds.feed.android.core.post.detail.adapter

import com.likeminds.feed.android.core.utils.base.*

class LMFeedPostDetailAdapter(

) : LMFeedBaseRecyclerAdapter<LMFeedBaseViewType>() {

    init {
        initViewDataBinders()
    }

    override fun getSupportedViewDataBinder(): MutableList<LMFeedViewDataBinder<*, *>> {
        val viewDataBinders = ArrayList<LMFeedViewDataBinder<*, *>>(8)

        return viewDataBinders
    }
}

interface LMFeedPostDetailAdapterListener {

    fun onCommentContentSeeMoreClicked(

    ) {
        //triggered when the user clicks on "See More" on comment content
    }

    fun onCommentLiked(

    ) {
        //triggered when the user likes a comment
    }
}