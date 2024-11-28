package com.example.aqua_pulse.presentation.screen.settings


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aqua_pulse.data.local.preferences.ReminderPreferences
import com.example.aqua_pulse.data.local.preferences.UserPreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class ReminderPreferencesViewModel @Inject constructor(
    private val userPreferencesManager: UserPreferencesManager
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
            val current = reminderPreferences.value
            userPreferencesManager.updateReminderPreferences(
                current.copy(enabled = enabled)
            )
        }
    }

    fun updateStartTime(time: LocalTime) {
        viewModelScope.launch {
            val current = reminderPreferences.value
            userPreferencesManager.updateReminderPreferences(
                current.copy(startTime = time)
            )
        }
    }

    fun updateEndTime(time: LocalTime) {
        viewModelScope.launch {
            val current = reminderPreferences.value
            userPreferencesManager.updateReminderPreferences(
                current.copy(endTime = time)
            )
        }
    }

    fun updateInterval(intervalMinutes: Int) {
        viewModelScope.launch {
            val current = reminderPreferences.value
            userPreferencesManager.updateReminderPreferences(
                current.copy(intervalMinutes = intervalMinutes)
            )
        }
    }
}