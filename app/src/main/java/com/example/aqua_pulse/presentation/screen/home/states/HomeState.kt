package com.example.aqua_pulse.presentation.screen.home.states

data class HomeState(
    val todayWaterAmount: Int = 0,
    val dailyGoal: Int = 2000, // Default goal in ml
    val isLoading: Boolean = false
)
