package com.sunasterisk.youtubebackground.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Snippet(
    val publishedAt: String = "",
    val channelId: String = "",
    val title: String = "",
    val thumbnail: Thumbnail?,
    val channelTitle: String = ""
) : Parcelable

@Parcelize
data class SearchSnippet(
    val publishedAt: String = "",
    val channelId: String = "",
    val title: String = "",
    val searchThumn: SearchThumbnail?,
    val channelTitle: String = ""
) : Parcelable

object SnippetEntry {
    const val PUBLISHE_AT = "publishedAt"
    const val CHANNEL_ID = "channelId"
    const val TITLE = "title"
    const val THUMBNAILS = "thumbnails"
    const val CHANNEL_TITLE = "channelTitle"
}
