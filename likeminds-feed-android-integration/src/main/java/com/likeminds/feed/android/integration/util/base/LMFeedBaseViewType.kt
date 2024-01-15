package com.likeminds.feed.android.integration.util.base

import com.likeminds.feed.android.integration.util.base.model.LMFeedViewType

interface LMFeedBaseViewType {

    @get:LMFeedViewType
    val viewType: Int
}