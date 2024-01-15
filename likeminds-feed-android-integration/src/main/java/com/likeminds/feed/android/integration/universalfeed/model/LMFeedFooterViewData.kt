package com.likeminds.feed.android.integration.universalfeed.model

import com.likeminds.feed.android.integration.util.base.LMFeedBaseViewType

class LMFeedFooterViewData private constructor(

) : LMFeedBaseViewType {

    override val viewType: Int
        get() = TODO("Not yet implemented")

    class Builder {

        fun build() = LMFeedFooterViewData()
    }

    fun toBuilder(): LMFeedMediaViewData.Builder {
        return LMFeedMediaViewData.Builder()
    }
}