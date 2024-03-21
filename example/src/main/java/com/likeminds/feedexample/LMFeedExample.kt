package com.likeminds.feedexample

import android.app.Application
import com.likeminds.feed.android.core.LMFeedCore
import com.likeminds.feed.android.core.ui.theme.model.LMFeedSetThemeRequest


class LMFeedExample : Application() {

    override fun onCreate() {
        super.onCreate()
        LMFeedCore.setup(
            this,
            "d66cfee8-070a-47da-b705-d98cf812630f",
            LMFeedSetThemeRequest.Builder()
                .fontResource(R.font.roboto)
                .build()
        )
    }
}