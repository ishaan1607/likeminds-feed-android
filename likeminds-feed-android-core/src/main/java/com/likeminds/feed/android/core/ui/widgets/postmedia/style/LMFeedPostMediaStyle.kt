package com.likeminds.feed.android.core.ui.widgets.postmedia.style

import com.likeminds.feed.android.core.util.LMFeedMediaUtils
import com.likeminds.feed.android.ui.base.styles.LMFeedImageStyle
import com.likeminds.feed.android.ui.utils.LMFeedViewStyle

class LMFeedPostMediaStyle private constructor(
    //style for image media in a post
    val postImageStyle: LMFeedImageStyle?,
    //style for video media in a post
    val postVideoStyle: LMFeedVideoStyle?,
    //style for link media in a post
    val postLinkStyle: LMFeedLinkStyle?,
    //style for documents media in a post
    val postDocumentsStyle: LMFeedDocumentsStyle?,
    //value for limit of video and images in a post
    val mediaLimit: Int
) : LMFeedViewStyle {

    class Builder {
        private var postImageStyle: LMFeedImageStyle? = null
        private var postVideoStyle: LMFeedVideoStyle? = null
        private var postLinkStyle: LMFeedLinkStyle? = null
        private var postDocumentsStyle: LMFeedDocumentsStyle? = null
        private var mediaLimit: Int = LMFeedMediaUtils.DEFAULT_MEDIA_LIMIT

        fun postImageStyle(postImageStyle: LMFeedImageStyle?) =
            apply { this.postImageStyle = postImageStyle }

        fun postVideoStyle(postVideoStyle: LMFeedVideoStyle?) =
            apply { this.postVideoStyle = postVideoStyle }

        fun postLinkStyle(postLinkStyle: LMFeedLinkStyle?) =
            apply { this.postLinkStyle = postLinkStyle }

        fun postDocumentsStyle(postDocumentsStyle: LMFeedDocumentsStyle?) =
            apply { this.postDocumentsStyle = postDocumentsStyle }

        fun mediaLimit(mediaLimit: Int) = apply { this.mediaLimit = mediaLimit }

        fun build() = LMFeedPostMediaStyle(
            postImageStyle,
            postVideoStyle,
            postLinkStyle,
            postDocumentsStyle,
            mediaLimit
        )
    }

    fun toBuilder(): Builder {
        return Builder().postImageStyle(postImageStyle)
            .postVideoStyle(postVideoStyle)
            .postLinkStyle(postLinkStyle)
            .postDocumentsStyle(postDocumentsStyle)
            .mediaLimit(mediaLimit)
    }
}