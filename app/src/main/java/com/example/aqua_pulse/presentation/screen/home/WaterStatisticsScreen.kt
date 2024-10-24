
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.aqua_pulse.domain.model.DailyWaterIntake
import com.example.aqua_pulse.presentation.screen.home.WaterStatisticsViewModel

@Composable
fun WaterStatisticsScreen(
    viewModel: WaterStatisticsViewModel = hiltViewModel()
) {
    val weeklyData by viewModel.weeklyData.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Weekly Water Intake",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        WeeklyChart(weeklyData)
    }
}

@Composable
fun WeeklyChart(data: List<DailyWaterIntake>) {
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) {
        val barWidth = size.width / 7
        val maxAmount = data.maxOfOrNull { it.totalAmount } ?: 1

        data.forEachIndexed { index, daily ->
            val barHeight = (daily.totalAmount.toFloat() / maxAmount) * size.height

            drawRect(
                color = Color.Blue,
                topLeft = Offset(index * barWidth, size.height - barHeight),
                size = Size(barWidth * 0.8f, barHeight)
            )
        }
    }
}