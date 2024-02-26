package com.likeminds.feed.android.core.ui.widgets.headerview.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.likeminds.feed.android.core.databinding.LmFeedHeaderViewBinding
import com.likeminds.feed.android.core.ui.base.styles.LMFeedTextStyle
import com.likeminds.feed.android.core.ui.base.styles.setStyle
import com.likeminds.feed.android.core.ui.widgets.headerview.styles.LMFeedHeaderViewStyle
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.hide
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.show
import com.likeminds.feed.android.core.utils.listeners.LMFeedOnClickListener

class LMFeedHeaderView : ConstraintLayout {

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

    private val binding: LmFeedHeaderViewBinding =
        LmFeedHeaderViewBinding.inflate(inflater, this, true)

    fun setStyle(headerViewStyle: LMFeedHeaderViewStyle) {

        //sets background color
        setBackgroundColor(ContextCompat.getColor(context, headerViewStyle.backgroundColor))

        //sets the elevation of the header view
        elevation = resources.getDimension(headerViewStyle.elevation)

        //configure header view elements
        configureTitle(headerViewStyle.titleTextStyle)
        configureSubtitle(headerViewStyle.subtitleTextStyle)
        configureNavigationIcon(headerViewStyle)
        configureSearchIcon(headerViewStyle)
    }

    private fun configureTitle(titleTextStyle: LMFeedTextStyle) {
        binding.tvHeaderTitle.setStyle(titleTextStyle)
    }

    private fun configureSubtitle(subtitleTextStyle: LMFeedTextStyle?) {
        binding.tvHeaderSubtitle.apply {
            if (subtitleTextStyle == null) {
                hide()
            } else {
                setStyle(subtitleTextStyle)
                show()
            }
        }
    }

    private fun configureNavigationIcon(headerViewStyle: LMFeedHeaderViewStyle) {
        binding.ivHeaderNavigation.apply {
            if (headerViewStyle.navigationIconStyle == null) {
                hide()
            } else {
                setStyle(headerViewStyle.navigationIconStyle)
                show()
            }
        }
    }

    private fun configureSearchIcon(headerViewStyle: LMFeedHeaderViewStyle) {
        binding.ivHeaderSearch.apply {
            if (headerViewStyle.searchIconStyle == null) {
                hide()
            } else {
                setStyle(headerViewStyle.searchIconStyle)
                show()
            }
        }
    }

    /**
     * Sets title text in the header view.
     *
     * @param title Text for the title in the header.
     */
    fun setTitleText(title: String) {
        binding.tvHeaderTitle.text = title
    }

    /**
     * Sets subtitle text in the header view.
     *
     * @param subtitle Text for the subtitle in the header.
     */
    fun setSubTitleText(subtitle: String) {
        binding.tvHeaderSubtitle.text = subtitle
    }

    fun setNavigationIconClickListener(listener: LMFeedOnClickListener) {
        binding.ivHeaderNavigation.setOnClickListener {
            listener.onClick()
        }
    }

    fun setSearchIconClickListener(listener: LMFeedOnClickListener) {
        binding.ivHeaderSearch.setOnClickListener {
            listener.onClick()
        }
    }
}