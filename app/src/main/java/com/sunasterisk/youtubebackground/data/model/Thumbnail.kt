package com.sunasterisk.youtubebackground.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Thumbnail(
    var default: ThumbnailSize?,
    var medium: ThumbnailSize?,
    var high: ThumbnailSize?,
    var standard: ThumbnailSize?
) : Parcelable

@Parcelize
data class SearchThumbnail(val high: ThumbnailSize?) : Parcelable

@Parcelize
data class ThumbnailSize(
    val url: String = "",
    val width: Int = 0,
    val height: Int = 0
) : Parcelable

object ThumbnailEntry {
    const val THUMBNAIL = "thumbnails"
    const val DEFAULT = "default"
    const val MEDIUM = "medium"
    const val HIGH = "high"
    const val STANDARD = "standard"
}

object ThumnailSizeEntry {
    const val URL = "url"
    const val WIDTH = "width"
    const val HEIGHT = "height"
}
