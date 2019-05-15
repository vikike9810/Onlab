package com.onlab.gymapp.Login

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.common.SignInButton
import com.onlab.gymapp.R
import kotlinx.android.synthetic.main.login_fragment.*
import java.io.Serializable

class LoginFragment : Fragment() {

    lateinit var act : LoginListener
    lateinit var googleSignInButton: SignInButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container:ViewGroup?, savedInstanceState: Bundle?): View?{

        val view= inflater.inflate(R.layout.login_fragment,container, false)
        googleSignInButton = view.findViewById(R.id.btn_google_signin) as SignInButton
        googleSignInButton.setOnClickListener {
            act.onGoogleSignIn()
        }
        return view

    }

    override fun onResume() {
        super.onResume()
        act.onLoggedIn()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        act=context as LoginListener

    }

     interface LoginListener  {
        fun onLoggedIn()
         fun onGoogleSignIn()
    }

}