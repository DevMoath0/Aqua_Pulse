package com.example.aqua_pulse.di

import android.content.Context
import androidx.room.Room
import com.example.aqua_pulse.data.local.WaterIntakeDatabase
import com.example.aqua_pulse.data.local.dao.WaterIntakeDao
import com.example.aqua_pulse.data.repository.WaterIntakeRepositoryImpl
import com.example.aqua_pulse.domain.repository.WaterIntakeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideWaterIntakeDatabase(@ApplicationContext context: Context): WaterIntakeDatabase{
        return Room.databaseBuilder(
            context,
            WaterIntakeDatabase::class.java,
            "water_intake"
        ).build()

    }

    @Provides
    @Singleton
    fun provideWaterIntakeDao(database: WaterIntakeDatabase): WaterIntakeDao {
        return database.waterIntakeDAO()
    }
}