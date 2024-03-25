package com.likeminds.feed.android.core.ui.widgets.viewmore.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.likeminds.feed.android.core.databinding.LmFeedViewMoreViewBinding
import com.likeminds.feed.android.core.ui.base.styles.LMFeedTextStyle
import com.likeminds.feed.android.core.ui.base.styles.setStyle
import com.likeminds.feed.android.core.ui.widgets.viewmore.style.LMFeedViewMoreViewStyle
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.hide
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.show
import com.likeminds.feed.android.core.utils.listeners.LMFeedOnClickListener

class LMFeedViewMoreView : ConstraintLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attributeSet: AttributeSet?) : super(context, attributeSet)

    constructor(context: Context, attributeSet: AttributeSet?, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle
    )

    private val inflater =
        (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)

    private val binding = LmFeedViewMoreViewBinding.inflate(inflater, this, true)

    //sets provided [viewMoreStyle] to the view more view
    fun setStyle(viewMoreStyle: LMFeedViewMoreViewStyle) {

        viewMoreStyle.apply {
            //set background color
            backgroundColor?.let {
                setBackgroundColor(ContextCompat.getColor(context, backgroundColor))
            }

            configureViewMoreText(viewMoreTextStyle)
            configureVisibleCountText(visibleCountTextStyle)
        }
    }

    private fun configureViewMoreText(viewMoreTextStyle: LMFeedTextStyle) {
        binding.tvViewMore.setStyle(viewMoreTextStyle)
    }

    private fun configureVisibleCountText(visibleCountTextStyle: LMFeedTextStyle?) {
        binding.tvVisibleCount.apply {
            if (visibleCountTextStyle == null) {
                hide()
            } else {
                setStyle(visibleCountTextStyle)
                show()
            }
        }
    }

    /**
     * Sets view more text to the view more text view.
     *
     * @param viewMoreText - string to be set for view more text.
     */
    fun setViewMoreText(viewMoreText: String) {
        binding.tvViewMore.text = viewMoreText
    }

    /**
     * Sets visible count text to the visible count text view.
     *
     * @param visibleCountText - string to be set for visible count text.
     */
    fun setVisibleCount(visibleCountText: String) {
        binding.tvVisibleCount.text = visibleCountText
    }

    /**
     * Sets click listener on the view more view
     *
     * @param listener [LMFeedOnClickListener] interface to have click listener
     */
    fun setViewMoreClickListener(listener: LMFeedOnClickListener) {
        binding.tvViewMore.setOnClickListener {
            listener.onClick()
        }
    }
}