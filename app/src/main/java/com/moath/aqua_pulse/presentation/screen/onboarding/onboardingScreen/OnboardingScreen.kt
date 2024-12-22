package com.moath.aqua_pulse.presentation.screen.onboarding.onboardingScreen

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.moath.aqua_pulse.presentation.screen.Screen
import com.moath.aqua_pulse.presentation.screen.onboarding.dailyGoalScreen.DailyGoalScreen
import com.moath.aqua_pulse.presentation.screen.onboarding.dailyGoalScreen.DailyGoalViewModel
import com.moath.aqua_pulse.presentation.screen.onboarding.genderScreen.GenderScreen
import com.moath.aqua_pulse.presentation.screen.onboarding.weightScreen.WeightScreen
import com.moath.aqua_pulse.presentation.screen.onboarding.WelcomeScreen
import com.moath.aqua_pulse.presentation.screen.onboarding.components.Stepper
import com.moath.aqua_pulse.presentation.screen.onboarding.genderScreen.GenderViewModel
import com.moath.aqua_pulse.presentation.screen.onboarding.weightScreen.WeightViewModel

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
            2 -> {
                val genderViewModel: GenderViewModel = hiltViewModel()
                GenderScreen(
                    onNext = { viewModel.nextStep() },
                    viewModel = genderViewModel
                )
            }
            3 -> {
                val weightViewModel: WeightViewModel = hiltViewModel()
                WeightScreen(
                    onNext = { viewModel.nextStep() },
                    viewModel = weightViewModel
                )
            }
            4 -> {
                val genderViewModel: GenderViewModel = hiltViewModel()
                val weightViewModel: WeightViewModel = hiltViewModel()
                val dailyGoalViewModel: DailyGoalViewModel = hiltViewModel()
                DailyGoalScreen(
                    onComplete = {
                        viewModel.completeOnboarding {
                            navController.navigate(Screen.HomeScreen.route) {
                                popUpTo(Screen.OnboardingScreen.route) { inclusive = true }
                            }
                        }
                    },
                    genderViewModel = genderViewModel,
                    weightViewModel = weightViewModel,
                    dailyGoalViewModel = dailyGoalViewModel
                )
            }
        }
    }
}


