package com.likeminds.feed.android.core.ui.widgets.postmedia.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.likeminds.feed.android.core.databinding.LmFeedPostDocumentsMediaViewBinding
import com.likeminds.feed.android.core.ui.base.styles.LMFeedTextStyle
import com.likeminds.feed.android.core.ui.base.styles.setStyle
import com.likeminds.feed.android.core.ui.widgets.postmedia.style.LMFeedPostDocumentsMediaViewStyle
import com.likeminds.feed.android.core.universalfeed.adapter.LMFeedUniversalFeedAdapterListener
import com.likeminds.feed.android.core.universalfeed.model.LMFeedMediaViewData
import com.likeminds.feed.android.core.universalfeed.model.LMFeedPostViewData
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.show

class LMFeedPostDocumentsMediaView : ConstraintLayout {

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

    private val binding = LmFeedPostDocumentsMediaViewBinding.inflate(inflater, this, true)

    fun setStyle(postDocumentsMediaViewStyle: LMFeedPostDocumentsMediaViewStyle) {

        configureShowMore(postDocumentsMediaViewStyle.documentShowMoreStyle)
    }

    private fun configureShowMore(documentShowMoreStyle: LMFeedTextStyle?) {
        binding.tvShowMore.apply {
            documentShowMoreStyle?.let {
                setStyle(documentShowMoreStyle)
                show()
            }
        }
    }

    fun setAdapter(
        mediaData: LMFeedMediaViewData,
        listener: LMFeedUniversalFeedAdapterListener
    ) {
        binding.apply {
            rvDocuments.setAdapter(
                mediaData,
                tvShowMore,
                listener
            )
        }
    }

    fun setShowMoreTextClickListener(
        postViewData: LMFeedPostViewData?,
        position: Int,
        listener: LMFeedUniversalFeedAdapterListener
    ) {
        binding.tvShowMore.setOnClickListener {
            postViewData?.let { postViewData ->
                listener.onPostMultipleDocumentsExpanded(postViewData, position)
            }
        }
    }
}