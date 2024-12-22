package com.moath.aqua_pulse.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moath.aqua_pulse.data.local.entity.WeightEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface WeightDao {

    //Insert The Entity
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeight(weightEntity: WeightEntity)


    @Query("SELECT * FROM user_weight ORDER BY time_stamp DESC LIMIT 1")
    fun getLastWeight(): Flow<WeightEntity?>

    @Query("SELECT weight FROM user_weight ORDER BY time_stamp DESC LIMIT 1")
    fun getWeight(): Flow<Float?>

}