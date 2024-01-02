package com.likeminds.feed.android.integration

import com.likeminds.feed.android.ui.theme.LMFeedTheme
import com.likeminds.feed.android.ui.theme.model.LMFeedSetThemeRequest

object LMFeedIntegration {

    private var apiKey: String? = null

    fun setup(apiKey: String, lmFeedTheme: LMFeedSetThemeRequest) {
        this.apiKey = apiKey
        LMFeedTheme.setTheme(lmFeedTheme)
    }
}