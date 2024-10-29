package com.example.aqua_pulse.presentation.screen

sealed class Screen(val route: String){
    data object HomeScreen: Screen("homeScreen")
    data object WaterStatView: Screen("waterStatView")
}