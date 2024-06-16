package dev.suai.greenkamchatka.ui.report.send

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun FileReportRoute(
    onReportSent:()->Unit,
    onBack:()->Unit
) {

    val viewModel: FileReportViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsState()

    FileReportScreen(
        state = state,
        viewUnsentReports = { /*TODO*/ },
        sendReport = { viewModel.fileReport() },
        onBackPressed = { /*TODO*/ },
        onTypeChange = { viewModel.onTypeChange(it) },
        onEmailChange = { viewModel.onEmailChange(it) },
        onPhoneChange = { viewModel.onPhoneChange(it) },
        onCommentChange = { viewModel.onCommentChange(it) },
        onImageChanged = { viewModel.onImageChanged(it) }
    )
}