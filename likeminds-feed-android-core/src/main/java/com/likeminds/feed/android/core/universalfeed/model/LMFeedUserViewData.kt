package com.likeminds.feed.android.core.universalfeed.model

import com.likeminds.feed.android.core.util.base.LMFeedBaseViewType

class LMFeedUserViewData private constructor(

) : LMFeedBaseViewType {

    override val viewType: Int
        get() = TODO("Not yet implemented")

    class Builder {

        fun build() = LMFeedUserViewData()
    }

    fun toBuilder(): Builder {
        return Builder()
    }
}