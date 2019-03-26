package com.onlab.gymapp.Contact

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import com.onlab.gymapp.R
import kotlinx.android.synthetic.main.activity_contact.*
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class ContactActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
        setLayout()
    }

    private fun setLayout() {
        ct_nev.setText(Gym.name)
        ct_tel.setText(Gym.phone)
        ct_cim.setText(Gym.address)
        ct_1.setText(Gym.monday)
        ct_2.setText(Gym.tuesday)
        ct_3.setText(Gym.wednesday)
        ct_4.setText(Gym.thursday)
        ct_5.setText(Gym.friday)
        ct_6.setText(Gym.saturday)
        ct_7.setText(Gym.sunday)
        ct_nyitva.setText("A mai napon " + getNyitvatartas()+"-ig van nyitva")
    }

    private fun getNyitvatartas(): String {

        val d  = Date(Calendar.getInstance().timeInMillis).day
       var nyit: String=" nm jó"
        when(d){
            1->nyit=Gym.monday
            2->nyit=Gym.tuesday
            3->nyit=Gym.wednesday
            4->nyit=Gym.thursday
            5->nyit=Gym.friday
            6->nyit=Gym.saturday
            7->nyit=Gym.sunday
        }
        return nyit
    }

}
