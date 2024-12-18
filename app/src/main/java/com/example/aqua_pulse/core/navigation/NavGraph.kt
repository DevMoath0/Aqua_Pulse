import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.aqua_pulse.presentation.screen.Screen
import com.example.aqua_pulse.presentation.screen.home.HomeScreen
import com.example.aqua_pulse.presentation.screen.home.HomeViewModel
import com.example.aqua_pulse.presentation.screen.onboarding.OnboardingScreen
import com.example.aqua_pulse.presentation.screen.onboarding.OnboardingViewModel
import com.example.aqua_pulse.presentation.screen.notifications.ReminderPreferencesScreen
import com.example.aqua_pulse.presentation.screen.notifications.ReminderPreferencesViewModel
import com.example.aqua_pulse.presentation.screen.splash.SplashScreen
import com.example.aqua_pulse.presentation.screen.splash.SplashViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.SplashScreen.route
    ) {
        composable(route = Screen.SplashScreen.route) {
            val splashViewModel: SplashViewModel = hiltViewModel()
            SplashScreen(
                navController = navController,
                viewModel = splashViewModel
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