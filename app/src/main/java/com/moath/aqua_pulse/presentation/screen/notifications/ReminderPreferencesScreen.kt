package com.moath.aqua_pulse.presentation.screen.notifications

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.moath.aqua_pulse.presentation.screen.notifications.composables.TimePickerDialog
import java.time.format.DateTimeFormatter

@Composable
fun ReminderPreferencesScreen(
    viewModel: ReminderPreferencesViewModel = hiltViewModel()
) {
    var showStartTimePicker by remember { mutableStateOf(false) }
    var showEndTimePicker by remember { mutableStateOf(false) }
    val reminderPreferences by viewModel.reminderPreferences.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        // Reminder Toggle
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Enable Reminders")
            Switch(
                checked = reminderPreferences.enabled,
                onCheckedChange = { viewModel.updateReminderEnabled(it) }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Start Time Input
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Start Time")
            Text(
                text = reminderPreferences.startTime.format(DateTimeFormatter.ofPattern("HH:mm")),
                modifier = Modifier
                    .clickable { showStartTimePicker = true }
                    .padding(8.dp)
            )
        }

        // End Time Input
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("End Time")
            Text(
                text = reminderPreferences.endTime.format(DateTimeFormatter.ofPattern("HH:mm")),
                modifier = Modifier
                    .clickable { showEndTimePicker = true }
                    .padding(8.dp)
            )
        }

        // Interval Selector
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Reminder Interval")
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = {
                        if (reminderPreferences.intervalMinutes > 1) {
                            viewModel.updateInterval(reminderPreferences.intervalMinutes - 1)
                        }
                    }
                ) {
                    Icon(Icons.Default.KeyboardArrowDown, contentDescription = "Decrease interval")
                }
                Text("${reminderPreferences.intervalMinutes} min")
                IconButton(
                    onClick = {
                        if (reminderPreferences.intervalMinutes < 240) {
                            viewModel.updateInterval(reminderPreferences.intervalMinutes + 1)
                        }
                    }
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Increase interval")
                }
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = {
                        viewModel.triggerNotifications()
                    }
                ) {
                    Icon(Icons.Default.MailOutline, contentDescription = "Trigger Notification")
                }
            }
        }

        // Time Picker Dialogs
        if (showStartTimePicker) {
            TimePickerDialog(
                initialTime = reminderPreferences.startTime,
                onDismiss = { showStartTimePicker = false },
                onTimeSelected = {
                    viewModel.updateStartTime(it)
                    showStartTimePicker = false
                }
            )
        }

        if (showEndTimePicker) {
            TimePickerDialog(
                initialTime = reminderPreferences.endTime,
                onDismiss = { showEndTimePicker = false },
                onTimeSelected = {
                    viewModel.updateEndTime(it)
                    showEndTimePicker = false
                }
            )
        }
    }
}