package com.sunasterisk.youtubebackground.data.source.remote

import com.sunasterisk.youtubebackground.data.source.VideoDataSource

class VideoRemoteDataSource : VideoDataSource.Remote {

    private object HOLDER {
        val INSTANCE = VideoRemoteDataSource()
    }

    companion object {
        val instance: VideoRemoteDataSource by lazy { HOLDER.INSTANCE }
    }
}
