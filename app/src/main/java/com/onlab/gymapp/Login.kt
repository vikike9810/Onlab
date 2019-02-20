package com.onlab.gymapp
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.*;
import kotlinx.android.synthetic.main.login.*

class Login : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        auth = FirebaseAuth.getInstance();
    }

    fun SignUp(v: View){
        Toast.makeText(this,"jauuuu", Toast.LENGTH_LONG).show()
    }

    fun SignIn(v: View){

        val email : String = EtEmail.text.toString()
        val password : String = EtPassword.text.toString()


        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val id = user?.uid
                    Toast.makeText(this,"Sikeres belépés",Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(baseContext, "Authentication failed. " + email + " " + password,
                        Toast.LENGTH_SHORT).show()
                }

            }


    }


}