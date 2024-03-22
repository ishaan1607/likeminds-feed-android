package com.likeminds.feed.android.core.post.detail.adapter.databinders

import android.view.LayoutInflater
import android.view.ViewGroup
import com.likeminds.feed.android.core.databinding.LmFeedItemNoCommentsFoundBinding
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer
import com.likeminds.feed.android.core.utils.base.LMFeedBaseViewType
import com.likeminds.feed.android.core.utils.base.LMFeedViewDataBinder
import com.likeminds.feed.android.core.utils.base.model.ITEM_NO_COMMENTS_FOUND

class LMFeedItemNoCommentsFoundViewDataBinder :
    LMFeedViewDataBinder<LmFeedItemNoCommentsFoundBinding, LMFeedBaseViewType>() {

    override val viewType: Int
        get() = ITEM_NO_COMMENTS_FOUND

    override fun createBinder(parent: ViewGroup): LmFeedItemNoCommentsFoundBinding {
        val binding = LmFeedItemNoCommentsFoundBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        val noCommentsFoundViewStyle =
            LMFeedStyleTransformer.postDetailFragmentViewStyle.noCommentsFoundViewStyle

        binding.layoutNoComments.setStyle(noCommentsFoundViewStyle)

        return binding
    }

    override fun bindData(
        binding: LmFeedItemNoCommentsFoundBinding,
        data: LMFeedBaseViewType,
        position: Int
    ) {
        //showing static data
    }
}