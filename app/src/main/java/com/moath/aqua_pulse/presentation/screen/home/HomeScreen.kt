package com.moath.aqua_pulse.presentation.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.moath.aqua_pulse.R
import com.moath.aqua_pulse.core.theme.background
import com.moath.aqua_pulse.core.utils.DateUtils
import com.moath.aqua_pulse.presentation.screen.Screen
import com.moath.aqua_pulse.presentation.screen.home.components.BlobsComp
import com.moath.aqua_pulse.presentation.screen.home.components.WaterDropComp

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel
) {

    val dailyData by viewModel.dailyData.collectAsState()
    val buildNumber by viewModel.buildNumber.collectAsState()

    val todayWaterAmount = dailyData.find { it.date == DateUtils.getCurrentDateOnly() }?.totalAmount ?: 0

    val dailyGoal by viewModel.dailyGoal.collectAsState(initial = 2000f)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background),
        contentAlignment = Alignment.Center
    ) {
        // Background Layer
        Box{
            Box(
                modifier = Modifier
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
                        if(todayWaterAmount in 0..<dailyGoal.toInt()){
                            viewModel.addWaterIntake(250)
                        }
                    }
                )
            }
            Image(
                painter = painterResource(id = R.drawable.water_drop),
                contentDescription = "Water Drop",
                modifier = Modifier
                    .size(300.dp)
                    .absoluteOffset(x = 55.dp, y = 155.dp)
                    .clip(shape = AbsoluteRoundedCornerShape(25.dp))
                    .clickable (
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null  // This removes the ripple effect
                    ){
                        viewModel.addWaterIntake(250)
                    },

            )
        }

        Column(
            modifier = Modifier.offset(y = 200.dp)
        ){
            Text(
                text = "${todayWaterAmount}ml",
                style = MaterialTheme.typography.displayMedium,
                color = Color.White
            )

            Text(
                text = "of ${dailyGoal.toInt()}ml daily goal",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )

            Button(
                onClick = {
                        navController.navigate(Screen.ReminderPreferencesScreen.route) {
                            popUpTo(Screen.HomeScreen.route) { inclusive = true }
                        }
                }
            ) {
                Text("Reminder")
            }

            Button(
                onClick = {
                    throw RuntimeException("Test Crash") // Force a crash
                }
            ) {
                Text("Crash")
            }

            Text(
                text = "Build number: v$buildNumber",
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
        }
    }


}

