package com.moath.aqua_pulse.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moath.aqua_pulse.data.local.entity.DailyGoalEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyGoalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyGoal(dailyGoalEntity: DailyGoalEntity)

    @Query("SELECT * FROM daily_goal WHERE id = 1")
    fun getDailyGoal(): Flow<DailyGoalEntity>

    @Query("DELETE FROM daily_goal")
    suspend fun deleteAllDailyGoal()


}