package com.likeminds.feed.android.core.ui.widgets.headerview.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.likeminds.feed.android.core.databinding.LmFeedHeaderViewBinding
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.ui.widgets.headerview.style.LMFeedHeaderViewStyle
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.hide
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.show
import com.likeminds.feed.android.core.utils.listeners.LMFeedOnClickListener

class LMFeedHeaderView : ConstraintLayout {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    private lateinit var style: LMFeedHeaderViewStyle

    private val inflater =
        (context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)

    private val binding: LmFeedHeaderViewBinding =
        LmFeedHeaderViewBinding.inflate(inflater, this, true)

    fun setStyle(headerViewStyle: LMFeedHeaderViewStyle) {

        headerViewStyle.apply {
            style = this

            //sets background color
            setBackgroundColor(ContextCompat.getColor(context, backgroundColor))

            //sets the elevation of the header view
            this@LMFeedHeaderView.elevation = resources.getDimension(elevation)

            //configure header view elements
            configureTitle(titleTextStyle)
            configureSubtitle(subtitleTextStyle)
            configureNavigationIcon(navigationIconStyle)
            configureSearchIcon(searchIconStyle)
            configureSubmitText(submitTextStyle)
        }
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
            }
        }
    }

    private fun configureNavigationIcon(navigationIconStyle: LMFeedIconStyle?) {
        binding.ivHeaderNavigation.apply {
            if (navigationIconStyle == null) {
                hide()
            } else {
                setStyle(navigationIconStyle)
                show()
            }
        }
    }

    private fun configureSearchIcon(searchIconStyle: LMFeedIconStyle?) {
        binding.ivHeaderSearch.apply {
            if (searchIconStyle == null) {
                hide()
            } else {
                setStyle(searchIconStyle)
                show()
            }
        }
    }

    private fun configureSubmitText(submitTextStyle: LMFeedTextStyle?) {
        binding.tvHeaderSubmit.apply {
            if (submitTextStyle == null) {
                hide()
            } else {
                setStyle(submitTextStyle)
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
        binding.tvHeaderSubtitle.apply {
            binding.tvHeaderSubtitle.text = subtitle
            show()
        }
    }

    /**
     * Sets submit text in the header view.
     *
     * @param submitText Text for the submit text in the header.
     */
    fun setSubmitText(submitText: String) {
        binding.tvHeaderSubmit.text = submitText
    }

    fun setSubmitButtonEnabled(isEnabled: Boolean, showProgress: Boolean = false) {
        binding.apply {
            if (showProgress) {
                pbSubmit.show()
                tvHeaderSubmit.hide()
            } else {
                pbSubmit.hide()
                tvHeaderSubmit.show()
                if (isEnabled) {
                    tvHeaderSubmit.isEnabled = true
                    style.activeSubmitColor?.let {
                        tvHeaderSubmit.setTextColor(ContextCompat.getColor(context, it))
                    }
                } else {
                    tvHeaderSubmit.isEnabled = false
                    style.submitTextStyle?.textColor?.let {
                        tvHeaderSubmit.setTextColor(ContextCompat.getColor(context, it))
                    }
                }
            }
        }
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

    fun setSubmitButtonClickListener(listener: LMFeedOnClickListener) {
        binding.tvHeaderSubmit.setOnClickListener {
            listener.onClick()
        }
    }
}