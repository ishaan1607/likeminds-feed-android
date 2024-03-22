package com.likeminds.feed.android.core.utils

internal fun emptyExtrasException(className: String) =
    IllegalStateException("$className cannot be called without passing a valid bundle")

internal const val UNKNOWN_ERROR = "Some unknown error occurred"