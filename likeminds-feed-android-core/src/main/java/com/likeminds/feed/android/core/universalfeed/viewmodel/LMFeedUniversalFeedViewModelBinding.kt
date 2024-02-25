package com.likeminds.feed.android.core.universalfeed.viewmodel

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView

public fun LMFeedUniversalFeedViewModel.bindView(
    view: RecyclerView,
    lifecycleOwner: LifecycleOwner
) {
    this.universalFeedResponse.observe(lifecycleOwner) { response ->
        Log.d("PUI", "observer 1 binding")
        Log.d(
            "PUI", """
                observer 1
            response: ${response.second.size}
        """.trimIndent()
        )
    }
}