package com.onlab.gymapp.Training

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import android.view.View
import com.onlab.gymapp.Dao.TrainingDatabase
import com.onlab.gymapp.R
import kotlinx.android.synthetic.main.activity_training.*
import kotlinx.android.synthetic.main.train_layout.view.*


class TrainingActivity : AppCompatActivity() {

    lateinit var List_of_TraningDays: ArrayList<Training_Day>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training)
        List_of_TraningDays= ArrayList<Training_Day>()
        loadTrainings()
    }

    fun load_View(){
        var cnt=0
        for( i in List_of_TraningDays) {
            var rowView = layoutInflater.inflate(R.layout.train_layout, null)
            when(cnt%7){
                0 -> rowView.setBackgroundColor(getResources().getColor(R.color.colorlightblue))
                1 -> rowView.setBackgroundColor(getResources().getColor(R.color._light_green))
                2 -> rowView.setBackgroundColor(getResources().getColor(R.color.pink))
                4 -> rowView.setBackgroundColor(getResources().getColor(R.color.peach_puff))
                5 -> rowView.setBackgroundColor(getResources().getColor(R.color.light_yellow))
                6 -> rowView.setBackgroundColor(getResources().getColor(R.color.mediumPurple))
            }
            rowView.lay_dur.setText(i.duration_sum.toString())
            rowView.lay_kcal.setText(i.kcal_sum.toString())
            rowView.lay_date.setText(i.date)
            Lay_Trains.addView(rowView)
            cnt++
        }
    }

    fun loadTrainings(){
        var training1=Training(null,Training_Type.Kardio.toString(),20,200,"2014.04.05")
        var training2=Training(null,Training_Type.Kardio.toString(),30,300,"")
        var training3=Training(null,Training_Type.Kardio.toString(),10,100,"2015.36.63")
        var training4=Training(null,Training_Type.Kardio.toString(),30,300,"2010.15.16")
        var training5=Training(null,Training_Type.Kardio.toString(),30,300,"2017.12.05")
        var training6=Training(null,Training_Type.Kardio.toString(),30,300,"")
        var training7=Training(null,Training_Type.Kardio.toString(),30,300,"")

        var list1=ArrayList<Training>()
        list1.add(training1)
        list1.add(training2)
        var day1=createTrainingDayfromlist(list1)

        var list2=ArrayList<Training>()
        list2.add(training3)
        list2.add(training4)
        var day2=createTrainingDayfromlist(list2)

        var list3=ArrayList<Training>()
        list3.add(training5)
        list3.add(training4)
        list3.add(training6)
        var day3=createTrainingDayfromlist(list3)

        var list4=ArrayList<Training>()
        list4.add(training7)
        var day4=createTrainingDayfromlist(list4)

        List_of_TraningDays.add(day1)
        List_of_TraningDays.add(day2)
        List_of_TraningDays.add(day3)
        List_of_TraningDays.add(day4)

        checkDate()
    }

    fun checkDate(){

            val dbThread = Thread {
                     var items: List<Training>? =null
                     items= TrainingDatabase.getAppDataBase(this)!!.TrainingDao().getAll() ?: null
                    runOnUiThread {
                        if (!(items == null) && items.isNotEmpty()) {
                            if (!(AddFragment.getcurrDate().equals(items.get(0).date))) {
                                var today = createTrainingDayfromlist(items)
                                List_of_TraningDays.add(today)
                                DeleteAll()
                            }
                        }
                        load_View()

                    }
            }
            dbThread.start()
    }

    fun DeleteAll(){
        val dbThread = Thread {
            TrainingDatabase.getAppDataBase(this)!!.TrainingDao().deleteAll()
        }
        dbThread.start()
    }


    fun createTrainingDayfromlist(list :List<Training>):Training_Day{
        var first=list.get(0)
        var newday =Training_Day()
        newday.date=first.date
        for(i in list){
            newday.duration_sum+=i.duration_in_min
            newday.kcal_sum+=i.kcal
        }
        return newday
    }

    fun nextButton(v: View){
        startActivity(Intent(this, TodayActivity::class.java))
    }
}
