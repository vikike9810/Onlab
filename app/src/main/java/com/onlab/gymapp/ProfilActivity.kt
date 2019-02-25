package com.onlab.gymapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.onlab.gymapp.Profile.User
import kotlinx.android.synthetic.main.activity_profil.*

class ProfilActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)
        et_name_profil.setText(User.Name)
        et_birth_profil.setText(User.Birth.toString())
        et_height_profil.setText(User!!.Height?.toString())
        et_weight_profil.setText(User!!.Weight?.toString())
    }

     fun toSettings(v: View){
        startActivity(Intent(this,SettingsActivity::class.java))
    }
}
