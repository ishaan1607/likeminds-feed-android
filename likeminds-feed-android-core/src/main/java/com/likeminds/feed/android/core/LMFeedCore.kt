package com.likeminds.feed.android.core

import android.app.Application
import android.content.Context
import com.likeminds.feed.android.core.ui.theme.LMFeedAppearance
import com.likeminds.feed.android.core.ui.theme.model.LMFeedAppearanceRequest
import com.likeminds.feed.android.core.utils.user.*
import com.likeminds.likemindsfeed.LMFeedClient
import com.likeminds.likemindsfeed.user.model.InitiateUserRequest
import com.likeminds.likemindsfeed.user.model.ValidateUserRequest
import kotlinx.coroutines.*

object LMFeedCore {

    /**
     * Initial setup function for customers and blocker function
     * @param application: Instance of the application class
     * @param theme: Theme selected for feed
     * @param domain: Domain of the client used in share post
     * @param enablePushNotifications: Whether to enable push notifications or not
     * @param deviceId: Device Id of the user
     * @param lmFeedAppearance: Object of [LMFeedAppearance] to add your customizable theme in whole feed
     * @param lmFeedCoreCallback: Instance of [LMFeedCoreCallback] so that we can share data/events to customers code
     */
    fun setup(
        application: Application,
        theme: LMFeedTheme,
        domain: String? = null,
        enablePushNotifications: Boolean = false,
        deviceId: String? = null,
        lmFeedAppearance: LMFeedAppearanceRequest? = null,
        lmFeedCoreCallback: LMFeedCoreCallback? = null
    ) {
        //set theme
        LMFeedAppearance.setAppearance(lmFeedAppearance)

        //initialize core application
        val coreApplication = LMFeedCoreApplication.getInstance()
        coreApplication.initCoreApplication(
            application,
            theme,
            lmFeedCoreCallback,
            domain,
            enablePushNotifications,
            deviceId
        )
    }

    fun showFeed(
        context: Context,
        apiKey: String,
        uuid: String,
        userName: String,
        success: ((UserResponse) -> Unit)?,
        error: ((String?) -> Unit)?
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val lmFeedClient = LMFeedClient.getInstance()
            val tokens = lmFeedClient.getTokens().data

            val userMeta = LMFeedUserMetaData.getInstance()
            val deviceId = userMeta.deviceId

            if (tokens?.first.isNullOrEmpty() || tokens?.second.isNullOrEmpty()) {
                val initiateUserRequest = InitiateUserRequest.Builder()
                    .apiKey(apiKey)
                    .userName(userName)
                    .uuid(uuid)
                    .deviceId(deviceId)
                    .build()

                val response = lmFeedClient.initiateUser(initiateUserRequest)
                if (response.success) {
                    success?.let { success ->
                        //return user response
                        response.data?.let { data ->
                            val userResponse = UserResponseConvertor.getUserResponse(data)
                            success(userResponse)

                            val imageUrl = userResponse.user?.imageUrl

                            //perform post session actions
                            userMeta.onPostSessionInit(
                                context,
                                userName,
                                uuid,
                                imageUrl
                            )
                        }
                    }
                } else {
                    error?.let { it(response.errorMessage) }
                }
            } else {
                showFeed(context, tokens?.first, tokens?.second, success, error)
            }
        }
    }

    fun showFeed(
        context: Context,
        accessToken: String?,
        refreshToken: String?,
        success: ((UserResponse) -> Unit)?,
        error: ((String?) -> Unit)?
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val userMeta = LMFeedUserMetaData.getInstance()
            val deviceId = userMeta.deviceId

            val lmFeedClient = LMFeedClient.getInstance()
            val tokens = if (accessToken.isNullOrEmpty() || refreshToken.isNullOrEmpty()) {
                lmFeedClient.getTokens().data
            } else {
                Pair(accessToken, refreshToken)
            } ?: Pair("", "")

            val validateUserRequest = ValidateUserRequest.Builder()
                .accessToken(tokens.first)
                .refreshToken(tokens.second)
                .deviceId(deviceId)
                .build()

            val response = lmFeedClient.validateUser(validateUserRequest)
            if (response.success) {
                success?.let { success ->
                    //return user response
                    response.data?.let { data ->
                        val userResponse = UserResponseConvertor.getUserResponse(data)
                        success(userResponse)
                    }

                    //perform post session actions
                    val user = response.data?.user
                    val userName = user?.name
                    val uuid = user?.sdkClientInfo?.uuid
                    val userImage = user?.imageUrl
                    userMeta.onPostSessionInit(
                        context,
                        userName,
                        uuid,
                        userImage
                    )
                }
            } else {
                error?.let { it(response.errorMessage) }
            }
        }
    }
}