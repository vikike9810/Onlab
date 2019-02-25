package com.onlab.gymapp

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import com.onlab.gymapp.Profile.User

import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        et_name_settings.setText(User?.Name)
        et_birth_settings.setText(User.Birth.toString())
        et_height_settings.setText(User!!.Height?.toString())
        et_weight_settings.setText(User!!.Weight?.toString())
    }


}
