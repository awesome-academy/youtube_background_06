package com.sunasterisk.youtubebackground.screen.fragment.colectiontrending

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
import com.sunasterisk.youtubebackground.screen.video.VideoActivity
import com.sunasterisk.youtubebackground.utils.Constants
import com.sunasterisk.youtubebackground.utils.OnItemRecyclerViewClickListener
import com.sunasterisk.youtubebackground.utils.VideoCategoryId
import kotlinx.android.synthetic.main.fragment_colection_trending.*

class CollectionTrendingFragment : Fragment(),
    CollectionTrendingContract.View,
    View.OnClickListener,
    OnItemRecyclerViewClickListener {

    private var page: Page? = null
    private val adapter: VideoAdapter by lazy { VideoAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_colection_trending, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initData()
        initButton()
    }

    private fun initButton() {
        buttonImgTrending.setOnClickListener(this)
        buttonImgGaming.setOnClickListener(this)
        buttonImgMusic.setOnClickListener(this)
    }

    private fun initData(category: String? = null) {
        val presenter = CollectionTrendingPresenter(VideoRepository.instance)
        presenter.setView(this)
        presenter.onStart(category)
    }

    private fun initView() {
        recyclerTrendingContent.setHasFixedSize(true)
        recyclerTrendingContent.adapter = adapter
        adapter.registerItemRecyclerViewClickListener(this)
    }

    override fun getPageSuccess(page: Page) {
        this.page = page
        page.videos?.let {
            adapter.updateData(it)
            adapter.notifyDataSetChanged()
        }
    }

    override fun getPageFail(exception: Exception?) {
        context?.let {
            Toast.makeText(it, R.string.errorLoad, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.buttonImgTrending -> {
                textPageTitle.text = getString(R.string.trendingPage)
                initData()
                initView()
            }
            R.id.buttonImgGaming -> {
                textPageTitle.text = getString(R.string.gamingPage)
                initData(VideoCategoryId.GAMING.category)
                initView()
            }
            R.id.buttonImgMusic -> {
                textPageTitle.text = getString(R.string.musicPage)
                initData(VideoCategoryId.MUSIC.category)
                initView()
            }
        }
    }

    override fun onItemClickListener(item: Int) {
        activity?.let {
            startActivity(VideoActivity.getIntent(it, item, page))
        }
    }

    companion object {
        fun newInstance() = CollectionTrendingFragment()
    }
}
