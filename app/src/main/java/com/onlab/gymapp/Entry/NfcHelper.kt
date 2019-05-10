package com.onlab.gymapp.Entry

import android.app.Activity
import android.content.Context
import android.nfc.NdefMessage
import android.nfc.NdefRecord
import android.nfc.NfcAdapter
import android.nfc.NfcEvent
import com.onlab.gymapp.TokenClass
import java.security.AccessControlContext

class NfcHelper(context: Context,  var activity: Activity) : NfcAdapter.OnNdefPushCompleteCallback {

    var nfcAdapter: NfcAdapter

    init {
        nfcAdapter = NfcAdapter.getDefaultAdapter(context)
    }

    fun createTextMessage(token: TokenClass.Token): NdefMessage {
        val record = NdefRecord(NdefRecord.TNF_WELL_KNOWN,NdefRecord.RTD_TEXT, ByteArray(0),token.toByteArray())
        return NdefMessage(arrayOf(record))
    }

    fun pushMessage(message: NdefMessage?){
        nfcAdapter.setNdefPushMessage(message,activity)
        nfcAdapter.setOnNdefPushCompleteCallback(this,activity)
    }

    override fun onNdefPushComplete(event: NfcEvent?) {
        pushMessage(null)
    }

    fun getAdapter(): NfcAdapter{
        return nfcAdapter
    }
}