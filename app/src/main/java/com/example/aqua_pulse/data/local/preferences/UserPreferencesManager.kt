package com.example.aqua_pulse.data.local.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalTime
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_preferences")

@Singleton
class UserPreferencesManager @Inject constructor(
    @ApplicationContext  private val context: Context
) {
    companion object {
        private val IS_FIRST_LAUNCH = booleanPreferencesKey("is_first_launch")
        private val REMINDER_ENABLED = booleanPreferencesKey("reminder_enabled")
        private val REMINDER_START_TIME = stringPreferencesKey("reminder_start_time")
        private val REMINDER_END_TIME = stringPreferencesKey("reminder_end_time")
        private val REMINDER_INTERVAL = intPreferencesKey("reminder_interval")
    }

    val isFirstLaunch: Flow<Boolean> = context.dataStore.data
        .map { preferences ->
            preferences[IS_FIRST_LAUNCH] ?: true
        }

    val reminderPreferences: Flow<ReminderPreferences> = context.dataStore.data
        .map { preferences ->
            ReminderPreferences(
                enabled = preferences[REMINDER_ENABLED] ?: false,
                startTime = LocalTime.parse(preferences[REMINDER_START_TIME] ?: "08:00"),
                endTime = LocalTime.parse(preferences[REMINDER_END_TIME] ?: "22:00"),
                intervalMinutes = preferences[REMINDER_INTERVAL] ?: 120
            )
        }

    suspend fun completeOnboarding() {
        context.dataStore.edit { preferences ->
            preferences[IS_FIRST_LAUNCH] = false
        }
    }

    suspend fun updateReminderPreferences(preferences: ReminderPreferences) {
        context.dataStore.edit { prefs ->
            prefs[REMINDER_ENABLED] = preferences.enabled
            prefs[REMINDER_START_TIME] = preferences.startTime.toString()
            prefs[REMINDER_END_TIME] = preferences.endTime.toString()
            prefs[REMINDER_INTERVAL] = preferences.intervalMinutes
        }
    }
}