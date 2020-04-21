package com.sunasterisk.youtubebackground.utils

import android.graphics.Bitmap

interface OnLoadImageListener {
    fun onLoadSuccess(bitmap: Bitmap)
    fun onLoadFail(exception: Exception?)
}
