package com.likeminds.feedexample.universalfeed

import com.likeminds.feed.android.integration.universalfeed.view.LMFeedUniversalFeedFragment
import com.likeminds.feed.android.integration.util.StyleTransformer
import com.likeminds.feed.android.ui.base.styles.setStyle
import com.likeminds.feed.android.ui.base.views.LMFeedFAB
import com.likeminds.feed.android.ui.widgets.headerview.views.LMFeedHeaderView
import com.likeminds.feedexample.R

class CustomLMUniversalFeedFragment : LMFeedUniversalFeedFragment() {

    override fun customizeCreateNewPostButton(fabNewPost: LMFeedFAB) {
        var fragmentStyle = StyleTransformer.universalFeedFragmentViewStyle

        var buttonStyle = fragmentStyle.createNewPostButtonViewStyle
        buttonStyle = buttonStyle.toBuilder()
            .backgroundColor(com.likeminds.feed.android.ui.R.color.azure)
            .build()

        fragmentStyle = fragmentStyle.toBuilder()
            .createNewPostButtonViewStyle(buttonStyle)
            .build()

        StyleTransformer.universalFeedFragmentViewStyle = fragmentStyle

        fabNewPost.setStyle(buttonStyle)
    }

    override fun onCreateNewPostClick() {
        super.onCreateNewPostClick()
    }
}