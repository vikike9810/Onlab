package com.onlab.gymapp.Storage

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.widget.Toast
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.onlab.gymapp.Dao.DayDatabase
import com.onlab.gymapp.Profile.User
import com.onlab.gymapp.Training.TrainingActivity
import com.onlab.gymapp.Training.Training_Day
import java.io.*

class DBStorage {
    companion object {

        var file:File?=null
        var storage = FirebaseStorage.getInstance()
        var storageRef = storage.reference

       fun saveFiles(user:String, c : Context,list: ArrayList<Training_Day>) {

           SaveList(list,c)

           var userRef: StorageReference? = storageRef.child("DB/" + user + "/lista.txt")

           var fileday = Uri.fromFile(file)

           val riversRef = storageRef.child("DB/" + user + "/${fileday.lastPathSegment}")
           var uploadTask = riversRef.putFile(fileday)

           uploadTask.addOnFailureListener {
               Toast.makeText(c, "Hiba történt", Toast.LENGTH_LONG).show()
           }.addOnSuccessListener {
               Toast.makeText(c, "Fájl1 feltöltve", Toast.LENGTH_LONG).show()
           }
       }

        fun SaveList(list: ArrayList<Training_Day>,c:Context){
            file= File(c.filesDir,"lista.txt")
           var finp=FileOutputStream(file)
           var iostream=ObjectOutputStream(finp)
           iostream.writeObject(list)
        }

         fun downloadFiles(user:String, c : Context, act: TrainingActivity){
             val userRef=storage.getReferenceFromUrl("gs://onlab-gymapp.appspot.com/DB/"+User.usID+"/lista.txt")

            if(file==null){
                file= File(c.filesDir,"lista.txt")
            }


            // Toast.makeText(c,"Kitörlés "+ret.toString(), Toast.LENGTH_LONG).show()

             userRef!!.getFile(file!!).addOnSuccessListener {
                 Toast.makeText(c,"Letöltve", Toast.LENGTH_LONG).show()
                 act.updateList(convertList(c))
             }.addOnFailureListener {
                 Toast.makeText(c,"Hiba a letöltésben", Toast.LENGTH_LONG).show()
             }
         }

        fun convertList(c:Context):ArrayList<Training_Day>{
            var finp=FileInputStream(file)
            var iostream=ObjectInputStream(finp)
            return iostream.readObject() as ArrayList<Training_Day>

        }



    }
}