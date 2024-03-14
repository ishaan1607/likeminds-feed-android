package com.likeminds.feed.android.core.universalfeed.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.likeminds.feed.android.core.databinding.LmFeedUniversalTopicSelectorBarBinding
import com.likeminds.feed.android.core.topics.model.LMFeedTopicViewData
import com.likeminds.feed.android.core.ui.base.styles.LMFeedTextStyle
import com.likeminds.feed.android.core.ui.base.styles.setStyle
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalSelectedTopicAdapterListener
import com.likeminds.feed.android.core.universalfeed.style.LMFeedUniversalTopicSelectorBarStyle
import com.likeminds.feed.android.core.utils.base.LMFeedBaseViewType
import com.likeminds.feed.android.core.utils.listeners.LMFeedOnClickListener

class LMFeedUniversalTopicSelectorBarView : ConstraintLayout {

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
    }

    private val inflater =
        (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)

    private val binding: LmFeedUniversalTopicSelectorBarBinding =
        LmFeedUniversalTopicSelectorBarBinding.inflate(inflater, this, true)

    fun setStyle(topicSelectorBarStyle: LMFeedUniversalTopicSelectorBarStyle) {
        topicSelectorBarStyle.apply {

            //sets background color
            backgroundColor?.let {
                setBackgroundColor(ContextCompat.getColor(context, it))
            }

            //sets the elevation of the header view
            elevation?.let {
                this@LMFeedUniversalTopicSelectorBarView.elevation = resources.getDimension(it)
            }

            configureAllTopicsSelector(allTopicsSelectorStyle)
            configureClearTopicFilter(clearTopicFilterStyle)
        }
    }

    private fun configureAllTopicsSelector(allTopicsSelectorStyle: LMFeedTextStyle) {
        binding.tvAllTopics.setStyle(allTopicsSelectorStyle)
    }

    private fun configureClearTopicFilter(clearTopicFilterStyle: LMFeedTextStyle) {
        binding.tvClear.setStyle(clearTopicFilterStyle)
    }

    /**
     * Sets text in all topics text view in the selector bar.
     *
     * @param allTopicsText Text for the all topics text view in the selector bar.
     */
    fun setAllTopicsText(allTopicsText: String) {
        binding.tvAllTopics.text = allTopicsText
    }

    /**
     * Sets text in clear topics text view in the selector bar.
     *
     * @param clearTopicsText Text for clear topics text view in the selector bar.
     */
    fun setClearTopicsText(clearTopicsText: String) {
        binding.tvClear.text = clearTopicsText
    }

    fun setAllTopicsClickListener(listener: LMFeedOnClickListener) {
        binding.tvAllTopics.setOnClickListener {
            listener.onClick()
        }
    }

    fun setClearSelectedTopicsClickListener(listener: LMFeedOnClickListener) {
        binding.tvClear.setOnClickListener {
            listener.onClick()
        }
    }

    fun setSelectedTopicAdapter(listener: LMFeedUniversalSelectedTopicAdapterListener) {
        binding.rvSelectedTopics.setAdapter(listener)
    }

    fun getAllSelectedTopics(): List<LMFeedBaseViewType> {
        return binding.rvSelectedTopics.allSelectedTopics()
    }

    fun replaceSelectedTopics(selectedTopics: List<LMFeedTopicViewData>) {
        binding.rvSelectedTopics.replaceSelectedTopics(selectedTopics)
    }

    fun clearSelectedTopicsAndNotify() {
        binding.rvSelectedTopics.clearAllSelectedTopicsAndNotify()
    }

    fun removeTopicAndNotify(position: Int) {
        binding.rvSelectedTopics.removeSelectedTopicAndNotify(position)
    }

    /**
     * Sets all topics text view visibility
     *
     * @param isVisible whether to make the all topics text view visible or not
     */
    fun setAllTopicsTextVisibility(isVisible: Boolean) {
        binding.tvAllTopics.isVisible = isVisible
    }

    /**
     * Sets selected topic filter visibility
     *
     * @param isVisible whether to make the selected topics filter visible or not
     */
    fun setSelectedTopicFilterVisibility(isVisible: Boolean) {
        binding.grpSelectedTopics.isVisible = isVisible
    }
}