package com.example.aqua_pulse.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.aqua_pulse.data.local.dao.WaterIntakeDao
import com.example.aqua_pulse.data.local.entity.WaterIntakeEntity

@Database(
    entities = [WaterIntakeEntity::class],
    version = 2,
    exportSchema = false
)
abstract class WaterIntakeDatabase: RoomDatabase(){
    abstract fun waterIntakeDAO(): WaterIntakeDao
}