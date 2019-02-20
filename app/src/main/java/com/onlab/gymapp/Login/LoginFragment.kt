package com.onlab.gymapp.Login

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.onlab.gymapp.R
import kotlinx.android.synthetic.main.login_fragment.*

class LoginFragment : Fragment() {

    lateinit var callbackManager : CallbackManager
    lateinit var act : LoginListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        callbackManager = CallbackManager.Factory.create()

    }

    override fun onCreateView(inflater: LayoutInflater, container:ViewGroup?, savedInstanceState: Bundle?): View?{

        val view= inflater.inflate(R.layout.login_fragment,container, false)
        return view

    }

    override fun onResume() {
        super.onResume()
        act.FacebookLoginSetup()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        act=context as LoginListener

    }

    public interface LoginListener {
        fun FacebookLoginSetup()
    }



   /* fun FacebookLoginSetup() {
        // Initialize Facebook Login button

        buttonFacebookLogin.setReadPermissions("email", "public_profile")
        buttonFacebookLogin.registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                EtEmail.setText("Success")
            }

            override fun onCancel() {
                EtEmail.setText("Cancel")
            }

            override fun onError(error: FacebookException) {
                EtEmail.setText("Error")
            }
        })
    }*/


}