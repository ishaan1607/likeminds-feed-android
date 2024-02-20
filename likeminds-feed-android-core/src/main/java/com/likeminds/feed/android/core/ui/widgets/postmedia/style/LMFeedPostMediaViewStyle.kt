package com.likeminds.feed.android.core.ui.widgets.postmedia.style

import com.likeminds.feed.android.core.ui.base.styles.LMFeedImageStyle
import com.likeminds.feed.android.core.utils.LMFeedViewStyle

class LMFeedPostMediaViewStyle private constructor(
    //style for image media in a post
    val postImageMediaStyle: LMFeedImageStyle?,
    //style for video media in a post
    val postVideoMediaStyle: LMFeedPostVideoMediaViewStyle?,
    //style for link media in a post
    val postLinkViewStyle: LMFeedPostLinkViewStyle?,
    //style for documents media in a post
    val postDocumentsMediaStyle: LMFeedPostDocumentsMediaViewStyle?
) : LMFeedViewStyle {

    class Builder {
        private var postImageMediaStyle: LMFeedImageStyle? = null
        private var postVideoMediaStyle: LMFeedPostVideoMediaViewStyle? = null
        private var postLinkStyle: LMFeedPostLinkViewStyle? = null
        private var postDocumentsMediaStyle: LMFeedPostDocumentsMediaViewStyle? = null

        fun postImageMediaStyle(postImageMediaStyle: LMFeedImageStyle?) =
            apply { this.postImageMediaStyle = postImageMediaStyle }

        fun postVideoMediaStyle(postVideoMediaStyle: LMFeedPostVideoMediaViewStyle?) =
            apply { this.postVideoMediaStyle = postVideoMediaStyle }

        fun postLinkStyle(postLinkStyle: LMFeedPostLinkViewStyle?) =
            apply { this.postLinkStyle = postLinkStyle }

        fun postDocumentsMediaStyle(postDocumentsMediaStyle: LMFeedPostDocumentsMediaViewStyle?) =
            apply { this.postDocumentsMediaStyle = postDocumentsMediaStyle }

        fun build() = LMFeedPostMediaViewStyle(
            postImageMediaStyle,
            postVideoMediaStyle,
            postLinkStyle,
            postDocumentsMediaStyle
        )
    }

    fun toBuilder(): Builder {
        return Builder().postImageMediaStyle(postImageMediaStyle)
            .postVideoMediaStyle(postVideoMediaStyle)
            .postLinkStyle(postLinkViewStyle)
            .postDocumentsMediaStyle(postDocumentsMediaStyle)
    }
}