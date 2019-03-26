package com.onlab.gymapp.Profile


import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import java.net.URL

class profilePictureTask : AsyncTask<Void, Void, Void>() {


    override fun doInBackground(vararg params: Void?): Void? {
        synchronized(User) {
            var imgURL = URL(User.imgUrl)
            var bitmap = BitmapFactory.decodeStream(imgURL.openConnection().getInputStream())
            User.image = bitmap
            return null
        }
    }
}