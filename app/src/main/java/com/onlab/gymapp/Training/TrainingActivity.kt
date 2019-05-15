package com.onlab.gymapp.Training

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import android.view.View
import android.widget.Toast
import com.onlab.gymapp.Dao.DayDatabase
import com.onlab.gymapp.Dao.TrainingDatabase
import com.onlab.gymapp.DialogFragments.LogoutDialogFragment
import com.onlab.gymapp.Login.Login
import com.onlab.gymapp.MainActivity
import com.onlab.gymapp.Profile.User
import com.onlab.gymapp.R
import com.onlab.gymapp.SettingsActivity
import com.onlab.gymapp.Storage.DBStorage
import kotlinx.android.synthetic.main.activity_training.*
import kotlinx.android.synthetic.main.train_layout.view.*
import java.io.File
import kotlinx.android.synthetic.main.activity_training.view.*


class TrainingActivity : AppCompatActivity() {

    lateinit var List_of_TraningDays: ArrayList<Training_Day>

    override fun onCreate(savedInstanceState: Bundle?) {
        List_of_TraningDays= ArrayList<Training_Day>()
        super.onCreate(savedInstanceState)
        setTheme(R.style.AppTheme_NoActionBar)
        setContentView(R.layout.activity_training)
        loadTrainings()
        setSupportActionBar(Lay_toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.restore_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_save -> {
                DBStorage.saveFiles(User.usID ,this,List_of_TraningDays)
                return true
            }
            R.id.action_refresh -> {
                DBStorage.downloadFiles(User.usID ,this,this)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun load_View(){
        var cnt=0
        Lay_Trains.removeAllViews()
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

        val dbThread = Thread {
            var items: List<Training_Day>? =null
            items=DayDatabase.getAppDataBase(this)!!.DayDao().getAll() ?: null
            runOnUiThread {
                if (!(items == null)) {
                        List_of_TraningDays.addAll(items)
                }
                checkDate()

            }
        }
        dbThread.start()
    }

    fun checkDate(){

            val dbThread = Thread {
                     var items: List<Training>? =null
                     items= TrainingDatabase.getAppDataBase(this)!!.TrainingDao().getAll() ?: null
                    runOnUiThread {
                        if (!(items == null) && items.isNotEmpty()) {
                            if (!(AddFragment.getcurrDate().equals(" rw"))) {//items.get(0).date
                                var today = createTrainingDayfromlist(items)
                                List_of_TraningDays.add(today)
                                saveDay(today)
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

    fun saveDay(d :Training_Day){

        val dbThread = Thread {
            val id = DayDatabase.getAppDataBase(this)!!.DayDao().insert(d)
            d.itemId = id
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

    fun updateList(list:ArrayList<Training_Day>){
        List_of_TraningDays.clear()
        List_of_TraningDays.addAll(list)
        deleteDB()
        load_View()
        for(item in List_of_TraningDays){
            saveDay(item)
        }
    }

    fun deleteDB(){
        val dbThread = Thread {
            DayDatabase.getAppDataBase(this)!!.DayDao().deleteAll()
        }
        dbThread.start()
    }
    fun nextButton(v: View){
        startActivity(Intent(this, TodayActivity::class.java))
    }
}
