package com.onlab.gymapp.Dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.onlab.gymapp.Training.Training

@Dao
interface TrainingDao {

    @Query("DELETE FROM Training")
    fun deleteAll()

    @Query("SELECT * FROM Training")
    fun getAll():List<Training>

    @Insert
    fun insert(training: Training): Long

    @Update
    fun update(training: Training)

    @Delete
    fun deleteItem(training: Training)

}