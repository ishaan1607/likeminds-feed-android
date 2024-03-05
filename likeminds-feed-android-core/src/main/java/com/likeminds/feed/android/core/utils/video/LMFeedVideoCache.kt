package com.likeminds.feed.android.core.utils.video

import android.content.Context
import com.google.android.exoplayer2.database.StandaloneDatabaseProvider
import com.google.android.exoplayer2.upstream.cache.LeastRecentlyUsedCacheEvictor
import com.google.android.exoplayer2.upstream.cache.SimpleCache
import java.io.File

class LMFeedVideoCache {
    companion object {
        private var sDownloadCache: SimpleCache? = null

        fun getInstance(context: Context): SimpleCache {
            val exoPlayerCacheSize = 50 * 1024 * 1024.toLong()// Set the size of cache for video
            val leastRecentlyUsedCacheEvictor =
                LeastRecentlyUsedCacheEvictor(exoPlayerCacheSize)
            val exoDatabaseProvider = StandaloneDatabaseProvider(context)

            val cache = File(context.cacheDir, "lm_feed_video_cache")
            if (!cache.exists()) {
                cache.mkdirs()
            }

            if (sDownloadCache == null) {
                sDownloadCache =
                    SimpleCache(cache, leastRecentlyUsedCacheEvictor, exoDatabaseProvider)
            }
            return sDownloadCache!!
        }
    }
}