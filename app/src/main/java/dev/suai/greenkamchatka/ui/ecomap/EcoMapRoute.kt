package dev.suai.greenkamchatka.ui.ecomap

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun EcoMapRoute(
    onSendReport: () -> Unit,
    onBackPress: () -> Unit
) {
    val viewModel: EcoMapViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    EcoMapScreen(reports = uiState.reports, sendReport = onSendReport)
}