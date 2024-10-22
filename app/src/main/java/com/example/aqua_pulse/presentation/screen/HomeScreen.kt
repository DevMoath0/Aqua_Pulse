package com.example.aqua_pulse.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.aqua_pulse.core.theme.background
import com.example.aqua_pulse.presentation.composables.BlobsComp
import com.example.aqua_pulse.presentation.composables.WaterDropComp

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize().background(background),
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
            WaterDropComp()
        }

    }
}