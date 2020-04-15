package com.sunasterisk.youtubebackground.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ContentDetail (
    val duration: String = ""
) : Parcelable

object ContentDetailEntry{
    const val CONTENT_DETAILS = "contentDetails"
    const val DURATION = "duration"
}
