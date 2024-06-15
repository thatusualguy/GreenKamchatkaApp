package dev.suai.greenkamchatka.ui.zones

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import dev.suai.greenkamchatka.ui.visitors.VisitorsViewModel


@Composable
fun ZonesRoute(
    onBackPressed:()->Unit,
    onZonePressed:(Int)->Unit
) {
    val viewModel: ZonesViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    ZonesScreen(uiState.zones.data.orEmpty(), onBackPress = onBackPressed, onZoneSelection = onZonePressed)
}
