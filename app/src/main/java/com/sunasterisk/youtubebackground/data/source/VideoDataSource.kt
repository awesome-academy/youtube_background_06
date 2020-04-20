package com.sunasterisk.youtubebackground.data.source

import com.sunasterisk.youtubebackground.data.model.Page
import com.sunasterisk.youtubebackground.data.source.remote.OnFetchDataJsonListener

interface VideoDataSource {

    interface Local

    interface Remote{

        fun getPageByCategory(category: String?, listener: OnFetchDataJsonListener<Page>)
    }
}
