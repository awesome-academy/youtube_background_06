package com.sunasterisk.youtubebackground.screen.adapter

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sunasterisk.youtubebackground.R
import com.sunasterisk.youtubebackground.data.model.SearchVideo
import com.sunasterisk.youtubebackground.utils.LoadImageHelper
import com.sunasterisk.youtubebackground.utils.OnLoadImageListener
import kotlinx.android.synthetic.main.single_search_result_item.view.*

class SearchVideoAdapter : RecyclerView.Adapter<SearchVideoAdapter.ViewHolder>() {

    private val listVideos = mutableListOf<SearchVideo>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchVideoAdapter.ViewHolder {
        return SearchVideoAdapter.ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.single_search_result_item, parent, false)
        )
    }

    override fun getItemCount(): Int = listVideos.size

    override fun onBindViewHolder(holder: SearchVideoAdapter.ViewHolder, position: Int) {
        holder.bindData(listVideos.get(position))
    }

    fun updateData(videos: List<SearchVideo>?) {
        videos?.let {
            listVideos.clear()
            listVideos.addAll(videos)
            notifyDataSetChanged()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), OnLoadImageListener {

        override fun onLoadSuccess(bitmap: Bitmap) {
            itemView.imageSearchThumb.setImageBitmap(bitmap)
        }

        override fun onLoadFail(exception: Exception?) {
            exception?.printStackTrace()
        }

        fun bindData(searchVideo: SearchVideo) {
            LoadImageHelper(this)
                .execute(searchVideo.searchSnippet?.searchThumn?.high?.url)
            itemView.textSearchTitle.text = searchVideo.searchSnippet?.title
            itemView.textSearchUpload.text = searchVideo.searchSnippet?.channelTitle
        }
    }
}
