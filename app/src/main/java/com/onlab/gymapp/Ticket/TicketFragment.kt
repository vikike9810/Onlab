package com.onlab.gymapp.Ticket

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.onlab.gymapp.R
import kotlinx.android.synthetic.main.ticket_fragment.*

class TicketFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.ticket_fragment, container, false)
    }

    override fun onResume() {
        super.onResume()
        fr_type.setText(Ticket.type.toString())
        if (Ticket.type == Type.HAVI) {
            fr_date.setText(DateConverter.convert(Ticket.Date.year, Ticket.Date.month,Ticket.Date.date))
            fr_num.visibility = View.INVISIBLE
        }
        else {
            fr_num.setText(Ticket.DaysLeft.toString())
            fr_date.visibility = View.INVISIBLE
        }
    }


}