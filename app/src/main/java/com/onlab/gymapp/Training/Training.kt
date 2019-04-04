package com.onlab.gymapp.Training

class Training {
   var type : Training_Type=Training_Type.Kardio
    var duration_in_min:Int=0
    var kcal: Int =0

   constructor(t:Training_Type, d:Int, kc:Int){
       type=t
       duration_in_min=d
       kcal=kc
   }
}