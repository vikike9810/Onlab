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
        type = Type.NINCS
    }
    fun getString(): String {
        when(type){
            Type.NINCS -> return "Nincs jegyed"
            Type.EGY_ALKALMAS -> return "Egy alkalmas"
            Type.OT_ALKALMAS -> return "Öt alkalmas"
            Type.TIZ_ALKALMAS -> return "Tíz alkalmas"
            Type.HAVI -> return "Egy hónapos bérlet"
        }
    }
}