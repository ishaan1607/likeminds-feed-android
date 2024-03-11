package com.likeminds.feed.android.core.post.style

import android.text.TextUtils
import android.view.View
import android.widget.ImageView
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.ui.widgets.post.postfooterview.style.LMFeedPostFooterViewStyle
import com.likeminds.feed.android.core.ui.widgets.post.postheaderview.style.LMFeedPostHeaderViewStyle
import com.likeminds.feed.android.core.ui.widgets.post.postmedia.style.*
import com.likeminds.feed.android.core.utils.LMFeedViewStyle
import com.likeminds.feed.android.core.utils.LMFeedViewUtils
import com.likeminds.feed.android.core.utils.model.LMFeedPadding

class LMFeedPostViewStyle private constructor(
    //post header style
    val postHeaderViewStyle: LMFeedPostHeaderViewStyle,
    // post text content style
    val postContentTextStyle: LMFeedTextStyle,
    //post media style
    val postMediaViewStyle: LMFeedPostMediaViewStyle,
    //post footer style
    val postFooterViewStyle: LMFeedPostFooterViewStyle,
    //post topics chip group style
    val postTopicsGroupStyle: LMFeedChipGroupStyle,
    //post topics chip style
    val postTopicChipsStyle: LMFeedChipStyle
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

        private var postTopicsGroupStyle: LMFeedChipGroupStyle =
            LMFeedChipGroupStyle.Builder()
                .build()

        private var postTopicChipStyle: LMFeedChipStyle =
            LMFeedChipStyle.Builder()
                .chipBackgroundColor(R.color.lm_feed_majorelle_blue_10)
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

        fun postTopicsGroupStyle(postTopicsGroupStyle: LMFeedChipGroupStyle) = apply {
            this.postTopicsGroupStyle = postTopicsGroupStyle
        }

        fun postTopicChipStyle(postTopicChipStyle: LMFeedChipStyle) = apply {
            this.postTopicChipStyle = postTopicChipStyle
        }

        fun build() = LMFeedPostViewStyle(
            postHeaderViewStyle,
            postContentTextStyle,
            postMediaViewStyle,
            postFooterViewStyle,
            postTopicsGroupStyle,
            postTopicChipStyle
        )
    }

    fun toBuilder(): Builder {
        return Builder().postHeaderViewStyle(postHeaderViewStyle)
            .postContentTextStyle(postContentTextStyle)
            .postMediaViewStyle(postMediaViewStyle)
            .postFooterViewStyle(postFooterViewStyle)
            .postTopicsGroupStyle(postTopicsGroupStyle)
            .postTopicChipStyle(postTopicChipsStyle)
    }
}