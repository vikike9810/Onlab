package com.onlab.gymapp
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.*;
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.login_fragment.*

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        auth = FirebaseAuth.getInstance();
        vpLogin.adapter= LoginPagerAdapter(supportFragmentManager)

    }

    fun SignUp(v: View){
        vpLogin.setCurrentItem(1,true)
    }

    fun SignIn(v: View){

        val email : String = EtEmail.text.toString()
        val password : String = EtPassword.text.toString()


        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val id = user?.uid
                    Toast.makeText(this,"Sikeres belépés", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Authentication failed. " + email + " " + password,
                        Toast.LENGTH_SHORT).show()
                }

            }


    }

}