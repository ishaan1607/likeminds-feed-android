package com.likeminds.feed.android.core.ui.base.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.google.android.material.chip.ChipGroup
import com.likeminds.feed.android.core.databinding.LmFeedTopicChipBinding
import com.likeminds.feed.android.core.ui.base.styles.setStyle
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer

class LMFeedChipGroup @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ChipGroup(context, attrs, defStyleAttr) {

    //removes all the chips from the chip group
    fun removeAllChips() {
        this.removeAllViews()
    }

    fun addChip(chipText: String) {
        val chipBinding = LmFeedTopicChipBinding.inflate(
            LayoutInflater.from(context),
            this,
            false
        )

        val chipStyle = LMFeedStyleTransformer.postViewStyle.postTopicChipsStyle

        chipBinding.chipTopic.apply {
            //text related
            text = chipText
            setStyle(chipStyle)
        }

        this.addView(chipBinding.chipTopic)
    }
}