package com.example.aqua_pulse

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import presentation.components.GaugeBarComponent

@Composable
fun WaterDropView(
    modifier: Modifier = Modifier,
    verticalOffset: Dp = (100).dp
) {

    var value by remember {mutableIntStateOf(0)}

    Column () {

    }

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.water_drop),
            contentDescription = "Water Drop",
            modifier = Modifier
                .size(300.dp)
                .offset(y = verticalOffset + 50.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null  // This removes the ripple effect
                ) {
                    value++
                }
        )
        Box(
            modifier = Modifier.offset(y = 160.dp)
        ){
            GaugeBarComponent(
                indicatorValue = value,
                maxIndicatorValue = 5,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WaterDropViewPreview(
    ){
    WaterDropView(
        modifier = Modifier
    )
}