package com.ihfazh.yonotifme.services

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class YNMFirebaseMessagingService: FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        Log.d(TAG, "onNewToken: this your new token $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "onMessageReceived: ${remoteMessage.data}")
        Log.d(TAG, "onMessageReceived: ${remoteMessage.rawData}")
    }

    companion object {
        const val TAG = "MessagingService"
    }
}