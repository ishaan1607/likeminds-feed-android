package com.likeminds.feed.android.core

import android.app.Application
import com.likeminds.feed.android.core.ui.theme.LMFeedTheme
import com.likeminds.feed.android.core.ui.theme.model.LMFeedSetThemeRequest
import com.likeminds.feed.android.core.utils.user.LMFeedConnectUser
import com.likeminds.likemindsfeed.initiateUser.model.InitiateUserResponse

object LMFeedCore {

    private var apiKey: String? = null

    /**
     * Initial setup function for customers and blocker function
     * @param application: Instance of the application class
     * @param apiKey: API Key of the Customer, generated from [here](https://dashboard.likeminds.community/auth)
     * @param lmFeedTheme: Object of [LMFeedTheme] to add your customizable theme in whole feed
     * @param lmFeedCoreCallback: Instance of [LMFeedCoreCallback] so that we can share data/events to customers code
     */
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
        userName: String,
        uuid: String?,
        deviceId: String,
        success: ((InitiateUserResponse?) -> Unit)? = null,
        error: ((String?) -> Unit)? = null
    ) {
        //Call initiate API
        initiateUser(userName, uuid, deviceId)

        //Inflate Feed
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
                success?.invoke(response)
            },
            error = { errorMessage ->
                error?.invoke(errorMessage)
            }
        )
    }

}