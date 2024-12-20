package com.moath.aqua_pulse.presentation.screen.notifications.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import java.time.LocalTime

@Composable
fun TimePickerDialog(
    initialTime: LocalTime,
    onDismiss: () -> Unit,
    onTimeSelected: (LocalTime) -> Unit
) {
    var selectedHour by remember { mutableStateOf(initialTime.hour) }
    var selectedMinute by remember { mutableStateOf(initialTime.minute) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Select Time") },
        text = {
            // Implement your custom time selection UI
            // This could be a custom view or a library time picker
            Row {
                // Hour selection
                NumberPicker(
                    value = selectedHour,
                    onValueChange = { selectedHour = it },
                    range = 0..23
                )

                // Minute selection
                NumberPicker(
                    value = selectedMinute,
                    onValueChange = { selectedMinute = it },
                    range = 0..59
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onTimeSelected(LocalTime.of(selectedHour, selectedMinute))
                    onDismiss()
                }
            ) {
                Text("OK")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

// Simple number picker (you might want to use a more sophisticated implementation)
@Composable
fun NumberPicker(
    value: Int,
    onValueChange: (Int) -> Unit,
    range: IntRange
) {
    // Implement a simple number picker
    // This is a placeholder and you'd want a more robust implementation
    Row {
        Button(onClick = {
            if (value > range.first) onValueChange(value - 1)
        }) {
            Text("-")
        }
        Text(value.toString())
        Button(onClick = {
            if (value < range.last) onValueChange(value + 1)
        }) {
            Text("+")
        }
    }
}