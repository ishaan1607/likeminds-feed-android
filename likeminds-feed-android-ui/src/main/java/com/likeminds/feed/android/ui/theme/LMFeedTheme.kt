package com.likeminds.feed.android.ui.theme

import com.likeminds.feed.android.ui.base.styles.LMFeedTextStyle
import com.likeminds.feed.android.ui.theme.model.LMFeedFonts
import com.likeminds.feed.android.ui.theme.model.LMFeedSetThemeRequest

object LMFeedTheme {
    private var fonts: LMFeedFonts? = null

    /**
     * @param lmFeedSetThemeRequest - Request to set base theme
     * sets fonts, used throughout the app as base theme
     * */
    fun setTheme(lmFeedSetThemeRequest: LMFeedSetThemeRequest) {
        fonts = lmFeedSetThemeRequest.fonts
    }

    // returns paths of the current fonts
    fun getCurrentFonts(): LMFeedFonts? {
        return fonts
    }

    fun setFont(lmFeedTextStyle: LMFeedTextStyle){

    }
}