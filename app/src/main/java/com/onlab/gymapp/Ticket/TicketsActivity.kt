package com.onlab.gymapp.Ticket

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.onlab.gymapp.R



class TicketsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tickets)
        showFragment()
    }

    private fun showFragment() {

        val transaction = supportFragmentManager.beginTransaction()

        if(Ticket.type.equals(Type.NINCS)){
            transaction.replace(R.id.layoutContent, BuyingFragment())
        }
        else {

            transaction.replace(R.id.layoutContent, TicketFragment())
        }

        transaction.commit()
    }

}
