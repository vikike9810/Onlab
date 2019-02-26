package com.onlab.gymapp.Profile

import android.graphics.Bitmap
import com.google.android.gms.flags.Flag
import java.sql.Timestamp
import java.util.*

object User {

     var Name : String
     var Height : Integer
     var Weight : Integer
     var Birth : Date
     var LoggedIn : Boolean
     lateinit var imgUrl : String

    init {
        Name = ""
        Height = Integer(0)
        Weight = Integer(0)
        Birth = Date(0)
        LoggedIn = false
    }

}