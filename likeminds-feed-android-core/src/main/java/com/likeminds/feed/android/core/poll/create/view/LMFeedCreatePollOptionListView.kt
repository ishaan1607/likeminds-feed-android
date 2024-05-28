package com.likeminds.feed.android.core.poll.create.view

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.likeminds.feed.android.core.poll.create.adapter.LMFeedCreatePollOptionAdapter
import com.likeminds.feed.android.core.poll.create.adapter.LMFeedCreatePollOptionAdapterListener
import com.likeminds.feed.android.core.poll.create.model.LMFeedCreatePollOptionViewData

class LMFeedCreatePollOptionListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    private lateinit var mAdapter: LMFeedCreatePollOptionAdapter

    val itemCount: Int
        get() = mAdapter.itemCount

    init {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
    }

    //set adapter
    fun setAdapter(listener: LMFeedCreatePollOptionAdapterListener) {
        mAdapter = LMFeedCreatePollOptionAdapter(listener)
        adapter = mAdapter
    }

    //get all options
    fun getAllOptions(): List<LMFeedCreatePollOptionViewData> {
        return mAdapter.items().map {
            it as LMFeedCreatePollOptionViewData
        }
    }

    //replace the whole list in adapter
    fun replaceOptions(options: List<LMFeedCreatePollOptionViewData>) {
        mAdapter.replace(options)
    }

    //remove option by index
    fun removeOption(index: Int) {
        mAdapter.removeIndex(index)
    }

    //add option
    fun addOption(position: Int, option: LMFeedCreatePollOptionViewData) {
        mAdapter.add(position, option)
    }

    //update the cache size
    fun updatePollItemCacheSize() {
        this.setItemViewCacheSize(itemCount)
    }

}