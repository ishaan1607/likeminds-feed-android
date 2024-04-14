package com.likeminds.feed.android.core.ui.theme.model

import androidx.annotation.*

// todo: add other theme parameters
class LMFeedSetThemeRequest private constructor(
    @FontRes
    val fontResource: Int?,
    val fontAssetsPath: String?,
    @ColorRes val textLinkColor: Int?,
    @ColorRes val buttonColor: Int?,
    val postCharacterLimit: Int?,
    @DrawableRes val notificationIcon: Int?,
    @ColorRes val notificationTextColor: Int?,
) {
    class Builder {
        @FontRes
        private var fontResource: Int? = null

        private var fontAssetsPath: String? = null

        @ColorRes
        private var textLinkColor: Int? = null

        @ColorRes
        private var buttonColor: Int? = null

        private var postCharacterLimit: Int? = null

        @DrawableRes
        private var notificationIcon: Int? = null

        @ColorRes
        private var notificationTextColor: Int? = null

        fun fontResource(@FontRes fontResource: Int?) = apply {
            this.fontResource = fontResource
        }

        fun fontAssetsPath(fontAssetsPath: String?) = apply {
            this.fontAssetsPath = fontAssetsPath
        }

        fun textLinkColor(@ColorRes textLinkColor: Int?) = apply {
            this.textLinkColor = textLinkColor
        }

        fun buttonColor(@ColorRes buttonColor: Int?) = apply {
            this.buttonColor = buttonColor
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

        fun build() = LMFeedSetThemeRequest(
            fontResource,
            fontAssetsPath,
            textLinkColor,
            buttonColor,
            postCharacterLimit,
            notificationIcon,
            notificationTextColor,
        )
    }

    fun toBuilder(): Builder {
        return Builder().fontResource(fontResource)
            .fontAssetsPath(fontAssetsPath)
            .textLinkColor(textLinkColor)
            .buttonColor(buttonColor)
            .postCharacterLimit(postCharacterLimit)
            .notificationIcon(notificationIcon)
            .notificationTextColor(notificationTextColor)
    }
}