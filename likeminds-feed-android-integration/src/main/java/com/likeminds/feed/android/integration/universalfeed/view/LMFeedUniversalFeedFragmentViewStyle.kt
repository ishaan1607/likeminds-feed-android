package com.likeminds.feed.android.integration.universalfeed.view

import com.likeminds.feed.android.integration.R
import com.likeminds.feed.android.ui.base.styles.LMFeedFABStyle
import com.likeminds.feed.android.ui.utils.ViewStyle
import com.likeminds.feed.android.ui.utils.model.LMFeedPadding

class LMFeedUniversalFeedFragmentViewStyle private constructor(
    val createNewPostButtonViewStyle: LMFeedFABStyle,
    //header
) : ViewStyle {

    class Builder {

        private var createNewPostButtonViewStyle: LMFeedFABStyle = LMFeedFABStyle.Builder()
            .isExtended(false)
            .backgroundColor(com.likeminds.feed.android.ui.R.color.majorelle_blue)
            .icon(R.drawable.lm_feed_ic_new_post_plus)
            .iconTint(R.color.white)
            .iconSize(R.dimen.lm_feed_create_new_post_icon_size)
            .textColor(com.likeminds.feed.android.ui.R.color.blue)
            .textAllCaps(true)
            .padding(
                LMFeedPadding(
                    R.dimen.lm_feed_create_new_post_padding,
                    0,
                    R.dimen.lm_feed_create_new_post_padding,
                    0
                )
            )
            .build()

        fun createNewPostButtonViewStyle(createNewPostButtonViewStyle: LMFeedFABStyle) = apply {
            this.createNewPostButtonViewStyle = createNewPostButtonViewStyle
        }

        fun build() = LMFeedUniversalFeedFragmentViewStyle(
            createNewPostButtonViewStyle
        )
    }
}