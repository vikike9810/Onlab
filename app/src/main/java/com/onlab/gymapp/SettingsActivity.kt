package com.onlab.gymapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.FirebaseFunctionsException
import com.onlab.gymapp.Profile.User

import kotlinx.android.synthetic.main.activity_settings.*
import com.onlab.gymapp.Login.DatePickerDialogFragment
import java.text.SimpleDateFormat


class SettingsActivity : AppCompatActivity(), DatePickerDialogFragment.OnDateSelectedListener {

    private lateinit var functions: FirebaseFunctions
    private lateinit var auth: FirebaseAuth
    var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        auth = FirebaseAuth.getInstance();
        functions = FirebaseFunctions.getInstance()
        user = auth.currentUser

    }

    override fun onResume() {
        super.onResume()
        et_name_settings.setText(User.Name)
        var year = User.Birth.year + 1900
        et_birth_settings.setText(DateConverter(year, User.Birth.month, User.Birth.date))
        et_height_settings.setText(User!!.Height?.toString())
        et_weight_settings.setText(User!!.Weight?.toString())
    }


    override fun onDateSelected(year: Int, month: Int, day: Int) {
        et_birth_settings.setText(DateConverter(year, month, day))
    }

    fun DateConverter(year: Int, month: Int, day: Int): String {
        var sday = day.toString()
        var smonth = (month + 1).toString()
        if (day < 10) {
            sday = "0" + day.toString()
        }
        if (month < 10) {
            smonth = "0" + (month + 1).toString()
        }
        return (year).toString() + "." + smonth + "." + sday
    }


    fun Save(v: View) {
        User.Name = et_name_settings.text.toString()
        User.Height = Integer.parseInt(et_height_settings.text.toString()) as Integer
        User.Weight = Integer.parseInt(et_weight_settings.text.toString()) as Integer
        val format = SimpleDateFormat("yyyy.MM.dd")
        User.Birth = format.parse(et_birth_settings.text.toString())
        saveSettings()
    }

    private fun saveSettings() {

        saveToServer().addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                val e = task.exception
                if (e is FirebaseFunctionsException) {
                    val code = e.code
                    val details = e.details
                }
            }
            else {
                if (task.result.equals("OK")){
                    Toast.makeText(this, "Adatok elmentve", Toast.LENGTH_LONG).show()
                    finish()
                }
                else
                    Toast.makeText(this, "Hiba", Toast.LENGTH_LONG).show()
            }
        })

    }

    private fun saveToServer(): Task<String> {
        val data = hashMapOf(
            "userid" to user?.uid,
            "name" to User.Name,
            "height" to User.Height.toString(),
            "weight" to User.Weight.toString(),
            "birth" to DateConverter(User.Birth.year+1900,User.Birth.month,User.Birth.date)
        )

        return functions.getHttpsCallable("setSettings")
            .call(data)
            .continueWith { task ->
                val result = task.result?.data as String
                result
            }
    }

    fun Cancel(v: View) {
        finish()
    }

    fun DateClick(v: View) {
        DatePickerDialogFragment().show(supportFragmentManager, "DATE_TAG")
    }

}
