package com.moath.aqua_pulse.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moath.aqua_pulse.data.local.dao.DailyGoalDao
import com.moath.aqua_pulse.data.local.dao.WaterIntakeDao
import com.moath.aqua_pulse.data.local.dao.WeightDao
import com.moath.aqua_pulse.data.local.entity.DailyGoalEntity
import com.moath.aqua_pulse.data.local.entity.WaterIntakeEntity
import com.moath.aqua_pulse.data.local.entity.WeightEntity

@Database(
    entities = [WaterIntakeEntity::class, WeightEntity::class, DailyGoalEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class WaterDropDatabase: RoomDatabase(){
    abstract fun waterIntakeDAO(): WaterIntakeDao
    abstract fun weightDao(): WeightDao
    abstract fun dailyGoalDao(): DailyGoalDao

}
