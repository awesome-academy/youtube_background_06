package com.sunasterisk.youtubebackground.screen.main

import com.sunasterisk.youtubebackground.data.model.Page
import com.sunasterisk.youtubebackground.data.source.VideoRepository
import com.sunasterisk.youtubebackground.data.source.remote.OnFetchDataJsonListener

class HomePresenter(private val repository: VideoRepository?) : HomeContract.Presenter {

    private var view: HomeContract.View? = null

    override fun getPageByCategory(category: String?) {
        repository?.getPageByCategory(category, object : OnFetchDataJsonListener<Page> {
            override fun onSuccess(data: Page) {
                view?.getPageSuccess(data)
            }

            override fun onError(exception: Exception?) {
                view?.getPageFail(exception)
            }
        })
    }

    override fun onStart() {
        getPageByCategory(null)
    }

    override fun onStop() {
    }

    override fun setView(view: HomeContract.View) {
        this.view = view
    }
}
