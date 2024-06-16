package dev.suai.greenkamchatka.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import dev.suai.greenkamchatka.R

val jostFamily = FontFamily(
    Font(R.font.jost_medium, FontWeight.Medium),
    Font(R.font.jost_regular, FontWeight.Normal),
)

private val h1 = TextStyle(
    fontFamily = jostFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 27.sp
)

private val h2 = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Medium,
    fontSize = 17.sp,
)

private val p = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 22.sp,
//        letterSpacing = 0.5.sp
)


val Typography = Typography(
    headlineLarge = h1,
    headlineSmall = h2,

    bodyLarge = p,
    labelLarge = p,
)