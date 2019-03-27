package com.onlab.gymapp.Ticket

class DateConverter {

    companion object {

        fun convert(year: Int, month: Int, day: Int): String {
            var sday = day.toString()
            var syear : String
            if (year < 1000){
                syear = (year+1900).toString()
            }
            else syear = year.toString()
            var smonth = (month + 1).toString()
            if (day < 10) {
                sday = "0" + day.toString()
            }
            if (month < 9) {
                smonth = "0" + (month + 1).toString()
            }
            return syear + "." + smonth + "." + sday
        }
    }
}