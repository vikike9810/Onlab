package com.onlab.gymapp.DialogFragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView

import com.onlab.gymapp.R

class BuyTicketDialogFragment : DialogFragment() {

    interface OnTicketBuyListener{
        fun goToTickets()
    }

    private lateinit var listener : OnTicketBuyListener
    private lateinit var tvGotoTickets: TextView
    companion object {
        var TAG: String = "BuyTicketDialogFragment"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setView(getContentView())
            .setPositiveButton(getString(R.string.jegyvasarlas)) { dialog, which ->
                listener.goToTickets()
            }
            .setNegativeButton(R.string.cancel,null)
            .create()
    }

    private fun getContentView(): View {
        val contentView = LayoutInflater.from(context).inflate(R.layout.fragment_dialog_template,null)
        tvGotoTickets = contentView.findViewById(R.id.tv_dialog)
        tvGotoTickets.text = getString(R.string.vegyel_jegyet_most)
        return contentView
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = context as OnTicketBuyListener
    }
}
