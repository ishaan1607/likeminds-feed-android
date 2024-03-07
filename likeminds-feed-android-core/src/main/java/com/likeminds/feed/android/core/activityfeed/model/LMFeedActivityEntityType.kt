package com.likeminds.feed.android.core.activityfeed.model

import androidx.annotation.IntDef

const val POST = 0
const val COMMENT = 1
const val USER = 2

@IntDef(
    POST,
    COMMENT,
    USER
)

@Retention
annotation class LMFeedActivityEntityType