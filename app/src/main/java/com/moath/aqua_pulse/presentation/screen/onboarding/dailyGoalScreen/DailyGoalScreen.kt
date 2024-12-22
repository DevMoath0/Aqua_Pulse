package com.moath.aqua_pulse.presentation.screen.onboarding.dailyGoalScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.moath.aqua_pulse.R
import com.moath.aqua_pulse.core.theme.buttonBarGradientBrush
import com.moath.aqua_pulse.presentation.screen.onboarding.components.GradientButton
import com.moath.aqua_pulse.presentation.screen.onboarding.genderScreen.GenderViewModel
import com.moath.aqua_pulse.presentation.screen.onboarding.weightScreen.WeightViewModel

@Composable
fun DailyGoalScreen(
    onComplete: () -> Unit,
    genderViewModel: GenderViewModel,
    weightViewModel: WeightViewModel,
    dailyGoalViewModel: DailyGoalViewModel
) {
    val gender by genderViewModel.gender.collectAsState(initial = "male")
    val weight by weightViewModel.latestWeight.collectAsState(initial = 0f) // Changed initial to 0f

    val multiplier = if (gender.equals("Man", ignoreCase = true)) 0.04f else 0.035f
    val dailyWaterGoal = (weight?.times(multiplier))?.times(1000)?.toInt() // Convert to milliliters

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Debug - Weight: $weight",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            color = Color.White
        )
        Text(
            text = "Debug - Gender: $gender",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            color = Color.White
        )
        Text(
            text = "Debug - Multiplier: $multiplier",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            color = Color.White
        )
        Text(
            text = "Your daily water intake goal is: $dailyWaterGoal milliliters",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center,
            color = Color.White
        )


        Spacer(modifier = Modifier.weight(1f)) // Push content up and button to the bottom

        GradientButton(
            text = stringResource(id = R.string.btn_start),
            gradient = buttonBarGradientBrush,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            textColor = Color.White,
            onClick = {
                if (dailyWaterGoal != null) {
                    dailyGoalViewModel.saveDailyGoal(dailyWaterGoal.toFloat())
                    onComplete()
                }
            }
        )
    }
}
