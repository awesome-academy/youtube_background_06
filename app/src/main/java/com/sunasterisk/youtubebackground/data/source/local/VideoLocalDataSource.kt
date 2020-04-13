package com.sunasterisk.youtubebackground.data.source.local

import com.sunasterisk.youtubebackground.data.source.VideoDataSource

class VideoLocalDataSource private constructor() :
VideoDataSource.Local {

    private object HOLDER {
        val INSTANCE = VideoLocalDataSource()
    }

    companion object {
        val instance: VideoLocalDataSource by lazy { HOLDER.INSTANCE }
    }
}
