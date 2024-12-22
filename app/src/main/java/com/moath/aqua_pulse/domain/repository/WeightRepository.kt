package com.moath.aqua_pulse.domain.repository

import kotlinx.coroutines.flow.Flow

interface WeightRepository {

    fun getLastWeight(): Flow<Float?>

    suspend fun saveWeight(weight: Float)
}