package com.likeminds.feedexample

import android.app.Application
import android.util.Log
import com.likeminds.feed.android.core.LMFeedCore
import com.likeminds.feed.android.core.LMFeedCoreCallback
import kotlinx.coroutines.runBlocking


class LMFeedExample : Application(), LMFeedCoreCallback {

    override fun onCreate() {
        super.onCreate()

        LMFeedCore.setup(
            application = this,
            domain = "https://www.examplefeed.com",
            lmFeedCoreCallback = this
        )
    }

    override fun onAccessTokenExpiredAndRefreshed(accessToken: String, refreshToken: String) {
        Log.d(
            "PUI", """
            Example Layer Callback -> onAccessTokenExpiredAndRefreshed
            accessToken: $accessToken
            refreshToken: $refreshToken
        """.trimIndent()
        )
    }

    override fun onRefreshTokenExpired(): Pair<String?, String?> {
        return runBlocking {
            Log.d(
                "PUI", """
                Example Layer Callback -> onRefreshTokenExpired
            """.trimIndent()
            )

            val task = GetTokensTask()
            val tokens = task.getTokens(applicationContext, false)
            Log.d("Example", "abc: $tokens")
            tokens
        }
    }
}