package com.sunasterisk.youtubebackground.screen.fragment.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.sunasterisk.youtubebackground.R
import com.sunasterisk.youtubebackground.data.model.Page
import com.sunasterisk.youtubebackground.data.model.Video
import com.sunasterisk.youtubebackground.data.source.VideoRepository
import com.sunasterisk.youtubebackground.screen.adapter.VideoAdapter
import com.sunasterisk.youtubebackground.screen.main.HomeContract
import com.sunasterisk.youtubebackground.screen.main.HomePresenter
import com.sunasterisk.youtubebackground.screen.video.VideoActivity
import com.sunasterisk.youtubebackground.utils.Constants
import com.sunasterisk.youtubebackground.utils.OnItemRecyclerViewClickListener
import kotlinx.android.synthetic.main.fragment_home.*
import java.lang.Exception

class HomeFragment : Fragment(), HomeContract.View, OnItemRecyclerViewClickListener {

    private var page: Page? = null
    private val adapter: VideoAdapter by lazy { VideoAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initView()
    }

    private fun initData() {
        val presenter = HomePresenter(VideoRepository.instance)
        presenter.setView(this)
        presenter.onStart()
    }

    private fun initView() {
        recyclerHome.setHasFixedSize(true)
        recyclerHome.adapter = adapter
        adapter.registerItemRecyclerViewClickListener(this)
    }

    override fun getPageSuccess(page: Page) {
        this.page = page
        page.videos?.let {
            adapter.updateData(it)
        }
    }

    override fun getPageFail(exception: Exception?) {
        this.context?.let {
            Toast.makeText(it, R.string.errorLoad, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onItemClickListener(item: Int) {
        startActivity(VideoActivity.getIntent(activity, item, page))
    }

    companion object {
        fun newInstance() = HomeFragment()
    }
}
