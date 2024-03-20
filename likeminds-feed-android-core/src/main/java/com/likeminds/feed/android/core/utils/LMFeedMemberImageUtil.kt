package com.likeminds.feed.android.core.utils

import com.likeminds.feed.android.core.utils.generator.LMFeedColorGenerator
import com.likeminds.feed.android.core.utils.generator.LMFeedTextDrawable

object LMFeedMemberImageUtil {

    private val SIXTY_PX = LMFeedViewUtils.dpToPx(60)

    fun getNameDrawable(
        id: String?,
        name: String?,
        circle: Boolean? = false,
        roundRect: Boolean? = false
    ): Pair<LMFeedTextDrawable, Int> {
        val uniqueId = id ?: name ?: "LM"
        val nameCode = getNameInitial(name)
        val color = LMFeedColorGenerator.MATERIAL.getColor(uniqueId)
        val builder =
            LMFeedTextDrawable.builder().beginConfig().bold().height(SIXTY_PX).width(SIXTY_PX)
                .endConfig()
        val drawable = when {
            circle == true -> {
                builder.buildRound(nameCode, color)
            }

            roundRect == true -> {
                builder.buildRoundRect(nameCode, color, LMFeedViewUtils.dpToPx(10))
            }

            else -> {
                builder.buildRect(nameCode, color)
            }
        }
        return Pair(drawable, color)
    }

    private fun getNameInitial(
        userName: String?
    ): String {
        val name = userName?.trim()
        if (name.isNullOrEmpty()) {
            return "LM"
        }
        if (!name.contains(" ")) {
            return name[0].uppercase()
        }
        val nameParts = name.split(" ").map { it.trim() }
        return "${nameParts.first()[0].uppercase()}${nameParts.last()[0].uppercase()}"
    }
}