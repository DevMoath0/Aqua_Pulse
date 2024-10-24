package com.example.aqua_pulse.domain.usecase

import com.example.aqua_pulse.domain.repository.WaterIntakeRepository
import javax.inject.Inject

class AddWaterIntakeUseCase @Inject constructor(
    private val repository: WaterIntakeRepository
) {
    suspend operator fun invoke(amount: Int){
        repository.addWaterIntake(amount)
    }
}