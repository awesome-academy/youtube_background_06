package com.sunasterisk.youtubebackground.screen.fragment.search

import com.sunasterisk.youtubebackground.data.model.SearchPage
import com.sunasterisk.youtubebackground.data.source.VideoRepository
import com.sunasterisk.youtubebackground.data.source.remote.OnFetchDataJsonListener

class SearchPresenter(private val repository: VideoRepository?) : SearchContract.Presenter {

    private var view: SearchContract.View? = null

    override fun getSearchPage(queryKey: String?) {
        repository?.getSearchPage(queryKey, object : OnFetchDataJsonListener<SearchPage> {
            override fun onSuccess(data: SearchPage) {
                view?.getPageSuccess(data)
            }

            override fun onError(exception: Exception?) {
                view?.getPageFail(exception)
            }
        })
    }

    override fun onStart(queryKey: String?) {
        getSearchPage(queryKey)
    }

    override fun onStop() {
    }

    override fun setView(view: SearchContract.View) {
        this.view = view
    }
}
