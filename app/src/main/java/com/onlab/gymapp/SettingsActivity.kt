package com.onlab.gymapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.View
import android.widget.DatePicker
import com.onlab.gymapp.Profile.User

import kotlinx.android.synthetic.main.activity_settings.*
import com.onlab.gymapp.Login.DatePickerDialogFragment



class SettingsActivity : AppCompatActivity(), DatePickerDialogFragment.OnDateSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        et_name_settings.setText(User.Name)
        et_birth_settings.setText(User.Birth.toString())
        et_height_settings.setText(User!!.Height?.toString())
        et_weight_settings.setText(User!!.Weight?.toString())
    }


    override fun onDateSelected(year: Int, month: Int, day: Int) {
    et_birth_settings.setText(DateConverter(year,month,day))
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
        return year.toString()+"."+smonth+"."+sday
    }






    fun DateClick(v: View){
        DatePickerDialogFragment().show(supportFragmentManager, "DATE_TAG")
    }

}
