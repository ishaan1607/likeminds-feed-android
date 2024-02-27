package com.likeminds.feed.android.core.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.likeminds.feed.android.core.R

object LMFeedAndroidUtils {

    /**
     * Shows document for the provided uri
     */
    fun startDocumentViewer(context: Context, uri: Uri) {
        val pdfIntent = Intent(Intent.ACTION_VIEW, uri)
        pdfIntent.flags = Intent.FLAG_ACTIVITY_NO_HISTORY or Intent.FLAG_GRANT_READ_URI_PERMISSION
        try {
            context.startActivity(pdfIntent)
        } catch (e: Exception) {
            e.printStackTrace()
            LMFeedViewUtils.showShortToast(
                context,
                context.getString(R.string.lm_feed_no_application_found_to_open_this_document)
            )
        }
    }
}