package com.moath.aqua_pulse.presentation.screen.home.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.moath.aqua_pulse.domain.model.DailyWaterIntake
import com.moath.aqua_pulse.presentation.screen.home.HomeViewModel

// ############# NOT USABLE SCREEN ###############

// Statistics Screen
@Composable
fun WaterStatisticsScreen(viewModel: HomeViewModel) {
    val weeklyData by viewModel.dailyData.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Weekly Water Intake",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        WeeklyChart(weeklyData)
    }
}

@Composable
fun WeeklyChart(data: List<DailyWaterIntake>) {
    // Using Canvas to draw bars
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