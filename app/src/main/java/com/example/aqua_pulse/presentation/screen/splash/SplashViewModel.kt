package com.example.aqua_pulse.presentation.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aqua_pulse.data.local.preferences.UserPreferencesManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userPreferencesManager: UserPreferencesManager
) : ViewModel() {

    private val _isLoading = MutableStateFlow(true)
    val isLoading = _isLoading.asStateFlow()

    private val _isFirstLaunch = MutableStateFlow<Boolean?>(null)
    val isFirstLaunch = _isFirstLaunch.asStateFlow()

    init {
        checkFirstLaunch()
    }

    private fun checkFirstLaunch() {
        viewModelScope.launch {
            try {
                val firstLaunch = userPreferencesManager.isFirstLaunch.first()
                _isFirstLaunch.value = firstLaunch
            } catch (e: Exception) {
                // TODO: Handle any potential errors
                _isFirstLaunch.value = true
            } finally {
                _isLoading.value = false
            }
        }
    }
}