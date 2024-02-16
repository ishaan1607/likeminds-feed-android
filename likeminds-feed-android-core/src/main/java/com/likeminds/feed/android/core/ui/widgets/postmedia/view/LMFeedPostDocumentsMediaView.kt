package com.likeminds.feed.android.core.ui.widgets.postmedia.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.likeminds.feed.android.core.ui.widgets.postmedia.style.LMFeedPostDocumentsMediaStyle
import com.likeminds.feed.android.integration.databinding.LmFeedPostDocumentsMediaViewBinding
import com.likeminds.feed.android.ui.base.styles.*
import com.likeminds.feed.android.ui.utils.LMFeedOnClickListener
import com.likeminds.feed.android.ui.utils.LMFeedViewUtils.hide
import com.likeminds.feed.android.ui.utils.LMFeedViewUtils.show

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
    fun setDocumentName(documentName: String) {
        binding.tvDocumentName.text = documentName
    }

    /**
     * Sets the number of pages in document media in the post
     *
     * @param documentPages - string to be set for number of pages in document.
     */
    fun setDocumentPages(documentPages: String) {
        binding.tvDocumentPages.text = documentPages
    }

    /**
     * Sets the size of document media in the post
     *
     * @param documentSize - string to be set for the size of the document.
     */
    fun setDocumentSize(documentSize: String) {
        binding.tvDocumentSize.text = documentSize
    }

    /**
     * Sets the type of document media in the post
     *
     * @param documentType - string to be set for the type of the document.
     */
    fun setDocumentType(documentType: String) {
        binding.tvDocumentType.text = documentType
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