package com.likeminds.feed.android.core

import com.likeminds.feed.android.core.universalfeed.model.LMFeedUserViewData

interface LMFeedCoreCallback {

    fun login() {
        //implement to re-login user when refresh token expires
    }

    fun openProfile(user: LMFeedUserViewData) {
        //implement to open your profile page with user data
    }
}