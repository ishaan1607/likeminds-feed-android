package com.likeminds.feedexample

import android.app.Application
import com.likeminds.feed.android.core.LMFeedCore
import com.likeminds.feed.android.core.ui.theme.model.LMFeedSetThemeRequest


class LMFeedExample : Application() {

    override fun onCreate() {
        super.onCreate()
        LMFeedCore.setup(
            this,
            "6b51af13-ce28-444b-a571-53a3fb125444",
            LMFeedSetThemeRequest.Builder()
                .fontResource(R.font.roboto)
                .build()
        )
    }
}