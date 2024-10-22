package com.example.aqua_pulse.presentation.screen

sealed class Screen(val route: String){
    data object HomeView: Screen("homeView")
}