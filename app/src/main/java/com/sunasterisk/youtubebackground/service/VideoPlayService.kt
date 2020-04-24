package com.sunasterisk.youtubebackground.service

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.widget.Toast
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.sunasterisk.youtubebackground.R
import com.sunasterisk.youtubebackground.data.model.Video
import com.sunasterisk.youtubebackground.screen.video.VideoActivity
import com.sunasterisk.youtubebackground.utils.Constants
import kotlinx.android.synthetic.main.activity_video.*

class VideoPlayService : Service(), YouTubePlayer.PlayerStateChangeListener,
    YouTubePlayer.PlaylistEventListener {

    private val binder = PlayBinder()
    private var view: VideoActivity? = null
    private var youTubePlayer: YouTubePlayer? = null
    private var possiton = 0

    override fun onBind(p0: Intent?) = binder

    fun playVideo(view: VideoActivity, videos: MutableList<Video>, possiton: Int) {
        this.view = view
        this.possiton = possiton
        view?.youTubePlayerView?.initialize(
            Constants.YOUTUBE_API_KEY,
            object : YouTubePlayer.OnInitializedListener {

                override fun onInitializationSuccess(
                    arg0: YouTubePlayer.Provider,
                    youTubePlayer: YouTubePlayer,
                    isFullScreen: Boolean
                ) {
                    youTubePlayer.loadVideos(videos?.let(::getListId), possiton, 0)
                    youTubePlayer.setPlayerStateChangeListener(this@VideoPlayService)
                    youTubePlayer.setPlaylistEventListener(this@VideoPlayService)
                    this@VideoPlayService.youTubePlayer = youTubePlayer
                }

                override fun onInitializationFailure(
                    arg0: YouTubePlayer.Provider,
                    youTubeInitializationResult: YouTubeInitializationResult
                ) {
                    youTubeInitializationResult?.let {
                        if (it.isUserRecoverableError) {
                            youTubeInitializationResult.getErrorDialog(
                                view,
                                VideoActivity.REQUEST_CODE
                            )
                        } else {
                            Toast.makeText(view, R.string.error, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
    }

    private fun getListId(videos: MutableList<Video>): List<String?> {
        return videos.map{ it.id }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    inner class PlayBinder : Binder() {
        fun getService(): VideoPlayService = this@VideoPlayService
    }

    override fun onAdStarted() {
    }

    override fun onLoading() {
    }

    override fun onVideoStarted() {
    }

    override fun onLoaded(p0: String?) {
    }

    override fun onVideoEnded() {
        view?.run {
            if (switchAutoNext.isChecked) {
                youTubePlayer?.pause()
            }
            if (switchLoop.isChecked) {
                youTubePlayer?.seekToMillis(0)
            }
        }
    }

    override fun onError(p0: YouTubePlayer.ErrorReason?) {
    }

    override fun onPlaylistEnded() {
    }

    override fun onPrevious() {
        possiton --
        view?.showViewCurVideo(possiton)
    }

    override fun onNext() {
        possiton ++
        view?.showViewCurVideo(possiton)
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, VideoPlayService::class.java)
    }
}
