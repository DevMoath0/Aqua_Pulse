package com.moath.aqua_pulse.presentation.screen

import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.moath.aqua_pulse.presentation.screen.home.HomeScreen
import com.moath.aqua_pulse.presentation.screen.home.HomeViewModel
import com.moath.aqua_pulse.presentation.screen.onboarding.onboardingScreen.OnboardingScreen
import com.moath.aqua_pulse.presentation.screen.onboarding.onboardingScreen.OnboardingViewModel
import com.moath.aqua_pulse.presentation.screen.notifications.ReminderPreferencesScreen
import com.moath.aqua_pulse.presentation.screen.notifications.ReminderPreferencesViewModel
import com.moath.aqua_pulse.presentation.screen.splash.SplashScreen
import com.moath.aqua_pulse.presentation.screen.splash.SplashViewModel

@Composable
fun NavGraph(
    requestPermissionLauncher: ActivityResultLauncher<String> // Pass the launcher here
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(route = Screen.SplashScreen.route) {
            val splashViewModel: SplashViewModel = hiltViewModel()
            SplashScreen(
                navController = navController,
                viewModel = splashViewModel,
                requestPermissionLauncher = requestPermissionLauncher // Pass launcher to SplashScreen
            )
        }

        composable(route = Screen.OnboardingScreen.route) {
            val onboardingViewModel: OnboardingViewModel = hiltViewModel()
            OnboardingScreen(
                navController = navController,
                viewModel = onboardingViewModel
            )
        }

        composable(route = Screen.HomeScreen.route) {
            val homeViewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                navController = navController,
                viewModel = homeViewModel
            )
        }

        composable(route = Screen.ReminderPreferencesScreen.route) {
            val reminderPreferencesViewModel: ReminderPreferencesViewModel = hiltViewModel()
            ReminderPreferencesScreen(viewModel = reminderPreferencesViewModel)
        }
    }
}
