package com.example.aqua_pulse.data.repository

import com.example.aqua_pulse.core.utils.DateUtils
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
        val currentDate = DateUtils.getCurrentDateOnly()
        val currentTimestamp = DateUtils.getCurrentDateTime()

        waterIntakeDao.insertWaterIntake(
            WaterIntakeEntity(
                date = currentDate,
                timestamp = currentTimestamp,
                amount = amount
            )
        )
    }

    override suspend fun getWaterIntakeForDay(date: String): List<WaterIntake> {
        //Todo: Didn't Understand the ".map" concept.
        return waterIntakeDao.getWaterIntakeForDay(date).map{
            it.toDomainModel()
        }
    }

    override suspend fun getWeeklyWaterIntake(): List<DailyWaterIntake> {
        val calendar = Calendar.getInstance()
        val endDate = DateUtils.getCurrentDateOnly()

        calendar.add(Calendar.DAY_OF_YEAR, -6)
        val startDate = DateUtils.formatDateOnly(calendar.timeInMillis)

        //Todo: there might be an error here, so change it if occurs to Map<Long, Int>
        return waterIntakeDao.getWaterIntakeForDateRange(startDate, endDate)
            .map { (date, amount) ->
                DailyWaterIntake(date = date, totalAmount = amount)
            }
    }

}