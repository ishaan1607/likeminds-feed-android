package com.likeminds.feed.android.core

import android.app.Application
import android.content.Context
import com.likeminds.feed.android.core.ui.theme.LMFeedTheme
import com.likeminds.feed.android.core.ui.theme.model.LMFeedSetThemeRequest
import com.likeminds.feed.android.core.utils.user.LMFeedConnectUser
import com.likeminds.feed.android.core.utils.user.LMFeedUserPreferences
import com.likeminds.likemindsfeed.initiateUser.model.InitiateUserResponse

object LMFeedCore {

    private var apiKey: String? = null

    fun setup(
        application: Application,
        apiKey: String,
        lmFeedTheme: LMFeedSetThemeRequest,
        lmFeedCoreCallback: LMFeedCoreCallback? = null
    ) {
        this.apiKey = apiKey
        LMFeedTheme.setTheme(lmFeedTheme)

        val coreApplication = LMFeedCoreApplication.getInstance()
        coreApplication.initCoreApplication(application, lmFeedCoreCallback)
    }

    fun showFeed(
        context: Context,
        userName: String,
        uuid: String?,
        deviceId: String,
        success: ((InitiateUserResponse?) -> Unit)? = null,
        error: ((String?) -> Unit)? = null
    ) {
        //Call initiate API
        initiateUser(context, userName, uuid, deviceId)

        //Inflate Feed
    }

    fun initiateUser(
        context: Context,
        userName: String,
        uuid: String?,
        deviceId: String,
        success: ((InitiateUserResponse?) -> Unit)? = null,
        error: ((String?) -> Unit)? = null
    ) {
        //validate API key
        if (apiKey == null) {
            throw IllegalAccessException("LMFeedCore.setup() is not called. Please call setup() to access this function")
        }

        val lmFeedConnectUser = LMFeedConnectUser.Builder()
            .userName(userName)
            .uuid(uuid)
            .deviceId(deviceId)
            .apiKey(apiKey ?: "")
            .build()

        lmFeedConnectUser.initiateUser(
            success = { response ->
                saveUserPreferences(
                    context,
                    apiKey,
                    response?.user?.name,
                    response?.user?.sdkClientInfo?.uuid,
                )
                success?.invoke(response)
            },
            error = { errorMessage ->
                error?.invoke(errorMessage)
            }
        )
    }

    private fun saveUserPreferences(
        context: Context,
        apiKey: String?,
        userName: String?,
        uuid: String?
    ) {
        //save details to pref
        val userPreferences = LMFeedUserPreferences(context)
        userPreferences.apply {
            saveApiKey(apiKey ?: "")
            saveUserName(userName ?: "")
            saveUUID(uuid ?: "")
        }
    }
}