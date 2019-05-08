package com.onlab.gymapp.Login

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*;
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.FirebaseFunctionsException
import com.onlab.gymapp.MainActivity
import com.onlab.gymapp.Profile.User
import com.onlab.gymapp.R
import kotlinx.android.synthetic.main.login.*
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.android.synthetic.main.register_fragment.*

class Login : AppCompatActivity(), LoginFragment.LoginListener {

    private lateinit var functions: FirebaseFunctions
    private lateinit var auth: FirebaseAuth
    lateinit var user: FirebaseUser
    var callbackManager = CallbackManager.Factory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        auth = FirebaseAuth.getInstance();
        functions = FirebaseFunctions.getInstance()
        vpLogin.adapter = LoginPagerAdapter(supportFragmentManager)
    }


    fun ToSignUp(v: View) {
        vpLogin.setCurrentItem(1, true)
    }

    fun SignIn(v: View) {

        val email: String = EtEmail.text.toString()
        val password: String = EtPassword.text.toString()


        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    user = auth.currentUser!!
                    MainActivity.user = user
                    val id = user?.uid
                    User.usID=id
                    Toast.makeText(this, "Sikeres belépés", Toast.LENGTH_LONG).show()
                    User.LoggedIn = false
                    finish()
                } else {
                    Toast.makeText(
                        this, "Authentication failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }


    fun addName(name: String) {
        var ret = "Sikertelen regisztráció"
        addMessage(name).addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                val e = task.exception
                if (e is FirebaseFunctionsException) {
                    val code = e.code
                    val details = e.details
                }
            }
            if (task.result.equals("OK" ) || task.result.equals("Login")) {
                Toast.makeText(this, "Sikeres regisztráció", Toast.LENGTH_LONG).show()
            } else
                Toast.makeText(this, "Sikertelen regisztráció", Toast.LENGTH_LONG).show()
        })
    }

    fun addMessage(name: String): Task<String> {

        val data = hashMapOf(
            "userid" to user.uid,
            "name" to name
        )

        return functions.getHttpsCallable("register")
            .call(data)
            .continueWith { task ->
                val result = task.result?.data as String
                result
            }


    }

    fun SignUp(v: View) {

        var regemail = RegEmail.text.toString()
        var regpass = RegPassword.text.toString()
        auth.createUserWithEmailAndPassword(regemail, regpass)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    user = auth.currentUser!!
                    addName(RegName.text.toString())

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this, task.exception!!.message, Toast.LENGTH_LONG).show()

                }

            }

    }

    fun gooUp(v: View) {
        //    FacebookLoginSetup()
    }

    override fun onLoggedIn() {
       FacebookLoginSetup()
    }

     fun FacebookLoginSetup() {

        buttonFacebookLogin.setReadPermissions("email", "public_profile")
        buttonFacebookLogin.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                buttonFacebookLogin.visibility = Button.INVISIBLE
                handleFacebookAccessToken(loginResult.accessToken)
            }

            override fun onCancel() {
                Toast.makeText(this@Login, "cancel", Toast.LENGTH_LONG).show()
            }

            override fun onError(error: FacebookException) {
                Toast.makeText(this@Login, error.message, Toast.LENGTH_LONG).show()
            }
        })
    }


    private fun handleFacebookAccessToken(token: AccessToken) {

        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    user = auth.currentUser!!
                    MainActivity.user = user
                    User.usID=user.uid
                    User.LoggedIn = false
                    addName(user!!.displayName ?: "nincs név")
                    finish()

                } else {
                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        callbackManager.onActivityResult(requestCode, resultCode, data)
    }


}