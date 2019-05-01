package com.onlab.gymapp.Dao


import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.onlab.gymapp.Training.Training_Day


@Database(entities = arrayOf(Training_Day::class), version = 1)
abstract class DayDatabase : RoomDatabase() {
    abstract fun DayDao(): DayDao

    companion object {
        private var INSTANCE: DayDatabase? = null

        fun getAppDataBase(context: Context): DayDatabase? {

            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                    DayDatabase::class.java, "trainingday2.db")
                    .build()
            }
            return INSTANCE!!
        }
        fun destroyDataBase(){
            INSTANCE = null
        }
    }
}