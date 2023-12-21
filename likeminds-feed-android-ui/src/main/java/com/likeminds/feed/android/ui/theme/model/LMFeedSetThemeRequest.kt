package com.likeminds.feed.android.ui.theme.model

// todo: add other theme parameters
class LMFeedSetThemeRequest private constructor(
    val fontResource: Int?,
    val fontAssetsPath: String?
) {
    class Builder {
        private var fontResource: Int? = null
        private var fontAssetsPath: String? = null

        fun fontResource(fontResource: Int?) = apply { this.fontResource = fontResource }
        fun fontAssetsPath(fontAssetsPath: String?) = apply { this.fontAssetsPath = fontAssetsPath }

        fun build() = LMFeedSetThemeRequest(fontResource, fontAssetsPath)
    }

    fun toBuilder(): Builder {
        return Builder().fontResource(fontResource)
            .fontAssetsPath(fontAssetsPath)
    }
}