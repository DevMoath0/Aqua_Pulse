package com.moath.aqua_pulse.data.repository

import android.util.Log
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.BuildConfig
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.moath.aqua_pulse.domain.repository.RemoteConfigRepository
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteConfigRepositoryImpl @Inject constructor() : RemoteConfigRepository {

    private val remoteConfig = Firebase.remoteConfig

    init {
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = if (BuildConfig.DEBUG) 0 else 3600
        }

        remoteConfig.setConfigSettingsAsync(configSettings)


        remoteConfig.setDefaultsAsync(
            mapOf(
                BUILD_NUMBER to 0.1
            )
        )


    }

    override suspend fun fetchAndActivate() {
        try {
            // Wait for fetch to complete
            remoteConfig.fetch(0).await()

            // Wait for activation to complete
            val isActivateSuccessful = remoteConfig.activate().await()
            if (isActivateSuccessful) {
                val fetchedBuildNumber = remoteConfig.getDouble(BUILD_NUMBER)
                Log.d("RemoteConfig", "Activated values successfully. Fetched build number: $fetchedBuildNumber")
            } else {
                Log.e("RemoteConfig", "Activation failed.")
            }
        } catch (e: Exception) {
            Log.e("RemoteConfig", "Error during fetch and activate: ${e.message}")
            Firebase.crashlytics.recordException(e)
        }
    }

    override fun getBuildNumber(): Double = remoteConfig.getDouble(BUILD_NUMBER)

    companion object {
        private const val BUILD_NUMBER = "build_number"
    }
}