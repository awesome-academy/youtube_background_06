package com.sunasterisk.youtubebackground.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL

class LoadImageHelper constructor(private val listener: OnLoadImageListener)
    : AsyncTask<String, Void, Bitmap>() {

    private var exception: Exception? = null

    override fun doInBackground(vararg string: String?): Bitmap? {
        var bitmap: Bitmap? = null
        try {
            val url = URL(string[0])
            val inputStream = url.openConnection().getInputStream()
            bitmap = BitmapFactory.decodeStream(inputStream)
        } catch (e: MalformedURLException) {
            exception = e
        } catch (e: IOException) {
            exception = e
        }

        return bitmap
    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
        if (result != null){
            listener.onLoadSuccess(result)
        } else {
            listener.onLoadFail(exception)
        }
    }
}
