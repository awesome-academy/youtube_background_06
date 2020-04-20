package com.sunasterisk.youtubebackground.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Statistic (
    var viewCount: Long = 0,
    var likeCount: Long = 0,
    var dislikeCount: Long = 0
) : Parcelable

object StatisticEntry {
    const val VIEW_COUNT = "viewCount"
    const val LIKE_COUNT = "likeCount"
    const val DISLIKE_COUNT = "dislikeCount"
}
