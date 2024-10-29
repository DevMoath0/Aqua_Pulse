package com.example.aqua_pulse.presentation.screen.home.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.aqua_pulse.R
import com.example.aqua_pulse.core.theme.gaugeBarGradientBrush
import com.example.aqua_pulse.core.theme.gauge_background

@Composable
fun WaterDropComp(
    modifier: Modifier,
    indicatorValue: Int,
    maxIndicatorValue: Int,
){

    val verticalOffset: Dp = (100).dp

    // Image and Gauge
    Box(
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.water_drop),
            contentDescription = "Water Drop",
            modifier = modifier
                .size(300.dp)
                .offset(y = verticalOffset + 50.dp)
        )
        Box(
            modifier = Modifier.offset(y = 160.dp)
        ){
            GaugeBarComponent(
                indicatorValue = indicatorValue,
                maxIndicatorValue = maxIndicatorValue,
                foregroundIndicatorColor = gaugeBarGradientBrush,
                backgroundIndicatorColor = gauge_background,
            )
        }
    }
}