package com.onlab.gymapp.Training

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.io.Serializable

@Entity(tableName= "TrainingDay")
data class Training_Day (

    @PrimaryKey(autoGenerate = true)
    var itemId: Long=0,

    @ColumnInfo(name = "dur_sum")
    var duration_sum:Int=0,

    @ColumnInfo(name = "kcal_sum")
    var kcal_sum:Int=0,

    @ColumnInfo(name = "date")
    var date:String=""
):Serializable
