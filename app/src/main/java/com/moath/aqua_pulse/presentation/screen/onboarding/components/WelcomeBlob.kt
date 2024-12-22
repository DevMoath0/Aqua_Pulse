package com.moath.aqua_pulse.presentation.screen.onboarding.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WelcomeBlob(){
    Box(
        modifier = Modifier
            .blur(radius = 50.dp)  // Increased blur for better blending
    ) {
        Box(
            modifier = Modifier.fillMaxSize().blur(radius = 80.dp)
        ) {
            // White gradient blob
            Box(
                modifier = Modifier
                    .size(200.dp)
                    // Using absoluteOffset instead of offset to not take space
                    .absoluteOffset(x = 90.dp, y = 220.dp)
                    .background(
                        Color.White.copy(alpha = 0.6f),
                        shape = CircleShape
                    )
            )
        }
    }
}