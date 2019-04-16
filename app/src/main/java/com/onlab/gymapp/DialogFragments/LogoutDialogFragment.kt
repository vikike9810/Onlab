package com.onlab.gymapp.DialogFragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.onlab.gymapp.Profile.User

import com.onlab.gymapp.R

class LogoutDialogFragment : DialogFragment() {

    interface LogoutListener{
        fun logout()
    }

    private lateinit var listener : LogoutListener
    private lateinit var tvOnLogout: TextView
    companion object {
        var TAG: String = "LogoutDialogFragment"
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setView(getContentView())
            .setPositiveButton(R.string.ok) { dialog, which ->
                listener.logout()
            }
            .setNegativeButton(R.string.cancel,null)
            .create()
    }

    private fun getContentView(): View {
        var contentView = LayoutInflater.from(context).inflate(R.layout.fragment_logout_dialog,null)
        tvOnLogout = contentView.findViewById(R.id.tv_on_logout)
        tvOnLogout.text = getString(R.string.onlogout, User.Name)
        return contentView
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = context as LogoutListener
    }
}
