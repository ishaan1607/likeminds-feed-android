package com.likeminds.feed.android.ui.theme.model

import androidx.annotation.FontRes

// todo: add other theme parameters
class LMFeedSetThemeRequest private constructor(
    @FontRes
    val fontResource: Int?,
    val fontAssetsPath: String?,
    val postCharacterLimit: Int?
) {
    class Builder {
        @FontRes
        private var fontResource: Int? = null
        private var fontAssetsPath: String? = null
        private var postCharacterLimit: Int? = null

        fun fontResource(@FontRes fontResource: Int?) = apply { this.fontResource = fontResource }
        fun fontAssetsPath(fontAssetsPath: String?) = apply { this.fontAssetsPath = fontAssetsPath }
        fun postCharacterLimit(postCharacterLimit: Int?) =
            apply { this.postCharacterLimit = postCharacterLimit }

        fun build() = LMFeedSetThemeRequest(
            fontResource,
            fontAssetsPath,
            postCharacterLimit
        )
    }

    fun toBuilder(): Builder {
        return Builder().fontResource(fontResource)
            .fontAssetsPath(fontAssetsPath)
            .postCharacterLimit(postCharacterLimit)
    }
}