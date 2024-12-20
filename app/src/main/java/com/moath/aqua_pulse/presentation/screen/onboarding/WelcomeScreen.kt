package com.moath.aqua_pulse.presentation.screen.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.moath.aqua_pulse.R
import com.moath.aqua_pulse.core.theme.background
import com.moath.aqua_pulse.core.theme.buttonBarGradientBrush
import com.moath.aqua_pulse.core.theme.water_drop_color5
import com.moath.aqua_pulse.presentation.screen.onboarding.components.GradientButton
import com.moath.aqua_pulse.presentation.screen.onboarding.components.WelcomeBlob

@Composable
fun WelcomeScreen(onNext: (String) -> Unit) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(background),
        contentAlignment = Alignment.Center
    ) {
        // Background Layer
        Box {
            WelcomeBlob()
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(
                    modifier = Modifier
                        .weight(0.1f)
                )

                Text(
                    text = stringResource(id = R.string.welcome_title),
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )

                Spacer(
                    modifier = Modifier
                        .weight(0.05f)
                )

                Text(
                    text = stringResource(id = R.string.welcome_subtitle),
                    style = MaterialTheme.typography.labelSmall,
                    textAlign = TextAlign.Center,
                    color = water_drop_color5
                )

                Spacer(
                    modifier = Modifier
                        .weight(0.3f)
                )

                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(
                            Alignment.CenterHorizontally
                        ),
                    painter = painterResource(
                        id = R.drawable.ic_water_drop_click
                    ),
                    contentDescription = "Background",
                    contentScale = ContentScale.Crop
                )

                Spacer(
                    modifier = Modifier
                        .weight(0.2f)
                )

                Text(
                    text = stringResource(id = R.string.welcome_describtion1),
                    style = MaterialTheme.typography.labelSmall,
                    color = water_drop_color5
                )

                Spacer(
                    modifier = Modifier
                        .weight(1f)
                )

                GradientButton(
                    text = stringResource(id = R.string.btn_start),
                    gradient = buttonBarGradientBrush,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    textColor = Color.White,
                    onClick = { onNext("Welcome") }
                )
            }
        }

    }

    }

