package com.sunasterisk.youtubebackground.data.source

import com.sunasterisk.youtubebackground.data.model.Page
import com.sunasterisk.youtubebackground.data.model.SearchPage
import com.sunasterisk.youtubebackground.data.source.local.VideoLocalDataSource
import com.sunasterisk.youtubebackground.data.source.remote.OnFetchDataJsonListener
import com.sunasterisk.youtubebackground.data.source.remote.VideoRemoteDataSource

class VideoRepository private constructor(
    private val localDataSource: VideoDataSource.Local,
    private val remoteDataSource: VideoDataSource.Remote
) {

    private object HOLDER {
        val INSTANCE = VideoRepository(
            localDataSource = VideoLocalDataSource.instance,
            remoteDataSource = VideoRemoteDataSource.instance
        )
    }

    fun getPageByCategory(category: String?, listener: OnFetchDataJsonListener<Page>) {
        remoteDataSource.getPageByCategory(category, listener)
    }

    fun getSearchPage(queryKey: String?, listener: OnFetchDataJsonListener<SearchPage>) {
        remoteDataSource.getSearchPage(queryKey, listener)
    }

    companion object {
        val instance: VideoRepository by lazy { HOLDER.INSTANCE }
    }
}
