package com.sunasterisk.youtubebackground.data.source.remote.fetchjson

import android.os.AsyncTask
import android.os.Debug
import android.util.Log
import com.sunasterisk.youtubebackground.data.source.remote.OnFetchDataJsonListener
import org.json.JSONObject

class GetJsonFromUrl<T> constructor(private val listener: OnFetchDataJsonListener<T>,
                                    private val keyEntity: String) : AsyncTask<String?, Void?, String?>() {

    private var exception: Exception? = null

    override fun doInBackground(vararg strings: String?): String? {
        var data = ""
        try {
            val parseDataWithJson = ParseDataWithJson()
            data = parseDataWithJson.getJsonFromUrl(strings[0]).toString()
        } catch (e: Exception) {
            exception = e
        }
        return data
    }

    override fun onPostExecute(data: String?) {
        super.onPostExecute(data)
        if (data != null && !data.isBlank()) {
            val jsonObject = JSONObject(data)
            @Suppress("UNCHECKED_CAST")
            listener.onSuccess(ParseDataWithJson().parseJsonToObject(jsonObject, keyEntity) as T)
        } else listener.onError(exception)
    }
}
