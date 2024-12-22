package com.moath.aqua_pulse.data.di

import com.moath.aqua_pulse.data.repository.DailyGoalRepositoryImpl
import com.moath.aqua_pulse.data.repository.WaterIntakeRepositoryImpl
import com.moath.aqua_pulse.data.repository.WeightRepositoryImpl
import com.moath.aqua_pulse.domain.repository.DailyGoalRepository
import com.moath.aqua_pulse.domain.repository.WaterIntakeRepository
import com.moath.aqua_pulse.domain.repository.WeightRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindWaterIntakeRepository(
        repositoryImpl: WaterIntakeRepositoryImpl
    ): WaterIntakeRepository

    @Binds
    @Singleton
    abstract fun bindWeightRepository(
        repositoryImpl: WeightRepositoryImpl
    ): WeightRepository

    @Binds
    @Singleton
    abstract fun bindDailyGoalRepository(
        dailyGoalRepositoryImpl: DailyGoalRepositoryImpl
    ): DailyGoalRepository
}
