package com.moath.aqua_pulse.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_weight")
data class WeightEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "weight")
    val weight: Float,

    @ColumnInfo(name = "time_stamp")
    val timestamp: String,
)