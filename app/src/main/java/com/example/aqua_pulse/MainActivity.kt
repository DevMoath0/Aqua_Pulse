package com.example.aqua_pulse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aqua_pulse.presentation.screen.home.HomeScreen
import com.example.aqua_pulse.presentation.screen.Screen
import com.example.aqua_pulse.core.theme.Aqua_PulseTheme
import com.example.aqua_pulse.presentation.screen.home.WaterStatisticsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            Aqua_PulseTheme {
                NavGraph()
            }
        }
    }
}

@Composable
fun NavGraph(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.HomeView.route){
        composable(route = Screen.HomeView.route){
            //Todo: Implement the UI in here.
        }
    }
}
