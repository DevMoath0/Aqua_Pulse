package com.example.aqua_pulse.domain.model

data class WaterIntake(
    val id: Int = 0,
    val date: Long,
    val amount: Int,
    val timestamp: Long = System.currentTimeMillis()
)