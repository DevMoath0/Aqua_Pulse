package com.example.aqua_pulse.di

import ReminderRepositoryImpl
import android.content.Context
import com.example.aqua_pulse.data.local.preferences.UserPreferencesManager
import com.example.aqua_pulse.domain.repository.ReminderRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ReminderModule {
    @Provides
    @Singleton
    fun provideReminderRepository(
        @ApplicationContext context: Context,
        userPreferencesManager: UserPreferencesManager
    ): ReminderRepository = ReminderRepositoryImpl(context, userPreferencesManager)
}