package com.moath.aqua_pulse.presentation.screen.notifications

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moath.aqua_pulse.data.local.preferences.ReminderPreferences
import com.moath.aqua_pulse.data.local.preferences.UserPreferencesManager
import com.moath.aqua_pulse.domain.repository.ReminderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class ReminderPreferencesViewModel @Inject constructor(
    private val userPreferencesManager: UserPreferencesManager,
    private val reminderRepository: ReminderRepository
) : ViewModel() {

    val reminderPreferences = userPreferencesManager.reminderPreferences
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            ReminderPreferences(
                enabled = false,
                startTime = LocalTime.of(8, 0),
                endTime = LocalTime.of(22, 0),
                intervalMinutes = 120
            )
        )

    fun updateReminderEnabled(enabled: Boolean) {
        viewModelScope.launch {
            val currentPreferences = reminderPreferences.value
            val updatedPreferences = currentPreferences.copy(enabled = enabled)

            // Log the state change
            Log.wtf("moathme", "Updating reminder enabled: $enabled")

            userPreferencesManager.updateReminderPreferences(updatedPreferences)
            reminderRepository.scheduleReminders(updatedPreferences)
        }
    }

    fun updateStartTime(time: LocalTime) {
        viewModelScope.launch {
            val currentPreferences = reminderPreferences.value
            val updatedPreferences = currentPreferences.copy(startTime = time)
            userPreferencesManager.updateReminderPreferences(updatedPreferences)
            reminderRepository.scheduleReminders(updatedPreferences)
        }
    }

    fun updateEndTime(time: LocalTime) {
        viewModelScope.launch {
            val currentPreferences = reminderPreferences.value
            val updatedPreferences = currentPreferences.copy(endTime = time)
            userPreferencesManager.updateReminderPreferences(updatedPreferences)
            reminderRepository.scheduleReminders(updatedPreferences)
        }
    }

    fun updateInterval(intervalMinutes: Int) {
        viewModelScope.launch {
            val currentPreferences = reminderPreferences.value
            val updatedPreferences = currentPreferences.copy(intervalMinutes = intervalMinutes)
            userPreferencesManager.updateReminderPreferences(updatedPreferences)
            reminderRepository.scheduleReminders(updatedPreferences)
        }
    }

    fun triggerNotifications() {
        viewModelScope.launch {
            reminderRepository.triggerReminderNotification()
        }
    }
}