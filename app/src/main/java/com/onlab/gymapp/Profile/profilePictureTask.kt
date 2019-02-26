package com.onlab.gymapp.Profile

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import java.net.URL

class profilePictureTask(context: Context) : AsyncTask<Void, Void, Bitmap>(){

    var callback = context as ProfilePictureCallbackInterface

    override fun doInBackground(vararg params: Void?): Bitmap {
        var imgURL = URL(User.imgUrl)
        var bitmap = BitmapFactory.decodeStream(imgURL.openConnection().getInputStream())
        return bitmap

    }

    override fun onPostExecute(result: Bitmap?) {
        super.onPostExecute(result)
        callback.setPicture(result!!)
    }

    interface ProfilePictureCallbackInterface{
        fun setPicture(bitmap: Bitmap);
    }
}