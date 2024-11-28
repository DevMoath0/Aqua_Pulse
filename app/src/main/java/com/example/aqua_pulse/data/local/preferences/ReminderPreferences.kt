package com.example.aqua_pulse.data.local.preferences

import java.time.LocalTime

data class ReminderPreferences(
    val enabled: Boolean,
    val startTime: LocalTime,
    val endTime: LocalTime,
    val intervalMinutes: Int
)