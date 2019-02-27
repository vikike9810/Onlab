package com.onlab.gymapp.Profile

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.android.gms.flags.Flag
import com.onlab.gymapp.R
import java.sql.Timestamp
import java.util.*

object User {

    var Name: String
    var Height: Integer
    var Weight: Integer
    var Birth: Date
    var LoggedIn: Boolean
    lateinit var imgUrl: String
    lateinit var image: Bitmap

    init {
        Name = ""
        Height = Integer(0)
        Weight = Integer(0)
        Birth = Date(0)
        LoggedIn = false
    }

}