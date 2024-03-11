package com.likeminds.feed.android.core.utils.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

abstract class LMFeedBasePreferences(
    prefName: String,
    context: Context
) {

    companion object {
        const val LM_FEED_MASTER_PREF = "LM_FEED_MASTER_PREF"
        const val LM_FEED_ALL_PREFS_SET = "LM_FEED_ALL_PREFS_SET"
    }

    init {
        val masterPref: SharedPreferences =
            context.getSharedPreferences(LM_FEED_MASTER_PREF, Context.MODE_PRIVATE)
        val list = masterPref.getStringSet(LM_FEED_ALL_PREFS_SET, emptySet())
        masterPref.edit().putStringSet(LM_FEED_ALL_PREFS_SET, list!!.plus(prefName)).apply()
    }

    private val preferences: SharedPreferences =
        context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

    fun putPreference(key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }

    fun getPreference(key: String, defaultVal: String): String? {
        return preferences.getString(key, defaultVal)
    }

    fun clear() {
        // clear all the keys individually to notify the listeners
        val editor = preferences.edit()
        for (key in preferences.all.keys) {
            editor.remove(key)
        }
        editor.apply()
    }
}