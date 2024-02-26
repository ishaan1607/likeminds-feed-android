package com.likeminds.feed.android.core.universalfeed.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.likeminds.feed.android.core.universalfeed.view.LMFeedUniversalFeedListView

public fun LMFeedUniversalFeedViewModel.bindView(
    view: LMFeedUniversalFeedListView,
    lifecycleOwner: LifecycleOwner
) {
    getFeed(1)
    this.universalFeedResponse.observe(lifecycleOwner) { response ->
        Log.d("PUI", "observer 1 binding")
        Log.d(
            "PUI", """
                observer 1
            response: ${response.second.size}
        """.trimIndent()
        )

        val page = response.first
        val posts = response.second

        if (page == 1) {
            view.replacePosts(posts)
        } else {
            view.addPosts(posts)
        }
    }
}