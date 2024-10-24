package com.example.aqua_pulse.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.aqua_pulse.domain.model.WaterIntake

// Data Entity
@Entity(tableName = "water_intake")
data class WaterIntakeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "date")
    val date: Long,  // Store as timestamp

    @ColumnInfo(name = "amount")
    val amount: Int, // Amount in ml

    @ColumnInfo(name = "time_stamp")
    val timestamp: Long = System.currentTimeMillis()
){
    fun toDomainModel() = WaterIntake(
        id = id,
        date = date,
        amount = amount,
        timestamp = timestamp
    )
}