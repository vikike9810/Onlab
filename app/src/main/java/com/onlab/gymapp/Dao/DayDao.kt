package com.onlab.gymapp.Dao

import android.arch.persistence.room.*
import com.onlab.gymapp.Training.Training_Day

@Dao
interface DayDao {

    @Query("DELETE FROM TrainingDay")
    fun deleteAll()

    @Query("SELECT * FROM TrainingDay")
    fun getAll():List<Training_Day>


    @Insert
    fun insert(trainingday: Training_Day): Long

    @Update
    fun update(trainingday: Training_Day)

    @Delete
    fun deleteItem(trainingday: Training_Day)

}