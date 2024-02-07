package com.likeminds.feed.android.core.universalfeed.model

import com.likeminds.feed.android.core.util.base.LMFeedBaseViewType

class LMFeedMediaViewData private constructor(

) : LMFeedBaseViewType {

    override val viewType: Int
        get() = TODO("Not yet implemented")

    class Builder {

        fun build() = LMFeedMediaViewData()
    }

    fun toBuilder(): Builder {
        return Builder()
    }
}