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

        // Cancel existing work
        workManager.cancelUniqueWork(WaterReminderWorker.WORK_NAME)

        if (!preferences.enabled) {
            Log.wtf("moathme","Reminders disabled. No work scheduled.")
            return
        }

        // Prepare input data
        val inputData = Data.Builder()
            .putBoolean("enabled", preferences.enabled)
            .putString("startTime", preferences.startTime.toString())
            .putString("endTime", preferences.endTime.toString())
            .putInt("intervalMinutes", preferences.intervalMinutes)
            .putBoolean("isSettingsUpdate", true) // Mark as a settings update
            .build()

        // Schedule the next work
        val workRequest = OneTimeWorkRequestBuilder<WaterReminderWorker>()
            .setInputData(inputData)
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                    .build()
            )
            .build()

        workManager.enqueueUniqueWork(
            WaterReminderWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            workRequest
        )

        Log.wtf("moathme","Scheduled reminders with preferences: $preferences")
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