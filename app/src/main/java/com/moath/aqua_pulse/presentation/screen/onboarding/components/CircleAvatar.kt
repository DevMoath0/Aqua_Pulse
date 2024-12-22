package com.moath.aqua_pulse.presentation.screen.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.moath.aqua_pulse.core.theme.water_drop_color2
import com.moath.aqua_pulse.core.theme.water_drop_color3

@Composable
fun CircleAvatar(
    selectedGender: String?,
    onGenderSelected: (String) -> Unit,
    wantedGender: String,
    painter: Painter,
    imageDescription: String,
) {
    Box(
        modifier = Modifier
            .size(130.dp)
            .clip(CircleShape)
            .background(
                color = if (selectedGender == wantedGender) Color.LightGray else water_drop_color3,
                shape = CircleShape
            )
            .border(
                width = if (selectedGender == wantedGender) 4.dp else 0.dp,
                color = water_drop_color2, // Border color for selected gender
                shape = CircleShape
            )
            .clickable(
                onClick = {
                    onGenderSelected(wantedGender) // Pass the wantedGender to the lambda
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = imageDescription,
            contentScale = ContentScale.Crop, // Ensures the image fits within the circle
            modifier = Modifier
                .clip(CircleShape)
                .offset(y = 16.dp)
        )
    }
}
