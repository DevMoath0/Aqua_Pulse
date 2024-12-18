package com.example.aqua_pulse.domain.repository

import com.example.aqua_pulse.data.local.preferences.ReminderPreferences

interface ReminderRepository {
    suspend fun scheduleReminders(preferences: ReminderPreferences)
    suspend fun triggerReminderNotification()
}