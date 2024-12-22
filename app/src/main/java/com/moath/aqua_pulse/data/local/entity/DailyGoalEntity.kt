package com.moath.aqua_pulse.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_goal")
data class DailyGoalEntity(
    @PrimaryKey
    val id: Int = 1,

    @ColumnInfo(name = "goal_amount")
    val goalAmount: Float
)