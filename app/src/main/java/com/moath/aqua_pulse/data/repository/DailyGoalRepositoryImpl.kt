package com.moath.aqua_pulse.data.repository

import com.moath.aqua_pulse.data.local.dao.DailyGoalDao
import com.moath.aqua_pulse.data.local.entity.DailyGoalEntity
import com.moath.aqua_pulse.domain.repository.DailyGoalRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DailyGoalRepositoryImpl @Inject constructor(
    private val dailyGoalDao: DailyGoalDao
) : DailyGoalRepository {
    override suspend fun saveDailyGoal(amount: Float) {
        dailyGoalDao.insertDailyGoal(DailyGoalEntity(goalAmount = amount))
    }

    override fun getDailyGoal(): Flow<Float> {
        return dailyGoalDao.getDailyGoal().map { it.goalAmount }
    }

    override suspend fun deleteAllDailyGoals() {
        dailyGoalDao.deleteAllDailyGoal()
    }

}