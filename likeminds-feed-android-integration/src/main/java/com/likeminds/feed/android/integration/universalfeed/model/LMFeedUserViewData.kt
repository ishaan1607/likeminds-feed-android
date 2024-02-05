package com.likeminds.feed.android.integration.universalfeed.model

import com.likeminds.feed.android.integration.util.base.LMFeedBaseViewType

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