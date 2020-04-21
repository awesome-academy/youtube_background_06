package com.sunasterisk.youtubebackground.utils

object VideoUtils {
    fun formatLikeCount(likeCount: Long?): String{

        likeCount?: return "00000"
        var suffixChar = ""
        var prefixChar = ""

        if (likeCount / 1000000000 > 0) {
            prefixChar = (likeCount / 1000).toString()
            suffixChar = "B"
        } else if (likeCount / 1000000 > 0) {
            prefixChar = (likeCount / 1000).toString()
            suffixChar = "M"
        } else {
            return likeCount.toString()
        }
        return prefixChar + " " + suffixChar
    }
}
