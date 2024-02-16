package com.likeminds.feed.android.core.ui.widgets.postmedia.style

import androidx.annotation.ColorRes
import com.likeminds.feed.android.ui.base.styles.LMFeedIconStyle

// todo: see videoScalingMode
class LMFeedPostVideoMediaStyle private constructor(
    val videoPlayButton: LMFeedIconStyle?,
    val videoPauseButton: LMFeedIconStyle?,
    val videoMuteButton: LMFeedIconStyle?,
    val showController: Boolean,
    val keepScreenOn: Boolean,
    @ColorRes val backgroundColor: Int?
) {

    class Builder {
        private var videoPlayButton: LMFeedIconStyle? = null
        private var videoPauseButton: LMFeedIconStyle? = null
        private var videoMuteButton: LMFeedIconStyle? = null
        private var showController: Boolean = false
        private var keepScreenOn: Boolean = false

        @ColorRes
        private var backgroundColor: Int? = null

        fun videoPlayButton(videoPlayButton: LMFeedIconStyle?) =
            apply { this.videoPlayButton = videoPlayButton }

        fun videoPauseButton(videoPauseButton: LMFeedIconStyle?) =
            apply { this.videoPauseButton = videoPauseButton }

        fun videoMuteButton(videoMuteButton: LMFeedIconStyle?) =
            apply { this.videoMuteButton = videoMuteButton }

        fun showController(showController: Boolean) = apply { this.showController = showController }
        fun keepScreenOn(keepScreenOn: Boolean) = apply { this.keepScreenOn = keepScreenOn }
        fun backgroundColor(backgroundColor: Int?) =
            apply { this.backgroundColor = backgroundColor }

        fun build() = LMFeedPostVideoMediaStyle(
            videoPlayButton,
            videoPauseButton,
            videoMuteButton,
            showController,
            keepScreenOn,
            backgroundColor
        )
    }

    fun toBuilder(): Builder {
        return Builder().videoPlayButton(videoPlayButton)
            .videoPauseButton(videoPauseButton)
            .videoMuteButton(videoMuteButton)
            .showController(showController)
            .keepScreenOn(keepScreenOn)
            .backgroundColor(backgroundColor)
    }
}