package com.likeminds.feed.android.ui.widgets.headerview.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.likeminds.feed.android.ui.base.styles.LMFeedTextStyle
import com.likeminds.feed.android.ui.base.styles.setStyle
import com.likeminds.feed.android.ui.databinding.LmFeedHeaderViewBinding
import com.likeminds.feed.android.ui.utils.LMFeedImageBindingUtil
import com.likeminds.feed.android.ui.utils.LMFeedOnClickListener
import com.likeminds.feed.android.ui.utils.ViewUtils.hide
import com.likeminds.feed.android.ui.utils.ViewUtils.setTint
import com.likeminds.feed.android.ui.utils.ViewUtils.show
import com.likeminds.feed.android.ui.widgets.headerview.styles.LMFeedHeaderViewStyle

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

        // sets background color
        setBackgroundColor(ContextCompat.getColor(context, headerViewStyle.backgroundColor))

        // configure header view elements
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
                show()
                setStyle(subtitleTextStyle)
            }
        }
    }

    private fun configureNavigationIcon(headerViewStyle: LMFeedHeaderViewStyle) {
        binding.ivHeaderNavigation.apply {
            if (headerViewStyle.navigationIcon == null) {
                this.hide()
            } else {
                this.show()
                LMFeedImageBindingUtil.loadImage(this, headerViewStyle.navigationIcon)

                // set tint of the icon
                if (headerViewStyle.navigationIconTint != null) {
                    setTint(headerViewStyle.navigationIconTint)
                }

                // sets padding for navigation icon
                val navigationIconPadding = headerViewStyle.navigationIconPadding
                if (navigationIconPadding != null) {
                    setPadding(
                        navigationIconPadding.paddingLeft,
                        navigationIconPadding.paddingTop,
                        navigationIconPadding.paddingRight,
                        navigationIconPadding.paddingBottom
                    )
                }
            }
        }
    }

    private fun configureSearchIcon(headerViewStyle: LMFeedHeaderViewStyle) {
        binding.ivHeaderSearch.apply {
            if (headerViewStyle.searchIcon == null) {
                this.hide()
            } else {
                this.show()
                LMFeedImageBindingUtil.loadImage(this, headerViewStyle.searchIcon)

                // set tint of the icon
                if (headerViewStyle.searchIconTint != null) {
                    setTint(headerViewStyle.searchIconTint)
                }

                // sets padding for search icon
                val searchIconPadding = headerViewStyle.searchIconPadding
                if (searchIconPadding != null) {
                    setPadding(
                        resources.getDimensionPixelOffset(searchIconPadding.paddingLeft),
                        resources.getDimensionPixelOffset(searchIconPadding.paddingTop),
                        resources.getDimensionPixelOffset(searchIconPadding.paddingRight),
                        resources.getDimensionPixelOffset(searchIconPadding.paddingBottom)
                    )
                }
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