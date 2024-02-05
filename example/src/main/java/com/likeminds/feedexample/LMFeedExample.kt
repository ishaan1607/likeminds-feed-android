package com.likeminds.feedexample

import android.app.Application
import com.likeminds.feed.android.integration.LMFeedIntegration
import com.likeminds.feed.android.ui.theme.model.LMFeedSetThemeRequest


class LMFeedExample : Application() {

    override fun onCreate() {
        super.onCreate()
        LMFeedIntegration.setup(
            "4f881a74-8d0b-4c73-9f60-3d2370216392",
            LMFeedSetThemeRequest.Builder()
                .fontResource(R.font.lm_feed_core_roboto)
                .build()
        )
    }
}