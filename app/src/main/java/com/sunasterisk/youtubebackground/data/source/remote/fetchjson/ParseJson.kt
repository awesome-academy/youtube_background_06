package com.sunasterisk.youtubebackground.data.source.remote.fetchjson

import com.sunasterisk.youtubebackground.data.model.*
import org.json.JSONArray
import org.json.JSONObject

class ParseJson {

    public fun pageParseJson(jsonObject: JSONObject) = Page(
        kind = jsonObject.getString(PageEntry.KIND),
        eTag = jsonObject.getString(PageEntry.ETAG),
        nextPageToken = jsonObject.getString(PageEntry.NEXT_PAGE_TOKEN),
        videos = videosParseJson(jsonObject.getJSONArray(PageEntry.ITEMS))
    )

    public fun searchPageParseJson(jsonObject: JSONObject) = SearchPage(
        kind = jsonObject.getString(PageEntry.KIND),
        eTag = jsonObject.getString(PageEntry.ETAG),
        nextPageToken = jsonObject.getString(PageEntry.NEXT_PAGE_TOKEN),
        searchVideos = searchVideosParseJson(jsonObject.getJSONArray(PageEntry.ITEMS))
    )

    private fun searchVideosParseJson(jsonArray: JSONArray): MutableList<SearchVideo>? {
        val searchVideos = mutableListOf<SearchVideo>()
        for (item in 0 until jsonArray.length()) {
            searchVideos.add(
                SearchVideo(
                    jsonArray.getJSONObject(item).getString(VideoEntry.ETAG),
                    searchSnipperParseJson(
                        jsonArray.getJSONObject(item).getJSONObject(VideoEntry.SNIPPET)
                    )
                )
            )
        }
        return searchVideos
    }

    private fun searchSnipperParseJson(jsonObject: JSONObject) = SearchSnippet(
        jsonObject.getString(SnippetEntry.PUBLISHE_AT),
        jsonObject.getString(SnippetEntry.CHANNEL_ID),
        jsonObject.getString(SnippetEntry.TITLE),
        searchThumbnailParseJson(jsonObject.getJSONObject(SnippetEntry.THUMBNAILS)),
        jsonObject.getString(SnippetEntry.CHANNEL_TITLE)
    )

    private fun searchThumbnailParseJson(jsonObject: JSONObject) = SearchThumbnail(
        searchThumbSizeParseJson(jsonObject.getJSONObject(ThumbnailEntry.HIGH))
    )

    private fun searchThumbSizeParseJson(jsonObject: JSONObject) = ThumbnailSize(
        jsonObject.getString(ThumnailSizeEntry.URL)
    )

    fun videosParseJson(jsonArray: JSONArray): MutableList<Video>? {
        val videos = mutableListOf<Video>()
        for (item in 0 until jsonArray.length()) {
            videos.add(
                Video(
                    jsonArray.getJSONObject(item).getString(VideoEntry.ETAG),
                    jsonArray.getJSONObject(item).getString(VideoEntry.ID),
                    snippetParseJson(
                        jsonArray.getJSONObject(item).getJSONObject(VideoEntry.SNIPPET)
                    ),
                    contentDetailParseJson(
                        jsonArray.getJSONObject(item).getJSONObject(VideoEntry.CONTENT_DETAILS)
                    ),
                    statisticParseJson(
                        jsonArray.getJSONObject(item).getJSONObject(VideoEntry.STATISTICS)
                    )
                )
            )
        }
        return videos
    }

    private fun statisticParseJson(jsonObject: JSONObject) = Statistic(
        jsonObject.getLong(StatisticEntry.VIEW_COUNT),
        jsonObject.getLong(StatisticEntry.LIKE_COUNT),
        jsonObject.getLong(StatisticEntry.DISLIKE_COUNT)
    )

    private fun contentDetailParseJson(jsonObject: JSONObject) =
        ContentDetail(jsonObject.getString(ContentDetailEntry.DURATION))

    fun snippetParseJson(jsonObject: JSONObject) = Snippet(
        jsonObject.getString(SnippetEntry.PUBLISHE_AT),
        jsonObject.getString(SnippetEntry.CHANNEL_ID),
        jsonObject.getString(SnippetEntry.TITLE),
        thumbnailParseJson(jsonObject.getJSONObject(SnippetEntry.THUMBNAILS)),
        jsonObject.getString(SnippetEntry.CHANNEL_TITLE)
    )

    fun thumbnailParseJson(jsonObject: JSONObject) = Thumbnail(
        thumbnailSizeParsejson(jsonObject.getJSONObject(ThumbnailEntry.DEFAULT)),
        thumbnailSizeParsejson(jsonObject.getJSONObject(ThumbnailEntry.MEDIUM)),
        thumbnailSizeParsejson(jsonObject.getJSONObject(ThumbnailEntry.HIGH)),
        thumbnailSizeParsejson(jsonObject.getJSONObject(ThumbnailEntry.STANDARD))
    )

    fun thumbnailSizeParsejson(jsonObject: JSONObject) = ThumbnailSize(
        jsonObject.getString(ThumnailSizeEntry.URL),
        jsonObject.getInt(ThumnailSizeEntry.WIDTH),
        jsonObject.getInt(ThumnailSizeEntry.HEIGHT)
    )
}
