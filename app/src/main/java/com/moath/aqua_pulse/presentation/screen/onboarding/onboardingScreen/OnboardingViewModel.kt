package com.moath.aqua_pulse.presentation.screen.onboarding.onboardingScreen

import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moath.aqua_pulse.data.local.preferences.UserPreferencesManager
import com.moath.aqua_pulse.presentation.screen.onboarding.genderScreen.GenderViewModel
import com.moath.aqua_pulse.presentation.screen.onboarding.weightScreen.WeightViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val userPreferencesManager: UserPreferencesManager
) : ViewModel() {
    private val _currentStep = mutableIntStateOf(1)
    val currentStep get() = _currentStep

    val totalSteps = 4

    fun nextStep() {
        if (_currentStep.intValue < totalSteps) {
            _currentStep.intValue++
        }
    }

    fun completeOnboarding(onComplete: () -> Unit) {
        viewModelScope.launch {
            userPreferencesManager.completeOnboarding()
        }
        onComplete()
    }
}
