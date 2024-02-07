package com.likeminds.feed.android.core

import com.likeminds.feed.android.ui.theme.LMFeedTheme
import com.likeminds.feed.android.ui.theme.model.LMFeedSetThemeRequest

object LMFeedCore {

    private var apiKey: String? = null

    fun setup(apiKey: String, lmFeedTheme: LMFeedSetThemeRequest) {
        this.apiKey = apiKey
        LMFeedTheme.setTheme(lmFeedTheme)
    }
}