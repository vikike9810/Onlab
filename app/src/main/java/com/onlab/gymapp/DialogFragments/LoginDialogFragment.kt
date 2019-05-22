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

class LoginDialogFragment : DialogFragment() {

    interface OnLoginListener{
        fun Login()
    }

    private lateinit var listener : OnLoginListener
    private lateinit var tvGotoLogin: TextView
    companion object {
        var TAG: String = "LoginDialogFragment"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setView(getContentView())
            .setPositiveButton(R.string.bejelentkez_s) { dialog, which ->
                listener.Login()
            }
            .setNegativeButton(R.string.cancel,null)
            .create()
    }

    private fun getContentView(): View {
        val contentView = LayoutInflater.from(context).inflate(R.layout.fragment_dialog_template,null)
        tvGotoLogin = contentView.findViewById(R.id.tv_dialog)
        tvGotoLogin.text = getString(R.string.a_folytat_shoz_be_kell_jelentkezni)
        return contentView
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = context as OnLoginListener
    }
}
