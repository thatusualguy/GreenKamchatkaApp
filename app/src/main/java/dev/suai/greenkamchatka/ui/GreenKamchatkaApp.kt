package dev.suai.greenkamchatka.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import dev.suai.greenkamchatka.navigation.GreenKamchatkaNavGraph
import dev.suai.greenkamchatka.navigation.GreenKamchatkaNavigationActions
import dev.suai.greenkamchatka.ui.theme.GreenKamchatkaTheme

@Composable
fun GreenKamchatkaApp() {
    GreenKamchatkaTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            GreenKamchatkaNavigationActions(navController)
        }

        GreenKamchatkaNavGraph(navController, navigationActions)
    }
}
