package com.mhirrr.fcm

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationManagerCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage


class FirebaseService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val title = remoteMessage.data["title"]
        val message = remoteMessage.data["message"]
//        val notificationChannel = NotificationChannel(
//            CHANNEL_ID,
//            "Heads up notification",
//            NotificationManager.IMPORTANCE_HIGH
//        )

        val intent = Intent(applicationContext, MainActivity::class.java)
        val pendingIntent =
            PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_MUTABLE)

//        getSystemService(NotificationManager::class.java).createNotificationChannel(
//            notificationChannel
//        )
        val notification = Notification.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)

        NotificationManagerCompat.from(this).notify(notificationId, notification.build())
        super.onMessageReceived(remoteMessage)

    }

    companion object {
        private const val CHANNEL_ID = "com.mhirrr.fcmpoc"
        private var notificationId = 1;

        var sharedPref: SharedPreferences? = null
        var token: String?
            get() = sharedPref?.getString("token", "")
            set(value) {
                sharedPref?.edit()?.putString("token", value)?.apply()
            }
    }

}