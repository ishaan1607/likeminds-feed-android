package com.likeminds.feed.android.core.ui.widgets.postmedia.view

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.google.android.exoplayer2.upstream.DefaultAllocator
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.upstream.cache.CacheDataSource
import com.google.android.exoplayer2.util.Util
import com.likeminds.feed.android.core.ui.widgets.postmedia.style.LMFeedPostVideoMediaStyle
import com.likeminds.feed.android.core.utils.LMFeedVideoCache
import com.likeminds.feed.android.core.R
import com.likeminds.feed.android.core.ui.base.views.LMFeedProgressBar
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.hide
import com.likeminds.feed.android.core.utils.LMFeedViewUtils.show

// todo: add customization for video
class LMFeedPostVideoMediaView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : StyledPlayerView(context, attrs, defStyleAttr) {

    private lateinit var exoPlayer: ExoPlayer
    private var progressBar: LMFeedProgressBar? = null

    private var lastPos: Long = 0

    // creates an instance with DataSourceFactory for reading and writing cache
    private val cacheDataSourceFactory by lazy {
        CacheDataSource.Factory()
            .setCache(LMFeedVideoCache.getInstance(context))
            .setUpstreamDataSourceFactory(
                DefaultHttpDataSource.Factory()
                    .setUserAgent(
                        Util.getUserAgent(
                            context, context.getString(
                                R.string.lm_feed_app_name
                            )
                        )
                    )
            )
            .setFlags(CacheDataSource.FLAG_IGNORE_CACHE_ON_ERROR)
    }

    init {
        descendantFocusability = FOCUS_AFTER_DESCENDANTS
        init()
    }

    // initializes the exoplayer and sets player
    private fun init() {
        // used to configure ms of media to buffer before starting playback
        val defaultLoadControl = DefaultLoadControl.Builder()
            .setBufferDurationsMs(
                DefaultLoadControl.DEFAULT_MAX_BUFFER_MS,
                DefaultLoadControl.DEFAULT_MAX_BUFFER_MS,
                DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_MS,
                DefaultLoadControl.DEFAULT_BUFFER_FOR_PLAYBACK_AFTER_REBUFFER_MS
            )
            .setAllocator(DefaultAllocator(true, 16))
            .setPrioritizeTimeOverSizeThresholds(true)
            .build()

        exoPlayer = ExoPlayer.Builder(context)
            .setLoadControl(defaultLoadControl)
            .build()

        exoPlayer.repeatMode = Player.REPEAT_MODE_ONE
        exoPlayer.playWhenReady = false
        player = exoPlayer

        exoPlayer.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                super.onPlaybackStateChanged(playbackState)
                when (playbackState) {
                    Player.STATE_READY -> {
                        alpha = 1f
                        progressBar?.hide()
                    }

                    Player.STATE_BUFFERING -> {
                        progressBar?.show()
                    }

                    Player.STATE_IDLE -> {
                        progressBar?.hide()
                    }

                    Player.STATE_ENDED -> {
                        progressBar?.hide()
                    }
                }
            }
        })
    }

    /**
     * This will reuse the player and will play new URI (remote url) we have provided
     */
    fun startPlayingRemoteUri(videoUri: Uri) {
//        this.progressBar = progressBar
        val mediaSource =
            ProgressiveMediaSource.Factory(cacheDataSourceFactory)
                .createMediaSource(MediaItem.fromUri(videoUri))
        exoPlayer.setMediaSource(mediaSource)
        exoPlayer.seekTo(lastPos)
        exoPlayer.playWhenReady = true
        exoPlayer.prepare()
    }

    /**
     * This will reuse the player and will play new URI (local uri) we have provided
     */
    fun startPlayingLocalUri(videoUri: Uri, progressBar: LMFeedProgressBar) {
        this.progressBar = progressBar
        val mediaSource = MediaItem.fromUri(videoUri)
        exoPlayer.setMediaItem(mediaSource)
        exoPlayer.seekTo(lastPos)
        exoPlayer.playWhenReady = true
        exoPlayer.prepare()
    }

    /**
     * This will stop the player, but stopping the player shows black screen
     * so to cover that we set alpha to 0 of player
     * and lastFrame of player using imageView over player to make it look like paused player
     *
     * If we will not stop the player, only pause it, then it can cause memory issue due to overload of player
     * and paused player can not be played with new URL, after stopping the player we can reuse that with new URL
     *
     */
    fun removePlayer() {
        exoPlayer.playWhenReady = false
        lastPos = exoPlayer.currentPosition
        exoPlayer.stop()
    }

    fun setStyle(postVideoMediaStyle: LMFeedPostVideoMediaStyle) {

        //set background color of the video view
        if (postVideoMediaStyle.backgroundColor != null) {
            setShutterBackgroundColor(
                ContextCompat.getColor(
                    context,
                    postVideoMediaStyle.backgroundColor
                )
            )
        }

        keepScreenOn = postVideoMediaStyle.keepScreenOn

        if (postVideoMediaStyle.showController) {
            controllerShowTimeoutMs = 0
            controllerAutoShow = true
        } else {
            controllerAutoShow = false
        }
    }
}