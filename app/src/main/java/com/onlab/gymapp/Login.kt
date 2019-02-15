package com.onlab.gymapp
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
    }

    fun SignUp(v: View){
        Toast.makeText(this,"jauuuu", Toast.LENGTH_LONG).show()
    }
}