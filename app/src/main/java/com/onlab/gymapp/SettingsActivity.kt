package com.onlab.gymapp

import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.View
import android.widget.DatePicker
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.FirebaseFunctionsException
import com.google.firebase.storage.FirebaseStorage
import com.onlab.gymapp.Profile.User

import kotlinx.android.synthetic.main.activity_settings.*
import com.onlab.gymapp.Login.DatePickerDialogFragment
import com.onlab.gymapp.Profile.profilePictureTask
import com.onlab.gymapp.Ticket.DateConverter
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat


class SettingsActivity : AppCompatActivity(), DatePickerDialogFragment.OnDateSelectedListener {


    private lateinit var functions: FirebaseFunctions
    private lateinit var auth: FirebaseAuth
    var user: FirebaseUser? = null
    var storage = FirebaseStorage.getInstance()

    companion object {
        const val PICK_IMAGE = 1
        const val PICTURE_SIZE = 150
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        auth = FirebaseAuth.getInstance();
        functions = FirebaseFunctions.getInstance()
        user = auth.currentUser

    }

    override fun onResume() {
        super.onResume()
        et_name_settings.setText(User.Name)
        et_birth_settings.setText(DateConverter.convert(User.Birth.year,User.Birth.month,User.Birth.date))
        et_height_settings.setText(User!!.Height?.toString())
        et_weight_settings.setText(User!!.Weight?.toString())
        refreshPicture()
    }


    override fun onDateSelected(year: Int, month: Int, day: Int) {
        et_birth_settings.setText(DateConverter.convert(year,month,day))
    }



    fun Save(v: View) {
        User.Name = et_name_settings.text.toString()
        User.Height = Integer.parseInt(et_height_settings.text.toString())
        User.Weight = Integer.parseInt(et_weight_settings.text.toString())
        val format = SimpleDateFormat("yyyy.MM.dd")
        User.Birth = format.parse(et_birth_settings.text.toString())
        saveSettings()
        uploadPictureToServer()
    }

    private fun uploadPictureToServer() {
        val storageRef = storage.reference
        val imageRef = storageRef.child("images/" + user?.uid + ".jpg")
        val baos = ByteArrayOutputStream()
        User.image.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        var uploadTask = imageRef.putBytes(data)
        uploadTask.addOnFailureListener {
            Toast.makeText(this, "Hiba a kép feltöltésnél", Toast.LENGTH_LONG).show()
        }
    }

    private fun saveSettings() {

        saveToServer().addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                val e = task.exception
                if (e is FirebaseFunctionsException) {
                    val code = e.code
                    val details = e.details
                }
            } else {
                if (task.result.equals("OK")) {
                    Toast.makeText(this, "Adatok elmentve", Toast.LENGTH_LONG).show()
                    finish()
                } else
                    Toast.makeText(this, "Hiba", Toast.LENGTH_LONG).show()
            }
        })

    }

    private fun saveToServer(): Task<String> {
        val data = hashMapOf(
            "userid" to user?.uid,
            "name" to User.Name,
            "height" to User.Height.toString(),
            "weight" to User.Weight.toString(),
            "birth" to DateConverter.convert(User.Birth.year, User.Birth.month, User.Birth.date)
        )

        return functions.getHttpsCallable("setSettings")
            .call(data)
            .continueWith { task ->
                val result = task.result?.data as String
                result
            }
    }

    fun Cancel(v: View) {
        finish()
    }

    fun DateClick(v: View) {
        DatePickerDialogFragment().show(supportFragmentManager, "DATE_TAG")
    }

    fun refreshPicture() {
        synchronized(User) {
            if (User.image != null)
                picture_settings.setImageBitmap(User.image)
        }
    }

    fun uploadNewPicture(v: View) {
        var intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE) {
            if (data != null) {
                var uri = data?.data
                var inputStream = this.contentResolver.openInputStream(uri)
                var bitmap = BitmapFactory.decodeStream(inputStream)
                bitmap = cutFromBitmap(bitmap)
                var newWidth = PICTURE_SIZE.toFloat() / bitmap.height.toFloat() * bitmap.width.toFloat()
                bitmap = resizeBitmap(bitmap, newWidth, PICTURE_SIZE.toFloat())
                User.image = bitmap
                refreshPicture()

            }
        }
    }

    private fun cutFromBitmap(bitmap: Bitmap?): Bitmap? {
        var newBitmap: Bitmap
        if (bitmap?.height!! > bitmap.width!!) {
            var start = (bitmap.height - bitmap.width) / 2
            newBitmap = Bitmap.createBitmap(bitmap, 0, start, bitmap.width, bitmap.width, null, false)
        } else {
            var start = (bitmap.width - bitmap.height) / 2
            var matrix = Matrix()
            matrix.postRotate(270F)
            newBitmap = Bitmap.createBitmap(bitmap, start, 0, bitmap.height, bitmap.height, matrix, false)
        }
        return newBitmap

    }

    private fun resizeBitmap(bm: Bitmap, newWidth: Float, newHeight: Float): Bitmap {
        var width = bm.width
        var height = bm.height
        var scaleWidth = newWidth / width
        var scaleHeight = newHeight / height
        var matrix = Matrix()
        matrix.postScale(scaleWidth, scaleHeight)
        var resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false)
        bm.recycle()
        return resizedBitmap
    }

    fun rotatePicture(v: View) {
        var newBitmap: Bitmap
        var matrix = Matrix()
        matrix.postRotate(90F)
        newBitmap = Bitmap.createBitmap(User.image, 0, 0, User.image.width, User.image.height, matrix, false)
        User.image = newBitmap
        picture_settings.setImageBitmap(User.image)

    }

}
