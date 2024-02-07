package com.likeminds.feed.android.core.universalfeed.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class LMFeedUniversalViewModelFactory() : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == LMFeedUniversalFeedModel::class.java) {
            "LMFeedUniversalViewModelFactory can only create instances of LMFeedUniversalFeedModel"
        }
 
        @Suppress("UNCHECKED_CAST")
        return LMFeedUniversalFeedModel() as T
    }
}