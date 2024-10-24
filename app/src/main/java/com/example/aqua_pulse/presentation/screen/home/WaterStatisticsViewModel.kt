package com.example.aqua_pulse.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aqua_pulse.domain.model.DailyWaterIntake
import com.example.aqua_pulse.domain.usecase.AddWaterIntakeUseCase
import com.example.aqua_pulse.domain.usecase.GetWeeklyWaterIntakeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel
class WaterStatisticsViewModel @Inject constructor(
    private val getWeeklyWaterIntakeUseCase: GetWeeklyWaterIntakeUseCase,
    private val addWaterIntakeUseCase: AddWaterIntakeUseCase
): ViewModel() {
    private val _weeklyData = MutableStateFlow<List<DailyWaterIntake>>(emptyList())
    val weeklyData: StateFlow<List<DailyWaterIntake>> = _weeklyData.asStateFlow()

    init {
        viewModelScope.launch {
            refreshWeeklyData()
        }
    }

    private suspend fun refreshWeeklyData() {
        _weeklyData.value = getWeeklyWaterIntakeUseCase()
    }

    fun addWaterIntake(amount: Int) {
        viewModelScope.launch {
            addWaterIntakeUseCase(amount)
            refreshWeeklyData()
        }
    }
}