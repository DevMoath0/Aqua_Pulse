package com.moath.aqua_pulse.presentation.screen.onboarding.genderScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.moath.aqua_pulse.R
import com.moath.aqua_pulse.core.theme.buttonBarGradientBrush
import com.moath.aqua_pulse.presentation.screen.onboarding.components.CircleAvatar
import com.moath.aqua_pulse.presentation.screen.onboarding.components.GradientButton

@Composable
fun GenderScreen(
    onNext: () -> Unit,
    viewModel: GenderViewModel
) {
    var selectedGender by remember { mutableStateOf<String?>(null) } // Track selected gender

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(0.5f)) // Push content down from the top

        Text(
            text = stringResource(id = R.string.gender_question),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = Color.White
        )

        Spacer(modifier = Modifier.weight(0.3f))


        Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {

            // Woman Image
            CircleAvatar(
                wantedGender = "Woman", // Pass "Woman" as the wantedGender
                selectedGender = selectedGender,
                painter = painterResource(id = R.drawable.ic_woman_model),
                onGenderSelected = { gender ->
                    selectedGender = gender // Update state
                    viewModel.saveGender(gender) // Save gender in ViewModel
                },
                imageDescription = "Woman Image"
            )

            // Man Image
            CircleAvatar(
                wantedGender = "Man", // Pass "Man" as the wantedGender
                selectedGender = selectedGender,
                painter = painterResource(id = R.drawable.ic_man_model),
                onGenderSelected = { gender ->
                    selectedGender = gender // Update state
                    viewModel.saveGender(gender) // Save gender in ViewModel
                },
                imageDescription = "Man Image"
            )

        }

        Spacer(modifier = Modifier.weight(1f))

        GradientButton(
            text = stringResource(id = R.string.btn_start),
            gradient = buttonBarGradientBrush,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            textColor = Color.White,
            onClick = { onNext() }
        )
    }
}
