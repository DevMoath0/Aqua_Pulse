package com.moath.aqua_pulse.domain.repository

import kotlinx.coroutines.flow.Flow

interface DailyGoalRepository {
    suspend fun saveDailyGoal(amount: Float)
    fun getDailyGoal(): Flow<Float>
    suspend fun deleteAllDailyGoals()
}