package com.likeminds.feed.android.core.ui.widgets.post.posttopicsview.view

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.google.android.material.chip.Chip
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.databinding.LmFeedPostTopicsViewBinding
import com.likeminds.feed.android.core.databinding.LmFeedTopicChipBinding
import com.likeminds.feed.android.core.topics.model.LMFeedTopicViewData
import com.likeminds.feed.android.core.ui.widgets.post.posttopicsview.style.LMFeedPostTopicsViewStyle
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer

class LMFeedPostTopicsView : ConstraintLayout {

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet) {
    }

    constructor(context: Context, attributeSet: AttributeSet?, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle
    ) {
    }

    private val inflater =
        (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)

    private val binding = LmFeedPostTopicsViewBinding.inflate(inflater, this, true)

    fun setStyle(postTopicsViewStyle: LMFeedPostTopicsViewStyle) {
        postTopicsViewStyle.apply {
            configureChipGroup(this)
        }
    }

    fun setChipStyle(chip: Chip, postTopicsViewStyle: LMFeedPostTopicsViewStyle) {
//        chip.apply {
//
//            //chip background
//            setChipBackgroundColorResource(postTopicsViewStyle.chipBackgroundColor)
//
//            //chip stroke width
//            if (postTopicsViewStyle.chipStrokeWidth == null) {
//                setChipStrokeWidthResource(R.dimen.zero_dp)
//            } else {
//                setChipStrokeWidthResource(postTopicsViewStyle.chipStrokeWidth)
//            }
//
//            //chip stroke width
//            postTopicsViewStyle.chipStrokeWidth?.let {
//                setChipStrokeWidthResource(it)
//            }
//
//            //chip min height
//            postTopicsViewStyle.chipMinHeight?.let {
//                setChipMinHeightResource(it)
//            }
//
//            //chip start padding
//            postTopicsViewStyle.chipStartPadding?.let {
//                setChipStartPaddingResource(it)
//            }
//
//            //chip end padding
//            postTopicsViewStyle.chipEndPadding?.let {
//                setChipEndPaddingResource(it)
//            }
//
//            val shape = this.shapeAppearanceModel.toBuilder()
//                .setAllCornerSizes(context.resources.getDimension(postTopicsViewStyle.chipCornerRadius))
//                .build()
//
//            shapeAppearanceModel = shape
//        }
    }

    private fun configureChipGroup(postTopicsViewStyle: LMFeedPostTopicsViewStyle) {
//        binding.cgTopics.apply {
//            isSingleLine = postTopicsViewStyle.isSingleLine
//            setChipSpacingHorizontalResource(postTopicsViewStyle.chipGroupHorizontalSpacing)
//            setChipSpacingVerticalResource(postTopicsViewStyle.chipGroupVerticalSpacing)
//        }
    }

    fun removeAllTopics() {
        binding.cgTopics.removeAllViews()
    }

//    fun addTopic(topicViewData: LMFeedTopicViewData) {
//        val context = binding.cgTopics.context
//        val chipBinding = LmFeedTopicChipBinding.inflate(
//            LayoutInflater.from(context),
//            binding.cgTopics,
//            false
//        )
//
//        val topicStyle = LMFeedStyleTransformer.postViewStyle.postTopicsViewStyle
//
//        chipBinding.chipTopic.apply {
//            //text related
//            text = topicViewData.name
//            setTextSize(
//                TypedValue.COMPLEX_UNIT_PX,
//                context.resources.getDimension(topicStyle.chipTextSize)
//            )
//            setTextColor(
//                ColorStateList.valueOf(
//                    ContextCompat.getColor(
//                        context,
//                        topicStyle.chipTextColor
//                    )
//                )
//            )
//            setEnsureMinTouchTargetSize(false)
//
//            this@LMFeedPostTopicsView.setChipStyle(this, topicStyle)
//        }
//
//        binding.cgTopics.addView(chipBinding.chipTopic)
//    }
}