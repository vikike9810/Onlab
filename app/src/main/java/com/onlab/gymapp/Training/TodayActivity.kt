package com.onlab.gymapp.Training

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.onlab.gymapp.R
import kotlinx.android.synthetic.main.activity_today.*
import kotlinx.android.synthetic.main.trainings_recycler_view.*

class TodayActivity : AppCompatActivity() {

    val trainings: ArrayList<Training> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_today)
        loadTrainings()

        // Creates a vertical Layout Manager
        rv_training_list.layoutManager = LinearLayoutManager(this)

        // Access the RecyclerView Adapter and load the data into it
        rv_training_list.adapter = TrainingAdapter(trainings, this)

    }

    private fun loadTrainings() {
        trainings.add(Training(Training_Type.Kardio,20,200))
        trainings.add(Training(Training_Type.Sulyzos_edzes,30,300))
        trainings.add(Training(Training_Type.Nyujtas,10,100))
        trainings.add(Training(Training_Type.Kardio,30,300))
        trainings.add(Training(Training_Type.Nyujtas,30,300))
        trainings.add(Training(Training_Type.Sulyzos_edzes,30,300))
        trainings.add(Training(Training_Type.Kardio,30,300))
        trainings.add(Training(Training_Type.Kardio,30,300))
        trainings.add(Training(Training_Type.Nyujtas,30,300))
        trainings.add(Training(Training_Type.Sulyzos_edzes,30,300))
        trainings.add(Training(Training_Type.Kardio,30,300))
    }

}
