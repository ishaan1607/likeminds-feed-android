package com.likeminds.feed.android.core.ui.widgets.postmedia.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.databinding.LmFeedPostDocumentsMediaViewBinding
import com.likeminds.feed.android.core.ui.base.styles.*
import com.likeminds.feed.android.core.ui.widgets.postmedia.style.LMFeedPostDocumentsMediaStyle
import com.likeminds.feed.android.core.utils.LMFeedOnClickListener
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.hide
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

    fun setStyle(postDocumentMediaViewStyle: LMFeedPostDocumentsMediaStyle) {

        //set background color
        if (postDocumentMediaViewStyle.backgroundColor != null) {
            setBackgroundColor(
                ContextCompat.getColor(
                    context,
                    postDocumentMediaViewStyle.backgroundColor
                )
            )
        }

        configureDocumentName(postDocumentMediaViewStyle.documentNameStyle)
        configureDocumentIcon(postDocumentMediaViewStyle.documentIconStyle)
        configureDocumentPageCount(postDocumentMediaViewStyle.documentPageCountStyle)
        configureDocumentSize(postDocumentMediaViewStyle.documentSizeStyle)
        configureDocumentType(postDocumentMediaViewStyle.documentTypeStyle)
    }

    private fun configureDocumentName(documentNameStyle: LMFeedTextStyle) {
        binding.tvDocumentName.setStyle(documentNameStyle)
    }

    private fun configureDocumentIcon(documentIconStyle: LMFeedIconStyle?) {
        binding.ivDocumentIcon.apply {
            if (documentIconStyle == null) {
                hide()
            } else {
                show()
                setStyle(documentIconStyle)
            }
        }
    }

    private fun configureDocumentPageCount(documentPageCountStyle: LMFeedTextStyle?) {
        binding.tvDocumentPages.apply {
            if (documentPageCountStyle == null) {
                hide()
            } else {
                show()
                setStyle(documentPageCountStyle)
            }
        }
    }

    private fun configureDocumentSize(documentSizeStyle: LMFeedTextStyle?) {
        binding.tvDocumentSize.apply {
            if (documentSizeStyle == null) {
                hide()
            } else {
                show()
                setStyle(documentSizeStyle)
            }
        }
    }

    private fun configureDocumentType(documentTypeStyle: LMFeedTextStyle?) {
        binding.tvDocumentType.apply {
            if (documentTypeStyle == null) {
                hide()
            } else {
                show()
                setStyle(documentTypeStyle)
            }
        }
    }

    /**
     * Sets the name of the document media in the post
     *
     * @param documentName - string to be set for name of the document.
     */
    fun setDocumentName(documentName: String?) {
        binding.tvDocumentName.text = documentName ?: context.getString(R.string.lm_feed_documents)
    }

    /**
     * Sets the number of pages in document media in the post
     *
     * @param documentPages - number of pages in the document.
     */
    fun setDocumentPages(documentPages: Int?) {
        binding.tvDocumentPages.apply {
            val noOfPage = documentPages ?: 0

            if (noOfPage > 0) {
                show()
                text = context.resources.getQuantityString(
                    R.plurals.lm_feed_placeholder_pages,
                    noOfPage,
                    noOfPage
                )
            } else {
                hide()
            }
        }
    }

    // todo: ask if we should write the logic here or simply pass the string from outside and set here?
    /**
     * Sets the size of document media in the post
     *
     * @param documentSize - size of documents in long.
     */
    fun setDocumentSize(documentSize: Long?) {
        binding.apply {
            if (documentSize != null) {
                tvDocumentSize.show()
                // todo: add this once media is implemented
//                tvDocumentSize.text = MediaUtils.getFileSizeText(attachmentMeta.size)
                if (tvDocumentSize.isVisible) {
                    viewMetaDot1.show()
                } else {
                    viewMetaDot1.hide()
                }
            } else {
                tvDocumentSize.hide()
                viewMetaDot1.hide()
            }
        }
    }

    /**
     * Sets the type of document media in the post
     *
     * @param documentType - string to be set for the type of the document.
     */
    fun setDocumentType(documentType: String?) {
        binding.apply {
            if (!documentType.isNullOrEmpty() && (tvDocumentName.isVisible || tvDocumentSize.isVisible)) {
                tvDocumentType.show()
                tvDocumentType.text = documentType
                viewMetaDot2.show()
            } else {
                tvDocumentType.hide()
                viewMetaDot2.hide()
            }
        }
    }

    /**
     * Sets click listener on the document
     *
     * @param listener [LMFeedOnClickListener] interface to have click listener
     */
    fun setDocumentClickListener(listener: LMFeedOnClickListener) {
        binding.root.setOnClickListener {
            listener.onClick()
        }
    }
}