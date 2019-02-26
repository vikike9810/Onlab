package com.onlab.gymapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.functions.FirebaseFunctions
import com.google.firebase.functions.FirebaseFunctionsException
import com.onlab.gymapp.Login.Login
import com.onlab.gymapp.Profile.User

import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat

class MainActivity : AppCompatActivity() {

    var user: FirebaseUser? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var functions: FirebaseFunctions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        auth = FirebaseAuth.getInstance()
        functions = FirebaseFunctions.getInstance()
        user = auth.currentUser
        if (user != null && !User.LoggedIn) {
            User.LoggedIn = true
            getUserDetails()
            User.imgUrl = user?.photoUrl.toString() + "?type=large"
        }

    }

    override fun onResume() {
        super.onResume()

        if (!User.LoggedIn) {
            getUserDetails()
        }
    }

    private fun getUserDetails() {
        getDetailsFromServer().addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                val e = task.exception
                if (e is FirebaseFunctionsException) {
                    val code = e.code
                    val details = e.details
                    User.LoggedIn = false
                }
            } else {
                if (task.result["name"].equals("Error")) {
                    Toast.makeText(this, "Hiba történt az adatok kérésekor", Toast.LENGTH_LONG).show()
                    User.LoggedIn = false
                } else {
                    User.Name = task.result["name"]!!
                    val format = SimpleDateFormat("yyyy.MM.dd")
                    User.Birth = format.parse(task.result["birth"])
                    User.Height = Integer.parseInt(task.result["height"]) as Integer
                    User.Weight = Integer.parseInt(task.result["weight"]) as Integer
                    User.LoggedIn = true
                }
            }
        })
    }

    private fun getDetailsFromServer(): Task<HashMap<String, String>> {
        val data = hashMapOf(
            "userid" to user?.uid
        )
        return functions.getHttpsCallable("getDetails")
            .call(data)
            .continueWith { task ->
                val result = task.result?.data as HashMap<String, String>
                result;
            }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_login -> {
                startActivity(Intent(this, Login::class.java))
                return true
            }
            R.id.action_settings -> {
                startActivity(Intent(this, SettingsActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun Profil(v: View) {
        startActivity(Intent(this, ProfilActivity::class.java))
    }
}
