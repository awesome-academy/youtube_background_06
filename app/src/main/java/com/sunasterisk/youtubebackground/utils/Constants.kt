package com.sunasterisk.youtubebackground.utils

import com.sunasterisk.youtubebackground.BuildConfig

object Constants {
    const val YOUTUBE_API_KEY = "&key=" + BuildConfig.YOUTUBE_API_KEY
    const val BASE_URL = "https://www.googleapis.com/youtube/v3/"
    const val PART = "part="
    const val CHART = "&chart="
    const val REGION_CODE = "&regionCode=vn"
    const val PART_TYPE = "snippet%2CcontentDetails%2Cstatistics"
    const val PATH_VIDEO = "videos?"
    const val VIDEO_CATEGORIES = "&videoCategoryId="
    const val MAX_RESULT = "&maxResults=20"
    const val MOST_POPULAR = "mostPopular"
    const val EXTRA_VIDEO = "VIDEO"
    const val EXTRA_PAGE = "PAGE"
    const val EXTRA_POSITION = "POSITION"
    const val EXTRA_VIDEOS = "VIDEOS"
    const val EXTRA_VIEW = "VIEW"
}
