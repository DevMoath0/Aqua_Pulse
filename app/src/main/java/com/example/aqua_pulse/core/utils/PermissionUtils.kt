package com.example.aqua_pulse.core.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.core.content.ContextCompat

object PermissionUtils {

    fun checkAndRequestNotificationPermission(
        context: Context,
        requestPermissionLauncher: ActivityResultLauncher<String>
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // Android 13+
            when {
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) == PackageManager.PERMISSION_GRANTED -> {
                    Log.d("PermissionUtils", "Notification permission already granted.")
                }
                (context as? ComponentActivity)?.shouldShowRequestPermissionRationale(
                    Manifest.permission.POST_NOTIFICATIONS
                ) == true -> {
                    Log.d("PermissionUtils", "Showing rationale for notification permission.")
                    // You can optionally show a dialog here if needed.
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
                else -> {
                    Log.d("PermissionUtils", "Requesting notification permission.")
                    requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                }
            }
        } else {
            Log.d("PermissionUtils", "Notification permission not required for this Android version.")
        }
    }
}
