package com.likeminds.feed.android.core.utils.base

import android.os.Bundle
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.likeminds.feed.android.core.utils.base.model.LMFeedViewType

abstract class LMFeedViewDataBinder<V : ViewDataBinding, T : LMFeedBaseViewType> {

    @get:LMFeedViewType
    abstract val viewType: Int

    abstract fun createBinder(parent: ViewGroup): V

    abstract fun bindData(binding: V, data: T, position: Int)

    fun bindData(binding: V, data: Bundle, position: Int) {
        //This function can be called in case to handle inflation of data dependent on `data`
    }
}