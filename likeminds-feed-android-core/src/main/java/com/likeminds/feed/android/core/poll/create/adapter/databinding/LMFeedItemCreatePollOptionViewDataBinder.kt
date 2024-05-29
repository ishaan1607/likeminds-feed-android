package com.likeminds.feed.android.core.poll.create.adapter.databinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import com.likeminds.feed.android.core.databinding.LmFeedItemCreatePollOptionBinding
import com.likeminds.feed.android.core.poll.create.adapter.LMFeedCreatePollOptionAdapterListener
import com.likeminds.feed.android.core.poll.create.model.LMFeedCreatePollOptionViewData
import com.likeminds.feed.android.core.ui.base.styles.setStyle
import com.likeminds.feed.android.core.utils.LMFeedStyleTransformer
import com.likeminds.feed.android.core.utils.base.LMFeedViewDataBinder
import com.likeminds.feed.android.core.utils.base.model.ITEM_CREATE_POLL_OPTION

class LMFeedItemCreatePollOptionViewDataBinder(
    val listener: LMFeedCreatePollOptionAdapterListener
) : LMFeedViewDataBinder<LmFeedItemCreatePollOptionBinding, LMFeedCreatePollOptionViewData>() {
    override val viewType: Int
        get() = ITEM_CREATE_POLL_OPTION

    override fun createBinder(parent: ViewGroup): LmFeedItemCreatePollOptionBinding {
        val binding = LmFeedItemCreatePollOptionBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        setListeners(binding)
        setViewStyle(binding)

        return binding
    }

    override fun bindData(
        binding: LmFeedItemCreatePollOptionBinding,
        data: LMFeedCreatePollOptionViewData,
        position: Int
    ) {
        binding.apply {
            //assign to binding
            createPollOption = data

            //add to viewmodel mapping
            listener.onPollOptionBinded(position, this)

            //clear text
            etOption.setText("")

            etOption.doAfterTextChanged { option ->
                if (!option.isNullOrEmpty()) {
                    listener.onPollOptionFilled()
                }
            }

            //set existing data
            val pollOption = data.text
            if (pollOption.isNotEmpty()) {
                etOption.setText(pollOption)
            }
        }
    }

    //set listeners
    private fun setListeners(binding: LmFeedItemCreatePollOptionBinding) {
        binding.ivCross.setOnClickListener {
            //clear focus
            binding.etOption.clearFocus()

            //get data
            val pollOption = binding.createPollOption ?: return@setOnClickListener

            //call listener
            listener.onPollOptionRemoved(pollOption)
        }
    }

    //set view style
    private fun setViewStyle(binding: LmFeedItemCreatePollOptionBinding) {
        val pollOptionsViewStyle =
            LMFeedStyleTransformer.createPollFragmentViewStyle.pollOptionsViewStyle

        binding.etOption.setStyle(pollOptionsViewStyle.optionViewStyle)
        binding.ivCross.setStyle(pollOptionsViewStyle.removeOptionViewStyle)
    }
}