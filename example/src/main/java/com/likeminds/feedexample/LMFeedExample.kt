package com.likeminds.feedexample

import android.app.Application
import com.likeminds.feed.android.core.LMFeedCore
import com.likeminds.feed.android.core.ui.theme.model.LMFeedSetThemeRequest


class LMFeedExample : Application() {

    override fun onCreate() {
        super.onCreate()

        LMFeedCore.setup(
            this,
            "https://www.examplefeed.com"
        )
    }
}