package com.likeminds.feed.android.core.ui.widgets.postmedia.style

import androidx.annotation.ColorRes
import com.likeminds.feed.android.core.util.LMFeedMediaUtils.DEFAULT_VIDEO_SIZE_LIMIT
import com.likeminds.feed.android.ui.base.styles.LMFeedIconStyle

class LMFeedVideoStyle private constructor(
    val videoPlayButton: LMFeedIconStyle?,
    val videoPauseButton: LMFeedIconStyle?,
    val videoMuteButton: LMFeedIconStyle?,
    val videoSizeLimit: Long,
    val videoDurationLimit: Long?,
    val showController: Boolean,
    @ColorRes val backgroundColor: Int?,
) {

    class Builder {
        private var videoPlayButton: LMFeedIconStyle? = null
        private var videoPauseButton: LMFeedIconStyle? = null
        private var videoMuteButton: LMFeedIconStyle? = null
        private var videoSizeLimit: Long = DEFAULT_VIDEO_SIZE_LIMIT
        private var videoDurationLimit: Long? = null
        private var showController: Boolean = false

        @ColorRes
        private var backgroundColor: Int? = null

        fun videoPlayButton(videoPlayButton: LMFeedIconStyle?) =
            apply { this.videoPlayButton = videoPlayButton }

        fun videoPauseButton(videoPauseButton: LMFeedIconStyle?) =
            apply { this.videoPauseButton = videoPauseButton }

        fun videoMuteButton(videoMuteButton: LMFeedIconStyle?) =
            apply { this.videoMuteButton = videoMuteButton }

        fun videoSizeLimit(videoSizeLimit: Long) = apply { this.videoSizeLimit = videoSizeLimit }
        fun videoDurationLimit(videoDurationLimit: Long?) =
            apply { this.videoDurationLimit = videoDurationLimit }

        fun showController(showController: Boolean) = apply { this.showController = showController }
        fun backgroundColor(backgroundColor: Int?) =
            apply { this.backgroundColor = backgroundColor }

        fun build() = LMFeedVideoStyle(
            videoPlayButton,
            videoPauseButton,
            videoMuteButton,
            videoSizeLimit,
            videoDurationLimit,
            showController,
            backgroundColor
        )
    }

    fun toBuilder(): Builder {
        return Builder().videoPlayButton(videoPlayButton)
            .videoPauseButton(videoPauseButton)
            .videoMuteButton(videoMuteButton)
            .videoSizeLimit(videoSizeLimit)
            .videoDurationLimit(videoDurationLimit)
            .showController(showController)
            .backgroundColor(backgroundColor)
    }
}