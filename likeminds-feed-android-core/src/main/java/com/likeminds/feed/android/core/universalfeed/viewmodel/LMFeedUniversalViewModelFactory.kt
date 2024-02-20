package com.likeminds.feed.android.core.universalfeed.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LMFeedUniversalViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == LMFeedUniversalFeedViewModel::class.java) {
            "LMFeedUniversalViewModelFactory can only create instances of LMFeedUniversalFeedModel"
        }

        @Suppress("UNCHECKED_CAST")
        return LMFeedUniversalFeedViewModel() as T
    }
}