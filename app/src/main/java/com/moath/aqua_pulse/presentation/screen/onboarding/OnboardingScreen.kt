package com.moath.aqua_pulse.presentation.screen.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.moath.aqua_pulse.presentation.screen.Screen
import com.moath.aqua_pulse.presentation.screen.onboarding.components.Stepper

@Composable
fun OnboardingScreen(
    navController: NavController,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val currentStep by viewModel.currentStep
    val totalSteps = viewModel.totalSteps

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Stepper Component
        Stepper(currentStep = currentStep, totalSteps = totalSteps)

        Spacer(modifier = Modifier.height(16.dp))

        // Step-Specific Content
        when (currentStep) {
            1 -> WelcomeScreen(onNext = { viewModel.nextStep() })
            2 -> GenderScreen(onNext = { viewModel.updateGender(it) })
            3 -> BirthDateScreen(onNext = { viewModel.updateBirthDate(it) })
            4 -> WeightScreen(onNext = { viewModel.updateWeight(it) })
            5 -> DailyGoalScreen(onComplete = {
                viewModel.updateDailyGoal(it)
                viewModel.completeOnboarding {
                    navController.navigate(Screen.HomeScreen.route) {
                        popUpTo(Screen.OnboardingScreen.route) { inclusive = true }
                    }
                }
            })
        }
    }
}
