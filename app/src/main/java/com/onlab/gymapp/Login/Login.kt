package com.onlab.gymapp.Login
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*;
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.FirebaseFunctionsException
import com.onlab.gymapp.R
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.register_fragment.*

class Login : AppCompatActivity() {

    private lateinit var functions: FirebaseFunctions
    private lateinit var auth: FirebaseAuth
    lateinit var user : FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        auth = FirebaseAuth.getInstance();
        functions = FirebaseFunctions.getInstance()
        vpLogin.adapter= LoginPagerAdapter(supportFragmentManager)

    }



    fun ToSignUp(v: View){
        vpLogin.setCurrentItem(1,true)
    }

    fun SignIn(v: View){

        val email : String = EtEmail.text.toString()
        val password : String = EtPassword.text.toString()


        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    user = auth.currentUser!!
                    val id = user?.uid
                    Toast.makeText(this,"Sikeres belépés", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Authentication failed. " + email + " " + password,
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    fun addName(){
        var ret="Sikertelen regisztráció"
    addMessage().addOnCompleteListener(OnCompleteListener {
        task->
        if(!task.isSuccessful){
            val e =task.exception
            if (e is FirebaseFunctionsException) {
                val code = e.code
                val details = e.details
            }
        }
        if(task.result.equals("OK")){
                Toast.makeText(this,"Sikeres regisztráció", Toast.LENGTH_LONG).show()
            }
        else
            Toast.makeText(this,"Sikertelen regisztráció", Toast.LENGTH_LONG).show()
    })
}

    fun addMessage(): Task<String> {

        val data = hashMapOf(
            "userid" to user.uid,
            "name" to RegName.text.toString()
        )

        return functions.getHttpsCallable("register")
            .call(data)
            .continueWith{task ->
                val result = task.result?.data as String
            result
            }


    }

    fun SignUp(v:View){

        var regemail=RegEmail.text.toString()
        var regpass=RegPassword.text.toString()
        auth.createUserWithEmailAndPassword(regemail, regpass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    user = auth.currentUser!!
                    addName()

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this,task.exception!!.message, Toast.LENGTH_LONG).show()

                }

            }

    }

}