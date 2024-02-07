package com.likeminds.feedexample

import android.app.Application
import com.likeminds.feed.android.core.LMFeedCore
import com.likeminds.feed.android.ui.theme.model.LMFeedSetThemeRequest


class LMFeedExample : Application() {

    override fun onCreate() {
        super.onCreate()
        LMFeedCore.setup(
            "4f881a74-8d0b-4c73-9f60-3d2370216392",
            LMFeedSetThemeRequest.Builder()
                .fontResource(R.font.roboto)
                .build()
        )
    }
}