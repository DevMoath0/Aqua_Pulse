package com.moath.aqua_pulse.data.di

import android.content.Context
import androidx.room.Room
import com.moath.aqua_pulse.data.local.WaterDropDatabase
import com.moath.aqua_pulse.data.local.dao.DailyGoalDao
import com.moath.aqua_pulse.data.local.dao.WaterIntakeDao
import com.moath.aqua_pulse.data.local.dao.WeightDao
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
    fun provideWaterIntakeDatabase(@ApplicationContext context: Context): WaterDropDatabase {
        return Room.databaseBuilder(
            context,
            WaterDropDatabase::class.java,
            "water_drop_database"
        ).fallbackToDestructiveMigration() // This will drop and recreate tables when version changes
        .build()
    }

    @Provides
    @Singleton
    fun provideWaterIntakeDao(database: WaterDropDatabase): WaterIntakeDao {
        return database.waterIntakeDAO()
    }

    @Provides
    @Singleton
    fun provideWeightDao(database: WaterDropDatabase): WeightDao {
        return database.weightDao()
    }

    @Provides
    @Singleton
    fun provideDailyGoalDao(database: WaterDropDatabase): DailyGoalDao {
        return database.dailyGoalDao()
    }
}
