package com.example.aqua_pulse.presentation.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
 import androidx.compose.ui.unit.dp
import com.example.aqua_pulse.core.theme.background
import com.example.aqua_pulse.presentation.screen.home.composables.BlobsComp
import com.example.aqua_pulse.presentation.screen.home.composables.WaterDropComp
import java.util.Calendar

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {

    val dailyData by viewModel.dailyData.collectAsState()

    // Get today's data
    val today = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.timeInMillis

    val todayWaterAmount = dailyData.find { it.date == today }?.totalAmount ?: 0

    ///Daily Goal in ml
    val dailyGoal = 2000 // Default goal in ml

    Box(
        modifier = Modifier.fillMaxSize()
            .background(background),
    ) {
        // Background Layer
        Box(
            modifier = Modifier
                .fillMaxSize()
                .blur(radius = 50.dp)  // Increased blur for better blending
        ) {
            BlobsComp()
        }

        // WaterDropView centered in the screen
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            WaterDropComp(
                indicatorValue = todayWaterAmount,
                maxIndicatorValue = dailyGoal,
                modifier = Modifier.clickable (
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null  // This removes the ripple effect
                ){
                    if(todayWaterAmount in 0..< dailyGoal){
                        viewModel.addWaterIntake(250)
                    }
                }
            )
        }

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column (
            ){
                Spacer(modifier = Modifier.heightIn(300.dp))
                Text(
                    text = "${todayWaterAmount}ml",
                    style = MaterialTheme.typography.displayMedium,
                    color = Color.White
                )

                Text(
                    text = "of ${dailyGoal}ml daily goal",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )
            }
        }
    }
}