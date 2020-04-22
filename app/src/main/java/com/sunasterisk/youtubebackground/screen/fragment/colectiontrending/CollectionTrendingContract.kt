package com.sunasterisk.youtubebackground.screen.fragment.colectiontrending

import com.sunasterisk.youtubebackground.data.model.Page
import com.sunasterisk.youtubebackground.utils.BasePresenter
import java.lang.Exception

interface CollectionTrendingContract {

    interface View {
        fun getPageSuccess(page: Page)
        fun getPageFail(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun getPageByCategory(category: String?)
    }
}
