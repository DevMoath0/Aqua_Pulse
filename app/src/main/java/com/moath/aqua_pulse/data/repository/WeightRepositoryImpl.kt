package com.moath.aqua_pulse.data.repository

import com.moath.aqua_pulse.core.utils.DateUtils
import com.moath.aqua_pulse.data.local.dao.WeightDao
import com.moath.aqua_pulse.data.local.entity.WeightEntity
import com.moath.aqua_pulse.domain.repository.WeightRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeightRepositoryImpl @Inject constructor(
    private val weightDao: WeightDao
): WeightRepository {

    override fun getLastWeight(): Flow<Float?>{
        return weightDao.getLastWeight().map { it?.weight }
    }

    override suspend fun saveWeight(weight: Float) {
        val timeStamp = DateUtils.getCurrentDateTime()
        weightDao.insertWeight(WeightEntity(weight = weight, timestamp = timeStamp))
    }

}