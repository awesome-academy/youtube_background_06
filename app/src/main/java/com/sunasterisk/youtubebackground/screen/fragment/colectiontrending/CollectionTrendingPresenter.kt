package com.sunasterisk.youtubebackground.screen.fragment.colectiontrending

import com.sunasterisk.youtubebackground.data.model.Page
import com.sunasterisk.youtubebackground.data.source.VideoRepository
import com.sunasterisk.youtubebackground.data.source.remote.OnFetchDataJsonListener
import com.sunasterisk.youtubebackground.screen.main.HomeContract

class CollectionTrendingPresenter(
    private val repository: VideoRepository?
) : CollectionTrendingContract.Presenter {

    private var view: CollectionTrendingContract.View? = null

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

    override fun onStart(category: String?) {
        getPageByCategory(category)
    }

    override fun onStop() {
    }

    override fun setView(view: CollectionTrendingContract.View) {
        this.view = view
    }
}
