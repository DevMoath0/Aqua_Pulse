package com.moath.aqua_pulse.presentation.screen.onboarding.genderScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moath.aqua_pulse.data.local.preferences.UserPreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenderViewModel @Inject constructor(
    private val userPreferencesManager: UserPreferencesManager
) : ViewModel() {

    val gender: Flow<String?> = userPreferencesManager.gender

    fun saveGender(gender: String) {
        viewModelScope.launch {
            userPreferencesManager.saveGender(gender)
        }
    }
}
