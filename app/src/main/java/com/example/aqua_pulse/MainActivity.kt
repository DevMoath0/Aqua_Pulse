package com.example.aqua_pulse

import com.example.aqua_pulse.presentation.screen.NavGraph
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import com.example.aqua_pulse.core.theme.Aqua_PulseTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            android.util.Log.d("MainActivity", "Notification permission granted.")
        } else {
            android.util.Log.e("MainActivity", "Notification permission denied.")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Aqua_PulseTheme {
                NavGraph(
                    requestPermissionLauncher = requestPermissionLauncher // Pass launcher to SplashScreen
                )
            }
        }
    }
}
