package com.example.aqua_pulse.domain.usecase

import com.example.aqua_pulse.domain.model.DailyWaterIntake
import com.example.aqua_pulse.domain.repository.WaterIntakeRepository
import javax.inject.Inject

class GetWeeklyWaterIntakeUseCase @Inject constructor(
    private val repository: WaterIntakeRepository
) {
    suspend operator fun invoke(): List<DailyWaterIntake>{
        return repository.getWeeklyWaterIntake()
    }
}