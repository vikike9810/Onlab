package com.onlab.gymapp

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.onlab.gymapp.Profile.User
import com.onlab.gymapp.Profile.profilePictureTask
import kotlinx.android.synthetic.main.activity_profil.*
import java.net.URL

class ProfilActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)
    }

    override fun onResume() {
        super.onResume()
        et_name_profil.setText(User.Name)
        et_birth_profil.setText(com.onlab.gymapp.Ticket.DateConverter.convert(User.Birth.year, User.Birth.month, User.Birth.date))
        et_height_profil.setText(User!!.Height?.toString())
        et_weight_profil.setText(User!!.Weight?.toString())
        refreshPicture()
    }

    fun toSettings(v: View) {
        startActivity(Intent(this, SettingsActivity::class.java))
    }


    fun refreshPicture() {
        synchronized(User) {
            if (User.image != null)
                picture_profil.setImageBitmap(User.image)
        }
    }


}
