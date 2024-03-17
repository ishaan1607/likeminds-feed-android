package com.likeminds.feed.android.core.ui.widgets.acitivityfeed.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.databinding.LmFeedActivityViewBinding
import com.likeminds.feed.android.core.post.model.*
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.ui.widgets.acitivityfeed.style.LMFeedActivityViewStyle
import com.likeminds.feed.android.core.universalfeed.model.LMFeedUserViewData
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer
import com.likeminds.feed.android.core.utils.LMFeedTimeUtil
import com.likeminds.feed.android.core.utils.LMFeedValueUtils.getValidTextForLinkify
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.hide
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.show
import com.likeminds.feed.android.core.utils.user.LMFeedUserImageUtil

class LMFeedActivityView : ConstraintLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    companion object {
        private const val MAX_LINES = 3
    }

    private val inflater =
        (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)

    private val binding: LmFeedActivityViewBinding =
        LmFeedActivityViewBinding.inflate(inflater, this, true)

    fun setStyle(activityViewStyle: LMFeedActivityViewStyle) {

        activityViewStyle.apply {
            //sets background color
            unreadActivityBackgroundColor?.let {
                setBackgroundColor(ContextCompat.getColor(context, unreadActivityBackgroundColor))
            }

            //configure activity view elements
            configureUserImage(userImageViewStyle)
            configureActivityText(activityTextStyle)
            configurePostTypeBadge(postTypeBadgeStyle)
            configureTimestampText(timestampTextStyle)
        }
    }

    private fun configureUserImage(userImageViewStyle: LMFeedImageStyle) {
        binding.ivUserImage.setStyle(userImageViewStyle)
    }

    private fun configureActivityText(activityTextStyle: LMFeedTextStyle) {
        binding.tvActivityContent.setStyle(activityTextStyle)
    }

    private fun configurePostTypeBadge(postTypeBadgeStyle: LMFeedImageStyle?) {
        binding.ivPostType.apply {
            if (postTypeBadgeStyle == null) {
                hide()
            } else {
                setStyle(postTypeBadgeStyle)
                show()
            }
        }
    }

    private fun configureTimestampText(timestampTextStyle: LMFeedTextStyle?) {
        binding.tvActivityDate.apply {
            if (timestampTextStyle == null) {
                hide()
            } else {
                setStyle(timestampTextStyle)
                show()
            }
        }
    }

    /**
     * Sets activity content in the activity context text view
     *
     * @param activityContent Text for the activity content.
     */
    fun setActivityContent(activityContent: String) {
        binding.tvActivityContent.apply {
            /**
             * Text is modified as Linkify doesn't accept texts with these specific unicode characters
             * @see #Linkify.containsUnsupportedCharacters(String)
             */
            val textForLinkify = activityContent.getValidTextForLinkify()

            // post is used here to get lines count in the text view
            post {
                //todo: member tagging
                setText(
                    textForLinkify,
                    TextView.BufferType.EDITABLE
                )

                // get the short text as per max lines
                var shortText: String? = null
                val ellipsize = context.getString(R.string.lm_feed_ellipsize)
                if (lineCount >= MAX_LINES) {
                    val lineEndIndex: Int = layout.getLineEnd(MAX_LINES - 1)
                    shortText = text.subSequence(0, lineEndIndex).toString().trim()
                }
                val finalText = shortText?.plus(ellipsize) ?: text
                text = finalText
            }
        }
    }

    /**
     * Sets the time the post was created.
     *
     * @param createdAtTimeStamp - timestamp when the post was created.
     */
    fun setTimestamp(createdAtTimeStamp: Long) {
        binding.tvActivityDate.apply {
            text = LMFeedTimeUtil.getRelativeTime(context, createdAtTimeStamp)
        }
    }

    /**
     * Sets the activity as read or unread
     *
     * @param isRead - whether the activity is read or not
     */
    fun setActivityRead(isRead: Boolean) {
        binding.root.apply {
            val activityViewStyle =
                LMFeedStyleTransformer.activityFeedFragmentViewStyle.activityViewStyle
            if (isRead) {
                activityViewStyle.readActivityBackgroundColor?.let { readActivityBackgroundColor ->
                    setBackgroundColor(ContextCompat.getColor(context, readActivityBackgroundColor))
                }
            } else {
                activityViewStyle.unreadActivityBackgroundColor?.let { unreadActivityBackgroundColor ->
                    setBackgroundColor(
                        ContextCompat.getColor(
                            context,
                            unreadActivityBackgroundColor
                        )
                    )
                }
            }
        }
    }

    /**
     * Sets the user image view.
     *
     * @param user - data of the author.
     */
    fun setUserImage(user: LMFeedUserViewData) {
        var userImageViewStyle =
            LMFeedStyleTransformer.activityFeedFragmentViewStyle.activityViewStyle.userImageViewStyle

        if (userImageViewStyle.placeholderSrc == null) {
            userImageViewStyle = userImageViewStyle.toBuilder().placeholderSrc(
                LMFeedUserImageUtil.getNameDrawable(
                    user.sdkClientInfoViewData.uuid,
                    user.name,
                    userImageViewStyle.isCircle,
                ).first
            ).build()
        }
        binding.ivUserImage.setImage(user.imageUrl, userImageViewStyle)
    }

    /**
     * Sets the post type badge for the activity as per the attachment type in the activity's post entity
     *
     * @param attachmentType - attachment type in the activity's post entity
     */
    fun setPostTypeBadge(@LMFeedAttachmentType attachmentType: Int?) {
        binding.apply {
            when (attachmentType) {
                IMAGE -> {
                    cvPostType.show()
                    ivPostType.setImageResource(R.drawable.lm_feed_ic_media_attachment)
                }

                VIDEO -> {
                    cvPostType.show()
                    ivPostType.setImageResource(R.drawable.lm_feed_ic_media_attachment)
                }

                DOCUMENT -> {
                    cvPostType.show()
                    ivPostType.setImageResource(R.drawable.lm_feed_ic_doc_attachment)
                }

                else -> {
                    cvPostType.hide()
                }
            }
        }
    }
}