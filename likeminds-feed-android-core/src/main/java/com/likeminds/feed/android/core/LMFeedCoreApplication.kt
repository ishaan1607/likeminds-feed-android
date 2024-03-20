package com.likeminds.feed.android.core

import android.app.Application
import com.likeminds.likemindsfeed.LMCallback
import com.likeminds.likemindsfeed.LMFeedClient

class LMFeedCoreApplication : LMCallback {

    companion object {
        const val LOG_TAG = "LikeMindsFeedCore"
        private var coreApplicationInstance: LMFeedCoreApplication? = null
        private var lmFeedCoreCallback: LMFeedCoreCallback? = null

        /**
         * @return Singleton Instance of Core Application class
         * */
        @JvmStatic
        fun getInstance(): LMFeedCoreApplication {
            if (coreApplicationInstance == null) {
                coreApplicationInstance = LMFeedCoreApplication()
            }
            return coreApplicationInstance!!
        }

        /**
         * @return Singleton Instance of Callbacks required
         * */
        @JvmStatic
        fun getLMFeedCoreCallback(): LMFeedCoreCallback? {
            return lmFeedCoreCallback
        }
    }

    fun initCoreApplication(
        application: Application,
        lmFeedCoreCallback: LMFeedCoreCallback?
    ) {
        LMFeedClient.Builder(application)
            .lmCallback(this)
            .build()
        LMFeedCoreApplication.lmFeedCoreCallback = lmFeedCoreCallback
    }

    override fun login() {
        super.login()
        lmFeedCoreCallback?.login()
    }
}