package com.likeminds.feed.android.core.universalfeed.model

import com.likeminds.feed.android.core.util.base.LMFeedBaseViewType

class LMFeedPostViewData private constructor(
    val id: String,
    val headerViewData: LMFeedPostHeaderViewData,
    val contentViewData: LMFeedPostContentViewData,
    val mediaViewData: LMFeedMediaViewData,
    val footerViewData: LMFeedPostFooterViewData,
    val fromPostLiked: Boolean,
    val fromPostSaved: Boolean,
    val fromVideoAction: Boolean,
) : LMFeedBaseViewType {

    override val viewType: Int
        get() = TODO("Not yet implemented")

    class Builder {
        private var id: String = ""
        private var headerViewData: LMFeedPostHeaderViewData =
            LMFeedPostHeaderViewData.Builder().build()
        private var contentViewData: LMFeedPostContentViewData =
            LMFeedPostContentViewData.Builder().build()
        private var mediaViewData: LMFeedMediaViewData =
            LMFeedMediaViewData.Builder().build()
        private var footerViewData: LMFeedPostFooterViewData =
            LMFeedPostFooterViewData.Builder().build()
        private var fromPostLiked: Boolean = false
        private var fromPostSaved: Boolean = false
        private var fromVideoAction: Boolean = false

        fun id(id: String) = apply { this.id = id }
        fun headerViewData(headerViewData: LMFeedPostHeaderViewData) =
            apply { this.headerViewData = headerViewData }

        fun contentViewData(contentViewData: LMFeedPostContentViewData) =
            apply { this.contentViewData = contentViewData }

        fun mediaViewData(mediaViewData: LMFeedMediaViewData) =
            apply { this.mediaViewData = mediaViewData }

        fun footerViewData(footerViewData: LMFeedPostFooterViewData) =
            apply { this.footerViewData = footerViewData }

        fun fromPostLiked(fromPostLiked: Boolean) = apply { this.fromPostLiked = fromPostLiked }
        fun fromPostSaved(fromPostSaved: Boolean) = apply { this.fromPostSaved = fromPostSaved }
        fun fromVideoAction(fromVideoAction: Boolean) =
            apply { this.fromVideoAction = fromVideoAction }

        fun build() = LMFeedPostViewData(
            id,
            headerViewData,
            contentViewData,
            mediaViewData,
            footerViewData,
            fromPostLiked,
            fromPostSaved,
            fromVideoAction
        )
    }

    fun toBuilder(): Builder {
        return Builder()
            .id(id)
            .headerViewData(headerViewData)
            .contentViewData(contentViewData)
            .mediaViewData(mediaViewData)
            .footerViewData(footerViewData)
            .fromPostLiked(fromPostLiked)
            .fromPostSaved(fromPostSaved)
            .fromVideoAction(fromVideoAction)
    }
}