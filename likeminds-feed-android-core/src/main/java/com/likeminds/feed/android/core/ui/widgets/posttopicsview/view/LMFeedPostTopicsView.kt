package com.likeminds.feed.android.core.ui.widgets.posttopicsview.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.likeminds.feed.android.core.databinding.LmFeedPostTopicsViewBinding
import com.likeminds.feed.android.core.ui.widgets.posttopicsview.style.LMFeedPostTopicsViewStyle

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

    private fun configureChipGroup(postTopicsViewStyle: LMFeedPostTopicsViewStyle) {
        binding.cgTopics.apply {
            isSingleLine = postTopicsViewStyle.isSingleLine
            setChipSpacingHorizontalResource(postTopicsViewStyle.chipGroupHorizontalSpacing)
            setChipSpacingVerticalResource(postTopicsViewStyle.chipGroupVerticalSpacing)
        }
    }
}