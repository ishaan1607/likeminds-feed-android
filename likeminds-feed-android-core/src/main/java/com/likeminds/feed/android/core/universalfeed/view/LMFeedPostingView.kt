package com.likeminds.feed.android.core.universalfeed.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout

class LMFeedPostingView : ConstraintLayout {

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
}