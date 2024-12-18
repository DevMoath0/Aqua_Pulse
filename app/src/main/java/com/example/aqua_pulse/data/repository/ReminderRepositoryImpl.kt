import android.content.Context
import android.util.Log
import androidx.work.*
import com.example.aqua_pulse.core.utils.WaterReminderWorker
import com.example.aqua_pulse.data.local.preferences.ReminderPreferences
import com.example.aqua_pulse.data.local.preferences.UserPreferencesManager
import com.example.aqua_pulse.domain.repository.ReminderRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject


class ReminderRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val userPreferencesManager: UserPreferencesManager
) : ReminderRepository {

    override suspend fun scheduleReminders(preferences: ReminderPreferences) {
        val workManager = WorkManager.getInstance(context)

        // Cancel any existing work
        workManager.cancelUniqueWork(WaterReminderWorker.WORK_NAME)

        // Explicitly log the enabled state
        Log.wtf("moathme", "Scheduling reminders - Enabled: ${preferences.enabled}")

        // If reminders are not enabled, stop here
        if (!preferences.enabled) return

        // Prepare input data
        val inputData = Data.Builder()
            .putBoolean("enabled", preferences.enabled)
            .putString("startTime", preferences.startTime.toString())
            .putString("endTime", preferences.endTime.toString())
            .putInt("intervalMinutes", preferences.intervalMinutes)
            .putBoolean("isSettingsUpdate", true)
            .build()

        // Create a one-time work request
        val reminderRequest = OneTimeWorkRequestBuilder<WaterReminderWorker>()
            .setInputData(inputData)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                    .build()
            )
            .build()

        // Enqueue the initial work
        workManager.enqueueUniqueWork(
            WaterReminderWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            reminderRequest
        )
    }

    override suspend fun triggerReminderNotification() {
        val workManager = WorkManager.getInstance(context)

        // Get current preferences
        val preferences = userPreferencesManager.reminderPreferences.first()

        // Prepare input data
        val inputData = Data.Builder()
            .putBoolean("enabled", preferences.enabled)
            .putString("startTime", preferences.startTime.toString())
            .putString("endTime", preferences.endTime.toString())
            .putInt("intervalMinutes", preferences.intervalMinutes)
            .putBoolean("isManualTrigger", true)
            .build()

        // Create a one-time work request to trigger the worker
        val reminderRequest = OneTimeWorkRequestBuilder<WaterReminderWorker>()
            .setInputData(inputData)
            .build()

        // Enqueue the work request to trigger the reminder
        workManager.enqueue(reminderRequest)
    }
}