package com.likeminds.feed.android.core.ui.theme

import androidx.annotation.FontRes
import com.likeminds.feed.android.core.ui.theme.model.LMFeedSetThemeRequest

object LMFeedTheme {
    private const val DEFAULT_POST_CHARACTER_LIMIT = 500
    const val DEFAULT_POST_MAX_LINES = 3

    @FontRes
    private var fontResource: Int? = null
    private var fontAssetsPath: String? = null
    private var postCharacterLimit: Int = DEFAULT_POST_CHARACTER_LIMIT

    /**
     * @param lmFeedSetThemeRequest - Request to set base theme
     * sets fonts, used throughout the app as base theme
     * */
    fun setTheme(lmFeedSetThemeRequest: LMFeedSetThemeRequest) {
        fontResource = lmFeedSetThemeRequest.fontResource
        fontAssetsPath = lmFeedSetThemeRequest.fontAssetsPath
        postCharacterLimit =
            (lmFeedSetThemeRequest.postCharacterLimit ?: DEFAULT_POST_CHARACTER_LIMIT)
    }

    //returns the pair of theme font resource and assets path
    fun getFontResources(): Pair<Int?, String?> {
        return Pair(fontResource, fontAssetsPath)
    }

    //returns the limit of characters in the post text view to see more
    fun getPostCharacterLimit(): Int {
        return postCharacterLimit
    }
}