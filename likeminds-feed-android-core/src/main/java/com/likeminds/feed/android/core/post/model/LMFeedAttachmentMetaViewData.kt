package com.likeminds.feed.android.core.post.model

import android.net.Uri
import com.likeminds.feed.android.core.universalfeed.model.LMFeedPostViewData

class LMFeedAttachmentMetaViewData private constructor(
    val name: String?,
    val url: String?,
    val format: String?,
    val size: Long?,
    val duration: Int?,
    val pageCount: Int?,
    val ogTags: LMFeedLinkOGTagsViewData,
    val width: Int?,
    val height: Int?,
    val uri: Uri?,
    val thumbnail: String?,
    val poll: LMFeedPostViewData?
) {

    class Builder {
        private var name: String? = null
        private var url: String? = null
        private var format: String? = null
        private var size: Long? = null
        private var duration: Int? = null
        private var pageCount: Int? = null
        private var ogTags: LMFeedLinkOGTagsViewData = LMFeedLinkOGTagsViewData.Builder().build()
        private var width: Int? = null
        private var height: Int? = null
        private var uri: Uri? = null
        private var thumbnail: String? = null
        private var poll: LMFeedPostViewData? = null

        fun name(name: String?) = apply {
            this.name = name
        }

        fun url(url: String?) = apply {
            this.url = url
        }

        fun format(format: String?) = apply {
            this.format = format
        }

        fun size(size: Long?) = apply {
            this.size = size
        }

        fun duration(duration: Int?) = apply {
            this.duration = duration
        }

        fun pageCount(pageCount: Int?) = apply {
            this.pageCount = pageCount
        }

        fun ogTags(ogTags: LMFeedLinkOGTagsViewData) = apply {
            this.ogTags = ogTags
        }

        fun width(width: Int?) = apply {
            this.width = width
        }

        fun height(height: Int?) = apply {
            this.height = height
        }

        fun uri(uri: Uri?) = apply {
            this.uri = uri
        }

        fun thumbnail(thumbnail: String?) = apply {
            this.thumbnail = thumbnail
        }

        fun poll(poll: LMFeedPostViewData?) = apply {
            this.poll = poll
        }

        fun build() = LMFeedAttachmentMetaViewData(
            name,
            url,
            format,
            size,
            duration,
            pageCount,
            ogTags,
            width,
            height,
            uri,
            thumbnail,
            poll
        )
    }

    fun toBuilder(): Builder {
        return Builder()
            .name(name)
            .url(url)
            .format(format)
            .size(size)
            .duration(duration)
            .pageCount(pageCount)
            .ogTags(ogTags)
            .width(width)
            .height(height)
            .uri(uri)
            .thumbnail(thumbnail)
            .poll(poll)
    }
}