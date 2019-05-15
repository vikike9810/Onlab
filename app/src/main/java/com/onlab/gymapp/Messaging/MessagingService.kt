package com.onlab.gymapp.Messaging


import android.content.Intent
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.onlab.gymapp.Entry.EntryActivity

class MessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        if (remoteMessage!!.data.size > 0) {
            val result = remoteMessage.data["result"]
            val body = remoteMessage.data["body"]
            val intent = Intent(this,EntryActivity::class.java)
            intent.putExtra("message",arrayOf(result!!,body!!))
            startActivity(intent)
        }
    }
}
