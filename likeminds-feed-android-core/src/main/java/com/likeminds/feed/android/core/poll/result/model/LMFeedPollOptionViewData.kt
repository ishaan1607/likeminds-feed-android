package com.likeminds.feed.android.core.poll.result.model

import android.os.Parcelable
import com.likeminds.feed.android.core.utils.base.LMFeedBaseViewType
import com.likeminds.feed.android.core.utils.base.model.ITEM_POST_POLL_OPTIONS
import com.likeminds.feed.android.core.utils.user.LMFeedUserViewData
import kotlinx.parcelize.Parcelize

@Parcelize
class LMFeedPollOptionViewData private constructor(
    val id: String,
    val isSelected: Boolean,
    val percentage: Float,
    val text: String,
    val voteCount: Int,
    val addedByUser: LMFeedUserViewData,
    val toShowResults: Boolean,
    val allowAddOption: Boolean,
) : Parcelable, LMFeedBaseViewType {

    override val viewType: Int
        get() = ITEM_POST_POLL_OPTIONS

    class Builder {
        private var id: String = ""
        private var isSelected: Boolean = false
        private var percentage: Float = 0f
        private var text: String = ""
        private var voteCount: Int = 0
        private var addedByUser: LMFeedUserViewData = LMFeedUserViewData.Builder().build()
        private var toShowResults: Boolean = false
        private var allowAddOption: Boolean = false

        fun id(id: String) = apply {
            this.id = id
        }

        fun isSelected(isSelected: Boolean) = apply {
            this.isSelected = isSelected
        }

        fun percentage(percentage: Float) = apply {
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

        fun toShowResults(toShowResults: Boolean) = apply {
            this.toShowResults = toShowResults
        }

        fun allowAddOption(allowAddOption: Boolean) = apply {
            this.allowAddOption = allowAddOption
        }

        fun build() = LMFeedPollOptionViewData(
            id,
            isSelected,
            percentage,
            text,
            voteCount,
            addedByUser,
            toShowResults,
            allowAddOption
        )
    }

    override fun toString(): String {
        return buildString {
            append("LMFeedPollOptionViewData(id='$id'\n")
            append("isSelected=$isSelected\n")
            append("percentage=$percentage\n")
            append("text='$text'\n")
            append("voteCount=$voteCount\n")
            append("addedByUser=$addedByUser\n")
            append("toShowResults=$toShowResults\n")
            append("allowAddOption=$allowAddOption)")
        }
    }

    fun toBuilder(): Builder {
        return Builder().id(id)
            .isSelected(isSelected)
            .percentage(percentage)
            .text(text)
            .voteCount(voteCount)
            .addedByUser(addedByUser)
            .toShowResults(toShowResults)
            .allowAddOption(allowAddOption)
    }
}