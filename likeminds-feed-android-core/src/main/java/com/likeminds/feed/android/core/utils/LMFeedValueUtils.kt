package com.likeminds.feed.android.core.utils

import android.util.Patterns

object LMFeedValueUtils {

    fun String.getValidTextForLinkify(): String {
        return this.replace("\u202C", "")
            .replace("\u202D", "")
            .replace("\u202E", "")
    }

    fun String?.isImageValid(): Boolean {
        return this?.isValidUrl() ?: false
    }

    fun <T> List<T>.getItemInList(position: Int): T? {
        if (position < 0 || position >= this.size) {
            return null
        }
        return this[position]
    }

    private fun String.isValidUrl(): Boolean {
        if (this.isEmpty()) {
            return false
        }
        return Patterns.WEB_URL.matcher(this).matches()
    }
}