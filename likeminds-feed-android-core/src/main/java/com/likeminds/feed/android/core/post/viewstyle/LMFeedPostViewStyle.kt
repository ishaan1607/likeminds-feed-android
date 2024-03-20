package com.likeminds.feed.android.core.post.viewstyle

import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.ui.widgets.post.postfooterview.style.LMFeedPostFooterViewStyle
import com.likeminds.feed.android.core.ui.widgets.post.postheaderview.style.LMFeedPostHeaderViewStyle
import com.likeminds.feed.android.core.ui.widgets.post.postmedia.style.*
import com.likeminds.feed.android.core.ui.widgets.post.posttopicsview.style.LMFeedPostTopicsViewStyle
import com.likeminds.feed.android.core.utils.LMFeedViewStyle
import com.likeminds.feed.android.core.utils.LMFeedViewUtils

/**
 * [LMFeedPostViewStyle] helps you to customize the header view
 *
 * @property postHeaderViewStyle : [LMFeedPostHeaderViewStyle] this will help you to customize the post header view [LMFeedPostHeaderView]
 * @property postContentTextStyle : [LMFeedTextStyle] this will help you to customize the text content of the post
 * @property postMediaStyle: [LMFeedPostMediaViewStyle] this will help you to customize the media attached in the post
 * @property postFooterViewStyle: [LMFeedPostFooterViewStyle] this will help you to customize the footer view of the post [LMFeedPostFooterView]
 *
 * */
