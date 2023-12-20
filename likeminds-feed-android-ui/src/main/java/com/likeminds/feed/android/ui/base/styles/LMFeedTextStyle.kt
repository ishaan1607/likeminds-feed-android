package com.likeminds.feed.android.ui.base.styles

import com.likeminds.feed.android.ui.base.views.LMFeedTextView
import com.likeminds.feed.android.ui.utils.ViewStyle

class LMFeedTextStyle : ViewStyle {

    fun apply(textView: LMFeedTextView) {

    }
}

fun LMFeedTextView.setStyle(viewStyle: LMFeedTextStyle) {
    viewStyle.apply(this)
}