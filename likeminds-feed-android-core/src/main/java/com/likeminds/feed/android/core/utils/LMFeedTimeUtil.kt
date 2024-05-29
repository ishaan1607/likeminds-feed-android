package com.likeminds.feed.android.core.utils

import android.content.Context
import android.text.format.DateUtils
import com.likeminds.feed.android.core.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object LMFeedTimeUtil {

    private const val DAY_IN_MILLIS = 24 * 60 * 60 * 1000
    private const val HOUR_IN_MILLIS = 60 * 60 * 1000
    private const val MINUTE_IN_MILLIS = 60 * 1000


    //to get the relative time for post/comment/reply
    fun getRelativeCreationTimeInString(createdTime: Long): String {
        val timeDifference = System.currentTimeMillis() - createdTime
        return getDaysHoursOrMinutes(timeDifference)
    }

    //to get the relative time for expiry
    fun getRelativeExpiryTimeInString(expiryTime: Long): String {
        val timeDifference = expiryTime - System.currentTimeMillis()
        return getDaysHoursOrMinutes(timeDifference)
    }

    // Sets the time of the post as
    // x min (if days & hours are 0 and min > 0)
    // x h (if days are 0)
    // x d (if days are greater than 1)
    // Just Now (otherwise)
    private fun getDaysHoursOrMinutes(timestamp: Long): String {
        val days = (timestamp / DAY_IN_MILLIS).toInt()
        val hours = ((timestamp - (days * DAY_IN_MILLIS)) / HOUR_IN_MILLIS).toInt()
        val minutes =
            ((timestamp - (days * DAY_IN_MILLIS) - (hours * HOUR_IN_MILLIS)) / MINUTE_IN_MILLIS).toInt()
        return when {
            days == 0 && hours == 0 && minutes > 0 -> "$minutes min"
            days == 0 && hours == 1 -> "${hours}h"
            days == 0 && hours > 1 -> "${hours}h"
            days == 1 && hours == 0 -> "${days}d"
            days >= 1 -> "${days}d"
            else -> "Just Now"
        }
    }

    /**
     * @param timestamp epoch time in milliseconds
     * @return time in " time ago" format
     * */
    fun getRelativeTime(context: Context, timestamp: Long): String {
        val relativeTime = DateUtils.getRelativeTimeSpanString(
            timestamp,
            System.currentTimeMillis(),
            DateUtils.MINUTE_IN_MILLIS
        ).toString()
        return if (relativeTime == "0 minutes ago") {
            context.getString(R.string.lm_feed_just_now)
        } else {
            relativeTime
        }
    }

    /**
     * @param timestamp epoch time in milliseconds
     * @return time in "dd MMM yyyy HH:mm a" format
     */
    fun getDateFormat(timestamp: Long): String{
        val sdf = SimpleDateFormat(
            "dd MMM yyyy HH:mm a",
            Locale.getDefault()
        )
        return sdf.format(Date(timestamp))
    }
}