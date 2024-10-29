package com.example.aqua_pulse.presentation.screen.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.aqua_pulse.core.theme.water_drop_color1
import com.example.aqua_pulse.core.theme.water_drop_color2

@Composable
fun BlobsComp(){
    // White gradient blob
    Box(
        modifier = Modifier
            .size(200.dp)
            // Using absoluteOffset instead of offset to not take space
            .absoluteOffset(x = 150.dp, y = 270.dp)
            .background(
                Color.White.copy(alpha = 0.6f),
                shape = CircleShape
            )
    )

    // Cyan Top gradient blob
    Box(
        modifier = Modifier
            .size(200.dp)
            .absoluteOffset(x = 105.dp, y = 150.dp)
            .background(
                water_drop_color1.copy(alpha = 0.6f),
                shape = CircleShape
            )
    )

    // Cyan gradient blob
    Box(
        modifier = Modifier
            .size(200.dp)
            .absoluteOffset(x = 60.dp, y = 270.dp)
            .background(
                water_drop_color2.copy(alpha = 0.6f),
                shape = CircleShape
            )
    )
}