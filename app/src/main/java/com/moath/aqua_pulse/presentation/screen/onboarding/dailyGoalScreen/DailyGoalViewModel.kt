package com.moath.aqua_pulse.presentation.screen.onboarding.dailyGoalScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moath.aqua_pulse.data.repository.DailyGoalRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DailyGoalViewModel @Inject constructor(
        private val dailyGoalRepositoryImpl: DailyGoalRepositoryImpl
): ViewModel() {

    // Get the current daily goal
    val dailyGoal: Flow<Float> = dailyGoalRepositoryImpl.getDailyGoal()

    // Function to save new daily goal
    fun saveDailyGoal(amount: Float){
        viewModelScope.launch {
            dailyGoalRepositoryImpl.saveDailyGoal(amount)
        }
    }

}