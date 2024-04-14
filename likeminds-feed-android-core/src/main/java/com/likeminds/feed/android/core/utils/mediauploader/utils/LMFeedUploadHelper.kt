package com.likeminds.feed.android.core.utils.mediauploader.utils

import com.likeminds.feed.android.core.utils.mediauploader.model.LMFeedAWSFileResponse

class LMFeedUploadHelper {

    companion object {
        private var uploadHelperInstance: LMFeedUploadHelper? = null

        @JvmStatic
        fun getInstance(): LMFeedUploadHelper {
            if (uploadHelperInstance == null)
                uploadHelperInstance = LMFeedUploadHelper()
            return uploadHelperInstance!!
        }
    }

    private val awsFileResponses: ArrayList<LMFeedAWSFileResponse> = arrayListOf()

    fun addAWSFileResponse(awsFileResponse: LMFeedAWSFileResponse) {
        awsFileResponses.add(awsFileResponse)
    }

    fun removeAWSFileResponse(awsFileResponse: LMFeedAWSFileResponse) {
        awsFileResponses.remove(awsFileResponse)
    }

    fun getAWSFileResponse(awsFolderPath: String?): LMFeedAWSFileResponse? {
        if (awsFolderPath == null)
            return null
        for (awsFileResponse in awsFileResponses) {
            if (awsFileResponse.awsFolderPath == awsFolderPath)
                return awsFileResponse
        }
        return null
    }
}
