package com.onlab.gymapp.DialogFragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.onlab.gymapp.MainActivity

import com.onlab.gymapp.R

class TicketUsedDialogFragment : DialogFragment() {

    interface MessageProvider{
        fun getMessage(): String
    }

    private lateinit var provider : MessageProvider
    private lateinit var tvTicketUsed: TextView
    companion object {
        var TAG: String = "TicketUsedDialogFragment"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setView(getContentView())
            .setPositiveButton(R.string.rendben) { dialog, which ->
                val intent = Intent(context,MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
            .create()
    }

    private fun getContentView(): View {
        var contentView = LayoutInflater.from(context).inflate(R.layout.fragment_dialog_template,null)
        tvTicketUsed = contentView.findViewById(R.id.tv_dialog)
        tvTicketUsed.text = provider.getMessage()
        return contentView
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        provider = context as MessageProvider
    }
}
