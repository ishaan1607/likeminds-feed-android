package com.likeminds.feed.android.core

import android.app.Application
import android.content.Context
import com.likeminds.feed.android.core.ui.theme.LMFeedTheme
import com.likeminds.feed.android.core.ui.theme.model.LMFeedSetThemeRequest
import com.likeminds.feed.android.core.utils.user.LMFeedConnectUser
import com.likeminds.feed.android.core.utils.user.LMFeedUserPreferences
import com.likeminds.likemindsfeed.user.model.InitiateUserResponse

object LMFeedCore {

    /**
     * Initial setup function for customers and blocker function
     * @param application: Instance of the application class
     * @param apiKey: API Key of the Customer, generated from [here](https://dashboard.likeminds.community/auth)
     * @param lmFeedTheme: Object of [LMFeedTheme] to add your customizable theme in whole feed
     * @param lmFeedCoreCallback: Instance of [LMFeedCoreCallback] so that we can share data/events to customers code
     */
    fun setup(
        application: Application,
        domain: String? = null,
        lmFeedTheme: LMFeedSetThemeRequest? = null,
        lmFeedCoreCallback: LMFeedCoreCallback? = null
    ) {
        LMFeedTheme.setTheme(lmFeedTheme)

        val coreApplication = LMFeedCoreApplication.getInstance()
        coreApplication.initCoreApplication(application, lmFeedCoreCallback, domain)
    }

    fun showFeed(
        context: Context,
        apiKey: String,
        userName: String,
        uuid: String?,
        deviceId: String,
        enablePushNotifications: Boolean,
        success: ((InitiateUserResponse?) -> Unit)? = null,
        error: ((String?) -> Unit)? = null
    ) {
        //Call initiate API
        initiateUser(
            context,
            apiKey,
            userName,
            uuid,
            deviceId,
            enablePushNotifications,
            success,
            error
        )
    }

    /**
     * instantiate the user in LikeMinds Code
     * @param userName: Name of the user
     * @param uuid: Unique id of the user in your db
     * @param deviceId: Device id of the user
     * @param success: Callback when user is successfully instantiated
     * @param error: Callback for any error is the process.
     *
     * @throws IllegalAccessException when [setup] function is not called before
     */
    fun initiateUser(
        context: Context,
        apiKey: String,
        userName: String,
        uuid: String?,
        deviceId: String,
        enablePushNotifications: Boolean = false,
        success: ((InitiateUserResponse?) -> Unit)? = null,
        error: ((String?) -> Unit)? = null
    ) {
        val lmFeedConnectUser = LMFeedConnectUser.Builder()
            .userName(userName)
            .uuid(uuid)
            .deviceId(deviceId)
            .apiKey(apiKey ?: "")
            .enablePushNotifications(enablePushNotifications)
            .build()

        lmFeedConnectUser.initiateUser(
            success = { response ->
                saveUserPreferences(
                    context,
                    apiKey,
                    response?.user?.name,
                    response?.user?.sdkClientInfo?.uuid,
                    enablePushNotifications
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
        uuid: String?,
        enablePushNotifications: Boolean
    ) {
        //save details to pref
        val userPreferences = LMFeedUserPreferences(context)
        userPreferences.apply {
            saveApiKey(apiKey ?: "")
            saveUserName(userName ?: "")
            saveUUID(uuid ?: "")
            savePushNotificationsEnabled(enablePushNotifications)
        }
    }
}