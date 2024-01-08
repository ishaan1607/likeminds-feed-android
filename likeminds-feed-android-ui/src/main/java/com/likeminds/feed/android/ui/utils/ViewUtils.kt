package com.likeminds.feed.android.ui.utils

import android.content.res.ColorStateList
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat

object ViewUtils {

    fun ImageView.setTint(@ColorRes tint: Int) {
        ImageViewCompat.setImageTintList(
            this,
            ColorStateList.valueOf(ContextCompat.getColor(context, tint))
        )
    }

    fun View.hide() {
        visibility = View.GONE
    }

    fun View.show() {
        visibility = View.VISIBLE
    }
}