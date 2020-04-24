package com.sunasterisk.youtubebackground.screen.video

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import androidx.fragment.app.FragmentActivity
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubePlayer
import com.sunasterisk.youtubebackground.R
import com.sunasterisk.youtubebackground.data.model.Page
import com.sunasterisk.youtubebackground.data.model.Video
import com.sunasterisk.youtubebackground.utils.Constants
import com.sunasterisk.youtubebackground.service.VideoPlayService
import kotlinx.android.synthetic.main.activity_video.*

class VideoActivity : YouTubeBaseActivity(), YouTubePlayer.OnFullscreenListener {

    private var page: Page? = null
    private var curVideo: Video? = null
    private var position = 0
    private var playBinder: VideoPlayService? = null
    private var serviceConnection: ServiceConnection? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_video)
        position = intent.getIntExtra(Constants.EXTRA_VIDEO, 0)
        page = intent.getParcelableExtra(Constants.EXTRA_PAGE)
        curVideo = page?.videos?.get(position)
        showViewCurVideo(position)

        serviceConnection = object : ServiceConnection {

            override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
                val binder = iBinder as VideoPlayService.PlayBinder
                playBinder = binder.getService()
                playBinder?.let {
                    it.playVideo(this@VideoActivity, page?.videos!!, position)
                }
            }

            override fun onServiceDisconnected(componentName: ComponentName) {
            }
        }
        bindService(VideoPlayService.getIntent(this),
                    serviceConnection as ServiceConnection,
                    Context.BIND_AUTO_CREATE)
    }

    fun showViewCurVideo(position: Int) {
        val video = page?.videos?.get(position)
        textVideoTitle.text = video?.snippet?.title
        textVideoLike.text = video?.statistic?.likeCount.toString()
        textVideoDislike.text = video?.statistic?.dislikeCount.toString()
        textVideoViewCounter.text = video?.statistic?.viewCount.toString()
        textVideoUploadTime.text = video?.snippet?.publishedAt.toString().substring(0, 10)
    }

    override fun onFullscreen(p0: Boolean) {
    }

    companion object {
        const val REQUEST_CODE = 123
        fun getIntent(activity: FragmentActivity?, item: Int, page: Page?) =
            Intent(activity, VideoActivity::class.java)
                .putExtra(Constants.EXTRA_VIDEO, item)
                .putExtra(Constants.EXTRA_PAGE, page)
    }
}
