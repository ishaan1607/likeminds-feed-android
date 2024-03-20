package com.likeminds.feed.android.core.utils

import android.content.Context
import android.content.Intent
import com.likeminds.feed.android.core.R

object LMFeedShareUtils {

    /**
     * Share post with url using default sharing in Android OS
     * @param context - context
     * @param postId - id of the shared post
     * @param domain - domain required to create share link
     */
    fun sharePost(
        context: Context,
        postId: String,
        domain: String,
        postAsVariable: String
    ) {
        val shareLink = "$domain/post?post_id=$postId"
        val shareTitle = context.getString(R.string.lm_feed_share_s, postAsVariable)
        shareLink(context, shareLink, shareTitle)
    }

    //create intent and open sharing options without link as text
    private fun shareLink(context: Context, shareLink: String, shareTitle: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, shareLink)
            type = "text/plain"
            putExtra(Intent.EXTRA_TITLE, shareTitle)
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    }
}