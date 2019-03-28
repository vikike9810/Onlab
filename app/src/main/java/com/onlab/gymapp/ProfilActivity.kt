package com.onlab.gymapp

import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.onlab.gymapp.Profile.User
import com.onlab.gymapp.Profile.profilePictureTask
import com.onlab.gymapp.Ticket.Ticket
import com.onlab.gymapp.Ticket.TicketsActivity
import com.onlab.gymapp.Ticket.Type
import kotlinx.android.synthetic.main.activity_profil.*
import java.net.URL
import java.util.*
import java.util.concurrent.TimeUnit

class ProfilActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)
        initTicketButton()
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


    private fun refreshPicture() {
        synchronized(User) {
            if (User.image != null)
                picture_profil.setImageBitmap(User.image)
        }
    }

    private fun initTicketButton() {
         if (Ticket.type.equals(Type.NINCS)){
                btn_ticket_profile.text = getString(R.string.nincs_rv_nyes_jegyed)
         }
        else{
             if (Ticket.type.equals(Type.HAVI)){
                 var today = Calendar.getInstance().time
                 var diffInMillis = Math.abs(Ticket.Date.time - today.time)
                 var diff = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS)
                 btn_ticket_profile.text = "Még " + diff.toString() + " napig érvényes a jegyed"
             }else{
                 btn_ticket_profile.text = "Még " + Ticket.DaysLeft + " alkalmad maradt"
             }
         }
    }

    fun goToTickets(v: View){
        startActivity(Intent(this, TicketsActivity::class.java))
    }


}
