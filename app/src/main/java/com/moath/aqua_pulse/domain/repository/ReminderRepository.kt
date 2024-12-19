package com.moath.aqua_pulse.domain.repository

import com.moath.aqua_pulse.data.local.preferences.ReminderPreferences

interface ReminderRepository {
    suspend fun scheduleReminders(preferences: ReminderPreferences)
    suspend fun triggerReminderNotification()
}