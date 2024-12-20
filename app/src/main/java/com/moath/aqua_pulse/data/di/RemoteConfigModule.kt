package com.moath.aqua_pulse.data.di

import com.moath.aqua_pulse.data.repository.RemoteConfigRepositoryImpl
import com.moath.aqua_pulse.domain.repository.RemoteConfigRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteConfigModule {

    @Provides
    @Singleton
    fun provideRemoteConfigRepository(): RemoteConfigRepository {
        return RemoteConfigRepositoryImpl()
    }
}