package com.likeminds.feed.android.core.ui.widgets.postmedia.style

import com.likeminds.feed.android.ui.base.styles.LMFeedImageStyle
import com.likeminds.feed.android.ui.utils.LMFeedViewStyle

class LMFeedPostMediaStyle private constructor(
    //style for image media in a post
    val postImageMediaStyle: LMFeedImageStyle?,
    //style for video media in a post
    val postVideoMediaStyle: LMFeedPostVideoMediaStyle?,
    //style for link media in a post
    val postLinkViewStyle: LMFeedPostLinkViewStyle?,
    //style for documents media in a post
    val postDocumentsMediaStyle: LMFeedPostDocumentsMediaStyle?
) : LMFeedViewStyle {

    class Builder {
        private var postImageMediaStyle: LMFeedImageStyle? = null
        private var postVideoMediaStyle: LMFeedPostVideoMediaStyle? = null
        private var postLinkStyle: LMFeedPostLinkViewStyle? = null
        private var postDocumentsMediaStyle: LMFeedPostDocumentsMediaStyle? = null

        fun postImageMediaStyle(postImageMediaStyle: LMFeedImageStyle?) =
            apply { this.postImageMediaStyle = postImageMediaStyle }

        fun postVideoMediaStyle(postVideoMediaStyle: LMFeedPostVideoMediaStyle?) =
            apply { this.postVideoMediaStyle = postVideoMediaStyle }

        fun postLinkStyle(postLinkStyle: LMFeedPostLinkViewStyle?) =
            apply { this.postLinkStyle = postLinkStyle }

        fun postDocumentsMediaStyle(postDocumentsMediaStyle: LMFeedPostDocumentsMediaStyle?) =
            apply { this.postDocumentsMediaStyle = postDocumentsMediaStyle }

        fun build() = LMFeedPostMediaStyle(
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