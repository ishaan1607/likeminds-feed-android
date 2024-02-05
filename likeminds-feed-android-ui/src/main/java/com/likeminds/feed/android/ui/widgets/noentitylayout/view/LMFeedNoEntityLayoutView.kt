package com.likeminds.feed.android.ui.widgets.noentitylayout.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.likeminds.feed.android.ui.base.styles.*
import com.likeminds.feed.android.ui.databinding.LmFeedNoEntityLayoutViewBinding
import com.likeminds.feed.android.ui.utils.LMFeedOnClickListener
import com.likeminds.feed.android.ui.utils.LMFeedViewUtils.hide
import com.likeminds.feed.android.ui.utils.LMFeedViewUtils.show
import com.likeminds.feed.android.ui.widgets.noentitylayout.style.LMFeedNoEntityLayoutViewStyle

class LMFeedNoEntityLayoutView : ConstraintLayout {

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

    private val binding = LmFeedNoEntityLayoutViewBinding.inflate(inflater, this, true)


    /**
     * Sets title text in the no entity layout view.
     *
     * @param title Text for the title in the header.
     */
    fun setTitleText(title: String) {
        binding.tvNoEntityTitle.text = title
    }

    /**
     * Sets subtitle text in the no entity layout view.
     *
     * @param subtitle Text for the subtitle in the header.
     */
    fun setSubtitleText(subtitle: String) {
        binding.tvNoEntitySubtitle.text = subtitle
    }

    /**
     * Sets click listener on the action FAB of the no entity layout view
     *
     * @param listener [LMFeedOnClickListener] interface to have click listener
     */
    fun setActionFABClickListener(listener: LMFeedOnClickListener) {
        binding.fabAction.setOnClickListener {
            listener.onClick()
        }
    }

    fun setStyle(noEntityLayoutViewStyle: LMFeedNoEntityLayoutViewStyle) {

        //set background color
        setBackgroundColor(ContextCompat.getColor(context, noEntityLayoutViewStyle.backgroundColor))

        configureTitle(noEntityLayoutViewStyle.titleStyle)
        configureSubtitle(noEntityLayoutViewStyle.subtitleStyle)
        configureImage(noEntityLayoutViewStyle.imageStyle)
        configureActionCTA(noEntityLayoutViewStyle.actionStyle)
    }

    private fun configureTitle(titleStyle: LMFeedTextStyle) {
        binding.tvNoEntityTitle.setStyle(titleStyle)
    }

    private fun configureSubtitle(subtitleStyle: LMFeedTextStyle?) {
        binding.tvNoEntitySubtitle.apply {
            if (subtitleStyle == null) {
                hide()
            } else {
                show()
                setStyle(subtitleStyle)
            }
        }
    }

    private fun configureImage(imageStyle: LMFeedImageStyle?) {
        binding.ivImage.apply {
            if (imageStyle == null) {
                hide()
            } else {
                show()
                setStyle(imageStyle)
            }
        }
    }

    private fun configureActionCTA(actionStyle: LMFeedFABStyle?) {
        binding.fabAction.apply {
            if (actionStyle == null) {
                hide()
            } else {
                show()
                setStyle(actionStyle)
            }
        }
    }
}