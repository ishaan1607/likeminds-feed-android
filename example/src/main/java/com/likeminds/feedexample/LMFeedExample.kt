package com.likeminds.feedexample

import android.app.Application
import android.util.Log
import com.likeminds.feed.android.core.LMFeedCore
import com.likeminds.feed.android.core.LMFeedCoreCallback
import com.likeminds.feedexample.auth.util.AuthPreferences
import com.likeminds.likemindsfeed.LMFeedClient
import com.likeminds.likemindsfeed.user.model.InitiateUserRequest
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

            val mClient = LMFeedClient.getInstance()

            val authPreferences = AuthPreferences(applicationContext)

            val user = mClient.getLoggedInUserWithRights().data?.user
            if (user != null) {
                Log.d("PUI", "User not null")
                val initiateUserRequest = InitiateUserRequest.Builder()
                    .apiKey(authPreferences.getApiKey())
                    .userName(user.name)
                    .uuid(user.sdkClientInfo.uuid)
                    .build()
                val response = mClient.initiateUser(initiateUserRequest)

                if (response.success) {
                    Log.d("PUI", "Initiate User Success")
                    val accessToken = response.data?.accessToken ?: ""
                    val refreshToken = response.data?.refreshToken ?: ""
                    Pair(accessToken, refreshToken)
                } else {
                    Log.d("PUI", "Initiate User Failed")
                    Pair("", "")
                }
            } else {
                Log.d("PUI", "User null")
                Pair("", "")
            }

            Pair("", "")
        }
    }
}