package com.sunasterisk.youtubebackground.utils

interface BasePresenter<T> {
    fun onStart(category: String? = null)
    fun onStop()
    fun setView(view: T)
}
