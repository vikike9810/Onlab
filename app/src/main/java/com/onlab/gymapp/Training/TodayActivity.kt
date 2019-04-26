package com.onlab.gymapp.Training

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View

import com.onlab.gymapp.R
import kotlinx.android.synthetic.main.trainings_recycler_view.*
import com.onlab.gymapp.Dao.TrainingDatabase


class TodayActivity : AppCompatActivity(),AddFragment.TrainingCreatedListener {


    var trainings: ArrayList<Training> = ArrayList()
    var adapter=TrainingAdapter(trainings, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_today)
        loadItems2()


        // Creates a vertical Layout Manager

        // Access the RecyclerView Adapter and load the data into it


    }

    private fun loadItems() {
        adapter.AddTraining(Training(null,Training_Type.Kardio.toString(),20,200,""))
        adapter.AddTraining(Training(null,Training_Type.Sulyzos_edzes.toString(),30,300,""))
        adapter.AddTraining(Training(null,Training_Type.Nyujtas.toString(),10,100,""))
        adapter.AddTraining(Training(null,Training_Type.Kardio.toString(),30,300,""))
        adapter.AddTraining(Training(null,Training_Type.Nyujtas.toString(),30,300,""))
        adapter.AddTraining(Training(null,Training_Type.Sulyzos_edzes.toString(),30,300,""))
        rv_training_list.adapter = adapter
        rv_training_list.layoutManager = LinearLayoutManager(this)
    }

    override fun onTrainingCreated(training: Training) {

        val dbThread = Thread {
            val id = TrainingDatabase.getAppDataBase(this)!!.TrainingDao().insert(training)
            training.itemId = id
            runOnUiThread{
                adapter.AddTraining(training)
            }
        }
        dbThread.start()
       // adapter.AddTraining(training)
    }

    fun FloatingAdd(v: View){
        val todoCreateFragment = AddFragment()
        todoCreateFragment.show(supportFragmentManager, "TAG")
    }

 private fun loadItems2() {

        val dbThread = Thread {
            val items = TrainingDatabase.getAppDataBase(this)!!.TrainingDao().getAll() ?:null
            runOnUiThread{
                //adapter.itemClickListener = this
                if(!(items==null)) {
                   adapter.addAll(items)
                }
                rv_training_list.adapter = adapter
                rv_training_list.layoutManager = LinearLayoutManager(this)
            }
        }
        dbThread.start()
    }


}
