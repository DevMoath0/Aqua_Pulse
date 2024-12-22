package com.moath.aqua_pulse.core.utils

import com.moath.aqua_pulse.data.repository.ReminderRepositoryImpl
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.moath.aqua_pulse.data.local.preferences.UserPreferencesManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class BootReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == Intent.ACTION_BOOT_COMPLETED) {
            // Reschedule reminders on boot
            CoroutineScope(Dispatchers.Main).launch {
                val userPreferencesManager = UserPreferencesManager(context)
                val reminderRepository = ReminderRepositoryImpl(context, userPreferencesManager)

                val preferences = userPreferencesManager.reminderPreferences.first()
                reminderRepository.scheduleReminders(preferences)
            }
        }
    }
}