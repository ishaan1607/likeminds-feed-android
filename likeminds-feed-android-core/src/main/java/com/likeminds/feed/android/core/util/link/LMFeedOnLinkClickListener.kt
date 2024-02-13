package com.likeminds.feed.android.core.util.link

fun interface LMFeedOnLinkClickListener {
    fun onLinkClicked(url: String): Boolean
}