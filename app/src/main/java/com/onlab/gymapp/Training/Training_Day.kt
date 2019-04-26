package com.onlab.gymapp.Training

class Training_Day {
    var list_Of_Trainings:ArrayList<Training>?=null
    var duration_sum:Int=0
    var kcal_sum:Int=0
    var date:String=""

    fun add_Train(t:Training){
        if(list_Of_Trainings==null){
          list_Of_Trainings= ArrayList<Training>()
        }
        else if(list_Of_Trainings!!.isNotEmpty()){
            date = list_Of_Trainings!!.get(0).date
        }
            list_Of_Trainings!!.add(t)
            duration_sum+=t.duration_in_min
            kcal_sum+=t.kcal

    }

}