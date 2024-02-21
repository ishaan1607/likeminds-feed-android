package com.likeminds.feed.android.core.ui.widgets.postmedia.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.likeminds.feed.android.core.databinding.LmFeedPostMultipleMediaViewBinding
import com.likeminds.feed.android.core.post.model.LMFeedAttachmentViewData
import com.likeminds.feed.android.core.ui.widgets.postmedia.style.LMFeedPostMultipleMediaViewStyle
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalFeedAdapterListener
import com.likeminds.feed.android.core.universalfeed.adapter.databinders.LMFeedMultipleMediaPostAdapter

class LMFeedPostMultipleMediaView : ConstraintLayout {

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

    private val binding = LmFeedPostMultipleMediaViewBinding.inflate(inflater, this, true)

    val viewpagerMultipleMedia = binding.viewpagerMultipleMedia

    fun setStyle(postMultipleMediaStyle: LMFeedPostMultipleMediaViewStyle) {

        configureIndicator(postMultipleMediaStyle)
    }

    private fun configureIndicator(postMultipleMediaStyle: LMFeedPostMultipleMediaViewStyle) {
        binding.dotsIndicator.apply {
            //sets slide mode for the indicator
            postMultipleMediaStyle.indicatorSlideMode?.let { slideMode ->
                setSlideMode(slideMode)
            }

            //sets active/inactive color for indicator
            setSliderColor(
                context.getColor(postMultipleMediaStyle.indicatorActiveColor),
                context.getColor(postMultipleMediaStyle.indicatorInActiveColor)
            )

            //sets the active/inactive width for the indicator
            setSliderWidth(
                resources.getDimension(postMultipleMediaStyle.indicatorActiveWidth),
                resources.getDimension(
                    postMultipleMediaStyle.indicatorInactiveWidth
                        ?: postMultipleMediaStyle.indicatorActiveWidth
                )
            )

            //sets the style of the indicator
            postMultipleMediaStyle.indicatorStyle?.let { indicatorStyle ->
                setIndicatorStyle(indicatorStyle)
            }

            //sets spacing between the indicators
            postMultipleMediaStyle.indicatorSpacing?.let { spacing ->
                setIndicatorGap(resources.getDimension(spacing))
            }

            //sets the height of the indicator (works for indicator style = [DASH or ROUND_RECT])
            postMultipleMediaStyle.indicatorHeight?.let { indicatorHeight ->
                setSliderHeight(resources.getDimension(indicatorHeight))
            }
        }
    }

    fun setViewPager(
        listener: LMFeedUniversalFeedAdapterListener,
        attachments: List<LMFeedAttachmentViewData>
    ) {
        binding.apply {
            viewpagerMultipleMedia.isSaveEnabled = false

            //set adapter to the view pager
            val multipleMediaPostAdapter = LMFeedMultipleMediaPostAdapter(listener)
            viewpagerMultipleMedia.adapter = multipleMediaPostAdapter

            val multipleMediaOnPageChangeCallback = object : OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    listener.onPostMultipleMediaPageChangeCallback(position)
                }
            }

            //registers page change callback
            viewpagerMultipleMedia.registerOnPageChangeCallback(multipleMediaOnPageChangeCallback)

            //setups the indicator with the view pager
            dotsIndicator.setupWithViewPager(viewpagerMultipleMedia)

            //replaces all the items in the multiple media post adapter
            multipleMediaPostAdapter.replace(attachments)
        }
    }
}