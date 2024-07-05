package dev.suai.greenkamchatka.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
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
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            GreenKamchatkaNavGraph(
                navController = navController,
                actions = navigationActions,
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}
