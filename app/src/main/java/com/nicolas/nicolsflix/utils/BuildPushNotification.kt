package com.nicolas.nicolsflix.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import com.nicolas.nicolsflix.MainActivity
import com.nicolas.nicolsflix.R

class BuildPushNotification(private val context: Context) {

    /* When click notification, open new activity. */
    val intent = Intent(context, MainActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    /* Use sound default notify smartphone. */
    val uri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

    private val pendingIntent: PendingIntent =
        PendingIntent.getActivities(context, 0, arrayOf(intent), 0)

    private val notificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun builder(notificationId: Int, title: String, message: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                Constants.ID_CHANNEL,
                Constants.NOTIFICATION_HELLO_WORLD,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        val createNotification = NotificationCompat.Builder(context, Constants.ID_CHANNEL)
            .setSmallIcon(R.drawable.ic_background_notification)
            .setContentTitle(title)
            .setContentText(message)
            .setSound(uri)
            .setStyle(
                NotificationCompat
                    .BigTextStyle()
                    .bigText("Confira os útimos lançamentos de filmes!")
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)

        notificationManager.notify(notificationId, createNotification.build())
    }
}