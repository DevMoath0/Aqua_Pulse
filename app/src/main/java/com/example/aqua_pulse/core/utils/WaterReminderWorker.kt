package com.example.aqua_pulse.core.utils

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.*
import com.example.aqua_pulse.R
import java.time.LocalTime
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class WaterReminderWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val enabled = inputData.getBoolean("enabled", false)
        val isSettingsUpdate = inputData.getBoolean("isSettingsUpdate", false)
        val startTimeStr = inputData.getString("startTime")
        val endTimeStr = inputData.getString("endTime")
        val intervalMinutes = inputData.getInt("intervalMinutes", 120)

        if (!enabled) {
            logDebug("Reminders are disabled. Exiting worker.")
            return Result.success()
        }

        if (isSettingsUpdate) {
            logDebug("Settings update detected. No notification triggered.")
            scheduleNextReminder(intervalMinutes) // Reschedule with the updated settings
            return Result.success()
        }

        val startTime = startTimeStr?.let { LocalTime.parse(it) } ?: LocalTime.of(8, 0)
        val endTime = endTimeStr?.let { LocalTime.parse(it) } ?: LocalTime.of(22, 0)
        val currentTime = LocalTime.now()

        if (currentTime.isBefore(startTime) || currentTime.isAfter(endTime)) {
            logDebug("Current time ($currentTime) is outside the time range ($startTime to $endTime).")
            return Result.success()
        }

        logDebug("Within time range. Sending notification.")
        showWaterReminderNotification()
        scheduleNextReminder(intervalMinutes)
        return Result.success()
    }

    private fun scheduleNextReminder(intervalMinutes: Int) {
        val workManager = WorkManager.getInstance(applicationContext)

        // Prepare data for the next reminder
        val nextReminderData = Data.Builder()
            .putBoolean("enabled", true) // Keep reminders enabled
            .putString("startTime", inputData.getString("startTime"))
            .putString("endTime", inputData.getString("endTime"))
            .putInt("intervalMinutes", intervalMinutes)
            .build()

        // Schedule the next reminder with delay
        val nextWorkRequest = OneTimeWorkRequestBuilder<WaterReminderWorker>()
            .setInputData(nextReminderData)
            .setInitialDelay(intervalMinutes.toLong(), TimeUnit.MINUTES)
            .build()

        workManager.enqueueUniqueWork(
            WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            nextWorkRequest
        )

        logDebug("Scheduled the next reminder in $intervalMinutes minutes.")
    }

    private fun showWaterReminderNotification() {
        val channelId = "aqua_pulse_channel"
        createNotificationChannel()

        val notification = NotificationCompat.Builder(applicationContext, channelId)
            .setContentTitle("Stay Hydrated!")
            .setContentText("Time to drink some water and stay refreshed.")
            .setSmallIcon(R.drawable.water_drop)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Don't forget to drink water. Staying hydrated helps maintain your health and energy levels.")
            )
            .build()

        with(NotificationManagerCompat.from(applicationContext)) {
            if (ActivityCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                logError("Notification permission not granted.")
                return
            }
            notify(Random.nextInt(), notification)
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Aqua Pulse Channel"
            val descriptionText = "Channel for water intake reminders"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("aqua_pulse_channel", name, importance).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun logDebug(message: String) {
        android.util.Log.d("WaterReminderWorker", message)
    }

    private fun logError(message: String) {
        android.util.Log.e("WaterReminderWorker", message)
    }

    companion object {
        const val WORK_NAME = "water_reminder_work"
    }
}
