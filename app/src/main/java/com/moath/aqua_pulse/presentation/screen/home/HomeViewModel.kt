package com.moath.aqua_pulse.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.crashlytics.crashlytics
import com.moath.aqua_pulse.domain.model.DailyWaterIntake
import com.moath.aqua_pulse.domain.repository.DailyGoalRepository
import com.moath.aqua_pulse.domain.repository.RemoteConfigRepository
import com.moath.aqua_pulse.domain.usecase.AddWaterIntakeUseCase
import com.moath.aqua_pulse.domain.usecase.GetWeeklyWaterIntakeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getWeeklyWaterIntakeUseCase: GetWeeklyWaterIntakeUseCase,
    private val addWaterIntakeUseCase: AddWaterIntakeUseCase,
    private val remoteConfigRepository: RemoteConfigRepository,
    dailyGoalRepository: DailyGoalRepository
) : ViewModel() {

    private val _dailyData = MutableStateFlow<List<DailyWaterIntake>>(emptyList())
    val dailyData: StateFlow<List<DailyWaterIntake>> = _dailyData.asStateFlow()

    val dailyGoal: Flow<Float> = dailyGoalRepository.getDailyGoal()

    private val _buildNumber = MutableStateFlow(1.0)
    val buildNumber: StateFlow<Double> = _buildNumber.asStateFlow()

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
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
            try {
                addWaterIntakeUseCase(amount)
                refreshWeeklyData()
            } catch (e: Exception) {
                Firebase.crashlytics.recordException(e)
            }
        }
    }
}