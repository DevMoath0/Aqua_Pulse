package com.moath.aqua_pulse.presentation.screen.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.moath.aqua_pulse.core.utils.PermissionUtils
import com.moath.aqua_pulse.presentation.screen.Screen

@Composable
fun SplashScreen(
    navController: NavController,
    viewModel: SplashViewModel = hiltViewModel(),
    requestPermissionLauncher: ActivityResultLauncher<String>
) {
    val context = LocalContext.current
    val isFirstLaunch by viewModel.isFirstLaunch.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    LaunchedEffect(Unit) {
        val activity = context as? ComponentActivity
        activity?.let {
            val splashScreen = it.installSplashScreen()
            splashScreen.setKeepOnScreenCondition { isLoading }
        }

        // Check and request notification permission
        PermissionUtils.checkAndRequestNotificationPermission(context, requestPermissionLauncher)
    }

    LaunchedEffect(isFirstLaunch) {
        isFirstLaunch?.let { firstLaunch ->
            if (firstLaunch) {
                navController.navigate(Screen.OnboardingScreen.route) {
                    popUpTo(Screen.SplashScreen.route) { inclusive = true }
                }
            } else {
                navController.navigate(Screen.HomeScreen.route) {
                    popUpTo(Screen.SplashScreen.route) { inclusive = true }
                }
            }
        }
    }
}
