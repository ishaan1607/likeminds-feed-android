package com.likeminds.feed.android.core.utils.link

fun interface LMFeedOnLinkClickListener {
    fun onLinkClicked(url: String): Boolean
}