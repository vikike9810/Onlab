package com.onlab.gymapp.Dao


import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.onlab.gymapp.Training.Training


@Database(entities = arrayOf(Training::class), version = 1)
abstract class TrainingDatabase : RoomDatabase() {
    abstract fun TrainingDao(): TrainingDao

    companion object {
        private var INSTANCE: TrainingDatabase? = null

        fun getAppDataBase(context: Context): TrainingDatabase? {

            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.applicationContext,
                    TrainingDatabase::class.java, "training2.db")
                    .build()
            }
            return INSTANCE!!
        }
        fun destroyDataBase(){
            INSTANCE = null
        }
    }


}