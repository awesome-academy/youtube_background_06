package com.sunasterisk.youtubebackground.data.source

import com.sunasterisk.youtubebackground.data.source.local.VideoLocalDataSource
import com.sunasterisk.youtubebackground.data.source.remote.VideoRemoteDataSource

class VideoRepository private constructor(
    private val localDataSource: VideoDataSource.Local,
    private val remoteDataSource: VideoDataSource.Remote
){

    private object HOLDER {
        val INSTANCE = VideoRepository(
                localDataSource = VideoLocalDataSource.instance,
                remoteDataSource = VideoRemoteDataSource.instance
        )
    }

    companion object{
        val instance: VideoRepository by lazy { HOLDER.INSTANCE }
    }
}
