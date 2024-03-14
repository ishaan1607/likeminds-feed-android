package com.likeminds.feed.android.core.topicselection.style

import android.graphics.Typeface
import android.text.TextUtils
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.ui.widgets.headerview.style.LMFeedHeaderViewStyle
import com.likeminds.feed.android.core.ui.widgets.noentitylayout.style.LMFeedNoEntityLayoutViewStyle
import com.likeminds.feed.android.core.utils.LMFeedViewStyle
import com.likeminds.feed.android.core.utils.model.LMFeedPadding

class LMFeedTopicSelectionFragmentViewStyle private constructor(
    //header
    val headerViewStyle: LMFeedHeaderViewStyle,
    //topic item style
    val topicItemStyle: LMFeedTextStyle,
    //no topics layout style
    val noTopicsLayoutViewStyle: LMFeedNoEntityLayoutViewStyle,
    //submit selected topics fab style
    val submitSelectedTopicsFABStyle: LMFeedFABStyle
) : LMFeedViewStyle {

    class Builder {
        private var headerViewStyle: LMFeedHeaderViewStyle = LMFeedHeaderViewStyle.Builder()
            .titleTextStyle(
                LMFeedTextStyle.Builder()
                    .textColor(R.color.lm_feed_black)
                    .textSize(R.dimen.lm_feed_header_view_title_text_size)
                    .maxLines(1)
                    .ellipsize(TextUtils.TruncateAt.END)
                    .build()
            )
            .backgroundColor(R.color.lm_feed_white)
            .searchIconStyle(
                LMFeedIconStyle.Builder()
                    .iconTint(R.color.lm_feed_black)
                    .inActiveSrc(R.drawable.lm_feed_ic_search_icon)
                    .iconPadding(
                        LMFeedPadding(
                            R.dimen.lm_feed_icon_padding,
                            R.dimen.lm_feed_icon_padding,
                            R.dimen.lm_feed_icon_padding,
                            R.dimen.lm_feed_icon_padding
                        )
                    )
                    .build()
            )
            .navigationIconStyle(
                LMFeedIconStyle.Builder()
                    .iconTint(R.color.lm_feed_black)
                    .inActiveSrc(R.drawable.lm_feed_ic_arrow_back_black_24dp)
                    .iconPadding(
                        LMFeedPadding(
                            R.dimen.lm_feed_icon_padding,
                            R.dimen.lm_feed_icon_padding,
                            R.dimen.lm_feed_icon_padding,
                            R.dimen.lm_feed_icon_padding
                        )
                    )
                    .build()
            )
            .build()

        private var topicItemStyle: LMFeedTextStyle = LMFeedTextStyle.Builder()
            .textSize(R.dimen.lm_feed_text_large)
            .textColor(R.color.lm_feed_dark_grey)
            .typeface(Typeface.BOLD)
            .fontResource(R.font.lm_feed_roboto)
            .drawableRightSrc(R.drawable.lm_feed_ic_topic_selected)
            .build()

        private var noTopicsLayoutViewStyle: LMFeedNoEntityLayoutViewStyle =
            LMFeedNoEntityLayoutViewStyle.Builder()
                .imageStyle(
                    LMFeedImageStyle.Builder()
                        .imageSrc(R.drawable.ic_not_found)
                        .build()
                )
                .titleStyle(
                    LMFeedTextStyle.Builder()
                        .textColor(R.color.lm_feed_dark_grey)
                        .textSize(R.dimen.lm_feed_text_small)
                        .typeface(Typeface.NORMAL)
                        .fontResource(R.font.lm_feed_roboto_medium)
                        .build()
                )
                .build()

        private var submitSelectedTopicsFABStyle: LMFeedFABStyle = LMFeedFABStyle.Builder()
            .isExtended(false)
            .backgroundColor(R.color.lm_feed_majorelle_blue)
            .icon(R.drawable.lm_feed_ic_arrow_right_white)
            .iconTint(R.color.lm_feed_white)
            .build()

        fun headerViewStyle(headerViewStyle: LMFeedHeaderViewStyle) = apply {
            this.headerViewStyle = headerViewStyle
        }

        fun topicItemStyle(topicItemStyle: LMFeedTextStyle) = apply {
            this.topicItemStyle = topicItemStyle
        }

        fun noTopicsLayoutViewStyle(noTopicsLayoutViewStyle: LMFeedNoEntityLayoutViewStyle) =
            apply {
                this.noTopicsLayoutViewStyle = noTopicsLayoutViewStyle
            }

        fun submitSelectedTopicsFABStyle(submitSelectedTopicsFABStyle: LMFeedFABStyle) = apply {
            this.submitSelectedTopicsFABStyle = submitSelectedTopicsFABStyle
        }

        fun build() = LMFeedTopicSelectionFragmentViewStyle(
            headerViewStyle,
            topicItemStyle,
            noTopicsLayoutViewStyle,
            submitSelectedTopicsFABStyle
        )
    }

    fun toBuilder(): Builder {
        return Builder().headerViewStyle(headerViewStyle)
            .topicItemStyle(topicItemStyle)
            .noTopicsLayoutViewStyle(noTopicsLayoutViewStyle)
            .submitSelectedTopicsFABStyle(submitSelectedTopicsFABStyle)
    }
}