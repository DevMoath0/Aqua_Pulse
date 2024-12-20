package com.moath.aqua_pulse.presentation.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.moath.aqua_pulse.R

@Composable
fun GenderScreen(onNext: (String) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "What is your gender?",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
            // Man Image
            Box(
                modifier = Modifier
                    .size(130.dp)
                    .background(
                        color = Color.White,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_man_model),
                    contentDescription = "Man Image",
                    contentScale = ContentScale.Crop, // Ensures the image fits within the circle
                    modifier = Modifier
                        .clip(CircleShape)
                        .offset(y = 16.dp),
                )
            }

            // Woman Image
            Box(
                modifier = Modifier
                    .size(130.dp)
                    .background(
                        color = Color.White,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_woman_model),
                    contentDescription = "Woman Image",
                    contentScale = ContentScale.Crop, // Ensures the image fits within the circle
                    modifier = Modifier
                        .clip(CircleShape)
                        .offset(y = 16.dp),
                )
            }
        }

        Button(
            onClick = { onNext("Gender Selected") },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Next")
        }
    }
}
