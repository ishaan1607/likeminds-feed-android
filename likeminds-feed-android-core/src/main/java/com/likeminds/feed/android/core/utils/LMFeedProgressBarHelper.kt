package com.likeminds.feed.android.core.utils

import android.view.View
import androidx.core.content.ContextCompat
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.databinding.LmFeedLayoutProgressBarBinding

object LMFeedProgressBarHelper {
    fun showProgress(
        progressBarBinding: LmFeedLayoutProgressBarBinding,
        enableBackground: Boolean = false
    ) {
        progressBarBinding.root.apply {
            if (enableBackground) {
                setBackgroundColor(
                    ContextCompat.getColor(
                        progressBarBinding.root.context,
                        R.color.lm_feed_background
                    )
                )
            } else {
                background = null
            }
            visibility = View.VISIBLE
            setOnClickListener { }
        }
    }

    fun isVisible(progressBarBinding: LmFeedLayoutProgressBarBinding): Boolean {
        return progressBarBinding.root.visibility == View.VISIBLE
    }

    fun hideProgress(progressBarBinding: LmFeedLayoutProgressBarBinding) {
        progressBarBinding.root.visibility = View.GONE
    }
}