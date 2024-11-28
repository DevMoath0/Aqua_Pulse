package com.example.aqua_pulse.presentation.screen.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.aqua_pulse.data.local.preferences.UserPreferencesManager
import kotlinx.coroutines.flow.first

@Composable
fun SplashScreen(
    userPreferencesManager: UserPreferencesManager,
    onFirstLaunch: () -> Unit,
    onNotFirstLaunch: () -> Unit
) {
    LaunchedEffect(Unit) {
        val isFirstLaunch = userPreferencesManager.isFirstLaunch.first()
        if (isFirstLaunch) {
            onFirstLaunch()
        } else {
            onNotFirstLaunch()
        }
    }

    // Optional UI while checking launch status
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("AquaPulse Splash", style = MaterialTheme.typography.headlineLarge)
    }
}