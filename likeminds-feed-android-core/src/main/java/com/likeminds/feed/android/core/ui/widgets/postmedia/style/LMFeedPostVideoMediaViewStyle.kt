package com.likeminds.feed.android.core.ui.widgets.postmedia.style

import androidx.annotation.ColorRes
import com.likeminds.feed.android.core.ui.base.styles.LMFeedIconStyle
import com.likeminds.feed.android.core.ui.base.styles.LMFeedImageStyle
import com.likeminds.feed.android.core.utils.LMFeedViewStyle

class LMFeedPostVideoMediaViewStyle private constructor(
    val videoThumbnailStyle: LMFeedImageStyle?,
    val videoPlayPauseButton: LMFeedIconStyle?,
    val videoMuteUnmuteButton: LMFeedIconStyle?,
    val showController: Boolean,
    val keepScreenOn: Boolean,
    val controllerAutoShow: Boolean,
    val controllerShowTimeoutMs: Int,
    @ColorRes val backgroundColor: Int?
) : LMFeedViewStyle {

    class Builder {
        private var videoThumbnailStyle: LMFeedImageStyle? = null
        private var videoPlayPauseButton: LMFeedIconStyle? = null
        private var videoMuteUnmuteButton: LMFeedIconStyle? = null
        private var showController: Boolean = false
        private var keepScreenOn: Boolean = true
        private var controllerAutoShow: Boolean = false
        private var controllerShowTimeoutMs: Int = 0

        @ColorRes
        private var backgroundColor: Int? = null

        fun videoThumbnailStyle(videoThumbnailStyle: LMFeedImageStyle?) =
            apply { this.videoThumbnailStyle = videoThumbnailStyle }

        fun videoPlayPauseButton(videoPlayPauseButton: LMFeedIconStyle?) =
            apply { this.videoPlayPauseButton = videoPlayPauseButton }

        fun videoMuteUnmuteButton(videoMuteUnmuteButton: LMFeedIconStyle?) =
            apply { this.videoMuteUnmuteButton = videoMuteUnmuteButton }

        fun showController(showController: Boolean) = apply { this.showController = showController }
        fun keepScreenOn(keepScreenOn: Boolean) = apply { this.keepScreenOn = keepScreenOn }
        fun controllerAutoShow(controllerAutoShow: Boolean) =
            apply { this.controllerAutoShow = controllerAutoShow }

        fun controllerShowTimeoutMs(controllerShowTimeoutMs: Int) =
            apply { this.controllerShowTimeoutMs = controllerShowTimeoutMs }

        fun backgroundColor(backgroundColor: Int?) =
            apply { this.backgroundColor = backgroundColor }

        fun build() = LMFeedPostVideoMediaViewStyle(
            videoThumbnailStyle,
            videoPlayPauseButton,
            videoMuteUnmuteButton,
            showController,
            keepScreenOn,
            controllerAutoShow,
            controllerShowTimeoutMs,
            backgroundColor
        )
    }

    fun toBuilder(): Builder {
        return Builder().videoThumbnailStyle(videoThumbnailStyle)
            .videoPlayPauseButton(videoPlayPauseButton)
            .videoMuteUnmuteButton(videoMuteUnmuteButton)
            .showController(showController)
            .keepScreenOn(keepScreenOn)
            .controllerAutoShow(controllerAutoShow)
            .controllerShowTimeoutMs(controllerShowTimeoutMs)
            .backgroundColor(backgroundColor)
    }
}