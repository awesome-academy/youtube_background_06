package com.sunasterisk.youtubebackground.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Video(
    val eTag: String? = "",
    val id: String? = "",
    val snippet: Snippet? = null,
    val contentDetail: ContentDetail? = null,
    val statistic: Statistic? = null
) : Parcelable

object VideoEntry {
    const val ETAG = "etag"
    const val ID = "id"
    const val SNIPPET = "snippet"
    const val CONTENT_DETAILS = "contentDetails"
    const val STATISTICS = "statistics"
}
