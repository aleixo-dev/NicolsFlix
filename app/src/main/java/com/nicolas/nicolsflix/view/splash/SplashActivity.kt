package com.nicolas.nicolsflix.view.splash

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.work.OneTimeWorkRequestBuilder
import com.nicolas.nicolsflix.MainActivity
import com.nicolas.nicolsflix.R
import com.nicolas.nicolsflix.utils.PushNotificationApp
import java.util.*

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT = 3000L

    @SuppressLint("UnspecifiedImmutableFlag")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Enhancements Send Notifications.
        /*
        val sendNotification: WorkRequest =
            PeriodicWorkRequestBuilder<CallApiWorker>(
                10, TimeUnit.MINUTES,
            ).build()

        WorkManager.getInstance(this).enqueue(sendNotification)

         */

        val alarmManager = this.getSystemService(ALARM_SERVICE) as AlarmManager

        // 21:00 PM
        val calendar = Calendar.getInstance()
        val currentDate = Calendar.getInstance()

        calendar.timeInMillis = System.currentTimeMillis()
        calendar.set(Calendar.HOUR_OF_DAY, 15)
        calendar.set(Calendar.MINUTE, 15)
        calendar.set(Calendar.SECOND, 0)

        // 10:00 AM
        val secondCalendar = Calendar.getInstance()
        secondCalendar.timeInMillis = System.currentTimeMillis()
        secondCalendar.timeInMillis = System.currentTimeMillis()
        secondCalendar.set(Calendar.HOUR_OF_DAY, 15)
        secondCalendar.set(Calendar.MINUTE, 20)
        secondCalendar.set(Calendar.SECOND, 0)

        if (calendar.before(currentDate)) {
            calendar.add(Calendar.DATE, 1)
            Log.d("Hey", "Adicionado para outro dia 21Horas");
        }

        if (secondCalendar.before(currentDate)) {
            secondCalendar.add(Calendar.DATE, 1)
        }

        val intent = Intent(this, PushNotificationApp::class.java)
        val intentPending = PendingIntent.getBroadcast(this, 0, intent, 0)


        if (alarmManager != null) {
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, intentPending
            );
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    intentPending
                )
            }
        }

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP, calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY, intentPending
        )

        Handler().postDelayed(
            {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }, SPLASH_TIME_OUT
        )
    }
}