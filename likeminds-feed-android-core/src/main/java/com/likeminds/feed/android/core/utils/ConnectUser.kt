package com.likeminds.feed.android.core.utils

import android.util.Log
import com.likeminds.feed.android.core.LMFeedCoreApplication
import com.likeminds.likemindsfeed.LMFeedClient
import com.likeminds.likemindsfeed.initiateUser.model.InitiateUserRequest
import kotlinx.coroutines.*

/**
 * This class helps to call `initiateUser()` API, to get access and refresh token
 */
class ConnectUser private constructor(
    val apiKey: String,
    val userName: String,
    val uuid: String?,
    val deviceId: String
) {
    class Builder {
        private var apiKey: String = ""
        private var userName: String = ""
        private var uuid: String? = null
        private var deviceId: String = ""

        fun apiKey(apiKey: String) = apply { this.apiKey = apiKey }
        fun userName(userName: String) = apply { this.userName = userName }
        fun uuid(uuid: String?) = apply { this.uuid = uuid }

        fun deviceId(deviceId: String) = apply { this.deviceId = deviceId }

        fun build(): ConnectUser {
            //validate API key
            if (apiKey.isEmpty()) {
                throw IllegalAccessException("API Key is not entered, it can't be empty. Please use InitiateUser.Builder() to add API Key.")
            }

            //validate user name
            if (userName.isEmpty()) {
                throw IllegalAccessException("User name is not entered, it can't be empty. Please use InitiateUser.Builder() to add User Name.")
            }

            //validate device id
            if (deviceId.isEmpty()) {
                throw IllegalAccessException("Device Id is not entered, it can't be empty. Please use InitiateUser.Builder() to add Device Id.")

            }

            //return the instance
            return ConnectUser(apiKey, userName, uuid, deviceId)
        }
    }

    //initiate user API Call
    fun initiateUser() {
        CoroutineScope(Dispatchers.IO).launch {
            val lmFeedClient = LMFeedClient.getInstance()
            val initiateRequest = InitiateUserRequest.Builder()
                .apiKey(apiKey)
                .uuid(uuid)
                .deviceId(deviceId)
                .userName(userName)
                .isGuest(false)
                .build()

            val initiateResponse = lmFeedClient.initiateUser(initiateRequest)
            Log.d(
                LMFeedCoreApplication.LOG_TAG, """
                initiate response: ${initiateResponse.success} ${initiateResponse.data?.user?.sdkClientInfo?.uuid}
            """.trimIndent()
            )
        }
    }
}