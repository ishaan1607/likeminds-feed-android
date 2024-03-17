package com.likeminds.feed.android.core.post.edit.style

import android.graphics.Typeface
import android.text.TextUtils
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.ui.widgets.alertdialog.style.LMFeedAlertDialogStyle
import com.likeminds.feed.android.core.ui.widgets.headerview.style.LMFeedHeaderViewStyle
import com.likeminds.feed.android.core.utils.model.LMFeedPadding

class LMFeedEditPostFragmentViewStyle private constructor(
    //header view style
    val headerViewStyle: LMFeedHeaderViewStyle,
    //post composer view style
    val postComposerStyle: LMFeedEditTextStyle,
    //progress bar view style
    val progressBarStyle: LMFeedProgressBarStyle,
    //select topics chip style
    val selectTopicsChipStyle: LMFeedChipStyle,
    //edit chip style
    val editChipStyle: LMFeedChipStyle,
    //disabled topics alert dialog style
    val disabledTopicsAlertDialogStyle: LMFeedAlertDialogStyle
) {

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
            .submitTextStyle(
                LMFeedTextStyle.Builder()
                    .textColor(R.color.lm_feed_grey)
                    .textSize(R.dimen.lm_feed_text_medium)
                    .fontResource(R.font.lm_feed_roboto_medium)
                    .build()
            )
            .activeSubmitColor(R.color.lm_feed_majorelle_blue)
            .build()

        private var postComposerStyle: LMFeedEditTextStyle = LMFeedEditTextStyle.Builder()
            .inputTextStyle(
                LMFeedTextStyle.Builder()
                    .ellipsize(TextUtils.TruncateAt.END)
                    .maxHeight(R.dimen.lm_feed_post_composer_max_height)
                    .minHeight(R.dimen.lm_feed_post_composer_min_height)
                    .textColor(R.color.lm_feed_dark_grey)
                    .textSize(R.dimen.lm_feed_text_medium)
                    .build()
            )
            .hintTextColor(R.color.lm_feed_maastricht_blue_40)
            .build()

        private var selectTopicsChipStyle: LMFeedChipStyle = LMFeedChipStyle.Builder()
            .chipBackgroundColor(R.color.lm_feed_majorelle_blue_10)
            .chipStartPadding(R.dimen.lm_feed_padding_big)
            .chipIcon(R.drawable.lm_feed_ic_add_topics)
            .chipIconSize(R.dimen.lm_feed_chip_default_icon_size)
            .chipIconTint(R.color.lm_feed_majorelle_blue)
            .build()

        private var editChipStyle: LMFeedChipStyle = LMFeedChipStyle.Builder()
            .chipBackgroundColor(R.color.lm_feed_majorelle_blue_10)
            .chipEndPadding(R.dimen.lm_feed_edit_chip_end_size)
            .chipIcon(R.drawable.lm_feed_ic_edit_topic)
            .chipIconSize(R.dimen.lm_feed_chip_default_icon_size)
            .chipIconTint(R.color.lm_feed_majorelle_blue)
            .build()

        private var progressBarStyle: LMFeedProgressBarStyle = LMFeedProgressBarStyle.Builder()
            .isIndeterminate(false)
            .progressColor(R.color.lm_feed_majorelle_blue)
            .build()

        private var disabledTopicsAlertDialogStyle: LMFeedAlertDialogStyle =
            LMFeedAlertDialogStyle.Builder()
                .alertSubtitleText(
                    LMFeedTextStyle.Builder()
                        .textColor(R.color.lm_feed_grey)
                        .textSize(R.dimen.lm_feed_text_medium)
                        .typeface(Typeface.NORMAL)
                        .fontResource(R.font.lm_feed_roboto)
                        .build()
                )
                .alertPositiveButtonStyle(
                    LMFeedTextStyle.Builder()
                        .textAllCaps(true)
                        .textColor(R.color.lm_feed_majorelle_blue)
                        .textSize(R.dimen.lm_feed_text_small)
                        .typeface(Typeface.NORMAL)
                        .fontResource(R.font.lm_feed_roboto_medium)
                        .build()
                )
                .build()

        fun headerViewStyle(headerViewStyle: LMFeedHeaderViewStyle) = apply {
            this.headerViewStyle = headerViewStyle
        }

        fun postComposerStyle(postComposerStyle: LMFeedEditTextStyle) = apply {
            this.postComposerStyle = postComposerStyle
        }

        fun progressBarStyle(progressBarStyle: LMFeedProgressBarStyle) = apply {
            this.progressBarStyle = progressBarStyle
        }

        fun selectTopicsChipStyle(selectTopicsChipStyle: LMFeedChipStyle) = apply {
            this.selectTopicsChipStyle = selectTopicsChipStyle
        }

        fun editChipStyle(editChipStyle: LMFeedChipStyle) = apply {
            this.editChipStyle = editChipStyle
        }

        fun disabledTopicsAlertDialogStyle(disabledTopicsAlertDialogStyle: LMFeedAlertDialogStyle) =
            apply {
                this.disabledTopicsAlertDialogStyle = disabledTopicsAlertDialogStyle
            }

        fun build() = LMFeedEditPostFragmentViewStyle(
            headerViewStyle,
            postComposerStyle,
            progressBarStyle,
            selectTopicsChipStyle,
            editChipStyle,
            disabledTopicsAlertDialogStyle
        )
    }

    fun toBuilder(): Builder {
        return Builder().headerViewStyle(headerViewStyle)
            .postComposerStyle(postComposerStyle)
            .progressBarStyle(progressBarStyle)
            .selectTopicsChipStyle(selectTopicsChipStyle)
            .editChipStyle(editChipStyle)
            .disabledTopicsAlertDialogStyle(disabledTopicsAlertDialogStyle)
    }
}