package com.likeminds.feed.android.core.poll.model

import com.likeminds.feed.android.core.utils.user.LMFeedUserViewData

class LMFeedPollOptionViewData private constructor(
    val id: String,
    val isSelected: Boolean,
    val percentage: Int,
    val text: String,
    val voteCount: Int,
    val addedByUser: LMFeedUserViewData
) {

    class Builder {
        private var id: String = ""
        private var isSelected: Boolean = false
        private var percentage: Int = 0
        private var text: String = ""
        private var voteCount: Int = 0
        private var addedByUser: LMFeedUserViewData = LMFeedUserViewData.Builder().build()

        fun id(id: String) = apply {
            this.id = id
        }

        fun isSelected(isSelected: Boolean) = apply {
            this.isSelected = isSelected
        }

        fun percentage(percentage: Int) = apply {
            this.percentage = percentage
        }

        fun text(text: String) = apply {
            this.text = text
        }

        fun voteCount(voteCount: Int) = apply {
            this.voteCount = voteCount
        }

        fun addedByUser(addedByUser: LMFeedUserViewData) = apply {
            this.addedByUser = addedByUser
        }

        fun build() = LMFeedPollOptionViewData(
            id,
            isSelected,
            percentage,
            text,
            voteCount,
            addedByUser
        )
    }

    fun toBuilder(): Builder {
        return Builder().id(id)
            .isSelected(isSelected)
            .percentage(percentage)
            .text(text)
            .voteCount(voteCount)
            .addedByUser(addedByUser)
    }
}