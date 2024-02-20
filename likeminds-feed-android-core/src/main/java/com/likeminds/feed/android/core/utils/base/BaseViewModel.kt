package com.likeminds.feed.android.core.utils.base

import androidx.lifecycle.ViewModel
import com.likeminds.likemindsfeed.LMFeedClient

abstract class BaseViewModel : ViewModel() {

    protected val lmFeedClient: LMFeedClient = LMFeedClient.getInstance()
}