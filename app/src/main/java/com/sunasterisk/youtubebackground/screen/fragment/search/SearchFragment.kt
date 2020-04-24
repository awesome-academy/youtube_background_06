package com.sunasterisk.youtubebackground.screen.fragment.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import com.sunasterisk.youtubebackground.R
import com.sunasterisk.youtubebackground.data.model.SearchPage
import com.sunasterisk.youtubebackground.data.source.VideoRepository
import com.sunasterisk.youtubebackground.screen.adapter.SearchVideoAdapter
import com.sunasterisk.youtubebackground.utils.Constants
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment(), SearchContract.View {

    private val adapter: SearchVideoAdapter by lazy { SearchVideoAdapter() }
    private var queryKey = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        textQuery.text = queryKey
    }

    private fun initView() {
        recylceSearchResult.setHasFixedSize(true)
        recylceSearchResult.adapter = adapter
    }

    private fun initData() {
        val presenter = SearchPresenter(VideoRepository.instance)
        presenter.setView(this)
        val bundle = arguments
        bundle?.getString(Constants.KEY_QUERY)?.let {
            presenter.onStart(it)
            queryKey = it
        }
    }

    override fun getPageSuccess(page: SearchPage) {
        page.searchVideos?.let {
            adapter.updateData(it)
        }
    }

    override fun getPageFail(exception: Exception?) {
        context?.let {
            Toast.makeText(it, R.string.errorLoad, Toast.LENGTH_SHORT).show()
        }
    }

    companion object {
        fun newInstance(queryKey: String) = SearchFragment().apply {
            arguments = bundleOf(Constants.KEY_QUERY to queryKey)
        }
    }
}
