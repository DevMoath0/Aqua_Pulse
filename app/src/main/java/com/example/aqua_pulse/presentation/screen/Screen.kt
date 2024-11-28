package com.example.aqua_pulse.presentation.screen

sealed class Screen(val route: String){
    data object WaterStatView: Screen("waterStatView")


    data object SplashScreen : Screen("splash_screen")
    data object HomeScreen : Screen("home_screen")
    data object OnboardingScreen : Screen("onboarding_screen")
    data object ReminderPreferencesScreen : Screen("reminder_preferences_screen")
}