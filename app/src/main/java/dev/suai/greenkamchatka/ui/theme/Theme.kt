package dev.suai.greenkamchatka.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Green,
    onPrimary = White,

    secondary = Salad,
    onSecondary = Black,

//    secondary = Green,
//    onSecondary = White,
//
//    primary = Salad,
//    onPrimary = Black,

    tertiary = Gold,
    onTertiary = Black,

    surface = Salad,
    onSurface = Black,

    background = White,


    surfaceVariant = Salad,
    onSurfaceVariant = Black

)

@Composable
fun GreenKamchatkaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content =
        {
            Surface(
                tonalElevation = 0.dp,
                color = colorScheme.background,
                content = content
            )
        }
    )
}