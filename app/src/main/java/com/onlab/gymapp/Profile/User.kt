package com.onlab.gymapp.Profile

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.android.gms.flags.Flag
import com.onlab.gymapp.R
import com.onlab.gymapp.Ticket.Ticket
import java.sql.Timestamp
import java.util.*

object User {


    var Name: String
    var Height: Int
    var Weight: Int
    var Birth: Date
    var LoggedIn: Boolean
    var usID:String="nincs Id"
    lateinit var imgUrl: String
    lateinit var image: Bitmap

    init {
        Name = ""
        Height = 0
        Weight = 0
        Birth = Date(0)
        LoggedIn = false
    }

    fun resetUser(resources: Resources?) {
        Name = ""
        Height = 0
        Weight = 0
        Birth = Date(0)
        LoggedIn = false
        image = BitmapFactory.decodeResource(resources, R.drawable.no_profile_picture)
        imgUrl = "null"

    }


}