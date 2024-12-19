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
        remoteConfig.fetch(0).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val fetchedBuildNumber = remoteConfig.getDouble("build_number")
                remoteConfig.activate().addOnCompleteListener {
                    Log.d("RemoteConfig", "Cleared cache and activated values, build number $fetchedBuildNumber")
                }
            } else {
                Log.e("RemoteConfig", "Fetch failed: ${task.exception?.message}")
            }
        }
    }

    override fun getBuildNumber(): Double = remoteConfig.getDouble(BUILD_NUMBER)

    companion object {
        private const val BUILD_NUMBER = "build_number"
    }
}