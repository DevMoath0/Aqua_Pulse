package com.example.aqua_pulse.presentation.screen.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.aqua_pulse.presentation.screen.Screen

@Composable
fun OnboardingScreen(
    navController: NavController,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    // TODO: Implement multi-step onboarding UI
    Column (
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        // Onboarding steps (hydration goals, notifications setup, etc.)
        Button(
            onClick = {
                viewModel.completeOnboarding {
                    navController.navigate(Screen.HomeScreen.route) {
                        popUpTo(Screen.OnboardingScreen.route) { inclusive = true }
                    }
                }
            }
        ) {
            Text("Complete Onboarding")
        }
    }
}