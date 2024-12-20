package com.moath.aqua_pulse.domain.repository

interface RemoteConfigRepository {

    suspend fun fetchAndActivate()
    fun getBuildNumber(): Double
}