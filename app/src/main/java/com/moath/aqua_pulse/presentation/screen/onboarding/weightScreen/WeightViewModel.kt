package com.moath.aqua_pulse.presentation.screen.onboarding.weightScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moath.aqua_pulse.data.local.entity.WeightEntity
import com.moath.aqua_pulse.data.repository.WeightRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeightViewModel @Inject constructor(
        private val weightRepositoryImpl: WeightRepositoryImpl
    ): ViewModel(){

        val latestWeight: Flow<Float?> = weightRepositoryImpl.getLastWeight()

        fun saveWeight(weight: Float){
            viewModelScope.launch {
                weightRepositoryImpl.saveWeight(weight)
            }
        }
}