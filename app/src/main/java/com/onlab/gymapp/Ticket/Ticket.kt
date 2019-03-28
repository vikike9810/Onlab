package com.onlab.gymapp.Ticket

import java.util.*

object Ticket {

    var type: Type = Type.NINCS
    var Date: Date = Date(2019, 9, 20)
    var DaysLeft: Int = 8
    var loaded: Boolean = false
    var token: String = ""

    fun reset() {
        loaded = false
    }
}