package com.likeminds.feed.android.core

import android.app.Application
import android.content.Context
import android.util.Base64
import com.amazonaws.auth.CognitoCachingCredentialsProvider
import com.amazonaws.mobile.client.AWSMobileClient
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility
import com.amazonaws.regions.Region
import com.amazonaws.regions.Regions
import com.amazonaws.services.s3.AmazonS3Client
import com.likeminds.feed.android.core.utils.mediauploader.utils.LMFeedAWSKeys
import com.likeminds.likemindsfeed.LMCallback
import com.likeminds.likemindsfeed.LMFeedClient

class LMFeedCoreApplication : LMCallback {

    private lateinit var transferUtility: TransferUtility

    companion object {
        const val LOG_TAG = "LikeMindsFeedCore"
        private var coreApplicationInstance: LMFeedCoreApplication? = null
        private var lmFeedCoreCallback: LMFeedCoreCallback? = null
        private lateinit var transferUtility: TransferUtility
        private var credentialsProvider: CognitoCachingCredentialsProvider? = null

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

        /**
         * @return Singleton Instance of transfer utility
         * */
        @JvmStatic
        fun getTransferUtility(context: Context): TransferUtility {
            if (!this::transferUtility.isInitialized) {
                val bucketName =
                    String(Base64.decode(LMFeedAWSKeys.getBucketName(), Base64.DEFAULT))

                if (credentialsProvider != null) {
                    credentialsProvider = CognitoCachingCredentialsProvider(
                        context,
                        LMFeedAWSKeys.getIdentityPoolId(), // Identity Pool ID
                        Regions.AP_SOUTHEAST_1 // Region
                    );
                }

                transferUtility = TransferUtility.builder()
                    .context(context)
                    .defaultBucket(bucketName)
                    .awsConfiguration(AWSMobileClient.getInstance().configuration)
                    .s3Client(
                        AmazonS3Client(
                            credentialsProvider,
                            Region.getRegion(Regions.AP_SOUTH_1)
                        )
                    )
                    .build()
            }

            return transferUtility
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