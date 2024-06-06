package com.likeminds.feed.android.core.poll.create.adapter

import android.content.Context
import android.view.*
import android.widget.ArrayAdapter
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.databinding.LmFeedItemPollDropdownBinding
import com.likeminds.feed.android.core.ui.base.styles.setStyle
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer

class LMFeedPollAdvancedOptionsAdapter(
    context: Context,
    list: List<String>
) : ArrayAdapter<String>(
    context,
    R.layout.lm_feed_item_poll_dropdown,
    list
) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return onBindView(parent, position)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return onBindView(parent, position)
    }

    private fun onBindView(parent: ViewGroup, position: Int): View {
        //get option
        val option = getItem(position) ?: ""

        //create binding
        val binding = LmFeedItemPollDropdownBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        //set text and style
        binding.tvDropdown.apply {
            text = option
            setStyle(LMFeedStyleTransformer.createPollFragmentViewStyle.pollDropdownViewStyle)
        }

        //return view
        return binding.root
    }
}