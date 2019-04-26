package com.onlab.gymapp.Training

import android.arch.persistence.room.*
import java.io.Serializable

enum class Training_Type {
    Kardio, Sulyzos_edzes, Nyujtas;
}

@Entity(tableName= "Training")
data class Training  (

    @PrimaryKey(autoGenerate = true)
    var itemId: Long?,

   @ColumnInfo(name = "type")
    var type : String=Training_Type.Kardio.toString(),

    @ColumnInfo(name = "duration")
    var duration_in_min:Int=0,

   @ColumnInfo(name = "kcal")
    var kcal: Int =0,

    @ColumnInfo(name = "date")
    var date: String): Serializable