class LMFeedPostViewStyle private constructor(
    //post header style
    val postHeaderViewStyle: LMFeedPostHeaderViewStyle,
    // post text content style
    val postContentTextStyle: LMFeedTextStyle,
    //post media style
    val postMediaViewStyle: LMFeedPostMediaViewStyle,
    //post footer style
    val postFooterViewStyle: LMFeedPostFooterViewStyle,
    //post topics style
    val postTopicsViewStyle: LMFeedPostTopicsViewStyle
) : LMFeedViewStyle {

    class Builder {
        private var postHeaderViewStyle: LMFeedPostHeaderViewStyle =
            LMFeedPostHeaderViewStyle.Builder()
                .authorImageViewStyle(
                    LMFeedImageStyle.Builder()
                        .isCircle(true)
                        .build()
                )
                .authorNameViewStyle(
                    LMFeedTextStyle.Builder()
                        .textColor(R.color.lm_feed_raisin_black)
                        .textSize(R.dimen.lm_feed_text_large)
                        .maxLines(1)
                        .ellipsize(TextUtils.TruncateAt.END)
                        .build()
                )
                .timestampTextStyle(
                    LMFeedTextStyle.Builder()
                        .textColor(R.color.lm_feed_grey)
                        .textSize(R.dimen.lm_feed_text_small)
                        .fontResource(R.font.lm_feed_roboto)
                        .build()
                )
                .postEditedTextStyle(
                    LMFeedTextStyle.Builder()
                        .textColor(R.color.lm_feed_grey)
                        .textSize(R.dimen.lm_feed_text_small)
                        .fontResource(R.font.lm_feed_roboto)
                        .maxLines(1)
                        .build()
                )
                .authorCustomTitleTextStyle(
                    LMFeedTextStyle.Builder()
                        .textColor(R.color.lm_feed_white)
                        .textSize(R.dimen.lm_feed_text_small)
                        .fontResource(R.font.lm_feed_roboto)
                        .maxLines(1)
                        .fontResource(R.font.lm_feed_roboto_medium)
                        .build()
                )
                .pinIconStyle(
                    LMFeedIconStyle.Builder()
                        .inActiveSrc(R.drawable.lm_feed_ic_pin)
                        .build()
                )
                .menuIconStyle(
                    LMFeedIconStyle.Builder()
                        .inActiveSrc(R.drawable.lm_feed_ic_overflow_menu)
                        .build()
                )
                .build()

        private var postContentTextStyle: LMFeedTextStyle =
            LMFeedTextStyle.Builder()
                .textColor(R.color.lm_feed_grey)
                .textSize(R.dimen.lm_feed_text_large)
                .maxLines(3)
                .fontResource(R.font.lm_feed_roboto)
                .build()

        private var postFooterViewStyle: LMFeedPostFooterViewStyle =
            LMFeedPostFooterViewStyle.Builder()
                .likeIconStyle(
                    LMFeedIconStyle.Builder()
                        .activeSrc(R.drawable.lm_feed_ic_like_filled)
                        .inActiveSrc(R.drawable.lm_feed_ic_like_unfilled)
                        .build()
                )
                .likeTextStyle(
                    LMFeedTextStyle.Builder()
                        .textColor(R.color.lm_feed_grey)
                        .textSize(R.dimen.lm_feed_text_medium)
                        .fontResource(R.font.lm_feed_roboto)
                        .textAllCaps(false)
                        .textAlignment(View.TEXT_ALIGNMENT_CENTER)
                        .build()
                )
                .commentTextStyle(
                    LMFeedTextStyle.Builder()
                        .textColor(R.color.lm_feed_grey)
                        .textSize(R.dimen.lm_feed_text_medium)
                        .fontResource(R.font.lm_feed_roboto)
                        .textAllCaps(false)
                        .textAlignment(View.TEXT_ALIGNMENT_CENTER)
                        .drawableLeftSrc(R.drawable.lm_feed_ic_comment)
                        .drawablePadding(R.dimen.lm_feed_padding_big)
                        .build()
                )
                .saveIconStyle(
                    LMFeedIconStyle.Builder()
                        .activeSrc(R.drawable.lm_feed_ic_bookmark_filled)
                        .inActiveSrc(R.drawable.lm_feed_ic_bookmark_unfilled)
                        .build()
                )
                .shareIconStyle(
                    LMFeedIconStyle.Builder()
                        .inActiveSrc(R.drawable.lm_feed_ic_share)
                        .build()
                )
                .build()

        private var postMediaViewStyle: LMFeedPostMediaViewStyle =
            LMFeedPostMediaViewStyle.Builder()
                .postImageMediaStyle(
                    LMFeedImageStyle.Builder()
                        .placeholderSrc(LMFeedViewUtils.getShimmer())
                        .scaleType(ImageView.ScaleType.CENTER_CROP)
                        .build()
                )
                .postVideoMediaStyle(
                    LMFeedPostVideoMediaViewStyle.Builder()
                        .videoProgressStyle(
                            LMFeedProgressBarStyle.Builder()
                                .progressColor(R.color.lm_feed_majorelle_blue)
                                .build()
                        )
                        .backgroundColor(R.color.lm_feed_white)
                        .build()
                )
                .postLinkStyle(
                    LMFeedPostLinkViewStyle.Builder()
                        .linkTitleStyle(
                            LMFeedTextStyle.Builder()
                                .fontResource(R.font.lm_feed_roboto)
                                .textColor(R.color.lm_feed_grey)
                                .textSize(R.dimen.lm_feed_text_medium)
                                .build()
                        )
                        .linkDescriptionStyle(
                            LMFeedTextStyle.Builder()
                                .fontResource(R.font.lm_feed_roboto)
                                .textColor(R.color.lm_feed_grey)
                                .textSize(R.dimen.lm_feed_text_medium)
                                .build()
                        )
                        .build()
                )
                .postDocumentsMediaStyle(
                    LMFeedPostDocumentsMediaViewStyle.Builder()
                        .documentIconStyle(
                            LMFeedIconStyle.Builder()
                                .inActiveSrc(R.drawable.lm_feed_ic_pdf)
                                .scaleType(ImageView.ScaleType.FIT_XY)
                                .build()
                        )
                        .documentPageCountStyle(
                            LMFeedTextStyle.Builder()
                                .fontResource(R.font.lm_feed_roboto)
                                .textColor(R.color.lm_feed_grey)
                                .textSize(R.dimen.lm_feed_text_medium)
                                .build()
                        )
                        .documentSizeStyle(
                            LMFeedTextStyle.Builder()
                                .fontResource(R.font.lm_feed_roboto)
                                .textColor(R.color.lm_feed_grey)
                                .textSize(R.dimen.lm_feed_text_medium)
                                .build()
                        )
                        .documentTypeStyle(
                            LMFeedTextStyle.Builder()
                                .fontResource(R.font.lm_feed_roboto)
                                .textColor(R.color.lm_feed_grey)
                                .textSize(R.dimen.lm_feed_text_medium)
                                .build()
                        )
                        .documentShowMoreStyle(
                            LMFeedTextStyle.Builder()
                                .fontResource(R.font.lm_feed_roboto)
                                .textColor(R.color.lm_feed_majorelle_blue)
                                .textSize(R.dimen.lm_feed_text_large)
                                .build()
                        )
                        .backgroundColor(R.color.lm_feed_light_grayish_blue)
                        .build()
                )
                .postMultipleMediaStyle(
                    LMFeedPostMultipleMediaViewStyle.Builder()
                        .indicatorSpacing(R.dimen.lm_feed_indicator_dots_spacing)
                        .build()
                )
                .build()

        private var postTopicsViewStyle: LMFeedPostTopicsViewStyle =
            LMFeedPostTopicsViewStyle.Builder()
                .chipBackgroundColor(R.color.lm_feed_majorelle_blue_10)
                .chipGroupHorizontalSpacing(R.dimen.lm_feed_topic_chip_group_horizontal_spacing)
                .chipGroupVerticalSpacing(R.dimen.lm_feed_topic_chip_group_vertical_spacing)
                .chipCornerRadius(R.dimen.lm_feed_corner_radius_regular)
                .chipTextColor(R.color.lm_feed_majorelle_blue)
                .chipTextSize(R.dimen.lm_feed_text_medium)
                .build()

        fun postHeaderViewStyle(postHeaderViewStyle: LMFeedPostHeaderViewStyle) = apply {
            this.postHeaderViewStyle = postHeaderViewStyle
        }

        fun postContentTextStyle(postContentTextStyle: LMFeedTextStyle) = apply {
            this.postContentTextStyle = postContentTextStyle
        }

        fun postMediaViewStyle(postMediaViewStyle: LMFeedPostMediaViewStyle) = apply {
            this.postMediaViewStyle = postMediaViewStyle
        }

        fun postFooterViewStyle(postFooterViewStyle: LMFeedPostFooterViewStyle) = apply {
            this.postFooterViewStyle = postFooterViewStyle
        }

        fun postTopicsViewStyle(postTopicsViewStyle: LMFeedPostTopicsViewStyle) =
            apply { this.postTopicsViewStyle = postTopicsViewStyle }

        fun build() = LMFeedPostViewStyle(
            postHeaderViewStyle,
            postContentTextStyle,
            postMediaViewStyle,
            postFooterViewStyle,
            postTopicsViewStyle
        )
    }

    fun toBuilder(): Builder {
        return Builder().postHeaderViewStyle(postHeaderViewStyle)
            .postContentTextStyle(postContentTextStyle)
            .postMediaViewStyle(postMediaViewStyle)
            .postFooterViewStyle(postFooterViewStyle)
            .postTopicsViewStyle(postTopicsViewStyle)
    }
}