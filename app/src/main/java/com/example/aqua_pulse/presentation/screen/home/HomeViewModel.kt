package com.example.aqua_pulse.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.aqua_pulse.domain.model.DailyWaterIntake
import com.example.aqua_pulse.domain.usecase.AddWaterIntakeUseCase
import com.example.aqua_pulse.domain.usecase.GetWeeklyWaterIntakeUseCase
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
): ViewModel() {

    private val _dailyData = MutableStateFlow<List<DailyWaterIntake>>(emptyList())
    val dailyData: StateFlow<List<DailyWaterIntake>> = _dailyData.asStateFlow()

    init {
        viewModelScope.launch {
            refreshWeeklyData()
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