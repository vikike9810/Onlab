package com.onlab.gymapp.Entry

import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.nfc.NfcAdapter
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.onlab.gymapp.Profile.User
import com.onlab.gymapp.R
import com.onlab.gymapp.Ticket.DateConverter
import com.onlab.gymapp.Ticket.Ticket
import com.onlab.gymapp.Ticket.Type
import com.onlab.gymapp.TokenClass
import kotlinx.android.synthetic.main.activity_entry.*

class EntryActivity : AppCompatActivity() {

    lateinit var pendingIntent: PendingIntent
    lateinit var intentFilters: Array<IntentFilter>
    lateinit var nfcHelper: NfcHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)
        img_entry.setImageBitmap(User.image)
        tv_entry_name.text = User.Name
        when (Ticket.type){
            Type.HAVI -> {tv_entry_usages.text = "Érvényes:\n"+DateConverter.convert(Ticket.Date.year,Ticket.Date.month,Ticket.Date.date)}

            else -> {tv_entry_usages.text = "Még " + Ticket.DaysLeft + " alkalom"}
        }
        var nfcIntent = Intent(this,javaClass)
        nfcIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        pendingIntent = PendingIntent.getActivity(this,0,nfcIntent,0)
        var ndefIntentFilter = IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED)
        try {
            ndefIntentFilter.addDataType("text/plain")
            intentFilters =  arrayOf(ndefIntentFilter)
        }catch (exception: IntentFilter.MalformedMimeTypeException){
            exception.stackTrace
        }

        nfcHelper = NfcHelper(this,this)
        nfcHelper.pushMessage(null)
    }

    override fun onResume() {
        super.onResume()
        nfcHelper.getAdapter().enableForegroundDispatch(this,pendingIntent,intentFilters,null)
        val token = TokenClass.Token.newBuilder().setToken(Ticket.token).build()
        val message = nfcHelper.createTextMessage(token)
        nfcHelper.pushMessage(message)
    }
}
