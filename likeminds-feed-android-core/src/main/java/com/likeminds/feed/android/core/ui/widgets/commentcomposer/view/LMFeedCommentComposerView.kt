package com.likeminds.feed.android.core.ui.widgets.commentcomposer.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.likeminds.feed.android.core.databinding.LmFeedCommentComposerViewBinding
import com.likeminds.feed.android.core.ui.widgets.commentcomposer.style.LMFeedCommentComposerStyle

class LMFeedCommentComposerView : ConstraintLayout {

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

    private val binding: LmFeedCommentComposerViewBinding =
        LmFeedCommentComposerViewBinding.inflate(inflater, this, true)

    fun setStyle(commentComposerStyle: LMFeedCommentComposerStyle) {

    }
}