package com.sunasterisk.youtubebackground.data.source.remote

import android.util.Log
import com.sunasterisk.youtubebackground.data.model.Page
import com.sunasterisk.youtubebackground.data.model.PageEntry
import com.sunasterisk.youtubebackground.data.model.SearchPage
import com.sunasterisk.youtubebackground.data.source.VideoDataSource
import com.sunasterisk.youtubebackground.data.source.remote.fetchjson.GetJsonFromUrl
import com.sunasterisk.youtubebackground.utils.Constants

class VideoRemoteDataSource private constructor() : VideoDataSource.Remote {

    private object HOLDER {
        val INSTANCE = VideoRemoteDataSource()
    }

    companion object {
        val instance: VideoRemoteDataSource by lazy { HOLDER.INSTANCE }
    }

    override fun getPageByCategory(category: String?, listener: OnFetchDataJsonListener<Page>) {

        var url = Constants.BASE_URL + Constants.PATH_VIDEO + Constants.PART +
                  Constants.PART_TYPE + Constants.CHART + Constants.MOST_POPULAR +
                  Constants.MAX_RESULT + Constants.REGION_CODE + Constants.YOUTUBE_API_KEY
        if (category != null) {
            url = url + Constants.VIDEO_CATEGORIES + category
        }

        GetJsonFromUrl(listener, PageEntry.PAGE).execute(url)
    }

    override fun getSearchPage(queryKey: String?, listener: OnFetchDataJsonListener<SearchPage>) {

        var url = Constants.BASE_URL + Constants.PATH_SEARCH + Constants.PART +
                Constants.PART_TYPE_SEARCH + Constants.MAX_RESULT + Constants.ORDER +
                Constants.QUERY + queryKey + Constants.YOUTUBE_API_KEY

        GetJsonFromUrl(listener, PageEntry.SEARCH).execute(url)
    }
}
