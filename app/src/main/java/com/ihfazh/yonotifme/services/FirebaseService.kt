package com.ihfazh.yonotifme.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.ihfazh.yonotifme.MainActivity
import com.ihfazh.yonotifme.R
import com.ihfazh.yonotifme.feeds.domain.models.Item
import com.ihfazh.yonotifme.feeds.usecases.InsertFeedUseCase
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import javax.inject.Inject
import kotlin.reflect.typeOf


@AndroidEntryPoint
class YNMFirebaseMessagingService: FirebaseMessagingService() {
    @Inject
    lateinit var insertFeedUseCase: InsertFeedUseCase

//    private val compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        super.onDestroy()
//        compositeDisposable.dispose()
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d(TAG, "onNewToken: this your new token $token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        // baca ini https://stackoverflow.com/questions/37358462/firebase-onmessagereceived-not-called-when-app-in-background
        super.onMessageReceived(remoteMessage)
        Log.d(TAG, "onMessageReceived: ${remoteMessage.data}")
        Log.d(TAG, "onMessageReceived: ${remoteMessage.rawData}")
        Log.d(TAG, "onNotification: ${remoteMessage.notification}")

        if (remoteMessage.data.isNotEmpty()){
            Log.d(TAG, "type: ${remoteMessage.data["type"]}")
            Log.d(TAG, "data: ${remoteMessage.data["data"]}")
            val type = remoteMessage.data["type"]
            val data = remoteMessage.data["data"]

            Log.d(TAG, "Intuk dataaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa...")
            if (type != null && data != null){
                if (type == "feed"){
                    Log.d(TAG, "Intuk dataaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa...")
                    val item = Json.decodeFromString<Item>(data)
                    sendNotification(item.title, item.description)
                    val disposable = insertFeedUseCase.insert(item).subscribe{
                        Log.d(TAG, "onMessageReceived: hallooooo aku sudah selesai save data.")
                    }
                    disposable.dispose()
                }
            }
            Log.d(TAG, "data type: ${data!!::class.simpleName}")


        }


    }

    private fun sendNotification(title: String, description: String) {
        val channelId = getString(R.string.default_notification_channel_id)
        val channelName = getString(R.string.default_notification_channel_name)
//        val intent = Intent(this, MainActivity::class.java)
////        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
//////        val pendingIntent = PendingIntent.getActivity(this, 0, intent,
//                PendingIntent.FLAG_ONE_SHOT)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(description)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
//                .setContentIntent(pendingIntent)
        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            notificationBuilder.setChannelId(channelId)
            mNotificationManager.createNotificationChannel(channel)
        }
        val notification = notificationBuilder.build()
        mNotificationManager.notify(0, notification)

    }

    companion object {
        const val TAG = "MessagingService"
    }
}