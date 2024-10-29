package com.example.aqua_pulse.data.repository

import com.example.aqua_pulse.data.local.dao.WaterIntakeDao
import com.example.aqua_pulse.data.local.entity.WaterIntakeEntity
import com.example.aqua_pulse.domain.model.DailyWaterIntake
import com.example.aqua_pulse.domain.model.WaterIntake
import com.example.aqua_pulse.domain.repository.WaterIntakeRepository
import java.util.Calendar
import javax.inject.Inject

class WaterIntakeRepositoryImpl @Inject constructor(
    private val waterIntakeDao: WaterIntakeDao
) : WaterIntakeRepository{

    // Getting the create_date for the water and the inserting it into the DB.
    override suspend fun addWaterIntake(amount: Int) {
        val today = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis

        waterIntakeDao.insertWaterIntake(
            WaterIntakeEntity(
                date = today,
                amount = amount
            )
        )
    }

    override suspend fun getWaterIntakeForDay(date: Long): List<WaterIntake> {
        //Todo: Didn't Understand the ".map" concept.
        return waterIntakeDao.getWaterIntakeForDay(date).map{
            it.toDomainModel()
        }
    }

    override suspend fun getWeeklyWaterIntake(): List<DailyWaterIntake> {
        val calendar = Calendar.getInstance()

        val endDate = calendar.apply{
            set(Calendar.HOUR_OF_DAY, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
        }.timeInMillis

        val startDate = calendar.apply {
            add(Calendar.DAY_OF_YEAR, -6)
        }.timeInMillis

        //Todo: there might be an error here, so change it if occurs to Map<Long, Int>
        return waterIntakeDao.getWaterIntakeForDateRange(startDate, endDate)
            .map { (date, amount) ->
                DailyWaterIntake(date = date, totalAmount = amount)
            }
    }

}