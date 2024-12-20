package com.moath.aqua_pulse.data.di

import android.content.Context
import androidx.room.Room
import com.moath.aqua_pulse.data.local.WaterIntakeDatabase
import com.moath.aqua_pulse.data.local.dao.WaterIntakeDao
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