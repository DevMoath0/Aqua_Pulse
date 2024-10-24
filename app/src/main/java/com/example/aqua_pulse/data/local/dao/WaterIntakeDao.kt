package com.example.aqua_pulse.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.aqua_pulse.data.local.entity.WaterIntakeEntity
import com.example.aqua_pulse.domain.model.DailyWaterIntake

// Data Access Object (DAO)
@Dao
interface WaterIntakeDao {

    @Insert
    suspend fun insertWaterIntake(waterIntake: WaterIntakeEntity)

    @Query("SELECT * FROM water_intake WHERE date = :date")
    suspend fun getWaterIntakeForDay(date: Long): List<WaterIntakeEntity>

    @Query("""
        SELECT date, SUM(amount) as totalAmount 
        FROM water_intake 
        WHERE date BETWEEN :startDate AND :endDate 
        GROUP BY date
    """)
    //TODO: List maybe exchanged with "Map<Long, Int>"
    suspend fun getWaterIntakeForDateRange(startDate: Long, endDate: Long): List<DailyWaterIntake>
}