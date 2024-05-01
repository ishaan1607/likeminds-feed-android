package com.likeminds.feedexample

import android.app.Application
import com.likeminds.feed.android.core.LMFeedCore


class LMFeedExample : Application() {

    override fun onCreate() {
        super.onCreate()

        LMFeedCore.setup(
            application = this,
            "https://www.examplefeed.com"
        )
    }
}