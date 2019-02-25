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
    }

    override fun onResume() {
        super.onResume()
        et_name_profil.setText(User.Name)
        var year = User.Birth.year + 1900
        et_birth_profil.setText(DateConverter(year,User.Birth.month,User.Birth.day))
        et_height_profil.setText(User!!.Height?.toString())
        et_weight_profil.setText(User!!.Weight?.toString())
    }

     fun toSettings(v: View){
        startActivity(Intent(this,SettingsActivity::class.java))
    }

    fun DateConverter(year: Int, month: Int, day: Int):String{
        var sday=day.toString()
        var smonth=(month+1).toString()
        if(day<10){
            sday="0"+day.toString()
        }
        if(month<10){
            smonth="0"+(month+1).toString()
        }
        return (year).toString()+"."+smonth+"."+sday
    }


}
