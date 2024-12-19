package com.moath.aqua_pulse.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moath.aqua_pulse.data.local.entity.WaterIntakeEntity
import com.moath.aqua_pulse.domain.model.DailyWaterIntake

// Data Access Object (DAO)
@Dao
interface WaterIntakeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWaterIntake(waterIntake: WaterIntakeEntity)

    @Query("SELECT * FROM water_intake WHERE date = :date")
    suspend fun getWaterIntakeForDay(date: String): List<WaterIntakeEntity>

    @Query("""
        SELECT date, SUM(amount) as totalAmount 
        FROM water_intake 
        WHERE date BETWEEN :startDate AND :endDate 
        GROUP BY date
    """)

    //TODO: List maybe exchanged with "Map<Long, Int>"
    suspend fun getWaterIntakeForDateRange(startDate: String, endDate: String): List<DailyWaterIntake>
}