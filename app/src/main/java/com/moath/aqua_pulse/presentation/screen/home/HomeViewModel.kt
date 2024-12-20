package com.moath.aqua_pulse.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import com.moath.aqua_pulse.domain.model.DailyWaterIntake
import com.moath.aqua_pulse.domain.repository.RemoteConfigRepository
import com.moath.aqua_pulse.domain.usecase.AddWaterIntakeUseCase
import com.moath.aqua_pulse.domain.usecase.GetWeeklyWaterIntakeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getWeeklyWaterIntakeUseCase: GetWeeklyWaterIntakeUseCase,
    private val addWaterIntakeUseCase: AddWaterIntakeUseCase,
    private val remoteConfigRepository: RemoteConfigRepository
): ViewModel() {

    private val _dailyData = MutableStateFlow<List<DailyWaterIntake>>(emptyList())
    val dailyData: StateFlow<List<DailyWaterIntake>> = _dailyData.asStateFlow()

    //private val _dailyGoal = MutableStateFlow(2000)
    //val dailyGoal: StateFlow<Int> = _dailyGoal.asStateFlow()

    //private val _defaultAmount = MutableStateFlow(250)
    //val defaultAmount: StateFlow<Int> = _defaultAmount.asStateFlow()

    private val _buildNumber = MutableStateFlow(0.1)
    val buildNumber: StateFlow<Double> = _buildNumber.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                remoteConfigRepository.fetchAndActivate()
                _buildNumber.value = remoteConfigRepository.getBuildNumber()
                refreshWeeklyData()
            } catch (e: Exception) {
                Firebase.crashlytics.recordException(e)
            }
        }
    }

    private suspend fun refreshWeeklyData() {
        _dailyData.value = getWeeklyWaterIntakeUseCase()
    }

    fun addWaterIntake(amount: Int) {
        viewModelScope.launch {
            addWaterIntakeUseCase(amount)
            refreshWeeklyData()
        }
    }
}