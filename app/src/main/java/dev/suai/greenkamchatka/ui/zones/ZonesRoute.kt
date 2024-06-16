package dev.suai.greenkamchatka.ui.zones

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun ZonesRoute(
    onBackPressed:()->Unit,
    onZonePressed:(Int)->Unit
) {
    val viewModel: ZonesViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    ZonesScreen(uiState.zones.data.orEmpty(), onBackPress = onBackPressed, onZoneSelection = onZonePressed)
}
