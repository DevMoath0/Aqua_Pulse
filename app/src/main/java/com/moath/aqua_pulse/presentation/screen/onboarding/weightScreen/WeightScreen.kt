package com.moath.aqua_pulse.presentation.screen.onboarding.weightScreen

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lt.compose_views.value_selector.*
import com.moath.aqua_pulse.R
import com.moath.aqua_pulse.core.theme.buttonBarGradientBrush
import com.moath.aqua_pulse.presentation.screen.onboarding.components.GradientButton

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WeightScreen(
    onNext: () -> Unit,
    viewModel: WeightViewModel
) {
    // Get the latest weight from ViewModel
    val latestWeight by viewModel.latestWeight.collectAsState(initial = 50f) // Default to 50 kg

    // State to track the selected weight
    val weightState = remember { ValueSelectState() }

    // Create a list of weight options (e.g., 30 to 150 kg)
    val weightOptions = remember { (8..150).map { it.toString() }.toMutableStateList() }

    // Calculate the default index safely, so it would be 40
    val defaultIndex = weightOptions.indexOf(latestWeight.toString()).takeIf { it >= 0 } ?: 32

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(0.3f))

        Text(
            text = "Select your weight",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = Color.White
        )

        Spacer(modifier = Modifier.weight(0.3f))

        // ValueSelector for selecting weight
        ValueSelector(
            values = weightOptions,
            state = weightState,
            defaultSelectIndex = defaultIndex, // Use the calculated default index
            isLoop = false, // Weight selection doesn't loop
            cacheSize = 2, // Number of items above and below the selected one
            selectedTextSize = 24.sp,
            selectedTextColor = Color.White,
        )

        Spacer(modifier = Modifier.weight(1f)) // Push content up and button to the bottom

        GradientButton(
            text = stringResource(id = R.string.btn_start),
            gradient = buttonBarGradientBrush,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            textColor = Color.White,
            onClick = { // Save the selected weight and proceed
                val selectedIndex = weightState.getSelectIndex()
                if (selectedIndex in weightOptions.indices) { // Validate index
                    val selectedWeight = weightOptions[selectedIndex].toFloat()
                    viewModel.saveWeight(selectedWeight)
                    onNext()
                }
            }
        )
    }
}

