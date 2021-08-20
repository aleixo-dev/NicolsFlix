package com.nicolas.nicolsflix.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class PushNotificationApp : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val mContext = context
        val showNotificationBuild: BuildPushNotification = BuildPushNotification(mContext)
        showNotificationBuild.builder(
            0,
            "Heein, Saiu novos filmes.",
            "Confira agora os útimos lançamentos"
        )
    }
}