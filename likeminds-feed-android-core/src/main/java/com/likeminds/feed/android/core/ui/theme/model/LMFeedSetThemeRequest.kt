package com.likeminds.feed.android.core.ui.theme.model

import androidx.annotation.*

// todo: add other theme parameters
class LMFeedSetThemeRequest private constructor(
    @FontRes
    val fontResource: Int?,
    val fontAssetsPath: String?,
    val postCharacterLimit: Int?,
    @DrawableRes val notificationIcon: Int?,
    @ColorRes val notificationTextColor: Int?,
    @ColorRes val textLinkColor: Int?
) {
    class Builder {
        @FontRes
        private var fontResource: Int? = null

        private var fontAssetsPath: String? = null

        private var postCharacterLimit: Int? = null

        @DrawableRes
        private var notificationIcon: Int? = null

        @ColorRes
        private var notificationTextColor: Int? = null

        @ColorRes
        private var textLinkColor: Int? = null

        fun fontResource(@FontRes fontResource: Int?) = apply {
            this.fontResource = fontResource
        }

        fun fontAssetsPath(fontAssetsPath: String?) = apply {
            this.fontAssetsPath = fontAssetsPath
        }

        fun postCharacterLimit(postCharacterLimit: Int?) = apply {
            this.postCharacterLimit = postCharacterLimit
        }

        fun notificationIcon(@DrawableRes notificationIcon: Int?) = apply {
            this.notificationIcon = notificationIcon
        }

        fun notificationTextColor(@ColorRes notificationTextColor: Int?) = apply {
            this.notificationTextColor = notificationTextColor
        }

        fun textLinkColor(@ColorRes textLinkColor: Int?) = apply {
            this.textLinkColor = textLinkColor
        }

        fun build() = LMFeedSetThemeRequest(
            fontResource,
            fontAssetsPath,
            postCharacterLimit,
            notificationIcon,
            notificationTextColor,
            textLinkColor
        )
    }

    fun toBuilder(): Builder {
        return Builder().fontResource(fontResource)
            .fontAssetsPath(fontAssetsPath)
            .postCharacterLimit(postCharacterLimit)
            .notificationIcon(notificationIcon)
            .notificationTextColor(notificationTextColor)
            .textLinkColor(textLinkColor)
    }
}