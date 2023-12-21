package com.likeminds.feed.android.ui.base.views

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.button.MaterialButton
import com.likeminds.feed.android.ui.base.styles.LMFeedTextStyle
import com.likeminds.feed.android.ui.base.styles.setStyle

class LMFeedButton : MaterialButton {

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

    fun init(){
        this.iconGravity = ICON_GRAVITY_START
        this.totalPaddingEnd
    }
}