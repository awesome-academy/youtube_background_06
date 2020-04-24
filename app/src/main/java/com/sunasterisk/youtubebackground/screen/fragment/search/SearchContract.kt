package com.sunasterisk.youtubebackground.screen.fragment.search

import com.sunasterisk.youtubebackground.data.model.SearchPage
import com.sunasterisk.youtubebackground.utils.BasePresenter

interface SearchContract {
    interface View {
        fun getPageSuccess(page: SearchPage)
        fun getPageFail(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun getSearchPage(queryKey: String?)
    }
}
