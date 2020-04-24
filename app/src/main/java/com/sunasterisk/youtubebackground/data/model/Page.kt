package com.sunasterisk.youtubebackground.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Page(
    val kind: String = "",
    val eTag: String = "",
    val nextPageToken: String = "",
    val videos: MutableList<Video>?
) : Parcelable

@Parcelize
data class SearchPage(
    val kind: String = "",
    val eTag: String = "",
    val nextPageToken: String = "",
    val searchVideos: MutableList<SearchVideo>?
) : Parcelable

object PageEntry {
    const val SEARCH = "search"
    const val PAGE = "page"
    const val KIND = "kind"
    const val ETAG = "etag"
    const val NEXT_PAGE_TOKEN = "nextPageToken"
    const val ITEMS = "items"
}
