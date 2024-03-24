package com.likeminds.feed.android.core.ui.widgets.post.postmedia.style

import com.likeminds.feed.android.core.ui.base.styles.LMFeedImageStyle
import com.likeminds.feed.android.core.utils.LMFeedViewStyle

/**
 * [LMFeedPostMediaViewStyle] helps you to customize the media attached in the post
 *
 * @property postImageMediaStyle : [LMFeedImageStyle] this will help you to customize the image attachment in the post
 * @property postVideoMediaStyle : [LMFeedPostVideoMediaViewStyle] this will help you to customize the video attachment in the post
 * @property postLinkViewStyle: [LMFeedPostLinkMediaViewStyle] this will help you to customize the  link type post
 * @property postDocumentsMediaStyle: [LMFeedPostDocumentsMediaViewStyle] this will help you to customize the document type post
 * @property postMultipleMediaStyle: [LMFeedPostMultipleMediaViewStyle] this will help you to customize the multiple media (images/videos) type post
 * */
class LMFeedPostMediaViewStyle private constructor(
    //style for image media in a post
    val postImageMediaStyle: LMFeedImageStyle?,
    //style for video media in a post
    val postVideoMediaStyle: LMFeedPostVideoMediaViewStyle?,
    //style for link media in a post
    val postLinkViewStyle: LMFeedPostLinkMediaViewStyle?,
    //style for documents media in a post
    val postDocumentsMediaStyle: LMFeedPostDocumentsMediaViewStyle?,
    //style for multiple media carousel in a post
    val postMultipleMediaStyle: LMFeedPostMultipleMediaViewStyle?
) : LMFeedViewStyle {

    class Builder {
        private var postImageMediaStyle: LMFeedImageStyle? = null
        private var postVideoMediaStyle: LMFeedPostVideoMediaViewStyle? = null
        private var postLinkStyle: LMFeedPostLinkMediaViewStyle? = null
        private var postDocumentsMediaStyle: LMFeedPostDocumentsMediaViewStyle? = null
        private var postMultipleMediaStyle: LMFeedPostMultipleMediaViewStyle? = null

        fun postImageMediaStyle(postImageMediaStyle: LMFeedImageStyle?) =
            apply { this.postImageMediaStyle = postImageMediaStyle }

        fun postVideoMediaStyle(postVideoMediaStyle: LMFeedPostVideoMediaViewStyle?) =
            apply { this.postVideoMediaStyle = postVideoMediaStyle }

        fun postLinkStyle(postLinkStyle: LMFeedPostLinkMediaViewStyle?) =
            apply { this.postLinkStyle = postLinkStyle }

        fun postDocumentsMediaStyle(postDocumentsMediaStyle: LMFeedPostDocumentsMediaViewStyle?) =
            apply { this.postDocumentsMediaStyle = postDocumentsMediaStyle }

        fun postMultipleMediaStyle(postMultipleMediaStyle: LMFeedPostMultipleMediaViewStyle?) =
            apply { this.postMultipleMediaStyle = postMultipleMediaStyle }

        fun build() = LMFeedPostMediaViewStyle(
            postImageMediaStyle,
            postVideoMediaStyle,
            postLinkStyle,
            postDocumentsMediaStyle,
            postMultipleMediaStyle
        )
    }

    fun toBuilder(): Builder {
        return Builder().postImageMediaStyle(postImageMediaStyle)
            .postVideoMediaStyle(postVideoMediaStyle)
            .postLinkStyle(postLinkViewStyle)
            .postDocumentsMediaStyle(postDocumentsMediaStyle)
            .postMultipleMediaStyle(postMultipleMediaStyle)
    }
}