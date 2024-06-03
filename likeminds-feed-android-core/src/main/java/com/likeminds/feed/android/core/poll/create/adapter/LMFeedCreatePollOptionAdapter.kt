package com.likeminds.feed.android.core.poll.create.adapter

import com.likeminds.feed.android.core.databinding.LmFeedItemCreatePollOptionBinding
import com.likeminds.feed.android.core.poll.create.adapter.databinding.LMFeedItemCreatePollOptionViewDataBinder
import com.likeminds.feed.android.core.poll.create.model.LMFeedCreatePollOptionViewData
import com.likeminds.feed.android.core.utils.base.*

class LMFeedCreatePollOptionAdapter(private val listener: LMFeedCreatePollOptionAdapterListener) :
    LMFeedBaseRecyclerAdapter<LMFeedBaseViewType>() {
    init {
        initViewDataBinders()
    }

    override fun getSupportedViewDataBinder(): MutableList<LMFeedViewDataBinder<*, *>> {
        val viewBinders = ArrayList<LMFeedViewDataBinder<*, *>>(1)

        val createPollOptionViewDataBinder = LMFeedItemCreatePollOptionViewDataBinder(listener)
        viewBinders.add(createPollOptionViewDataBinder)

        return viewBinders
    }
}

interface LMFeedCreatePollOptionAdapterListener {

    fun onPollOptionRemoved(createPollOptionViewData: LMFeedCreatePollOptionViewData){
        //on poll option removed
    }

    
    fun onPollOptionBinded(position: Int, binding: LmFeedItemCreatePollOptionBinding){
        //on poll option binded
    }

    fun onPollOptionFilled(){
        //when user fill the option in input box
    }
}