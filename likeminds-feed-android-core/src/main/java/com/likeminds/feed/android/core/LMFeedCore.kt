package com.likeminds.feed.android.core

import android.app.Application
import com.likeminds.feed.android.core.ui.theme.LMFeedTheme
import com.likeminds.feed.android.core.ui.theme.model.LMFeedSetThemeRequest
import com.likeminds.feed.android.core.utils.ConnectUser

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

    fun showFeed(userName: String, uuid: String?, deviceId: String) {
        //Call initiate API
        initiateUser(userName, uuid, deviceId)

        //Inflate Feed
    }

    fun initiateUser(userName: String, uuid: String?, deviceId: String) {
        //validate API key
        if (apiKey == null) {
            throw IllegalAccessException("LMFeedCore.setup() is not called. Please call setup() to access this function")
        }

        val connectUser = ConnectUser.Builder()
            .userName(userName)
            .uuid(uuid)
            .deviceId(deviceId)
            .apiKey(apiKey ?: "")
            .build()

        connectUser.initiateUser()
    }

}