package com.moath.aqua_pulse.presentation.screen.onboarding

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
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

    // Current step state
    private val _currentStep = mutableIntStateOf(1)
    val currentStep: MutableState<Int> = _currentStep

    // Total number of steps
    val totalSteps = 5

    // User inputs
    private val _gender = mutableStateOf<String?>(null)
    val gender: MutableState<String?> = _gender

    private val _birthDate = mutableStateOf<String?>(null)
    val birthDate: MutableState<String?> = _birthDate

    private val _weight = mutableStateOf<Int?>(null)
    val weight: MutableState<Int?> = _weight

    private val _dailyGoal = mutableStateOf<Int?>(null)
    val dailyGoal: MutableState<Int?> = _dailyGoal

    // Navigate to the next step
    fun nextStep() {
        if (_currentStep.intValue < totalSteps) {
            _currentStep.intValue++
        }
    }

    // Navigate to the previous step
    fun previousStep() {
        if (_currentStep.intValue > 1) {
            _currentStep.intValue--
        }
    }

    // Update user inputs
    fun updateGender(value: String) {
        _gender.value = value
        nextStep()
    }

    fun updateBirthDate(value: String) {
        _birthDate.value = value
        nextStep()
    }

    fun updateWeight(value: Int) {
        _weight.value = value
        nextStep()
    }

    fun updateDailyGoal(value: Int) {
        _dailyGoal.value = value
        nextStep()
    }

    // Complete onboarding and save preferences
    fun completeOnboarding(onNavigateToHome: () -> Unit) {
        viewModelScope.launch {
            userPreferencesManager.completeOnboarding()
            onNavigateToHome()
        }
    }

    fun setStep(step: Int) {
        if (step in 1..totalSteps) {
            _currentStep.intValue = step
        }
    }

}
