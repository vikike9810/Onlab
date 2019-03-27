package com.onlab.gymapp.Ticket

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.auth0.android.jwt.JWT
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.functions.FirebaseFunctions
import com.onlab.gymapp.R
import java.util.*


class TicketsActivity : AppCompatActivity() {

    private lateinit var functions: FirebaseFunctions
    private lateinit var user: FirebaseUser
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tickets)
        functions = FirebaseFunctions.getInstance()
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser!!
        if (!Ticket.loaded){
        getTicket().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                when (task.result!!["type"]) {
                    "1" -> {Ticket.type = Type.EGY_ALKALMAS;Ticket.DaysLeft = task.result!!["usages"]!!.toInt() }
                    "5" -> {Ticket.type = Type.OT_ALKALMAS;Ticket.DaysLeft = task.result!!["usages"]!!.toInt() }
                    "10" -> {Ticket.type = Type.TIZ_ALKALMAS;Ticket.DaysLeft = task.result!!["usages"]!!.toInt() }
                    "31" -> {
                        Ticket.type = Type.HAVI
                        var date = task.result!!["expiration"].toString().toLong()
                        Ticket.Date = Date( date*1000)
                    }
                    "noticket" -> Ticket.type = Type.NINCS
                }
                Ticket.loaded = true
                showFragment()
            } else {
                Toast.makeText(this, task.exception.toString(), Toast.LENGTH_SHORT).show()
            }
        }
        }
        else{
            showFragment()
        }
    }


    private fun showFragment() {

        val transaction = supportFragmentManager.beginTransaction()

        if (Ticket.type.equals(Type.NINCS)) {
            transaction.replace(R.id.layoutContent, BuyingFragment())
        } else {

            transaction.replace(R.id.layoutContent, TicketFragment())
        }

        transaction.commit()
    }

    fun buyTicket(v: View) {
        var tag = v.tag.toString()
        buyTicketsFromServer(tag).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                var ticket = task.result
                val jwt = JWT(ticket!!)
                if (tag.equals("31")) {
                    var date = jwt.getClaim("exp").asDate()
                    Ticket.Date = date!!
                    Ticket.type = Type.HAVI
                } else {
                    var daysleft = jwt.getClaim("usages").asInt()
                    Ticket.DaysLeft = daysleft!!
                    when (daysleft) {
                        1 -> Ticket.type = Type.EGY_ALKALMAS
                        5 -> Ticket.type = Type.OT_ALKALMAS
                        10 -> Ticket.type = Type.TIZ_ALKALMAS
                    }
                }
                Ticket.loaded = true
                showFragment()
            } else {

            }
        }
    }

    private fun buyTicketsFromServer(tag: String): Task<String> {
        val data = hashMapOf(
            "tag" to tag,
            "userid" to user.uid
        )
        return functions.getHttpsCallable("buyTicket")
            .call(data)
            .continueWith { task ->
                val result = task.result!!.data as String
                result
            }
    }

    private fun getTicket(): Task<HashMap<String, String>> {
        val data = hashMapOf(
            "userid" to user.uid
        )
        return functions.getHttpsCallable("getTicket")
            .call(data)
            .continueWith { task ->
                val result = task.result!!.data as HashMap<String, String>
                result
            }

    }

}
