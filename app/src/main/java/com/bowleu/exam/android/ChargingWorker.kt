package com.bowleu.exam.android

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.Worker
import androidx.work.WorkerParameters

class ChargingWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    private var notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val channelId = "charging_channel"

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Charging notifications",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }
    }

    override fun doWork(): Result {
        showNotification()
        return Result.success()
    }

    private fun showNotification() {
        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setContentTitle("Устройство на зарядке")
            .setContentText("Устройство на зарядке")
            .setAutoCancel(true)
            .build()

        notificationManager.notify(1, notification)
    }
}