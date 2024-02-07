package com.likeminds.feed.android.core.util.base

import com.likeminds.feed.android.core.util.base.model.LMFeedViewType

interface LMFeedBaseViewType {

    @get:LMFeedViewType
    val viewType: Int
}