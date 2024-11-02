package com.example.aqua_pulse.domain.repository

import com.example.aqua_pulse.domain.model.DailyWaterIntake
import com.example.aqua_pulse.domain.model.WaterIntake

interface WaterIntakeRepository {

    suspend fun addWaterIntake(amount: Int)
    suspend fun getWaterIntakeForDay(date: String): List<WaterIntake>
    suspend fun getWeeklyWaterIntake(): List<DailyWaterIntake>
}