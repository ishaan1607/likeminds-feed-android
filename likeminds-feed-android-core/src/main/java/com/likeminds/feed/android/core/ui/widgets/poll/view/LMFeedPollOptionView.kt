package com.likeminds.feed.android.core.ui.widgets.poll.view

import android.content.Context
import android.graphics.drawable.*
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.databinding.LmFeedPollOptionViewBinding
import com.likeminds.feed.android.core.poll.result.model.LMFeedPollOptionViewData
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.ui.widgets.poll.style.LMFeedPostPollOptionViewStyle
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer
import com.likeminds.feed.android.core.utils.LMFeedViewUtils
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.hide
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.show
import com.likeminds.feed.android.core.utils.listeners.LMFeedOnClickListener
import com.likeminds.feed.android.core.utils.user.LMFeedUserPreferences
import kotlin.math.roundToInt

/**
 * Represents a poll option view
 * To customize this view use [LMFeedPostPollOptionViewStyle]
 */
class LMFeedPollOptionView : ConstraintLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)

    constructor(context: Context, attributeSet: AttributeSet?, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle
    )

    private val inflater =
        (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)

    private val binding = LmFeedPollOptionViewBinding.inflate(inflater, this, true)

    //sets provided [postPollOptionViewStyle] to the post poll option view
    fun setStyle(postPollOptionViewStyle: LMFeedPostPollOptionViewStyle) {
        postPollOptionViewStyle.apply {
            configurePollOptionText(pollOptionTextStyle)
            configurePollOptionAddedByText(pollOptionAddedByTextStyle)
            configurePollOptionCheckedIcon(pollOptionCheckIconStyle)
            configurePollOptionVotesCountText(pollOptionVotesCountTextStyle)
        }
    }

    private fun configurePollOptionText(pollOptionTextStyle: LMFeedTextStyle) {
        binding.tvPollOption.setStyle(pollOptionTextStyle)
    }

    private fun configurePollOptionAddedByText(pollOptionAddedByTextStyle: LMFeedTextStyle?) {
        binding.tvAddedBy.apply {
            if (pollOptionAddedByTextStyle == null) {
                hide()
            } else {
                setStyle(pollOptionAddedByTextStyle)
            }
        }
    }

    private fun configurePollOptionCheckedIcon(pollOptionCheckIconStyle: LMFeedIconStyle?) {
        binding.ivChecked.apply {
            if (pollOptionCheckIconStyle == null) {
                hide()
            } else {
                setStyle(pollOptionCheckIconStyle)
            }
        }
    }

    private fun configurePollOptionVotesCountText(pollOptionVotesCountTextStyle: LMFeedTextStyle?) {
        binding.tvNoVotes.apply {
            if (pollOptionVotesCountTextStyle == null) {
                hide()
            } else {
                setStyle(pollOptionVotesCountTextStyle)
            }
        }
    }

    /**
     * Sets the text to the poll option
     *
     * @param pollOptionText - string to be set to the poll option.
     */
    fun setPollOptionText(pollOptionText: String) {
        binding.tvPollOption.text = pollOptionText
    }

    /**
     * Sets the text of the user who added the poll option
     *
     * @param pollOptionViewData - data of the poll option.
     */
    fun setPollOptionAddedByText(pollOptionViewData: LMFeedPollOptionViewData) {
        val postMediaStyle = LMFeedStyleTransformer.postViewStyle.postMediaViewStyle
        postMediaStyle.postPollMediaStyle?.pollOptionsViewStyle?.pollOptionAddedByTextStyle
            ?: return

        binding.tvAddedBy.apply {
            if (pollOptionViewData.allowAddOption) {
                val addedByUser = pollOptionViewData.addedByUser

                val userPreferences = LMFeedUserPreferences(context)
                val loggedInUUID = userPreferences.getUUID()

                text = if (addedByUser.sdkClientInfoViewData.uuid == loggedInUUID) {
                    context.getString(R.string.lm_feed_added_by_you)
                } else {
                    context.getString(R.string.lm_feed_added_by_s, addedByUser.name)
                }
                show()
            } else {
                hide()
            }
        }
    }

    /**
     * Sets the visibility of the checked icon of the poll option
     *
     * @param pollOptionViewData - data of the poll option.
     */
    fun setPollOptionCheckedIconVisibility(pollOptionViewData: LMFeedPollOptionViewData) {
        val postMediaStyle = LMFeedStyleTransformer.postViewStyle.postMediaViewStyle
        postMediaStyle.postPollMediaStyle?.pollOptionsViewStyle?.pollOptionCheckIconStyle
            ?: return

        binding.ivChecked.apply {
            if ((pollOptionViewData.isMultiChoicePoll || !pollOptionViewData.isInstantPoll)
                && pollOptionViewData.isSelected
            ) {
                show()
            } else {
                hide()
            }
        }
    }

    /**
     * Sets the text for the votes count on the poll option
     *
     * @param pollOptionViewData - data of the poll option.
     */
    fun setPollVotesCountText(pollOptionViewData: LMFeedPollOptionViewData) {
        val postMediaStyle = LMFeedStyleTransformer.postViewStyle.postMediaViewStyle
        postMediaStyle.postPollMediaStyle?.pollOptionsViewStyle?.pollOptionVotesCountTextStyle
            ?: return

        binding.tvNoVotes.apply {
            if (pollOptionViewData.toShowResults) {
                text = context.resources.getQuantityString(
                    R.plurals.lm_feed_votes_count,
                    pollOptionViewData.voteCount,
                    pollOptionViewData.voteCount
                )
                show()
            } else {
                hide()
            }
        }
    }

    /**
     * Sets poll option background and its progress as per the percentage of votes on the option
     *
     * @param pollOptionViewData [LMFeedPollOptionViewData] data for the poll option
     * @param pollOptionViewStyle [LMFeedPostPollOptionViewStyle] view style for the poll option
     */
    fun setPollOptionBackgroundAndProgress(
        pollOptionViewData: LMFeedPollOptionViewData,
        pollOptionViewStyle: LMFeedPostPollOptionViewStyle
    ) {
        binding.apply {
            val progressDrawable = pbPollBackground.progressDrawable as LayerDrawable
            val progressClip =
                progressDrawable.findDrawableByLayerId(R.id.lm_feed_poll_progress_clip) as ClipDrawable

            val optionBackgroundDrawable = binding.clPollOption.background as GradientDrawable
            optionBackgroundDrawable.mutate()
            val strokeWidth = LMFeedViewUtils.dpToPx(1)

            if (pollOptionViewData.toShowResults) {
                //set progress as per the percentage of votes
                pbPollBackground.progress = pollOptionViewData.percentage.roundToInt()
            } else {
                //set progress to 0 if results are not to be shown
                pbPollBackground.progress = 0
            }

            if (pollOptionViewData.isSelected) {
                //set progress clip color to selected option color
                progressClip.setTint(
                    ContextCompat.getColor(
                        root.context,
                        pollOptionViewStyle.pollSelectedOptionColor
                    )
                )

                //set option stroke color to selected option color
                optionBackgroundDrawable.setStroke(
                    strokeWidth,
                    ContextCompat.getColor(
                        root.context,
                        pollOptionViewStyle.pollSelectedOptionColor
                    )
                )
            } else {
                //set progress clip color to other option color
                progressClip.setTint(
                    ContextCompat.getColor(
                        root.context,
                        pollOptionViewStyle.pollOtherOptionColor
                    )
                )

                //set option stroke color to other option color
                optionBackgroundDrawable.setStroke(
                    strokeWidth,
                    ContextCompat.getColor(
                        root.context,
                        pollOptionViewStyle.pollOtherOptionColor
                    )
                )
            }
        }
    }

    /**
     * Sets click listener on the poll option
     *
     * @param listener [LMFeedOnClickListener] interface to have click listener
     */
    fun setPollOptionClicked(listener: LMFeedOnClickListener) {
        binding.clPollOption.setOnClickListener {
            listener.onClick()
        }
    }

    /**
     * Sets click listener on the poll option votes count text
     *
     * @param listener [LMFeedOnClickListener] interface to have click listener
     */
    fun setPollOptionVotesCountClicked(listener: LMFeedOnClickListener) {
        binding.tvNoVotes.setOnClickListener {
            listener.onClick()
        }
    }
}