package com.moath.aqua_pulse.presentation.screen.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moath.aqua_pulse.data.local.preferences.UserPreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val userPreferencesManager: UserPreferencesManager
) : ViewModel() {

    fun completeOnboarding(onNavigateToHome: () -> Unit) {
        viewModelScope.launch {
            userPreferencesManager.completeOnboarding()
            onNavigateToHome()
        }
    }
}
