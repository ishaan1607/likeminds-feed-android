package com.likeminds.feed.android.core.utils.base

import com.likeminds.feed.android.core.utils.base.model.LMFeedViewType

interface LMFeedBaseViewType {

    @get:LMFeedViewType
    val viewType: Int
}