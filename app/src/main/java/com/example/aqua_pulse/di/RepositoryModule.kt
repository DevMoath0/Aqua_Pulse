package com.example.aqua_pulse.di

import com.example.aqua_pulse.data.repository.WaterIntakeRepositoryImpl
import com.example.aqua_pulse.domain.repository.WaterIntakeRepository
import dagger.Binds
import dagger.Module
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
    ) : WaterIntakeRepository
}