package com.onlab.gymapp.Login

import android.support.v4.app.DialogFragment
import android.widget.DatePicker
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import java.util.*


class DatePickerDialogFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private var onDateSelectedListener: OnDateSelectedListener? = null

   override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context !is OnDateSelectedListener) {
            throw RuntimeException("The activity does not implement the" + "OnDateSelectedListener interface")
        }

        onDateSelectedListener = context
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(
            activity!!, this,
            year, month, day
        )
    }

    override fun onDateSet(datePicker: DatePicker, year: Int, month: Int, day: Int) {
        onDateSelectedListener!!.onDateSelected(year, month, day)
    }

    interface OnDateSelectedListener {
        fun onDateSelected(year: Int, month: Int, day: Int)
    }
}