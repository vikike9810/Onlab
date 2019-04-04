package com.onlab.gymapp.Training

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
        load_View()
    }

    fun load_View(){
        var cnt=0
        for( i in List_of_TraningDays) {
            var rowView = layoutInflater.inflate(R.layout.train_layout, null)
            if((cnt%2)==0){
                rowView.setBackgroundColor(getResources().getColor(R.color.colorlightblue))
            }
            rowView.lay_dur.setText(i.duration_sum.toString())
            rowView.lay_kcal.setText(i.kcal_sum.toString())
            Lay_Trains.addView(rowView)
            cnt++
        }
    }

    fun loadTrainings(){
        var training1=Training(Training_Type.Kardio,20,200)
        var training2=Training(Training_Type.Kardio,30,300)
        var training3=Training(Training_Type.Kardio,10,100)
        var training4=Training(Training_Type.Kardio,30,300)
        var training5=Training(Training_Type.Kardio,30,300)
        var training6=Training(Training_Type.Kardio,30,300)
        var training7=Training(Training_Type.Kardio,30,300)

        var day1=Training_Day()
        day1.add_Train(training1)
        day1.add_Train(training2)

        var day2=Training_Day()
        day2.add_Train(training3)
        day2.add_Train(training4)

        var day3=Training_Day()
        day3.add_Train(training5)
        day3.add_Train(training4)
        day3.add_Train(training6)

        var day4=Training_Day()
        day4.add_Train(training7)

        List_of_TraningDays.add(day1)
        List_of_TraningDays.add(day2)
        List_of_TraningDays.add(day3)
        List_of_TraningDays.add(day4)
    }

    fun nextButton(v: View){
        startActivity(Intent(this, TodayActivity::class.java))
    }
}
