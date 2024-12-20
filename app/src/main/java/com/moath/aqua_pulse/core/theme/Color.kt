package com.moath.aqua_pulse.core.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)



/*
<color name="dark_cyan">#2c87be</color>
<color name="background">#252525</color>
<color name="gauge_background">#EEEDED</color>

 */


val background = Color(0xFF252525)
val gauge_background = Color(0xFFEEEDED)


val water_drop_color1 = Color(0xFF316b93)
val water_drop_color2 = Color(0xFF3095d2)
val water_drop_color3 = Color(0xFF32b5f0)
val water_drop_color4 = Color(0xFF2cb8e9)
val water_drop_color5 = Color(0xFF2dccfb)
val water_drop_color6 = Color(0xFF31e6fe)

val gaugeBarGradientBrush = Brush.horizontalGradient(
    colors = listOf(
        water_drop_color6,
        water_drop_color5,
        water_drop_color4,
        water_drop_color3,
        water_drop_color2,
        water_drop_color1
    )
)