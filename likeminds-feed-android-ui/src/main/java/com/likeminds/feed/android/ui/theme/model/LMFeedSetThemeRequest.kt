package com.likeminds.feed.android.ui.theme.model

// todo: add other theme parameters
class LMFeedSetThemeRequest private constructor(
    val fonts: LMFeedFonts?
) {
    class Builder {
        private var fonts: LMFeedFonts? = null

        fun fonts(fonts: LMFeedFonts?) = apply { this.fonts = fonts }

        fun build() = LMFeedSetThemeRequest(fonts)
    }

    fun toBuilder(): Builder {
        return Builder().fonts(fonts)
    }
}