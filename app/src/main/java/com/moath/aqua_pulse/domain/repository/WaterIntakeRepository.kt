package com.moath.aqua_pulse.domain.repository

import com.moath.aqua_pulse.domain.model.DailyWaterIntake
import com.moath.aqua_pulse.domain.model.WaterIntake

interface WaterIntakeRepository {

    suspend fun addWaterIntake(amount: Int)
    suspend fun getWaterIntakeForDay(date: String): List<WaterIntake>
    suspend fun getWeeklyWaterIntake(): List<DailyWaterIntake>
}