package com.likeminds.feed.android.core.ui.widgets.overflowmenu.view

import android.content.Context
import android.view.Menu.NONE
import android.view.View
import android.widget.PopupMenu
import com.likeminds.feed.android.core.utils.LMFeedOnClickListener

class LMFeedOverflowMenu(context: Context, view: View, gravity: Int) :
    PopupMenu(context, view, gravity) {

    /**
     * Sets click listener on the menu icon
     *
     * @param listener [LMFeedOnClickListener] interface to have click listener
     */
    fun setMenuItemClickListener(listener: LMFeedOnClickListener) {
        setOnMenuItemClickListener {
            listener.onClick()
            true
        }
    }

    fun addItem(menuItemId: Int, menuItemTitle: String) {
        menu.add(
            NONE,
            menuItemId,
            NONE,
            menuItemTitle
        )
    }
}