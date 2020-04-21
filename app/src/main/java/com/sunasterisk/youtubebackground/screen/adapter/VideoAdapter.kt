package com.sunasterisk.youtubebackground.screen.adapter

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sunasterisk.youtubebackground.R
import com.sunasterisk.youtubebackground.data.model.Video
import com.sunasterisk.youtubebackground.utils.LoadImageHelper
import com.sunasterisk.youtubebackground.utils.OnLoadImageListener
import com.sunasterisk.youtubebackground.utils.VideoUtils
import kotlinx.android.synthetic.main.single_video_item.view.*

class VideoAdapter() : RecyclerView.Adapter<VideoAdapter.ViewHolder>() {

    private val listVideos = mutableListOf<Video>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoAdapter.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.single_video_item, parent, false)
        )
    }

    override fun getItemCount(): Int = listVideos.size

    override fun onBindViewHolder(holder: VideoAdapter.ViewHolder, position: Int) {
        holder.bindData(listVideos.get(position))
    }

    fun updateData(videos: List<Video>?) {
        videos?.let {
            listVideos.clear()
            listVideos.addAll(videos)
            notifyDataSetChanged()
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), OnLoadImageListener {

        fun bindData(video: Video) {
            LoadImageHelper(this).execute(video.snippet?.thumbnail?.high?.url)
            itemView.textItemVideoTitle.text = video.snippet?.title
            itemView.textItemOwnerName.text = video.snippet?.channelTitle
            itemView.textItemLikeCount.text = VideoUtils.formatLikeCount(video.statistic?.likeCount)
            itemView.textItemUploadTime.text = video.snippet?.publishedAt?.substring(0, 10)
        }

        override fun onLoadSuccess(bitmap: Bitmap) {
            itemView.imageThumbnail.setImageBitmap(bitmap)
        }

        override fun onLoadFail(exception: Exception?) {
            exception?.printStackTrace()
        }
    }
}
